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
 * @author Elina Jaimes
 */
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
     * @param domiciliosId El ID del libro del cual se le agrega la reseña
     * @param pago {@link PagoDTO} - La reseña que se desea guardar.
     * @return JSON {@link PagoDTO} - La reseña guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la reseña.
     */
    @POST
    public PagoDTO createPago(@PathParam("domiciliosId") Long domiciliosId, PagoDTO pago) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PagoResource createPago: input: {0}", pago);

        PagoDTO nuevoPagoDTO = new PagoDTO(pagoLogic.crearPago(domiciliosId, pago.toEntity()));
        if (nuevoPagoDTO == null) {
            throw new WebApplicationException("El recurso /books/" + domiciliosId + "/reviews" + "no existe.", 404);
        }
        LOGGER.log(Level.INFO, "PagoResource createPago: output: {0}", nuevoPagoDTO);
        return nuevoPagoDTO;
    }

    /**
     * Busca y devuelve todas las reseñas que existen en un libro.
     *
     * @param domiciliosId El ID del libro del cual se buscan las reseñas
     * @return JSONArray {@link PagoDTO} - Las reseñas encontradas en el libro.
     * Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public PagoDTO getPagos(@PathParam("domiciliosId") Long domiciliosId) {
        LOGGER.log(Level.INFO, "PagoResource getPagos: input: {0}");
        PagoEntity jeje = pagoLogic.getPagos(domiciliosId);
        if (jeje == null) {
            throw new WebApplicationException("El recurso /books/" + domiciliosId + "/reviews" + "no existe.", 404);
        }
        PagoDTO paguito = new PagoDTO(jeje);
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", paguito);
        return paguito;
    }

    /**
     * Busca y devuelve la reseña con el ID recibido en la URL, relativa a un
     * libro.
     *
     * @param domiciliosId El ID del libro del cual se buscan las reseñas
     * @param pagosId El ID de la reseña que se busca
     * @return {@link PagoDTO} - La reseña encontradas en el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @GET
    @Path("{pagosId: \\d+}")
    public PagoDTO getPago(@PathParam("domiciliosId") Long domiciliosId, @PathParam("pagosId") Long pagosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PagoResource getPago: input: {0}", pagosId);
        PagoEntity entity = pagoLogic.getPago(domiciliosId, pagosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /domicilios/" + domiciliosId + "/pagos/" + pagosId + " no existe.", 404);
        }
        PagoDTO pagoDTO = new PagoDTO(entity);
        LOGGER.log(Level.INFO, "PagoResource getPago: output: {0}", pagoDTO);
        return pagoDTO;
    }

    /**
     * Actualiza una reseña con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param domiciliosId El ID del libro del cual se guarda la reseña
     * @param pagosId El ID de la reseña que se va a actualizar
     * @param pago {@link PagoDTO} - La reseña que se desea guardar.
     * @return JSON {@link PagoDTO} - La reseña actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la reseña.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @PUT
    @Path("{pagosId: \\d+}")
    public PagoDTO updatePago(@PathParam("domiciliosId") Long domiciliosId, @PathParam("pagosId") Long pagosId, PagoDTO pago) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PagoResource updatePago: input: domiciliosId: {0} , pagosId: {1} , pago:{2}", new Object[]{domiciliosId, pagosId, pago});
        if (pagosId.equals(pago.getId())) {
            throw new BusinessLogicException("Los ids del Pago no coinciden.");
        }
        PagoEntity entity = pagoLogic.getPago(domiciliosId, pagosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /domicilios/" + domiciliosId + "/pagos/" + pagosId + " no existe.", 404);

        }
        PagoDTO pagoDTO = new PagoDTO(pagoLogic.updatePago(domiciliosId, pago.toEntity()));
        LOGGER.log(Level.INFO, "PagoResource updatePago: output:{0}", pagoDTO);
        return pagoDTO;

    }

    /**
     * Borra la reseña con el id asociado recibido en la URL.
     *
     * @param domiciliosId El ID del libro del cual se va a eliminar la reseña.
     * @param pagosId El ID de la reseña que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la reseña.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @DELETE
    @Path("{pagosId: \\d+}")
    public void deletePago(@PathParam("domiciliosId") Long domiciliosId, @PathParam("pagosId") Long pagosId) throws BusinessLogicException {
        PagoEntity entity = pagoLogic.getPago(domiciliosId, pagosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /domicilios/" + domiciliosId + "/pagos/" + pagosId + " no existe.", 404);
        }
        pagoLogic.deletePago(domiciliosId, pagosId);
    }

    /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PrizeEntity a una lista de
     * objetos PagoDTO (json)
     *
     * @param entityList corresponde a la lista de reseñas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de reseñas en forma DTO (json)
     */
    private List<PagoDTO> listEntity2DTO(List<PagoEntity> entityList) {
        List<PagoDTO> list = new ArrayList<PagoDTO>();
        for (PagoEntity entity : entityList) {
            list.add(new PagoDTO(entity));
        }
        return list;
    }

}
