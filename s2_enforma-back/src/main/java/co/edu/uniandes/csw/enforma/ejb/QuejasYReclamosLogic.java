/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.QuejasYReclamosPersistence;
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
public class QuejasYReclamosLogic 
{
    private static Logger LOGGER = Logger.getLogger(QuejasYReclamosLogic.class.getName());
    
    @Inject 
    private QuejasYReclamosPersistence persistence;
    
    public QuejasYReclamosEntity createQuejasYReclamos(QuejasYReclamosEntity quejaReclamo) throws BusinessLogicException
    {
        if(quejaReclamo.getAsunto() == null)
        {
            throw new BusinessLogicException("El asunto de la queja o reclamo esta vacio");
        }
        
        if(quejaReclamo.getDescripcion() == null)
        {
            throw new BusinessLogicException("La descripcion de la queja o reclamo esta vacia");
        }
        
        if(quejaReclamo.getFecha() == null)
        {
            throw new BusinessLogicException("La fecha de la queja o reclamo esta vacia");
        }
        
        quejaReclamo = persistence.create(quejaReclamo);
        return quejaReclamo;
    }
    
    public List<QuejasYReclamosEntity> getQuejasYReclamos()
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las quejas y reclamos");
        List<QuejasYReclamosEntity> quejasYReclamos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos las quejas y reclamos");
        return quejasYReclamos;
    }
    
    public QuejasYReclamosEntity getQuejaOReclamo(Long quejaOReclamoId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la queja o reclamo con id = {0}", quejaOReclamoId);
        QuejasYReclamosEntity quejasYReclamosEntity = persistence.find(quejaOReclamoId);
        if (quejasYReclamosEntity == null) 
        {
            LOGGER.log(Level.SEVERE, "La queja o reclamo con el id = {0} no existe", quejaOReclamoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la queja o reclamo = {0}", quejaOReclamoId);
        return quejasYReclamosEntity;
    }
    
    public QuejasYReclamosEntity updateQuejasYReclamos(QuejasYReclamosEntity quejasYReclamosEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la queja o reclamo con id = {0}", quejasYReclamosEntity.getId());
        if(!validateAsunto(quejasYReclamosEntity.getAsunto()))
        {
            throw new BusinessLogicException("El asunto es invalido");
        }
        if(!validateDescripcion(quejasYReclamosEntity.getDescripcion()))
        {
            throw new BusinessLogicException("La descripcion es invalida");
        }
        if(!validateFecha(quejasYReclamosEntity.getFecha()))
        {
            throw new BusinessLogicException("La fecha es invalida");
        }
        QuejasYReclamosEntity newEntity = persistence.update(quejasYReclamosEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la queja o reclamo con id = {0}", quejasYReclamosEntity.getId());
        return newEntity;
    }
    
    public void deleteQuejasYreclamos(Long quejasYReclamosId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la queja o reclamo con id = {0}", quejasYReclamosId);
        persistence.delete(quejasYReclamosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el libro con id = {0}", quejasYReclamosId);
    }
    
    private boolean validateAsunto(String asunto)
    {
        return!(asunto == null || asunto.isEmpty());
    }
    
    private boolean validateDescripcion(String descripcion)
    {
        return!(descripcion == null || descripcion.isEmpty());
    }
    
    private boolean validateFecha(Date fecha)
    {
        return !(fecha == null || fecha.toString().isEmpty());
    }
}
