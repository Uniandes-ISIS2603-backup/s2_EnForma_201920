/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

import co.edu.uniandes.csw.enforma.dtos.DomicilioDTO;
import co.edu.uniandes.csw.enforma.ejb.ClienteDomiciliosLogic;
import co.edu.uniandes.csw.enforma.ejb.DomicilioLogic;
import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
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
 * @author Sofia Vargas
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteDomiciliosResource {
    

    private static final Logger LOGGER = Logger.getLogger(ClienteDomiciliosResource.class.getName());

    @Inject
    private ClienteDomiciliosLogic clienteDomiciliosLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private DomicilioLogic domicilioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un libro dentro de una cliente con la informacion que recibe el
     * la URL. Se devuelve el libro que se guarda en la cliente.
     *
     * @param clientesId Identificador de la cliente que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param domiciliosId Identificador del libro que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link DomicilioDTO} - El libro guardado en la cliente.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{domiciliosId: \\d+}")
    public DomicilioDTO addDomicilio(@PathParam("clientesId") Long clientesId, @PathParam("domiciliosId") Long domiciliosId) {
        LOGGER.log(Level.INFO, "ClienteDomiciliosResource addDomicilio: input: clientesID: {0} , domiciliosId: {1}", new Object[]{clientesId, domiciliosId});
        if (domicilioLogic.getDomicilio(domiciliosId) == null) {
            throw new WebApplicationException("El recurso /domicilios/" + domiciliosId + " no existe.", 404);
        }
        DomicilioDTO domicilioDTO = new DomicilioDTO(clienteDomiciliosLogic.addDomicilio(domiciliosId, clientesId));
        LOGGER.log(Level.INFO, "ClienteDomiciliosResource addDomicilio: output: {0}", domicilioDTO);
        return domicilioDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en la cliente.
     *
     * @param clientesId Identificador de la cliente que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link DomicilioDetailDTO} - Los libros encontrados en la
     * cliente. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<DomicilioDTO> getDomicilios(@PathParam("clientesId") Long clientesId) {
        LOGGER.log(Level.INFO, "ClienteDomiciliosResource getDomicilios: input: {0}", clientesId);
        List<DomicilioDTO> listaDetailDTOs = domiciliosListEntity2DTO(clienteDomiciliosLogic.getDomicilios(clientesId));
        LOGGER.log(Level.INFO, "ClienteDomiciliosResource getDomicilios: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Busca el libro con el id asociado dentro de la cliente con id asociado.
     *
     * @param clientesId Identificador de la cliente que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param domiciliosId Identificador del libro que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link DomicilioDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro en la
     * cliente.
     */
    @GET
    @Path("{domiciliosId: \\d+}")
    public DomicilioDTO getDomicilio(@PathParam("clientesId") Long clientesId, @PathParam("domiciliosId") Long domiciliosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteDomiciliosResource getDomicilio: input: clientesID: {0} , domiciliosId: {1}", new Object[]{clientesId, domiciliosId});
        if (domicilioLogic.getDomicilio(domiciliosId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + "/domicilios/" + domiciliosId + " no existe.", 404);
        }
        DomicilioDTO domicilioDetailDTO = new DomicilioDTO(clienteDomiciliosLogic.getDomicilio(clientesId, domiciliosId));
        LOGGER.log(Level.INFO, "ClienteDomiciliosResource getDomicilio: output: {0}", domicilioDetailDTO);
        return domicilioDetailDTO;
    }

   
    /**
     * Convierte una lista de DomicilioEntity a una lista de DomicilioDetailDTO.
     *
     * @param entityList Lista de DomicilioEntity a convertir.
     * @return Lista de DomicilioDTO convertida.
     */
    private List<DomicilioDTO> domiciliosListEntity2DTO(List<DomicilioEntity> entityList) {
        List<DomicilioDTO> list = new ArrayList();
        for (DomicilioEntity entity : entityList) {
            list.add(new DomicilioDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de DomicilioDetailDTO a una lista de DomicilioEntity.
     *
     * @param dtos Lista de DomicilioDetailDTO a convertir.
     * @return Lista de DomicilioEntity convertida.
     */
    private List<DomicilioEntity> domiciliosListDTO2Entity(List<DomicilioDTO> dtos) {
        List<DomicilioEntity> list = new ArrayList<>();
        for (DomicilioDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    
    
}
