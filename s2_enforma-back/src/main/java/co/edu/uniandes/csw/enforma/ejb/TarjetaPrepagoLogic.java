/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.TarjetaPrepagoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.TarjetaPrepagoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Sebastián Clavijo
 */
@Stateless
public class TarjetaPrepagoLogic 
{
    @Inject
    private TarjetaPrepagoPersistence persistence;
    
    public TarjetaPrepagoEntity createTarjetaPrepago(TarjetaPrepagoEntity tarjetaPrepago) throws BusinessLogicException
    {
        if(tarjetaPrepago.getIdTarjetaPrepago() == null)
            throw new BusinessLogicException("El id de la tarjeta no pude ser vacío");
        else if(tarjetaPrepago.getSaldo() < 0)
            throw new BusinessLogicException("El saldo no puede ser menor a cero");
        else if(tarjetaPrepago.getPuntos() < 0)
            throw new BusinessLogicException("Los puntos no pueden ser negativos");
        
        tarjetaPrepago = persistence.create(tarjetaPrepago);
        return tarjetaPrepago;
    }
}
