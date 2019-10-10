/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;


import co.edu.uniandes.csw.enforma.dtos.ComidaTipoDTO;
import co.edu.uniandes.csw.enforma.dtos.DietaTipoDTO;


import co.edu.uniandes.csw.enforma.ejb.ComidaTipoDietaTipoLogic;
import co.edu.uniandes.csw.enforma.ejb.ComidaTipoLogic;
import co.edu.uniandes.csw.enforma.ejb.DietaTipoLogic;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jose Manuel Flórez
 */

@Path("ComidasTipo/{comidaTipoId: \\d+}/dietaTipo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComidaTipoDietaTipoResource 
{
     private static final Logger LOGGER = Logger.getLogger(ComidaTipoDietaTipoResource.class.getName());

    @Inject
    private ComidaTipoLogic comidaTipoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ComidaTipoDietaTipoLogic comidaTipoDietaTipoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private DietaTipoLogic dietaTipoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    
    
    
    /**
     * Remplaza la instancia de Editorial asociada a un Book.
     *
     * @param comidaTipoId Identificador del libro que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param dietaTipo La dietaTipo que se será del libro.
     * @return JSON {@link ComidaTipoDTO} - El arreglo de libros guardado en la
     * dietaTipo.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la dietaTipo o el
     * libro.
     */
    @PUT
    public ComidaTipoDTO replaceDietaTipo(@PathParam("booksId") Long comidaTipoId, DietaTipoDTO dietaTipo) {
        LOGGER.log(Level.INFO, "ComidatipoDietaTipoResource replaceDietaTipo: input: comidaTipoId{0} , DietaTipo:{1}", new Object[]{comidaTipoId, dietaTipo});
        if (comidaTipoLogic.getComidaTipo(comidaTipoId) == null) {
            throw new WebApplicationException("El recurso /ComidasTipo/" + comidaTipoId + " no existe.", 404);
        }
        if (dietaTipoLogic.getDietaTipo(dietaTipo.getId()) == null) {
            throw new WebApplicationException("El recurso /dietas/" + dietaTipo.getId() + " no existe.", 404);
        }
        ComidaTipoDTO comidaTipoDTO = new ComidaTipoDTO(comidaTipoDietaTipoLogic.replaceDietaTipo(comidaTipoId, dietaTipo.getId()));
        LOGGER.log(Level.INFO, "ComdiaTipoDietaTipoResource replaceDietaTipo: output: {0}", comidaTipoDTO);
        return comidaTipoDTO;
    }
    
    
    
    
    
    
    
    
}
