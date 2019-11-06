/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

import co.edu.uniandes.csw.enforma.dtos.TarjetaPrepagoDTO;
import co.edu.uniandes.csw.enforma.dtos.TarjetaPrepagoDetailDTO;
import co.edu.uniandes.csw.enforma.ejb.TarjetaPrepagoLogic;
import co.edu.uniandes.csw.enforma.entities.TarjetaPrepagoEntity;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Juan Sebastián Clavijo
 */
@Path("tarjetasprepago")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TarjetaPrepagoResource 
{
    private static final Logger LOGGER = Logger.getLogger(TarjetaPrepagoResource.class.getName());

    @Inject
    private TarjetaPrepagoLogic tarjetaPrepagoLogic;
    
     /**
     * Crea una nueva tarjeta prepago con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param tarjeta {@link TarjetaPrepagoDTO} - la tarjeta que se desea
     * guardar.
     * @return JSON {@link TarjetaPrepagoDTO} - la tarjeta prepago guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la tarjeta.
     */
    @POST
    public TarjetaPrepagoDTO createTarjetaPrepago(TarjetaPrepagoDTO tarjeta) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "TarjetaPrepagoResource createTarjetaPrepago: input: {0}", tarjeta);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        TarjetaPrepagoEntity tarjetaPrepagoEntity = tarjeta.toEntity();
        // Invoca la lógica para crear la editorial nueva
        TarjetaPrepagoEntity nuevaTarjetaPrepagoEntity = tarjetaPrepagoLogic.createTarjetaPrepago(tarjetaPrepagoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        TarjetaPrepagoDTO nuevaTarjetaPrepagoDTO = new TarjetaPrepagoDTO(nuevaTarjetaPrepagoEntity);
        LOGGER.log(Level.INFO, "TarjetaPrepagoResource createTarjetaPrepago: output: {0}", nuevaTarjetaPrepagoDTO);
        return nuevaTarjetaPrepagoDTO;
    }
    
     /**
     * Busca y devuelve todas las tarjetas prepagos que existen en la aplicacion.
     *
     * @return JSONArray {@link EditorialDetailDTO} - Las tarjetas prepago
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<TarjetaPrepagoDetailDTO> getTarjetasPrepago() 
    {
        LOGGER.info("TarjetaPrepagoResource getTarjetasPrepago: input: void");
        List<TarjetaPrepagoDetailDTO> listaTarjetasPrepago = listEntity2DetailDTO(tarjetaPrepagoLogic.getTarjetasPrepago());
        LOGGER.log(Level.INFO, "TarjetaPrepagoResource getTarjetasPrepago: output: {0}", listaTarjetasPrepago);
        return listaTarjetasPrepago;
    }

    /**
     * Busca la tarjeta prepago con el id asociado recibido en la URL y la devuelve.
     *
     * @param tarjetasprepagoId Identificador de la tarjeta prepago que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link TarjetaPrepagoDetailDTO} - La tarjeta prepago buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la tarjeta prepago.
     */
    @GET
    @Path("{tarjetasprepagoId: \\d+}")
    public TarjetaPrepagoDetailDTO getTarjetaPrepago(@PathParam("tarjetasprepagoId") Long tarjetasprepagoId) throws WebApplicationException 
    {
        LOGGER.log(Level.INFO, "TarjetaPrepagoResource getTarjetaPrepago: input: {0}", tarjetasprepagoId);
        TarjetaPrepagoEntity tarjetaPrepagoEntity = tarjetaPrepagoLogic.getTarjetaPrepago(tarjetasprepagoId);
        if (tarjetaPrepagoEntity == null) 
        {
            throw new WebApplicationException("El recurso /tarjetasprepago/" + tarjetasprepagoId + " no existe.", 404);
        }
        TarjetaPrepagoDetailDTO detailDTO = new TarjetaPrepagoDetailDTO(tarjetaPrepagoEntity);
        LOGGER.log(Level.INFO, "TarjetaPrepagoResource getTarjetaPrepago: output: {0}", detailDTO);
        return detailDTO;
    }
    
     /**
     * Actualiza la tarjeta prepago con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param tarjetasprepagoId Identificador de la tarjeta prepago que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param tarjeta {@link TarjetaPrepagoDetailDTO} La tarjeta prepago que se desea
     * guardar.
     * @return JSON {@link EditorialDetailDTO} - La editorial guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la tarjeta prepago a
     * actualizar.
     * @throws BusinessLogicException si hay problemas con la entidad
     */
    @PUT
    @Path("{tarjetasprepagoId: \\d+}")
    public TarjetaPrepagoDetailDTO updateTarjetaPrepago(@PathParam("tarjetasprepagoId") Long tarjetasprepagoId, TarjetaPrepagoDetailDTO tarjeta) throws WebApplicationException, BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "TarjetaPrepagoResource updateTarjetaPrepago: input: id:{0} , tarjeta: {1}", new Object[]{tarjetasprepagoId, tarjeta});
        tarjeta.setId(tarjetasprepagoId);
        if (tarjetaPrepagoLogic.getTarjetaPrepago(tarjetasprepagoId) == null)
        {
            throw new WebApplicationException("El recurso /tarjetasprepago/" + tarjetasprepagoId + " no existe.", 404);
        }
        TarjetaPrepagoDetailDTO detailDTO = new TarjetaPrepagoDetailDTO(tarjetaPrepagoLogic.updateTarjetaPrepago(tarjetasprepagoId, tarjeta.toEntity()));
        LOGGER.log(Level.INFO, "TarjetaPrepagoResource updateTarjetaPrepago: output: {0}", detailDTO);
        return detailDTO;

    }
    
     /**
     * Borra la tarjeta prepago con el id asociado recibido en la URL.
     *
     * @param tarjetasprepagoId Identificador de la tarjeta prepago que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la tarjeta prepago.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la tarjeta prepago.
     */
    @DELETE
    @Path("{tarjetasprepagoId: \\d+}")
    public void deleteTarjetaPrepago(@PathParam("tarjetasprepagoId") Long tarjetasprepagoId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "TarjetaPrepagoResource deleteTarjetaPrepago: input: {0}", tarjetasprepagoId);
        if (tarjetaPrepagoLogic.getTarjetaPrepago(tarjetasprepagoId) == null)
        {
            throw new WebApplicationException("El recurso /tarjetasprepago/" + tarjetasprepagoId + " no existe.", 404);
        }
        tarjetaPrepagoLogic.deleteTarjetaPrepago(tarjetasprepagoId);
        LOGGER.info("TarjetaPrepagoResource deleteTarjetaPrepago: output: void");
    }
    
    
    
    
    
    @Path("{tarjetasprepagoId: \\d+}/pagos")
    public Class<PagoResource> getpagoResource(@PathParam("domiciliosId") Long domiciliosId)
    {
        if(tarjetaPrepagoLogic.getTarjetaPrepago(domiciliosId) == null)
        {
            throw new WebApplicationException("El recurso /domicilio/" + domiciliosId + "/pagos no existe.", 404);
        }
        return PagoResource.class;
    }
    
     /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos TarjetaPrepagoEntity a una lista de
     * objetos TarjetaPrepagoDetailDTO (json)
     *
     * @param entityList corresponde a la lista de editoriales de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de tarjetas prepago en forma DTO (json)
     */
    private List<TarjetaPrepagoDetailDTO> listEntity2DetailDTO(List<TarjetaPrepagoEntity> entityList) {
        List<TarjetaPrepagoDetailDTO> list = new ArrayList<>();
        for (TarjetaPrepagoEntity entity : entityList) {
            list.add(new TarjetaPrepagoDetailDTO(entity));
        }
        return list;
    }
    
}
