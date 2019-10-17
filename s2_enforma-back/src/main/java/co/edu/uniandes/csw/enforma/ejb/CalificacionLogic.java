/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.CalificacionEntity;
import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.enforma.persistence.ClientePersistence;
import co.edu.uniandes.csw.enforma.persistence.DietaTipoPersistence;
import java.util.Date;
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
    
    @Inject
    private ClientePersistence clientePersistence;
    
    @Inject
    private DietaTipoPersistence dietaPersistence;
    
    public CalificacionEntity createCalificacion( CalificacionEntity calificacion) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de creacion de la calificacion");

        if(calificacion.getCliente() == null || clientePersistence.find(calificacion.getCliente().getId()) == null)
        {
            throw new BusinessLogicException("El id del cliente que esta creando la calificacion no se encuentra");
        }
        if(calificacion.getDietaTipo()== null || dietaPersistence.find(calificacion.getDietaTipo().getId()) == null)
        {
            throw new BusinessLogicException("El id de la dieta que se esta calificando no se encuentra");
        }
        if(calificacion.getPuntaje() == null )
        {
            throw new BusinessLogicException("El puntaje de la calificacion esta sin marcar");
        }
        if(calificacion.getFecha() == null )
        {
            throw new BusinessLogicException("La fecha de la calificacion es null");
        }

        LOGGER.log(Level.INFO, "Termian el proceso de creacion de la calificacion");
        return calificacionPersistence.create(calificacion);
    }
    
    public List<CalificacionEntity> getCalificaciones()
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las calificaciones");
        List<CalificacionEntity> calificaciones = calificacionPersistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos las calificaciones");
        return calificaciones;
    }
    
    public CalificacionEntity getCalificacion(Long calificacionId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la calificacion con id = {0}",calificacionId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionId);
        if(calificacionEntity == null)
        {
            LOGGER.log(Level.INFO, "La calificacion con el id = {0} no existe", calificacionId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la caificacion con id = {0}", calificacionId);
        return calificacionEntity;
    }
    
    public CalificacionEntity getCalificacionesByDietaId(Long dietaId)
    {
       LOGGER.log(Level.INFO,"Inicia proceso de consultar todas las calificaciones de la dieta con id = {0}", dietaId);
       CalificacionEntity dieta = calificacionPersistence.findByDietaTipoId(dietaId);
       LOGGER.log(Level.INFO, "Termina el proceso de consultar todas las calificaciones de la dieta con id = {0}", dietaId);
       return dieta;
    }
    
    public CalificacionEntity getCalificacionByClienteIdYDietaTipoId(Long clienteId, Long dietaId, Long calificacionId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la calificacion con id = {0}, hecha por el cliente con id = " + clienteId + " a la dieta con id = " + dietaId, calificacionId);
        CalificacionEntity calificacionEntity = calificacionPersistence.findByClienteYDietaTipo(clienteId, dietaId, calificacionId);
        if(calificacionEntity == null)
        {
            LOGGER.log(Level.SEVERE, "La calificacion con el id = {0} no existe", calificacionId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la caificacion con id = {0}", calificacionId);
        return calificacionEntity;
    }
    
    public CalificacionEntity updateCalificacion(Long calificacionId, CalificacionEntity calificacionEntity)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la calificacion con id = {0}", calificacionId);
        CalificacionEntity newCalificacionEntity = calificacionPersistence.update(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la calificacion con id = {0}", calificacionId);
        return newCalificacionEntity;
    }
    
    public CalificacionEntity updateCalificacionByClienteIdYDietaTipoId(Long clienteId, Long dietaId, CalificacionEntity calificacionEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la calificacion con id = {0} del cliente con id = " + clienteId + " a la dieta con id = " + dietaId, calificacionEntity.getId());
        
        ClienteEntity clienteEntity = clientePersistence.find(clienteId);
        if(clienteEntity == null)
        {
            throw new BusinessLogicException("El id del cliente que esta generando la calificacion no se encontro");
        }
        DietaTipoEntity dietaEntity = dietaPersistence.find(dietaId);
        if(dietaEntity == null)
        {
            throw new BusinessLogicException("El id de la dieta que contiene la calificacion no se encontro");
        }
        if(!validateFecha(calificacionEntity.getFecha()))
        {
            throw new BusinessLogicException("La fecha es invalida");
        }
        calificacionEntity.setCliente(clienteEntity);
        calificacionEntity.setDietaTipo(dietaEntity);
        CalificacionEntity newEntity  = calificacionPersistence.update(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la calificacion con id = {0} del cliente con id = " + clienteId + " a la dieta con id = " + dietaId, calificacionEntity.getId());
        return newEntity;
    }
    
    public void deleteCalificacion(Long calificacionId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la calificacion con id = {0}", calificacionId);
        calificacionPersistence.delete(calificacionId);
    }
    
    public void deleteCalificacionByClienteIdYDietaTipoId(Long clienteId, Long dietaId, Long calificacionId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la calificacion con id = {0} hecha por el cliente con id = " + clienteId + " a la dieta con id = " + dietaId, calificacionId);
        CalificacionEntity old = getCalificacionByClienteIdYDietaTipoId(clienteId, dietaId, calificacionId);
        if(old == null)
        {
            throw new BusinessLogicException("La calificacion con id = " + calificacionId + " no esta asociada con el cliente con id = " + clienteId + " y/o con la dieta con id = " + dietaId);
        }
        calificacionPersistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la calificacion con id = {0} hecha por el cliente con id = " + clienteId + " a la dieta con id = " + dietaId, calificacionId);
    }
    
    private boolean validateFecha(Date fecha)
    {
        return !(fecha == null || fecha.toString().isEmpty());
    }
    
}
