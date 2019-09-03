/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.QuejasYReclamosPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Jimmy Pepinosa
 */
@Stateless
public class QuejasYReclamosLogic 
{
    @Inject 
    QuejasYReclamosPersistence persistence;
    
    public QuejasYReclamosEntity createCalificacion(QuejasYReclamosEntity quejaReclamo) throws BusinessLogicException
    {
        if(quejaReclamo.getAsusnto() == null)
        {
            throw new BusinessLogicException("El asunto de la queja o reclamo esta vacio");
        }
        
        if(quejaReclamo.getDescripcion() == null)
        {
            throw new BusinessLogicException("La descripcion de la queja o reclamo esta vacia");
        }
        
        quejaReclamo = persistence.create(quejaReclamo);
        return quejaReclamo;
    }
}
