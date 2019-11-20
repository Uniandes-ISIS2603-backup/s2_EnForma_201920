/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;


import co.edu.uniandes.csw.enforma.dtos.PagoDTO;
import co.edu.uniandes.csw.enforma.ejb.PagoLogic;

import co.edu.uniandes.csw.enforma.entities.PagoEntity;
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
 * @author Elina Jaimes
 */

@Path("pagos")
@Produces("application/json")
@Consumes("application/json")
public class PagoResource {

    private static final Logger LOGGER = Logger.getLogger(PagoResource.class.getName());

    @Inject
    private PagoLogic pagoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una nueva reseña con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param pagosId El ID del libro del cual se le agrega la reseña
     * @param pago {@link PagoDTO} - La reseña que se desea guardar.
     * @return JSON {@link PagoDTO} - La reseña guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la reseña.
     */
   
    
    
    
     @POST
    public PagoDTO createPago(PagoDTO pago) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "PagoResource createPago: input: {0}", pago);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        PagoEntity pagoEntity = pago.toEntity();
        // Invoca la lógica para crear la editorial nueva
        PagoEntity nuevoPagoEntity = pagoLogic.crearPago(pagoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        PagoDTO nuevoPagoDTO = new PagoDTO(nuevoPagoEntity);
        LOGGER.log(Level.INFO, "PagoResource createPago: output: {0}", nuevoPagoDTO);
        return nuevoPagoDTO;
    }
    
     /**
     * Busca y devuelve todos los pagos que existen.
     *
     * @return JSONArray {@link PagoDTO} - Las reseñas encontradas en el
     * libro. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<PagoDTO> getPagos() 
    {
        LOGGER.log(Level.INFO, "PagoResource getPagos: input: void");
        List<PagoDTO> listaDTOs = listEntity2DTO(pagoLogic.getPagos());
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDTOs);
        return listaDTOs;
    }
    
     /**
     * Busca y devuelve el pago con el ID recibido en la URL, relativa a un
     * pago.
     *
     * @param pagosId El ID del pago que se busca
     * @return {@link PagoDetailDTO} - El pago encontrado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pago.
     */
    @GET
    @Path("{pagosId: \\d+}")
    public PagoDTO getPago(@PathParam("pagosId") Long pagosId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "PagoResource getPago: input: {0}", pagosId);
        PagoEntity entity = pagoLogic.getPago(pagosId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /pago/" + pagosId + " no existe.", 404);
        }
        PagoDTO pagoDTO = new PagoDTO(entity);
        LOGGER.log(Level.INFO, "PagoResource getPago: output: {0}", pagoDTO);
        return pagoDTO;
    }
    
     /**
     * Actualiza un pago con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param pagosId El ID del pago que se va a actualizar
     * @param pago {@link PagoDTO} - el pago que se desea guardar.
     * @return JSON {@link PagoDTO} - El pago actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el pago.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pago.
     */
    @PUT
    @Path("{pagosId: \\d+}")
    public PagoDTO updatePago(@PathParam("pagosId") Long pagosId, PagoDTO pago) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "PagoResource updatePago: input: pagosId: {0} , pago:{1}", new Object[]{pagosId, pago});
        pago.setId(pagosId);
        PagoEntity entity = pagoLogic.getPago(pagosId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /pago/" + pagosId + " no existe.", 404);

        }
        PagoDTO pagoDTO = new PagoDTO(pagoLogic.updatePago(pagosId, pago.toEntity())); 
        LOGGER.log(Level.INFO, "PagoResource updatePago: output:{0}", pagoDTO);
        return pagoDTO;

    }
    
     /**
     * Borra el pago con el id asociado recibido en la URL.
     *
     * @param pagosId El ID del pago que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el pago.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra lel pago.
     */
    @DELETE
    @Path("{pagosId: \\d+}")
    public void deletePago(@PathParam("pagosId") Long pagosId) throws BusinessLogicException 
    {
        PagoEntity entity = pagoLogic.getPago(pagosId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /pago/" + pagosId + " no existe.", 404);
        }
        pagoLogic.deletePago(pagosId);
    }
    

    
    
     /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PagoEntity a una lista de
     * objetos PagoDTO (json)
     *
     * @param entityList corresponde a la lista de reseñas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de reseñas en forma DTO (json)
     */
    private List<PagoDTO> listEntity2DTO(List<PagoEntity> entityList) 
    {
        List<PagoDTO> list = new ArrayList<PagoDTO>();
        for (PagoEntity entity : entityList) 
        {
            list.add(new PagoDTO(entity));
        }
        return list;
    }

}
