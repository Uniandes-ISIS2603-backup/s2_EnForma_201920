/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

import co.edu.uniandes.csw.enforma.dtos.AdministradorDTO;
import co.edu.uniandes.csw.enforma.ejb.AdministradorLogic;
import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Elina Jaimes
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
     * Crea un nuevo administrador con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param administrador {@link AdministradorDTO} - el administrador que se desea
     * guardar.
     * @return JSON {@link AdministradorDTO} - el administrador guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el administrador.
     */
    @POST
    public AdministradorDTO createAdministrador(AdministradorDTO administrador) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "AdministradorResource createAdministrador: input: {0}", administrador);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        AdministradorEntity administradorEntity = administrador.toEntity();
        // Invoca la lógica para crear la editorial nueva
        AdministradorEntity nuevoAdministradorEntity = administradorLogic.crearAdministrador(administradorEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        AdministradorDTO nuevoAdministradorDTO = new AdministradorDTO(nuevoAdministradorEntity);
        LOGGER.log(Level.INFO, "AdministradorResource createAdministrador: output: {0}", nuevoAdministradorDTO);
        return nuevoAdministradorDTO;
    }
    
}
