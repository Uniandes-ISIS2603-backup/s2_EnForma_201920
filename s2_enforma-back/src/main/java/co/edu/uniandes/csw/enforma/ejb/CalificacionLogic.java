/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.CalificacionEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.CalificacionPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Jimmy Pepinosa
 */
@Stateless
public class CalificacionLogic 
{
    @Inject
    private CalificacionPersistence persistence;
    
    public CalificacionEntity createCalificacion(CalificacionEntity calificacion) throws BusinessLogicException
    {
        if(calificacion.getPuntaje() == null)
        {
            throw new BusinessLogicException("El puntaje de la calificacion esta sin marcar");
        }
        
        calificacion = persistence.create(calificacion);
        return calificacion;
    }
}
