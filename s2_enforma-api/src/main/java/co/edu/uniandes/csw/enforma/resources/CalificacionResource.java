/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

import co.edu.uniandes.csw.enforma.dtos.CalificacionDTO;
import co.edu.uniandes.csw.enforma.ejb.CalificacionLogic;
import co.edu.uniandes.csw.enforma.entities.CalificacionEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Jimmy Pepinosa
 */
@Path("calificaciones")
@Produces("application/json")
@Consumes("application/json")
public class CalificacionResource 
{
    private static final  Logger LOGGER = Logger.getLogger(CalificacionResource.class.getName());
    
    @Inject
    private CalificacionLogic calificacionLogic; //Variable para acceder a la logica de la aplicacion. Es una inyeccion de dependencias
    
    
    /**
     * Crea una nueva calificacion con la informacion que se recibe en el cuerpo de la peticion y se regresa un 
     * objeto identico con un id auto-generado por la base de datos
     * @param clienteId
     * @param calificacion {@link CalificacionDTO} - La calificacion que desea guardar 
     * @param dietaId 
     * @return JSON {@link CalificacionDTO} - La calificacion guardada con el atributo id autogenerado
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de  logica que se genera cuando ya existe la calificacion
     * o cuando no ingresa un puntaje 
     */
    @POST
    public CalificacionDTO createCalificacion(CalificacionDTO calificacion) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: input: {0}", calificacion);
        CalificacionDTO nuevaCalificacionDTO = new CalificacionDTO(calificacionLogic.createCalificacion( calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: output: {0}", nuevaCalificacionDTO);
        return nuevaCalificacionDTO;
    }
    
    
    @GET
    public List<CalificacionDTO> getCalificaciones()
    {
        LOGGER.info("CalificacionResource getCalificaciones: input: void");
        List<CalificacionEntity> listaEntity = calificacionLogic.getCalificaciones();
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacionesEntity: output: {0}", listaEntity);
        List<CalificacionDTO> listaCalificaciones = listEntity2DTO(listaEntity);
        LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: output: {0}", listaCalificaciones);
        return listaCalificaciones;
    }
    
    @GET
    @Path("{calificacionesId: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("calificacionesId") Long calificacionId)
    {
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: input: {0}", calificacionId);
        CalificacionEntity calificacionEntity = calificacionLogic.getCalificacion(calificacionId);
        if(calificacionEntity == null)
        {
            throw new WebApplicationException("El recurso /calificaciones/"+ calificacionId + " no exitee", 404 );
        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(calificacionEntity);
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: input: {0}", calificacionDTO);
        return calificacionDTO;
    }
    
    @PUT
    @Path("{calificacionesId: \\d+}")
    public void updateCalificacion(@PathParam("calificacionesId") Long calificacionesId, CalificacionDTO calificacion)
    {
        LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: input: calificacionesId: {0}, calificacion: {1}", new Object[]{calificacionesId, calificacion});
        calificacion.setId(calificacionesId);
        if(calificacionLogic.getCalificacion(calificacionesId) == null)
        {
            throw new WebApplicationException("El recursoo calificaciones/" + calificacionesId + " no existe.", 404);
        }
        LOGGER.info("CalificacionResource updateCalificacion: output: void");
        
    }
    
    @DELETE
    @Path("{calificacionesId: \\d+}")
    public void deleteCalificacion(@PathParam("calificacionesId") Long calificacionId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CalificacionResource deleteCalificacion: input: {0}", calificacionId);
        if(calificacionLogic.getCalificacion(calificacionId) == null)
        {
            throw new WebApplicationException("El recurso calificaciones" + calificacionId + " no existe.", 404);
        }
        calificacionLogic.deleteCalificacion(calificacionId);
        LOGGER.info("CalificacionResource deleteCalificacion: output: void");
    }
    
    /**
     * Convierte una lista de entidades a DTO
     * @param entityList
     * @return 
     */
    private List<CalificacionDTO> listEntity2DTO(List<CalificacionEntity> entityList)
    {
        List<CalificacionDTO> list = new ArrayList<>();
        for(CalificacionEntity entity: entityList)
        {
            list.add(new CalificacionDTO(entity));
        }
        return list;
    }
}

