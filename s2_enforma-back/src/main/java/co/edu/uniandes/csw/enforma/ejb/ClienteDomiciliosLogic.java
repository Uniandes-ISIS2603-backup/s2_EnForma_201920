/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;


import co.edu.uniandes.csw.enforma.entities.ClienteEntity;

import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;

import co.edu.uniandes.csw.enforma.persistence.ClientePersistence;

import co.edu.uniandes.csw.enforma.persistence.DomicilioPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Cliente y Domicilio.
 * @author Sofia Vargas
 */
@Stateless
public class ClienteDomiciliosLogic 
{

      private static final Logger LOGGER = Logger.getLogger(ClienteDomiciliosLogic.class.getName());

    @Inject
    private DomicilioPersistence domicilioPersistence;

    @Inject
    private ClientePersistence clientePersistence;

    /**
     * Agregar un domicilio a la cliente
     *
     * @param domiciliosId El id domicilio a guardar
     * @param clientesId El id de la cliente en la cual se va a guardar el
     * domicilio.
     * @return El domicilio creado.
     */
    public DomicilioEntity addDomicilio(Long domiciliosId, Long clientesId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un domicilio a la cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        DomicilioEntity domicilioEntity = domicilioPersistence.find(domiciliosId);
        domicilioEntity.setCliente(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un domicilio a la cliente con id = {0}", clientesId);
        return domicilioEntity;
    }

    /**
     * Retorna todos los domicilios asociados a una cliente
     *
     * @param clientesId El ID de la cliente buscada
     * @return La lista de domicilios de la cliente
     */
    public List<DomicilioEntity> getDomicilios(Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los domicilios asociados a la cliente con id = {0}", clientesId);
        return clientePersistence.find(clientesId).getDomicilios();
    }

    /**
     * Retorna un domicilio asociado a una cliente
     *
     * @param clientesId El id de la cliente a buscar.
     * @param domiciliosId El id del domicilio a buscar
     * @return El domicilio encontrado dentro de la cliente.
     * @throws BusinessLogicException Si el domicilio no se encuentra en la
     * cliente
     */
    public DomicilioEntity getDomicilio(Long clientesId, Long domiciliosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el domicilio con id = {0} de la cliente con id = " + clientesId, domiciliosId);
        List<DomicilioEntity> domicilios = clientePersistence.find(clientesId).getDomicilios();
        DomicilioEntity domicilioEntity = domicilioPersistence.find(domiciliosId);
        int index = domicilios.indexOf(domicilioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el domicilio con id = {0} de la cliente con id = " + clientesId, domiciliosId);
        if (index >= 0) {
            return domicilios.get(index);
        }
        throw new BusinessLogicException("El domicilio no está asociado a la cliente");
    }
}
