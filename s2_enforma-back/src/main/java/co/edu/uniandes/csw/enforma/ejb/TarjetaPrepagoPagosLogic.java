/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.PagoEntity;
import co.edu.uniandes.csw.enforma.entities.TarjetaPrepagoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.PagoPersistence;
import co.edu.uniandes.csw.enforma.persistence.TarjetaPrepagoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Sebastián Clavijo
 */
@Stateless
public class TarjetaPrepagoPagosLogic 
{
    private static final Logger LOGGER = Logger.getLogger(TarjetaPrepagoPagosLogic.class.getName());
    
    @Inject
    private PagoPersistence pagoPersistence;
    
    @Inject
    private TarjetaPrepagoPersistence tarjetaPrepagoPersistence;
    
     /**
     * Agregar un pago a la tarjeta prepago
     *
     * @param pagosId El id del pago a guardar
     * @param tarjetasId El id de la tarjeta en la cual se va a guardar el 
     * libro.
     * @return El libro creado.
     */
    public PagoEntity addPago(Long pagosId, Long tarjetasId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un pago a la tarjeta prepago con id = {0}", tarjetasId);
        TarjetaPrepagoEntity tarjetaPrepagoEntity = tarjetaPrepagoPersistence.find(tarjetasId);
        PagoEntity pagoEntity = pagoPersistence.find(pagosId);
        pagoEntity.setTarjetaPrepago(tarjetaPrepagoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un pago a la tarjeta prepago con id = {0}", tarjetasId);
        return pagoEntity;
    }
    
     /**
     * Retorna todos los pagos asociados a una tarjeta prepago
     *
     * @param tarjetasId El ID de la tarjeta buscada
     * @return La lista de pagos de la tarjeta
     */
    public List<PagoEntity> getPagos(Long tarjetasId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los pagos asociados a la tarjeta prepago con id = {0}", tarjetasId);
        return tarjetaPrepagoPersistence.find(tarjetasId).getPagos();
    }
    
     /**
     * Retorna un pago asociado a una tarjeta prepago
     *
     * @param tarjetasId El id de la tarjeta a buscar.
     * @param pagosId El id del pago a buscar
     * @return El pago encontrado dentro de la tarjeta.
     * @throws BusinessLogicException Si el pago no se encuentra en la
     * tarjeta
     */
    public PagoEntity getPago(Long tarjetasId, Long pagosId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el pago con id = {1} de la tarjeta con id = {0} ", new Object[]{tarjetasId, pagosId});
        List<PagoEntity> pagos = tarjetaPrepagoPersistence.find(tarjetasId).getPagos();
        PagoEntity pagoEntity = pagoPersistence.find(pagosId);
        int index = pagos.indexOf(pagoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el pago con id = {1} de la tarjeta con id = {0} ", new Object[]{tarjetasId, pagosId});
        if (index >= 0) 
        {
            return pagos.get(index);
        }
        throw new BusinessLogicException("El pago no está asociado a la tarjeta prepago");
    }
    
     /**
     * Remplazar pagos de una tarjeta prepago
     *
     * @param pagos Lista de pagos que serán los de la tarjeta.
     * @param tarjetasId El id de la tarjeta que se quiere actualizar.
     * @return La lista de pagos actualizada.
     */
    public List<PagoEntity> replacePagos(Long tarjetasId, List<PagoEntity> pagos) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la tarjeta prepago con id = {0}", tarjetasId);
        TarjetaPrepagoEntity tpEntity = tarjetaPrepagoPersistence.find(tarjetasId);
        List<PagoEntity> pagoList = pagoPersistence.findAll();
        for (PagoEntity pago : pagoList) 
        {
            if (pagos.contains(pago)) 
            {
                pago.setTarjetaPrepago(tpEntity);
            } 
            else if (pago.getTarjetaPrepago()!= null && pago.getTarjetaPrepago().equals(tpEntity)) 
            {
                pago.setTarjetaPrepago(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la tarjeta prepago con id = {0}", tarjetasId);
        return pagos;
    }
    
}
