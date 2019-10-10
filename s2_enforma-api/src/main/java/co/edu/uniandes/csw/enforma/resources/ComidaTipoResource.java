/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

import co.edu.uniandes.csw.enforma.dtos.ComidaTipoDTO;
import co.edu.uniandes.csw.enforma.ejb.AdministradorLogic;
import co.edu.uniandes.csw.enforma.ejb.ComidaTipoLogic;
import co.edu.uniandes.csw.enforma.ejb.DietaTipoLogic;
import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.PathParam;

/**
 *
 * @author Jose Manuel Flórez
 */

@Path("ComidasTipo")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped


public class ComidaTipoResource 
{
    
    private static final Logger LOGGER = Logger.getLogger(ComidaTipoResource.class.getName());
    
    @Inject
    private ComidaTipoLogic comidaTipoLogic; 
    
    
    @Inject
    private DietaTipoLogic dietaTipoLogic;
    
    @Inject
    private AdministradorLogic administradorLogic;
    
    /**
     * Crea un nueva nueva comidaTipo con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param comidaTipo {@link ComidaTipoDTO} - La comidaTipo que se desea guardar.
     * @return JSON {@link ComidaTipoDTO} -La ComidaTipo guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la comidaTipo o las calorias son
     * inválidas, etc.
     */
    @POST
    public ComidaTipoDTO createComidaTipo(ComidaTipoDTO comidaTipo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ComidaTipoResource createComidatipo: input: {0}", comidaTipo);
        ComidaTipoDTO nuevaComidaTipoDTO = new ComidaTipoDTO(comidaTipoLogic.createComidaTipo(comidaTipo.toEntity()));
        LOGGER.log(Level.INFO, "ComidaTipoResource createComidaTipo: output: {0}", nuevaComidaTipoDTO);
        return nuevaComidaTipoDTO;
    }

     /**
     * Busca y devuelve todas las comida Tipo que existen en la aplicacion.
     *
     * @return JSONArray {@link ComidaTipoDTO} - Las Comidatipo encontradas en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ComidaTipoDTO> getComidasTipo() {
        LOGGER.info("ComidatipoResource getComidasTipo: input: void");
        List<ComidaTipoDTO> listaComidasTipo = listEntity2DetailDTO(comidaTipoLogic.getComidasTipo());
        LOGGER.log(Level.INFO, "ComidaTipoResource getComidasTipo: output: {0}", listaComidasTipo);
        return listaComidasTipo;
    }
    
     /**
     * Busca el libro con el id asociado recibido en la URL y lo devuelve.
     *
     * @param comidaTipoId Identificador del libro que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    
    @GET
    @Path("{comidaTipoId: \\d+}")
    public ComidaTipoDTO getComidaTipo(@PathParam("comidaTipoId") Long comidaTipoId) {
        LOGGER.log(Level.INFO, "BookResource getBook: input: {0}", comidaTipoId);
        ComidaTipoEntity comidaTipoEntity = comidaTipoLogic.getComidaTipo(comidaTipoId);
        if (comidaTipoEntity == null) {
            throw new WebApplicationException("El recurso /comidaTipo/" + comidaTipoId + " no existe.", 404);
        }
        ComidaTipoDTO comidaTipoDTO = new ComidaTipoDTO(comidaTipoEntity);
        LOGGER.log(Level.INFO, "ComidaTipoResource getComidatipo: output: {0}", comidaTipoDTO);
        return comidaTipoDTO;
    }
    
    
   
    /**
     * Borra el libro con el id asociado recibido en la URL.
     *
     * @param booksId Identificador del libro que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * cuando el libro tiene autores asociados.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @DELETE
    @Path("{comidaTipoId: \\d+}")
    public void deleteComidaTipo(@PathParam("comidaTipoId") Long comidaTipoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BookResource deleteComidaTipo: input: {0}", comidaTipoId);
        ComidaTipoEntity entity = comidaTipoLogic.getComidaTipo(comidaTipoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + comidaTipoId + " no existe.", 404);
        }
      //  bookEditorialLogic.removeEditorial(booksId);
        comidaTipoLogic.deleteComidaTipo(comidaTipoId);
        LOGGER.info("ComidaTipoResource deleteComidaTipo: output: void");
    }
    
    
    
     /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BookEntity a una lista de
     * objetos BookDetailDTO (json)
     *
     * @param entityList corresponde a la lista de libros de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de libros en forma DTO (json)
     */
    private List<ComidaTipoDTO> listEntity2DetailDTO(List<ComidaTipoEntity> entityList) {
        List<ComidaTipoDTO> list = new ArrayList<>();
        for (ComidaTipoEntity entity : entityList) {
            list.add(new ComidaTipoDTO(entity));
        }
        return list;
    }
}
