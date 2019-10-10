/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;
import co.edu.uniandes.csw.enforma.dtos.DietaTipoDTO;
import co.edu.uniandes.csw.enforma.dtos.DietaTipoDetailDTO;
import co.edu.uniandes.csw.enforma.ejb.AdministradorDietaTipoLogic;
import co.edu.uniandes.csw.enforma.ejb.DietaTipoLogic;
import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Estudiante
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdministradorDietaTipoResource {
    
    private static final Logger LOGGER = Logger.getLogger(AdministradorDietaTipoResource.class.getName());

    @Inject
    private AdministradorDietaTipoLogic administradorDietaTiposLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private DietaTipoLogic dietaTipoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un libro dentro de una administrador con la informacion que recibe el
     * la URL. Se devuelve el libro que se guarda en la administrador.
     *
     * @param administradorsId Identificador de la administrador que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param dietaTiposId Identificador del libro que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link DietaTipoDTO} - El libro guardado en la administrador.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{dietaTiposId: \\d+}")
    public DietaTipoDTO addDietaTipo(@PathParam("administradorsId") Long administradorsId, @PathParam("dietaTiposId") Long dietaTiposId) {
        LOGGER.log(Level.INFO, "AdministradorDietaTiposResource addDietaTipo: input: administradorsID: {0} , dietaTiposId: {1}", new Object[]{administradorsId, dietaTiposId});
        if (dietaTipoLogic.getDietaTipo(dietaTiposId) == null) {
            throw new WebApplicationException("El recurso /dietaTipos/" + dietaTiposId + " no existe.", 404);
        }
        DietaTipoDTO dietaTipoDTO = new DietaTipoDTO(administradorDietaTiposLogic.addDietaTipo(dietaTiposId, administradorsId));
        LOGGER.log(Level.INFO, "AdministradorDietaTiposResource addDietaTipo: output: {0}", dietaTipoDTO);
        return dietaTipoDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en la administrador.
     *
     * @param administradorsId Identificador de la administrador que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link DietaTipoDetailDTO} - Los libros encontrados en la
     * administrador. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<DietaTipoDetailDTO> getDietaTipos(@PathParam("administradorsId") Long administradorsId) {
        LOGGER.log(Level.INFO, "AdministradorDietaTiposResource getDietaTipos: input: {0}", administradorsId);
        List<DietaTipoDetailDTO> listaDetailDTOs = dietaTiposListEntity2DTO(administradorDietaTiposLogic.getDietaTipos(administradorsId));
        LOGGER.log(Level.INFO, "AdministradorDietaTiposResource getDietaTipos: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Busca el libro con el id asociado dentro de la administrador con id asociado.
     *
     * @param administradorsId Identificador de la administrador que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param dietaTiposId Identificador del libro que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link DietaTipoDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro en la
     * administrador.
     */
    @GET
    @Path("{dietaTiposId: \\d+}")
    public DietaTipoDetailDTO getDietaTipo(@PathParam("administradorsId") Long administradorsId, @PathParam("dietaTiposId") Long dietaTiposId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorDietaTiposResource getDietaTipo: input: administradorsID: {0} , dietaTiposId: {1}", new Object[]{administradorsId, dietaTiposId});
        if (dietaTipoLogic.getDietaTipo(dietaTiposId) == null) {
            throw new WebApplicationException("El recurso /administradors/" + administradorsId + "/dietaTipos/" + dietaTiposId + " no existe.", 404);
        }
        DietaTipoDetailDTO dietaTipoDetailDTO = new DietaTipoDetailDTO(administradorDietaTiposLogic.getDietaTipo(administradorsId, dietaTiposId));
        LOGGER.log(Level.INFO, "AdministradorDietaTiposResource getDietaTipo: output: {0}", dietaTipoDetailDTO);
        return dietaTipoDetailDTO;
    }

    /**
     * Remplaza las instancias de DietaTipo asociadas a una instancia de Administrador
     *
     * @param administradorsId Identificador de la administrador que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param dietaTipos JSONArray {@link DietaTipoDTO} El arreglo de libros nuevo para la
     * administrador.
     * @return JSON {@link DietaTipoDTO} - El arreglo de libros guardado en la
     * administrador.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @PUT
    public List<DietaTipoDetailDTO> replaceDietaTipos(@PathParam("administradorsId") Long administradorsId, List<DietaTipoDetailDTO> dietaTipos) {
        LOGGER.log(Level.INFO, "AdministradorDietaTiposResource replaceDietaTipos: input: administradorsId: {0} , dietaTipos: {1}", new Object[]{administradorsId, dietaTipos});
        for (DietaTipoDetailDTO dietaTipo : dietaTipos) {
            if (dietaTipoLogic.getDietaTipo(dietaTipo.getId()) == null) {
                throw new WebApplicationException("El recurso /dietaTipos/" + dietaTipo.getId() + " no existe.", 404);
            }
        }
        List<DietaTipoDetailDTO> listaDetailDTOs = dietaTiposListEntity2DTO(administradorDietaTiposLogic.replaceDietaTipos(administradorsId, dietaTiposListDTO2Entity(dietaTipos)));
        LOGGER.log(Level.INFO, "AdministradorDietaTiposResource replaceDietaTipos: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Convierte una lista de DietaTipoEntity a una lista de DietaTipoDetailDTO.
     *
     * @param entityList Lista de DietaTipoEntity a convertir.
     * @return Lista de DietaTipoDTO convertida.
     */
    private List<DietaTipoDetailDTO> dietaTiposListEntity2DTO(List<DietaTipoEntity> entityList) {
        List<DietaTipoDetailDTO> list = new ArrayList();
        for (DietaTipoEntity entity : entityList) {
            list.add(new DietaTipoDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de DietaTipoDetailDTO a una lista de DietaTipoEntity.
     *
     * @param dtos Lista de DietaTipoDetailDTO a convertir.
     * @return Lista de DietaTipoEntity convertida.
     */
    private List<DietaTipoEntity> dietaTiposListDTO2Entity(List<DietaTipoDetailDTO> dtos) {
        List<DietaTipoEntity> list = new ArrayList<>();
        for (DietaTipoDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
}
