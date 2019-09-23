/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.PagoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.PagoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Elina Jaimes
 */

 @Stateless
public class PagoLogic {
    
     //Inicializa cosas del container, nos provee los objetos, los inyecta.
     //Evitar dependencias, crear un objeto puede ser muy complejo
     //Buscamos no tener que hacerlo, inyectamos las dependencias.
     //Lo hace Payara
     
     private static final Logger LOGGER = Logger.getLogger(PagoLogic.class.getName());
     
     @Inject
     private PagoPersistence persistence;
     
     
     
     public PagoEntity crearPago(PagoEntity pago) throws BusinessLogicException
     {
         LOGGER.log(Level.INFO, "Inicia proceso de creación del pago");
         if(pago.getMonto()<=0)
         {
             throw new BusinessLogicException("El valor del pago es inválido");
         }
         if(pago.getNumeroTarjeta()==null || pago.getNumeroTarjeta()<=0)
         {
             throw new BusinessLogicException("El numero de Tarjeta del pago es inválido");
         }
         pago=persistence.create(pago);
          LOGGER.log(Level.INFO, "Termina proceso de creación del pago");
         return pago;
     } 
     
     
     public List<PagoEntity> getPagos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los pagos");
        List<PagoEntity> pagos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los pagos");
        return pagos;
    }

    /**
     * Busca un pago por ID
     *
     * @param pagosId El id del pago a buscar
     * @return El pago encontrado, null si no lo encuentra.
     */
    public PagoEntity getPago(Long pagosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el pago con id = {0}", pagosId);
        PagoEntity pagoEntity = persistence.find(pagosId);
        if (pagoEntity == null) {
            LOGGER.log(Level.SEVERE, "El pago con el id = {0} no existe", pagosId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el pago con id = {0}", pagosId);
        return pagoEntity;
    }

    /**
     * Actualizar un pago por ID
     *
     * @param pagosId El ID del pago a actualizar
     * @param pagoEntity La entidad del pago con los cambios deseados
     * @return La entidad del pago luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public PagoEntity updatePago(Long pagosId, PagoEntity pagoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el pago con id = {0}", pagosId);
        
        if(pagoEntity.getMonto()<=0 || pagoEntity.getNumeroTarjeta()==0 || pagoEntity.getNumeroTarjeta()==null)
        {
            throw new BusinessLogicException("Error para actualizar el pago");
        }
        PagoEntity newEntity = persistence.update(pagoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el pago con id = {0}", pagoEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar un pago por ID
     *
     * @param pagosId El ID del pago a eliminar
     * @throws BusinessLogicException si el pago tiene autores asociados
     */
    public void deletePago(Long pagosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el pago con id = {0}", pagosId);
        persistence.delete(pagosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el pago con id = {0}", pagosId);
    }
     
     
    
}
