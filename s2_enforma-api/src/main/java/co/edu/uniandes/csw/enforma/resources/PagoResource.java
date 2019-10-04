/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

import co.edu.uniandes.csw.enforma.dtos.PagoDTO;
import co.edu.uniandes.csw.enforma.ejb.PagoLogic;
import co.edu.uniandes.csw.enforma.entities.PagoEntity;
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

@Path("pagos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PagoResource {
    
    private static final Logger LOGGER = Logger.getLogger(PagoResource.class.getName());

    @Inject
    private PagoLogic pagoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * Crea un nuevo pago con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param pago {@link PagoDTO} - el pago que se desea
     * guardar.
     * @return JSON {@link PagoDTO} - el pago guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el pago.
     */
    @POST
    public PagoDTO createPago(PagoDTO pago) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "PagoResource createPago: input: {0}", pago);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        PagoEntity pagoEntity = pago.toEntity();
        // Invoca la lógica para crear la editorial nueva
        PagoEntity nuevoPagoEntity = pagoLogic.crearPago(pagoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        PagoDTO nuevoPagoDTO = new PagoDTO(nuevoPagoEntity);
        LOGGER.log(Level.INFO, "PagoResource createPago: output: {0}", nuevoPagoDTO);
        return nuevoPagoDTO;
    }
    
}
