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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
}
