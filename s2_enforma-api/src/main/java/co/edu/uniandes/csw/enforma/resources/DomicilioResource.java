/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

import co.edu.uniandes.csw.enforma.dtos.DomicilioDTO;
import co.edu.uniandes.csw.enforma.ejb.DomicilioLogic;
import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
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
import static javax.ws.rs.HttpMethod.GET;
import static javax.ws.rs.HttpMethod.POST;
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
@Path("domicilios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class DomicilioResource 
{
    private static final Logger LOGGER = Logger.getLogger(DomicilioResource.class.getName());

    @Inject
    private DomicilioLogic domicilioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * Crea un nuevo domicilio con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param domicilio {@link DomicilioDTO} - el domicilio que se desea
     * guardar.
     * @return JSON {@link DomicilioDTO} - el domicilio guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el domicilio.
     */
    @POST
    public DomicilioDTO createDomicilio(DomicilioDTO domicilio) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "DomicilioResource createDomicilio: input: {0}", domicilio);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        DomicilioEntity domicilioEntity = domicilio.toEntity();
        // Invoca la lógica para crear la editorial nueva
        DomicilioEntity nuevoDomicilioEntity = domicilioLogic.createDomicilio(domicilioEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        DomicilioDTO nuevoDomicilioDTO = new DomicilioDTO(nuevoDomicilioEntity);
        LOGGER.log(Level.INFO, "DomicilioResource createDomicilio: output: {0}", nuevoDomicilioDTO);
        return nuevoDomicilioDTO;
    }
    
     /**
     * Busca y devuelve todos los domicilios que existen.
     *
     * @return JSONArray {@link DomicilioDTO} - Las reseñas encontradas en el
     * libro. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<DomicilioDTO> getDomicilios() 
    {
        LOGGER.log(Level.INFO, "DomicilioResource getDomicilios");
        List<DomicilioDTO> listaDTOs = listEntity2DTO(domicilioLogic.getDomicilios());
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDTOs);
        return listaDTOs;
    }
    
     /**
     * Busca y devuelve el domicilio con el ID recibido en la URL, relativa a un
     * domicilio.
     *
     * @param domicilioId El ID del domicilio que se busca
     * @return {@link DomicilioDTO} - El domicilio encontrado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el domicilio.
     */
    @GET
    @Path("{domicilioId: \\d+}")
    public DomicilioDTO getDomicilio(@PathParam("booksId") Long domicilioId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "DomicilioResource getDomicilio: input: {0}", domicilioId);
        DomicilioEntity entity = domicilioLogic.getDomicilio(domicilioId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /domicilio/" + domicilioId + " no existe.", 404);
        }
        DomicilioDTO domicilioDTO = new DomicilioDTO(entity);
        LOGGER.log(Level.INFO, "DomicilioResource getDomicilio: output: {0}", domicilioDTO);
        return domicilioDTO;
    }
    
     /**
     * Actualiza un domicilio con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param domicilioId El ID del domicilio que se va a actualizar
     * @param domicilio {@link DomicilioDTO} - el domicilio que se desea guardar.
     * @return JSON {@link DomicilioDTO} - El domicilio actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el domicilio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el domicilio.
     */
    @PUT
    @Path("{domicilioId: \\d+}")
    public DomicilioDTO updateDomicilio(@PathParam("domicilioId") Long domicilioId, DomicilioDTO domicilio) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "DomicilioResource updateDomicilio: input: domicilioId: {0} , domicilio:{1}", new Object[]{domicilioId, domicilio});
        if (domicilioId.equals(domicilio.getId())) 
        {
            throw new BusinessLogicException("Los ids del domicilio no coinciden.");
        }
        DomicilioEntity entity = domicilioLogic.getDomicilio(domicilioId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /domicilio/" + domicilioId + " no existe.", 404);

        }
        DomicilioDTO domicilioDTO = new DomicilioDTO(domicilioLogic.updateDomicilio(domicilioId, domicilio.toEntity())); 
        LOGGER.log(Level.INFO, "DomicilioResource updateDomicilio: output:{0}", domicilioDTO);
        return domicilioDTO;

    }
    
     /**
     * Borra el domicilio con el id asociado recibido en la URL.
     *
     * @param domicilioId El ID del domicilio que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el domicilio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra lel domicilio.
     */
    @DELETE
    @Path("{domicilioId: \\d+}")
    public void deleteDomicilio(@PathParam("domicilioId") Long domicilioId) throws BusinessLogicException 
    {
        DomicilioEntity entity = domicilioLogic.getDomicilio(domicilioId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /domicilio/" + domicilioId + " no existe.", 404);
        }
        domicilioLogic.deleteDomicilio(domicilioId);
    }
    
    
     /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos DomicilioEntity a una lista de
     * objetos DomicilioDTO (json)
     *
     * @param entityList corresponde a la lista de reseñas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de reseñas en forma DTO (json)
     */
    private List<DomicilioDTO> listEntity2DTO(List<DomicilioEntity> entityList) {
        List<DomicilioDTO> list = new ArrayList<DomicilioDTO>();
        for (DomicilioEntity entity : entityList) {
            list.add(new DomicilioDTO(entity));
        }
        return list;
    }
}
