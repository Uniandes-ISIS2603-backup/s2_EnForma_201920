/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

import co.edu.uniandes.csw.enforma.dtos.DietaTipoDTO;
import co.edu.uniandes.csw.enforma.dtos.DietaTipoDetailDTO;
import co.edu.uniandes.csw.enforma.ejb.DietaTipoLogic;
import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "dietas".
 * @author Julio Morales
 */
@Path("dietas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class DietaTipoResource {
    
    private static final Logger LOGGER = Logger.getLogger(DietaTipoResource.class.getName());

    @Inject
    private DietaTipoLogic dietaTipoLogic;
    
    
    /**
     * Crea una nueva dietaTipo con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param dietaTipo {@link DietaTipoDTO} - La dietaTipo que se desea guardar.
     * @return JSON {@link DietaTipoDTO} - La dietaTipo guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe laDietaTipo.
     */
    @POST
    public DietaTipoDTO createDietaTipo(DietaTipoDTO dietaTipo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "DietaTipoResource createDietaTipo: input: {0}", dietaTipo);
        DietaTipoDTO nuevoDietaTipoDTO = new DietaTipoDTO(dietaTipoLogic.createDietaTipo(dietaTipo.toEntity()));
        LOGGER.log(Level.INFO, "DietaTipoResource createDietaTipo: output: {0}", nuevoDietaTipoDTO);
        return nuevoDietaTipoDTO;
    }
    
    
    
    
    
    /**
     * Busca y devuelve todos las dietasTipo que existen en la aplicacion.
     *
     * @return JSONArray {@link DietaTipoDetailDTO} - Las DietasTipo encontradas en la
     * aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<DietaTipoDetailDTO> getDietasTipo() {
        LOGGER.info("DietaTipoResource getDietasTipo: input: void");
        List<DietaTipoDetailDTO> listaDietasTipo = listEntity2DetailDTO(dietaTipoLogic.getDietasTipo());
        LOGGER.log(Level.INFO, "DietaTipoResource getDietasTipo: output: {0}", listaDietasTipo);
        return listaDietasTipo;
    }
    
    
    
    
    /**
     * Busca la dietaTipo con el id asociado recibido en la URL y lo devuelve.
     *
     * @param dietasId Identificador de la dietaTipo que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link DietaTipoDetailDTO} - La DietaTipo buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la DietaTipo.
     */
    @GET
    @Path("{dietasId: \\d+}")
    public DietaTipoDetailDTO getDietaTipo(@PathParam("dietasId") Long dietasId) {
        LOGGER.log(Level.INFO, "DietaTipoResource getDietaTipo: input: {0}", dietasId);
        DietaTipoEntity dietaTipoEntity = dietaTipoLogic.getDietaTipo(dietasId);
        if (dietaTipoEntity == null) {
            throw new WebApplicationException("El recurso /dietas/" + dietasId + " no existe.", 404);
        }
        DietaTipoDetailDTO dietaTipoDetailDTO = new DietaTipoDetailDTO(dietaTipoEntity);
        LOGGER.log(Level.INFO, "DietaTipoResource getDietaTipo: output: {0}", dietaTipoDetailDTO);
        return dietaTipoDetailDTO;
    }
    
    
    
    

    /**
     * Borra la dietaTipo con el id asociado recibido en la URL.
     *
     * @param dietasId Identificador de la dietaTipo que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * cuando 
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la dietaTipo.
     */
    @DELETE
    @Path("{dietasId: \\d+}")
    public void deleteDietaTipo(@PathParam("dietasId") Long dietasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "DietaTipoResource deleteDietaTipo: input: {0}", dietasId);
        DietaTipoEntity entity = dietaTipoLogic.getDietaTipo(dietasId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /dietas/" + dietasId + " no existe.", 404);
        }
        dietaTipoLogic.deleteDietaTipo(dietasId);
        LOGGER.info("DietaTipoResource deleteDietaTipo: output: void");
    }
    
    
    
    
    
    
    
    
    
    
    private List<DietaTipoDetailDTO> listEntity2DetailDTO(List<DietaTipoEntity> entityList) {
        List<DietaTipoDetailDTO> list = new ArrayList<>();
        for (DietaTipoEntity entity : entityList) {
            list.add(new DietaTipoDetailDTO(entity));
        }
        return list;
    }
}
