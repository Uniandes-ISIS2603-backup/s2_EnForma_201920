/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

import co.edu.uniandes.csw.enforma.dtos.PagoDTO;
import co.edu.uniandes.csw.enforma.ejb.PagoLogic;
import co.edu.uniandes.csw.enforma.ejb.TarjetaPrepagoPagosLogic;
import co.edu.uniandes.csw.enforma.entities.PagoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import javax.inject.Inject;


/**
 *
 * @author Juan Sebastián Clavijo
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TarjetaPrepagoPagosResource 
{
    private static final Logger LOGGER = Logger.getLogger(TarjetaPrepagoPagosResource.class.getName());

    @Inject
    private TarjetaPrepagoPagosLogic tarjetaPrepagoPagosLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private PagoLogic pagoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
     /**
     * Guarda un pago dentro de una tarjeta preapgo con la informacion que recibe el
     * la URL. Se devuelve el pago que se guarda en la tarjeta.
     *
     * @param tarjetasId Identificador de la tarjeta que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param pagosId Identificador del pago que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @param domicilioId
     * @return JSON {@link PagoDTO} - El pago guardado en la tarjeta.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pago.
     */
    @POST
    @Path("{pagosId: \\d+}")
    public PagoDTO addPago(@PathParam("tarjetasId") Long tarjetasId, @PathParam("pagosId") Long pagosId) 
    {
        LOGGER.log(Level.INFO, "TarjetaPrepagoPagosResource addPago: input: tarjetasId: {0} , pagosId: {1}", new Object[]{tarjetasId, pagosId});
        if (pagoLogic.getPago(pagosId) == null) 
        {
            throw new WebApplicationException("El recurso /pagos/" + pagosId + " no existe.", 404);
        }
        PagoDTO pagoDTO = new PagoDTO(tarjetaPrepagoPagosLogic.addPago(pagosId, tarjetasId));
        LOGGER.log(Level.INFO, "TarjetaPrepagoPagosResource addPago: output: {0}", pagoDTO);
        return pagoDTO;
    }

    /**
     * Busca y devuelve todos los pagos que existen en la tarjeta.
     *
     * @param tarjetasId Identificador de la tarjeta que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link PagoDTO} - Los pagos encontrados en la
     * tarjeta. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PagoDTO> getPagos(@PathParam("tarjetasId") Long tarjetasId) 
    {
        LOGGER.log(Level.INFO, "TarjetaPrepagoPagosResource getPagos: input: {0}", tarjetasId);
        List<PagoDTO> listaDTOs = pagosListEntity2DTO(tarjetaPrepagoPagosLogic.getPagos(tarjetasId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDTOs);
        return listaDTOs;
    }

    /**
     * Busca el pago con el id asociado dentro de la tarjeta con id asociado.
     *
     * @param tarjetasId Identificador de la tarjeta que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param pagosId Identificador del pago que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link PagoDTO} - El pago buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pago.
     * @param domicilioId
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pago en la
     * editorial.
     */
    @GET
    @Path("{pagosId: \\d+}")
    public PagoDTO getPago(@PathParam("tarjetasId") Long tarjetasId, @PathParam("pagosId") Long pagosId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "TarjetaPrepagoPagosResource getPago: input: tarjetasId: {0} , pagosId: {1} , domicilioId: (2)", new Object[]{tarjetasId, pagosId});
        if (pagoLogic.getPago(pagosId) == null) 
        {
            throw new WebApplicationException("El recurso /tarjetas/" + tarjetasId + "/pagos/" + pagosId + " no existe.", 404);
        }
        PagoDTO pagoDTO = new PagoDTO(tarjetaPrepagoPagosLogic.getPago(tarjetasId, pagosId));
        LOGGER.log(Level.INFO, "TarjetaPrepagoPagosResource getPago: output: {0}", pagoDTO);
        return pagoDTO;
    }

    /**
     * Remplaza las instancias de pago asociadas a una instancia de tarjeta prepago
     * @param tarjetasId Identificador de la tarjeta que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param pagos JSONArray {@link PagoDTO} El arreglo de pagos nuevo para la
     * tarjeta.
     * @return JSON {@link PagoDTO} - El arreglo de pagos guardado en la
     * tarjeta.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el tarjeta.
     */
    @PUT
    public List<PagoDTO> replacePagos(@PathParam("tarjetasId") Long tarjetasId, List<PagoDTO> pagos) 
    {
        LOGGER.log(Level.INFO, "TarjetaPrepagoPagosResource replacePagos: input: tarjetasId: {0} , pagos: {1}", new Object[]{tarjetasId, pagos});
        for (PagoDTO pago : pagos) 
        {
            if (pagoLogic.getPago(pago.getId()) == null) 
            {
                throw new WebApplicationException("El recurso /pagos/" + pago.getId() + " no existe.", 404);
            }
        }
        List<PagoDTO> listaDTOs = pagosListEntity2DTO(tarjetaPrepagoPagosLogic.replacePagos(tarjetasId, pagosListDTO2Entity(pagos)));
        LOGGER.log(Level.INFO, "TarjetaPrepagoPagosResource replacePagos: output: {0}", listaDTOs);
        return listaDTOs;
    }
    
    
     /**
     * Convierte una lista de PagoEntity a una lista de PagoDTO.
     *
     * @param entityList Lista de PagoEntity a convertir.
     * @return Lista de PagoDTO convertida.
     */
    private List<PagoDTO> pagosListEntity2DTO(List<PagoEntity> entityList) 
    {
        List<PagoDTO> list = new ArrayList();
        for (PagoEntity entity : entityList) 
        {
            list.add(new PagoDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de PagoDTO a una lista de PagoEntity.
     *
     * @param dtos Lista de PagoEntity a convertir.
     * @return Lista de PagoEntity convertida.
     */
    private List<PagoEntity> pagosListDTO2Entity(List<PagoDTO> dtos) 
    {
        List<PagoEntity> list = new ArrayList<>();
        for (PagoDTO dto : dtos) 
        {
            list.add(dto.toEntity());
        }
        return list;
    }
}
