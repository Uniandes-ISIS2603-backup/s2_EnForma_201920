/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.CalificacionEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.enforma.persistence.ClientePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Jimmy Pepinosa
 */
@Stateless
public class CalificacionLogic 
{
    private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());
    
    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    //@Inject
    //private ClientePersistence clientePersistence;
    
    public CalificacionEntity createCalificacion(CalificacionEntity calificacion) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de creacion de la calificacion");
        //if(calificacion.getPuntaje() == null || clientePersistence.find(calificacion.getUsuario().getId())== null)
        if(calificacion.getPuntaje() == null )
        {
            throw new BusinessLogicException("El puntaje de la calificacion esta sin marcar");
        }
        
        calificacionPersistence.create(calificacion);
        LOGGER.log(Level.INFO, "Termian el proceso de creacion de la calificacion");
        return calificacion;
    }
    
    public List<CalificacionEntity> getCalificaciones()
    {
       LOGGER.log(Level.INFO,"Inicia proceso de consultar todas las calificaciones");
       List<CalificacionEntity> calificaciones = calificacionPersistence.findAll();
       LOGGER.log(Level.INFO, "Termina el proceso de consultar todas las calificaciones");
       return calificaciones;
    }
    
    public CalificacionEntity getCalificacion(Long calificacionId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0}", calificacionId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionId);
        if(calificacionEntity == null)
        {
            LOGGER.log(Level.SEVERE, "La calificacion con el id = {0} no existe", calificacionId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la caificacion con id = {0}", calificacionId);
        return calificacionEntity;
    }
    
    public CalificacionEntity updateCalificacion(CalificacionEntity calificacionEntity)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la calificacion con id = {0}", calificacionEntity.getId());
        CalificacionEntity newEntity  = calificacionPersistence.update(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la calificacion con id = {0}", calificacionEntity.getId());
        return newEntity;
    }
    
    public void deleteCalificacion(Long calificacionId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la calificacion con id = {0}", calificacionId);
        calificacionPersistence.delete(calificacionId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la calificacion con id = {0}", calificacionId);
    }
    
}
