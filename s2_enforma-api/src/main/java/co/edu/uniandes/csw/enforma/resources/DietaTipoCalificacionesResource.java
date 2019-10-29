/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

import co.edu.uniandes.csw.enforma.dtos.CalificacionDTO;
import co.edu.uniandes.csw.enforma.ejb.CalificacionLogic;
import co.edu.uniandes.csw.enforma.ejb.DietaTipoCalificacionesLogic;
import co.edu.uniandes.csw.enforma.entities.CalificacionEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 *  Clase que implementa el recurso "dietas/{id}/calificaciones".
 * 
 * @author Julio Morales
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DietaTipoCalificacionesResource {
    
    private static final Logger LOGGER = Logger.getLogger(DietaTipoCalificacionesResource.class.getName());

    @Inject
    private DietaTipoCalificacionesLogic dietaTipoCalificacionesLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CalificacionLogic calificacionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    
    
    
    
    
    /**
     * Guarda una calificacion dentro de una dieta con la informacion que recibe el
     * la URL. Se devuelve la calificacion que se guarda en la dieta.
     *
     * @param dietaId Identificador de la dieta que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param calificacionesId Identificador del libro que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link DietaTipoDTO} - El libro guardado en la administrador.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{calificacionesId: \\d+}")
    public CalificacionDTO addCalificacion(@PathParam("dietaId") Long dietaId, @PathParam("calificacionesId") Long calificacionesId) {
        LOGGER.log(Level.INFO, "DietaTipoCalificacionesResource addCalificacion: input: dietaId: {0} , calificacionesId: {1}", new Object[]{dietaId, calificacionesId});
        if (calificacionLogic.getCalificacion(calificacionesId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionesId + " no existe.", 404);
        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(dietaTipoCalificacionesLogic.addCalificacion(calificacionesId, dietaId));
        LOGGER.log(Level.INFO, "DietaTipoCalificacionesResource addCalificacion: output: {0}", calificacionDTO);
        return calificacionDTO;
    }
    
    
    
    
    /**
     * Busca y devuelve todos las calificaciones que existen en la dieta.
     *
     * @param dietaId Identificador de la dieta que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link CalificacionDTO} - Las calificaciones encontrados en la
     * dieta. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<CalificacionDTO> getCalificaciones(@PathParam("dietaId") Long dietaId) {
        LOGGER.log(Level.INFO, "DietaTipoCalificacionesResource getCalificaciones: input: {0}", dietaId);
        List<CalificacionDTO> listaDTOs = dietaTiposListEntity2DTO(dietaTipoCalificacionesLogic.getCalificaciones(dietaId));
        LOGGER.log(Level.INFO, "DietaTipoCalificacionesResource getCalificaciones: output: {0}", listaDTOs);
        return listaDTOs;
    }
    
    
    
    
    /**
     * Busca la calificacion con el id asociado dentro de la dieta con id asociado.
     *
     * @param dietaId Identificador de la dieta que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param calificacionId Identificador de la calificacion que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link CalificacionDTO} - La calificacion buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion en la
     * dieta.
     */
    @GET
    @Path("{calificacionesId: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("dietaId") Long dietaId, @PathParam("calificacionId") Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "DietaTipoCalificacionesResource getCalificacion: input: dietaId: {0} , calificacionId: {1}", new Object[]{dietaId, calificacionId});
        if (calificacionLogic.getCalificacion(calificacionId) == null) {
            throw new WebApplicationException("El recurso /dietas/" + dietaId + "/calificaciones/" + calificacionId + " no existe.", 404);
        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(dietaTipoCalificacionesLogic.getCalificacion(calificacionId, dietaId));
        LOGGER.log(Level.INFO, "DietaTipoCalificacionesResource getCalificacion: output: {0}", calificacionDTO);
        return calificacionDTO;
    }
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Convierte una lista de CalificacionEntity a una lista de CalificacionDTO.
     *
     * @param entityList Lista de CalificacionEntity a convertir.
     * @return Lista de CalificacionDTO convertida.
     */
    private List<CalificacionDTO> dietaTiposListEntity2DTO(List<CalificacionEntity> entityList) {
        List<CalificacionDTO> list = new ArrayList<>();
        for (CalificacionEntity entity : entityList) {
            list.add(new CalificacionDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de DietaTipoDetailDTO a una lista de DietaTipoEntity.
     *
     * @param dtos Lista de DietaTipoDetailDTO a convertir.
     * @return Lista de DietaTipoEntity convertida.
     */
    private List<CalificacionEntity> dietaTiposListDTO2Entity(List<CalificacionDTO> dtos) {
        List<CalificacionEntity> list = new ArrayList<>();
        for (CalificacionDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    
}
