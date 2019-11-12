/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

import co.edu.uniandes.csw.enforma.dtos.QuejasYReclamosDTO;
import co.edu.uniandes.csw.enforma.ejb.ClienteQuejasLogic;
import co.edu.uniandes.csw.enforma.ejb.QuejasYReclamosLogic;
import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
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
 * @author Sofía Vargas
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteQuejasResource 
{
    
    private static final Logger LOGGER = Logger.getLogger(ClienteQuejasResource.class.getName());

    @Inject
    private ClienteQuejasLogic clienteQuejasLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private QuejasYReclamosLogic quejasLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un libro dentro de una cliente con la informacion que recibe el
     * la URL. Se devuelve el libro que se guarda en la cliente.
     *
     * @param clientesId Identificador de la cliente que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param quejasId Identificador del libro que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link QuejasDTO} - El libro guardado en la cliente.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{quejasId: \\d+}")
    public QuejasYReclamosDTO addQuejas(@PathParam("clientesId") Long clientesId, @PathParam("quejasId") Long quejasId)
    {
        LOGGER.log(Level.INFO, "ClienteQuejasResource addQueja: input: clientesID: {0} , quejasId: {1}", new Object[]{clientesId, quejasId});
        if (quejasLogic.getQuejaOReclamo(quejasId) == null) {
            throw new WebApplicationException("El recurso /quejas/" + quejasId + " no existe.", 404);
        }
        QuejasYReclamosDTO quejaDTO = new QuejasYReclamosDTO(clienteQuejasLogic.addQueja(quejasId, clientesId));
        LOGGER.log(Level.INFO, "ClienteQuejasResource addQueja: output: {0}", quejaDTO);
        return quejaDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en la cliente.
     *
     * @param clientesId Identificador de la cliente que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link QuejasDetailDTO} - Los libros encontrados en la
     * cliente. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<QuejasYReclamosDTO> getQuejas(@PathParam("clientesId") Long clientesId) {
        LOGGER.log(Level.INFO, "ClienteQuejasResource getQuejas: input: {0}", clientesId);
        List<QuejasYReclamosDTO> listaDTOs = (List<QuejasYReclamosDTO>) quejasListEntity2DTO(clienteQuejasLogic.getQuejas(clientesId));
        LOGGER.log(Level.INFO, "ClienteQuejassResource getQuejas: output: {0}", listaDTOs);
        return listaDTOs;
    }

    /**
     * Busca el libro con el id asociado dentro de la cliente con id asociado.
     *
     * @param clientesId Identificador de la cliente que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param quejasId Identificador del libro que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link QuejasDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro en la
     * cliente.
     */
    @GET
    @Path("{quejasId: \\d+}")
    public QuejasYReclamosDTO getQueja(@PathParam("clientesId") Long clientesId, @PathParam("quejasId") Long quejasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteQuejassResource getQuejas: input: clientesID: {0} , quejasId: {1}", new Object[]{clientesId, quejasId});
        if (quejasLogic.getQuejaOReclamo(quejasId) == null) 
        {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + "/quejas/" + quejasId + " no existe.", 404);
        }
        QuejasYReclamosDTO quejaDetailDTO = new QuejasYReclamosDTO(clienteQuejasLogic.getQuejas(clientesId, quejasId));
        LOGGER.log(Level.INFO, "ClienteQuejassResource getQuejas: output: {0}", quejaDetailDTO);
        return quejaDetailDTO;
    }

   

    /**
     * Convierte una lista de QuejasEntity a una lista de QuejasDetailDTO.
     *
     * @param entityList Lista de QuejasEntity a convertir.
     * @return Lista de QuejasDTO convertida.
     */
    private List<QuejasYReclamosDTO> quejasListEntity2DTO(List<QuejasYReclamosEntity> entityList) {
        List<QuejasYReclamosDTO> list = new ArrayList();
        for (QuejasYReclamosEntity entity : entityList) {
            list.add(new QuejasYReclamosDTO(entity));
        }
        return list;
    }

    
    /**
     * Convierte una lista de QuejasDetailDTO a una lista de QuejasEntity.
     *
     * @param dtos Lista de QuejasDetailDTO a convertir.
     * @return Lista de QuejasEntity convertida.
     */
    private List<QuejasYReclamosEntity> quejasListDTO2Entity(List<QuejasYReclamosDTO> dtos) {
        List<QuejasYReclamosEntity> list = new ArrayList<>();
        for (QuejasYReclamosDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    
    
}


