/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.ClientePersistence;
import co.edu.uniandes.csw.enforma.persistence.DomicilioPersistence;
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
    
    @Inject
    private ClientePersistence clientePersistence;
    
    @Inject
    private DomicilioPersistence domicilioPersistence;
    
    public QuejasYReclamosEntity createQuejasYReclamos(Long clienteId, Long domicilioId, QuejasYReclamosEntity quejaReclamo) throws BusinessLogicException
    {
        if(quejaReclamo.getUsuario() == null)
        {
            throw new BusinessLogicException("El id del cliente que esta creando la calificacion no se encuentra");
        }
        if(quejaReclamo.getDomicilio() == null)
        {
            throw new BusinessLogicException("El id del domicilio que esta recibiendo la calificacion no se encuentra");
        }
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
        ClienteEntity cliente = clientePersistence.find(clienteId);
        DomicilioEntity domicilio = domicilioPersistence.find(domicilioId);
        quejaReclamo.setUsuario(cliente);
        quejaReclamo.setDomicilio(domicilio);
        return persistence.create(quejaReclamo);
    }
    
    public List<QuejasYReclamosEntity> getQuejasYReclamos()
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las quejas y reclamos");
        List<QuejasYReclamosEntity> quejasYReclamos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos las quejas y reclamos");
        return quejasYReclamos;
    }
    
    public QuejasYReclamosEntity getQuejaOReclamo(Long clienteId, Long domicilioId, Long quejaOReclamoId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la queja o reclamo con id = {0}, hecha por el cliente con id = " + clienteId + " a el domicilio con id = " + domicilioId, quejaOReclamoId);
        QuejasYReclamosEntity quejasYReclamosEntity = persistence.find(clienteId, domicilioId, quejaOReclamoId);
        if (quejasYReclamosEntity == null) 
        {
            LOGGER.log(Level.SEVERE, "La queja o reclamo con el id = {0} no existe", quejaOReclamoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la queja o reclamo con id = {0}, hecha por el cliente con id = " + clienteId + " a el domiciolio con id=" + domicilioId, quejaOReclamoId);
        return quejasYReclamosEntity;
    }
    
    public QuejasYReclamosEntity getQuejasYReclamosByClienteId(Long clienteId)
    {
       LOGGER.log(Level.INFO,"Inicia proceso de consultar todas las quejas y reclamos del cliente con id = {0}", clienteId);
       QuejasYReclamosEntity quejasYReclamos = persistence.findByClienteId(clienteId);
       LOGGER.log(Level.INFO, "Termina el proceso de consultar todas quejas y reclamos del cliente con id = {0}", clienteId);
       return quejasYReclamos; 
    }
    
    public QuejasYReclamosEntity getQuejasYReclamosByDomicilioId(Long domicilioId)
    {
       LOGGER.log(Level.INFO,"Inicia proceso de consultar todas las quejas y reclamos del domicilio con id = {0}", domicilioId);
       QuejasYReclamosEntity quejasYReclamos = persistence.findByDomicilioId(domicilioId);
       LOGGER.log(Level.INFO, "Termina el proceso de consultar todas quejas y reclamos del cliente con id = {0}", domicilioId);
       return quejasYReclamos; 
    }
    
    public QuejasYReclamosEntity updateQuejasYReclamos(Long clienteId,  Long domicilioId, QuejasYReclamosEntity quejasYReclamosEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la queja o reclamo con id = {0} del cliente con id = " + clienteId + " de el domicilio con id = "+ domicilioId, quejasYReclamosEntity.getId());
        ClienteEntity clienteEntity = clientePersistence.find(clienteId);
        if(clienteEntity == null)
        {
            throw new BusinessLogicException("El id del cliente que esta generando la calificacion no se encontro");
        }
        DomicilioEntity domicilioEntity = domicilioPersistence.find(domicilioId);
        if(domicilioEntity == null)
        {
            throw new BusinessLogicException("El id del domicilio que esta recibiendo la calificacion no se encontro");
        }
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
        quejasYReclamosEntity.setUsuario(clienteEntity);
        quejasYReclamosEntity.setDomicilio(domicilioEntity);
        QuejasYReclamosEntity newEntity = persistence.update(quejasYReclamosEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la queja o reclamo con id = {0} del cliente con id = " + clienteId + " de el domicilio con id = "+ domicilioId, quejasYReclamosEntity.getId());
        return newEntity;
    }
    
    public void deleteQuejasYReclamos(Long clienteId, Long domicilioId, Long quejasYReclamosId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la queja o reclamo con id = {0} hecha por el cliente con id = " + clienteId + " a el domicilio con id = " + domicilioId, quejasYReclamosId);
        QuejasYReclamosEntity old = getQuejaOReclamo(clienteId, domicilioId, quejasYReclamosId);
        if(old == null)
        {
            throw new BusinessLogicException("La queja o reclmo con id = " + quejasYReclamosId + " no esta asociada con el cliente con id = " + clienteId + " y/o con el domicilio con el id = " + domicilioId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el libro con id = {0} hecha por el cliente con id = " + clienteId + " a el domicilio con id = " + domicilioId, quejasYReclamosId);
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
