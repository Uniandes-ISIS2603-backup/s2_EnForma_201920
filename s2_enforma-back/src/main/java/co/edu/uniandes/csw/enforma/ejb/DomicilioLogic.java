/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.DomicilioPersistence;
import java.util.Date;
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
public class DomicilioLogic 
{
    private static final Logger LOGGER = Logger.getLogger(DomicilioLogic.class.getName());
    
    @Inject
    private DomicilioPersistence persistence;
    
     /**
     * Guardar un nuevo domicilio
     *
     * @param domicilio La entidad de tipo domicilio del nuevo domicilio a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el el lugar de entrega es inválido o ya existe en la
     * persistencia, lo mismo con el costo y la fecha.
     */
    public DomicilioEntity createDomicilio(DomicilioEntity domicilio) throws BusinessLogicException
    {
        if(domicilio.getLugarEntrega() == null)
            throw new BusinessLogicException("El lugar de entrega no puede ser nulo"); 
        else if(domicilio.getCosto() < 0)
            throw new BusinessLogicException("El costo no puede ser negativo");
        else if(domicilio.getFecha() == null)
            throw new BusinessLogicException("La fecha no puede ser invalida");
        domicilio = persistence.create(domicilio);
        return domicilio;
    }
    
     /**
     * Devuelve todos los domicilios que hay en la base de datos.
     *
     * @return Lista de entidades de tipo domicilio.
     */
    public List<DomicilioEntity> getDomicilios() 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los domicilios");
        List<DomicilioEntity> domicilios = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los domicilios");
        return domicilios;
    }
    
     /**
     * Busca un domicilio por ID
     *
     * @param domicilioId El id del domicilio a buscar
     * @return El domicilio encontrado, null si no lo encuentra.
     */
    public DomicilioEntity getDomicilio(Long domicilioId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el domicilio con id = {0}", domicilioId);
        DomicilioEntity domicilioEntity = persistence.find(domicilioId);
        if (domicilioEntity == null) {
            LOGGER.log(Level.SEVERE, "El domicilio con el id = {0} no existe", domicilioId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el domicilio con id = {0}",domicilioId);
        return domicilioEntity;
    }
    
     /**
     * Actualizar un domicilio por ID
     *
     * @param domId El ID del domicilio a actualizar
     * @param domEntity La entidad del domicilio con los cambios deseados
     * @return La entidad del domicilio luego de actualizarla
     * @throws BusinessLogicException Si la fecha o el precio es invalido de la actualización es inválido
     */
    public DomicilioEntity updateDomicilio(Long domId, DomicilioEntity domEntity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el domicilio con id = {0}", domId);
        if (!validateFECHA(domEntity.getFecha())) 
        {
            throw new BusinessLogicException("La fecha es inválida");
        }
        else if(!validatePRECIO(domEntity.getCosto()))
        {
            throw new BusinessLogicException("El precio no puede ser negativo");
        }
        else if(!validateLUGAR(domEntity.getLugarEntrega()))
        {
            throw new BusinessLogicException("El lugar de entrega no pude ser invalido");
        }
        DomicilioEntity newEntity = persistence.update(domEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el domicilio con id = {0}", domEntity.getId());
        return newEntity;
    }
    
     /**
     * Eliminar un domicilio por ID
     *
     * @param dId El ID del domicilio a eliminar
     * @throws BusinessLogicException si el domicilio tiene fecha invalida
     */
    public void deleteDomicilio(Long dId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el domicilio con id = {0}", dId);
        DomicilioEntity dp = getDomicilio(dId);

        persistence.delete(dId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el domicilio con id = {0}", dId);
    }
    
    
     /**
     * Verifica que la fecha no sea invalida.
     *
     * @param fecha a verificar
     * @return true si la fecha es valido.
     */
    private boolean validateFECHA(Date fecha) 
    {
        return !(fecha == null || fecha.toString().isEmpty());
    }
    
     /**
     * Verifica que el precio no sea invalida.
     *
     * @param precio a verificar
     * @return true si el precio es valido.
     */
    private boolean validatePRECIO(double precio) 
    {
        return !(precio < 0);
    }
    
     /**
     * Verifica que el lugar de entraga no sea invalido.
     *
     * @param lugar a verificar
     * @return true si el precio es valido.
     */
    private boolean validateLUGAR(String lugar) 
    {
        return !(lugar == null || lugar.isEmpty());
    }
}
