/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.DomicilioPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Sebastián Clavijo
 */
@Stateless
public class DomicilioLogic 
{
    @Inject
    private DomicilioPersistence persistence;
    
    public DomicilioEntity createDomicilio(DomicilioEntity domicilio) throws BusinessLogicException
    {
        if(domicilio.getLugarEntrega() == null)
            throw new BusinessLogicException("El lugar de entrega no puede ser nulo"); 
        
        domicilio = persistence.create(domicilio);
        return domicilio;
    }
}
