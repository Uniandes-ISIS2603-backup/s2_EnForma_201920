/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.TarjetaPrepagoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
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
public class TarjetaPrepagoLogic 
{
    private static final Logger LOGGER = Logger.getLogger(TarjetaPrepagoLogic.class.getName());
    
    @Inject
    private TarjetaPrepagoPersistence persistence;
    
     /**
     * Guardar una nueva tarjeta prepago
     *
     * @param tarjetaPrepago La entidad de tipo tarjetaPrepago de la nueva tarjeta a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el id es inválido o ya existe en la
     * persistencia. al igual con el saldo y los puntos.
     */
    public TarjetaPrepagoEntity createTarjetaPrepago(TarjetaPrepagoEntity tarjetaPrepago) throws BusinessLogicException
    {
        if(tarjetaPrepago.getId() == null)
            throw new BusinessLogicException("El id de la tarjeta no pude ser vacío");
        else if(tarjetaPrepago.getSaldo() < 0)
            throw new BusinessLogicException("El saldo no puede ser menor a cero");
        else if(tarjetaPrepago.getPuntos() < 0)
            throw new BusinessLogicException("Los puntos no pueden ser negativos");
        else if(tarjetaPrepago.getIdTarjetaPrepago() == null)
            throw new BusinessLogicException("El numero de la tarjeta no puede ser nulo");
        else if(tarjetaPrepago.getIdTarjetaPrepago().equals(""))
            throw new BusinessLogicException("El numero de la tarjeta no puede ser vacío");
        
        
        tarjetaPrepago = persistence.create(tarjetaPrepago);
        return tarjetaPrepago;
    }
    
     /**
     * Devuelve todas las tarjetas que hay en la base de datos.
     *
     * @return Lista de entidades de tipo tarjetaPrepago.
     */
    public List<TarjetaPrepagoEntity> getTarjetasPrepago() 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las tarjetas prepago");
        List<TarjetaPrepagoEntity> tarjetas = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las tarjetas prepago");
        return tarjetas;
    }
    
     /**
     * Busca una tarjeta prepago por ID
     *
     * @param tpId El id de la tarjeta a buscar
     * @return la tarjeta encontrada, null si no la encuentra.
     */
    public TarjetaPrepagoEntity getTarjetaPrepago(Long tpId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la tarjeta prepago con id = {0}", tpId);
        TarjetaPrepagoEntity tarjetaPrepagoEntity = persistence.find(tpId);
        if (tarjetaPrepagoEntity == null) {
            LOGGER.log(Level.SEVERE, "La tarjeta prepago con el id = {0} no existe", tpId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la tarjeta prepago con id = {0}",tpId);
        return tarjetaPrepagoEntity;
    }
    
     /**
     * Actualizar una tarjeta prepago por ID
     *
     * @param tpId El ID de la tarjeta prepago a actualizar
     * @param tarjetaPrepagoEntity La entidad de la tarjeta prepago con los cambios deseados
     * @return La entidad de la tarjeta prepago luego de actualizarla
     * @throws BusinessLogicException Si el saldo o los puntos son negativos
     */
    public TarjetaPrepagoEntity updateTarjetaPrepago(Long tpId, TarjetaPrepagoEntity tarjetaPrepagoEntity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la tarjeta prepago con id = {0}", tpId);
        if (!validateSALDO(tarjetaPrepagoEntity.getSaldo())) 
        {
            throw new BusinessLogicException("El saldo no puede ser negativo");
        }
        else if(!validatePUNTOS(tarjetaPrepagoEntity.getPuntos()))
        {
            throw new BusinessLogicException("Los puntos no puede ser negativos");
        }
        TarjetaPrepagoEntity newEntity = persistence.update(tarjetaPrepagoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la tarjeta prepago con id = {0}", tarjetaPrepagoEntity.getId());
        return newEntity;
    }
    
     /**
     * Eliminar una tarjeta prepago por ID
     *
     * @param tpId El ID de la tarjeta a eliminar
     * @throws BusinessLogicException si la tarjeta tiene saldo disponible
     */
    public void deletetarjetaPrepago(Long tpId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la tarjeta prepago con id = {0}", tpId);
        TarjetaPrepagoEntity tp = getTarjetaPrepago(tpId);
        if (tp.getSaldo() > 0 ) 
        {
            throw new BusinessLogicException("No se puede borrar la tarjeta prepago con id = " + tpId + " porque tiene saldo mayor a 0");
        }
        persistence.delete(tpId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la tarjeta prepago con id = {0}", tpId);
    }
    
     /**
     * Verifica que el saldo no sea invalida.
     *
     * @param saldo a verificar
     * @return true si el precio es valido.
     */
    private boolean validateSALDO(double saldo) 
    {
        return !(saldo < 0);
    }
    
     /**
     * Verifica que los puntos no sean invalidos.
     *
     * @param puntos a verificar
     * @return true si el precio es valido.
     */
    private boolean validatePUNTOS(double puntos) 
    {
        return !(puntos < 0);
    }
}
