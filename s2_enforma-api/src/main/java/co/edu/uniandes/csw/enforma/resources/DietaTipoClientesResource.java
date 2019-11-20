/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

import co.edu.uniandes.csw.enforma.ejb.ClienteLogic;
import co.edu.uniandes.csw.enforma.ejb.DietaTipoClientesLogic;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "dietas/{id}/clientes".
 * 
 * @author Julio Morales
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DietaTipoClientesResource {
    

    @Inject
    private DietaTipoClientesLogic dietaTipoClientesLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ClienteLogic clienteLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    
}
