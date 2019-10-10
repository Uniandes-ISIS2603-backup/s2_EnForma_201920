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
    public CalificacionDTO createCalificacion(@PathParam("clientesId") Long clienteId, @PathParam("dietatipoId") Long dietaId, CalificacionDTO calificacion) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: input: {0}", calificacion);
        CalificacionDTO nuevaCalificacionDTO = new CalificacionDTO(calificacionLogic.createCalificacion(clienteId, dietaId, calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: output: {0}", nuevaCalificacionDTO);
        return nuevaCalificacionDTO;
    }
    
    
    @GET
    public List<CalificacionDTO> getCalificaciones()
    {
        LOGGER.info("CalificacionResource getCalificaciones: input: void");
        List<CalificacionDTO> listaCalificaciones = listEntity2DTO(calificacionLogic.getCalificaciones());
        LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: output: {0}", listaCalificaciones);
        return listaCalificaciones;
    }
    
    /**
     * Busca y devuelve todas las calificaciones que existen 
     * @return JSONArray {@link CalificacionDetailDTO} - Las calificaciones encontradas. Si no hay ninguna retorna una lista vacia
     */
    @GET
    @Path("{dietastipoId: \\d")       
    public CalificacionDTO getCalificacionesByDiettasTipoId(@PathParam("dietastipoId") Long dietaId)
    {
        LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: input: {0}", dietaId);
        CalificacionDTO listaCalificaciones = new CalificacionDTO(calificacionLogic.getCalificacionesByDietaId(dietaId));
        LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: output: {0}", listaCalificaciones);
        return listaCalificaciones;
    }
    
    /**
     * Busca la calificacion con el id asociado en la URL y lo devuelve 
     * @param calificacionesId Identificador del la calificacion que se esta buscando. 
     * Este debe ser una cadena de digitos 
     * @return JSON {@link CalificacionDetailDTO} - La calificacion que se esta buscando
     * @throws WebApplicationException  {@link WebApplicationExceptionMapper} - Error de la logica 
     * que se genera cuando no se encuentra la calificacion 
     */
    @GET
    @Path("{calificacionesId: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("clientesId") Long clienteId, @PathParam("dietastipoId") Long dietaId, @PathParam("calificacionesId") Long calificacionesId)
    {
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: input: {0}", calificacionesId);
        CalificacionEntity calificacionEntity = calificacionLogic.getCalificacion(clienteId, dietaId, calificacionesId);
        if(calificacionEntity == null)
        {
            throw new WebApplicationException("El recurso /clientes/"+ clienteId + "/dietastipo/" + dietaId +"/calificaciones/" + calificacionesId + " no existe.", 404);
        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(calificacionEntity);
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: output: {0}", calificacionDTO);
        return calificacionDTO;
    }
    
    /**
     * ACtualiza la calificacion con el id recibido en la URL con la informacion que se recibe en el cuerpo de la peticion
     * @param calificacionesId Identificador de la calificacion que se desea actualizar. Este debe ser una cadena de digitos
     * @param calificacion {@link CalificacionDTO} La calificacion que se desea guardar
     * @return JSON {@link CalificacionDetailDTO) - La calificacion que guarda
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - 
     * Error de logica que se genera cuando no se encuentra la calificacion a actualizar
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - 
     * Error de logica que se genera cuando no se puede actualizar el libro.
     */
    @PUT
    @Path("{calificacionesId: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("clientesId") Long clienteId, @PathParam("dietastipoId") Long dietaId , @PathParam("calificacionesId") Long calificacionId, CalificacionDTO calificacion) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: input: clientesId: {0}, dietastipoId: {1}, calificacionesId: {2}, calificacion {3}", new Object[]{clienteId, dietaId, calificacionId, calificacion});
        
        if(calificacionId.equals(calificacion.getId()))
        {
            throw new BusinessLogicException("Los ids de la calificacion coinciden");
        }
        CalificacionEntity entity = calificacionLogic.getCalificacion(clienteId, dietaId, calificacionId);
        if(entity == null)
        {
            throw new WebApplicationException("El recurso /clientes/" + clienteId + "/dietastipoId/" + dietaId + "/calificacionesId/" + calificacionId + " no existe.", 404);
        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(calificacionLogic.updateCalificacion(clienteId, dietaId, calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: output: {0}", calificacionDTO);
        return calificacionDTO;
    }
    
    
    @DELETE
    @Path("{calificacionesId: \\d+}")
    public void deleteCalificacion(@PathParam("clientesId") Long clienteId, @PathParam("dietastipoId") Long dietaId, @PathParam("calificacionesId") Long calificacionesId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CalificacionResource deleteCalificacion: input: {0}", calificacionesId);
        CalificacionEntity entity = calificacionLogic.getCalificacion(clienteId, dietaId, calificacionesId);
        if(entity == null)
        {
            throw new WebApplicationException("El recurso /clientes/" + clienteId + "/dietastipo/" + dietaId + "/calificaciones/" + calificacionesId + " no existe.", 404);
        }
        calificacionLogic.deleteCalificacion(clienteId, dietaId, calificacionesId);
        LOGGER.info("CalificacionResource deleteCalificacion: output: void");
    }
    
    
    /**
     * Convierte una lista de entidades a DTO
     * @param entityList
     * @return 
     */
    private List<CalificacionDTO> listEntity2DTO(List<CalificacionEntity> entityList)
    {
        List<CalificacionDTO> list = new ArrayList<CalificacionDTO>();
        for(CalificacionEntity entity: entityList)
        {
            list.add(new CalificacionDTO(entity));
        }
        return list;
    }
}

