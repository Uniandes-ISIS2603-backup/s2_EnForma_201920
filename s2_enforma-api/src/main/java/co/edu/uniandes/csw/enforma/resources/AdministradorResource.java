/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

import co.edu.uniandes.csw.enforma.dtos.AdministradorDTO;
import co.edu.uniandes.csw.enforma.dtos.AdministradorDetailDTO;
import co.edu.uniandes.csw.enforma.ejb.AdministradorLogic;
import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
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
 * @administrador Elina Jaimes
 */

@Path("administradores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AdministradorResource {
    
     private static final Logger LOGGER = Logger.getLogger(AdministradorResource.class.getName());

    @Inject
    private AdministradorLogic administradorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * Crea un nuevo administradoristrador con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param administradoristrador {@link AdministradorDTO} - el administradoristrador que se desea
     * guardar.
     * @return JSON {@link AdministradorDTO} - el administradoristrador guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el administradoristrador.
     */
    @POST
    public AdministradorDTO createAdministrador(AdministradorDTO administradoristrador) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "AdministradorResource createAdministrador: input: {0}", administradoristrador);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        AdministradorEntity administradoristradorEntity = administradoristrador.toEntity();
        // Invoca la lógica para crear la editorial nueva
        AdministradorEntity nuevoAdministradorEntity = administradorLogic.crearAdministrador(administradoristradorEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        AdministradorDTO nuevoAdministradorDTO = new AdministradorDTO(nuevoAdministradorEntity);
        LOGGER.log(Level.INFO, "AdministradorResource createAdministrador: output: {0}", nuevoAdministradorDTO);
        return nuevoAdministradorDTO;
    }
    
     /**
     * Busca y devuelve todos los autores que existen en la aplicacion.
     *
     * @return JSONArray {@link AdministradorDetailDTO} - Los autores encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<AdministradorDetailDTO> getAdministradors() {
        LOGGER.info("AdministradorResource getAdministradors: input: void");
        List<AdministradorDetailDTO> listaAdministradors = listEntity2DTO(administradorLogic.getAdministradors());
        LOGGER.log(Level.INFO, "AdministradorResource getAdministradors: output: {0}", listaAdministradors);
        return listaAdministradors;
    }

    /**
     * Busca el autor con el id asociado recibido en la URL y lo devuelve.
     *
     * @param administradorsId Identificador del autor que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link AdministradorDetailDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{administradorsId: \\d+}")
    public AdministradorDetailDTO getAdministrador(@PathParam("administradorsId") Long administradorsId) {
        LOGGER.log(Level.INFO, "AdministradorResource getAdministrador: input: {0}", administradorsId);
        AdministradorEntity administradorEntity = administradorLogic.getAdministrador(administradorsId);
        if (administradorEntity == null) {
            throw new WebApplicationException("El recurso /administradors/" + administradorsId + " no existe.", 404);
        }
        AdministradorDetailDTO detailDTO = new AdministradorDetailDTO(administradorEntity);
        LOGGER.log(Level.INFO, "AdministradorResource getAdministrador: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza el autor con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param administradorsId Identificador del autor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param administrador {@link AdministradorDetailDTO} El autor que se desea guardar.
     * @return JSON {@link AdministradorDetailDTO} - El autor guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor a
     * actualizar.
     */
    @PUT
    @Path("{administradorsId: \\d+}")
    public AdministradorDetailDTO updateAdministrador(@PathParam("administradorsId") Long administradorsId, AdministradorDetailDTO administrador) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorResource updateAdministrador: input: administradorsId: {0} , administrador: {1}", new Object[]{administradorsId, administrador});
        administrador.setId(administradorsId);
        if (administradorLogic.getAdministrador(administradorsId) == null) {
            throw new WebApplicationException("El recurso /administradors/" + administradorsId + " no existe.", 404);
        }
        AdministradorDetailDTO detailDTO = new AdministradorDetailDTO(administradorLogic.updateAdministrador(administradorsId, administrador.toEntity()));
        LOGGER.log(Level.INFO, "AdministradorResource updateAdministrador: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el autor con el id asociado recibido en la URL.
     *
     * @param administradorsId Identificador del autor que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * si el autor tiene libros asociados
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor a borrar.
     */
    @DELETE
    @Path("{administradorsId: \\d+}")
    public void deleteAdministrador(@PathParam("administradorsId") Long administradorsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorResource deleteAdministrador: input: {0}", administradorsId);
        if (administradorLogic.getAdministrador(administradorsId) == null) {
            throw new WebApplicationException("El recurso /administradors/" + administradorsId + " no existe.", 404);
        }
        administradorLogic.deleteAdministrador(administradorsId);
        LOGGER.info("AdministradorResource deleteAdministrador: output: void");
    }
    
    
    
    /**
     * Conexión con el servicio de libros para una editorial.
     * {@link EditorialBooksResource}
     *
     * Este método conecta la ruta de /editorials con las rutas de /books que
     * dependen de la editorial, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga de los libros de una editorial.
     *
     * @param administradorsId El ID de la editorial con respecto a la cual se
     * accede al servicio.
     * @return El servicio de libros para esta editorial en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la editorial.
     */
    @Path("{administradorsId: \\d+}/dietas")
    public Class<AdministradorDietaTipoResource> getEditorialBooksResource(@PathParam("administradorsId") Long administradorsId) {
        if (administradorLogic.getAdministrador(administradorsId)==null) {
            throw new WebApplicationException("El recurso /administradores/" + administradorsId + " no existe.", 404);
        }
        return AdministradorDietaTipoResource.class;
    }
    
    
    private List<AdministradorDetailDTO> listEntity2DTO(List<AdministradorEntity> entityList) {
        List<AdministradorDetailDTO> list = new ArrayList<>();
        for (AdministradorEntity entity : entityList) {
            list.add(new AdministradorDetailDTO(entity));
        }
        return list;
    
}
    
}
