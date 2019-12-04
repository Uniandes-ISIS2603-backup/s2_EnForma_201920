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
    
    public QuejasYReclamosEntity createQuejasYReclamos(QuejasYReclamosEntity quejaReclamo) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de creacion de la queja o reclamo");
//        if(quejaReclamo.getCliente() == null)
//        {
//            throw new BusinessLogicException("El id del cliente que esta creando la calificacion no se encuentra");
//        }
//        if(quejaReclamo.getDomicilio() == null)
//        {
//            throw new BusinessLogicException("El id del domicilio que esta recibiendo la calificacion no se encuentra");
//        }
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
         LOGGER.log(Level.INFO, "Termina el proceso de creacion de la queja y reclamo");
        return persistence.create(quejaReclamo);
    }
    
    public QuejasYReclamosEntity createQuejasYReclamosByClienteIdYDomicilioId(Long clienteId, Long domicilioId, QuejasYReclamosEntity quejaReclamo) throws BusinessLogicException
    {
        if(quejaReclamo.getCliente() == null)
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
        quejaReclamo.setCliente(cliente);
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
    
    public QuejasYReclamosEntity getQuejaOReclamo(Long quejasYReclamosId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la queja o reclamo con id = {0}",quejasYReclamosId);
        QuejasYReclamosEntity quejasYReclamosEntity = persistence.find(quejasYReclamosId);
        if(quejasYReclamosEntity == null)
        {
            LOGGER.log(Level.INFO, "La queja o reclamo con el id = {0} no existe", quejasYReclamosId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la queja o reclamo con id = {0}", quejasYReclamosId);
        return quejasYReclamosEntity;
    }
    
    public QuejasYReclamosEntity updateQuejasYReclamos(Long quejasYReclamosId, QuejasYReclamosEntity quejasYReclamosEntity)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la queja o reclamo con id = {0}", quejasYReclamosId);
        QuejasYReclamosEntity newQuejasYReclamosEntity = persistence.update(quejasYReclamosEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la calificacion con id = {0}", quejasYReclamosId);
        return newQuejasYReclamosEntity;
    }
    public void deleteQuejasYReclamos(Long quejasYReclamosId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la queja o reclamo con id = {0}", quejasYReclamosId);
        persistence.delete(quejasYReclamosId);
    }
    
}
