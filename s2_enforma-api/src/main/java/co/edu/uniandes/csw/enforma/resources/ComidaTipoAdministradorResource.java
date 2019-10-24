/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

//import co.edu.uniandes.csw.enforma.dtos.AdministradorDTO;
import co.edu.uniandes.csw.enforma.dtos.ComidaTipoDTO;
import co.edu.uniandes.csw.enforma.ejb.AdministradorLogic;
import co.edu.uniandes.csw.enforma.ejb.ComidaTipoAdministradorLogic;
import co.edu.uniandes.csw.enforma.ejb.ComidaTipoLogic;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jose Manuel Flórez
 */

@Path("ComidasTipo/{comidaTipoId: \\d+}/administrador")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComidaTipoAdministradorResource 
{
     private static final Logger LOGGER = Logger.getLogger(ComidaTipoAdministradorResource.class.getName());

    @Inject
    private ComidaTipoLogic comidaTipoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ComidaTipoAdministradorLogic comidaTipoAdministradorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private AdministradorLogic administradorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

//   /**
//     * Crea una nueva reseña con la informacion que se recibe en el cuerpo de la
//     * petición y se regresa un objeto identico con un id auto-generado por la
//     * base de datos.
//     *
//     * @param administradorId El ID del libro del cual se le agrega la reseña
//     * @param comidaTipoDTO {@link ComidaTipoTO} - La reseña que se desea guardar.
//     * @return JSON {@link ReviewDTO} - La reseña guardada con el atributo id
//     * autogenerado.
//     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
//     * Error de lógica que se genera cuando ya existe la reseña.
//     */
//    @POST
//    public ComidaTipoDTO createComidaTipo(@PathParam("administradoresId") Long administradorId, ComidaTipoDTO comidaTipoDTO) throws BusinessLogicException {
//        LOGGER.log(Level.INFO, "ReviewResource createReview: input: {0}", comidaTipoDTO);
//        ComidaTipoDTO nuevoReviewDTO = new ComidaTipoDTO(comidaTipoAdministradorLogic.createComidaTipoAdmin(administradorId, comidaTipoDTO.toEntity()));
//        LOGGER.log(Level.INFO, "ReviewResource createReview: output: {0}", nuevoReviewDTO);
//        return nuevoReviewDTO;
//    }

    
    
}
