/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

import co.edu.uniandes.csw.enforma.ejb.TarjetaPrepagoLogic;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Juan Sebastián Clavijo
 */
@Path("tarjetasprepago")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TarjetaPrepagoResource 
{
    private static final Logger LOGGER = Logger.getLogger(TarjetaPrepagoResource.class.getName());

    @Inject
    private TarjetaPrepagoLogic tarjetaPrepagoLogic;
    
}
