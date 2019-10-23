/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.ClientePersistence;
import co.edu.uniandes.csw.enforma.persistence.DietaTipoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Julio Morales
 */
@Stateless
public class DietaTipoClientesLogic {
    
    private static final Logger LOGGER = Logger.getLogger(DietaTipoClientesLogic.class.getName());

    @Inject
    private DietaTipoPersistence dietaTipoPersistence;

    @Inject
    private ClientePersistence clientePersistence;
    
    
    
    /**
     * Agregar un cliente a la dieta
     *
     * @param clienteId El cliene a guardar
     * @param dietaTipoId El id de la dieta en la cual se va a guardar el cliente.
     * @return El cliente creado.
     */
    public ClienteEntity addCliente(Long clienteId, Long dietaTipoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un cliente a la dieta con id = {0}", dietaTipoId);
        DietaTipoEntity dietaTipoEntity = dietaTipoPersistence.find(dietaTipoId);
        ClienteEntity clienteEntity = clientePersistence.find(clienteId);
        clienteEntity.setDietaTipo(dietaTipoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un cliente a la dieta con id = {0}", dietaTipoId);
        return clienteEntity;
    }
    
    
    
    
    
    
    
    
    /**
     * Retorna todos los clientes asociados a una dietaTipo
     *
     * @param dietaId El ID de la dieta buscada
     * @return La lista de clientes de la dieta
     */
    public List<ClienteEntity> getClientes(Long dietaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los clientes asociados a la dieta con id = {0}", dietaId);
        return dietaTipoPersistence.find(dietaId).getClientes();
    }
    
    
    /**
     * Retorna un cliente asociado a una dieta
     *
     * @param clienteId El id del cliente a buscar.
     * @param dietaTipoId El id de la dieta a buscar
     * @return El cliente encontrado dentro de la dieta.
     * @throws BusinessLogicException Si el cliente no se encuentra en la
     * dieta.
     */
    public ClienteEntity getCliente(Long clienteId, Long dietaTipoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el cliente con id = {0} de la dieta con id = " + dietaTipoId, clienteId);
        List<ClienteEntity> clientes = dietaTipoPersistence.find(dietaTipoId).getClientes();
        ClienteEntity clienteEntity = clientePersistence.find(clienteId);
        int index = clientes.indexOf(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el cliente con id = {0} de la dieta con id = " + dietaTipoId, clienteId);
        if (index >= 0) {
            return clientes.get(index);
        }
        throw new BusinessLogicException("El cliente no está asociado a la dieta");
    }
    
    
    
    /**
     * Remplazar clientes de una dieta
     *
     * @param clientes Lista de clientes que serán los de la dieta.
     * @param dietaId El id de la dieta que se quiere actualizar.
     * @return La lista de clientes actualizada.
     */
    public List<ClienteEntity> replaceComidas(Long dietaId, List<ClienteEntity> clientes) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la dieta con id = {0}", dietaId);
        DietaTipoEntity dietaTipoEntity = dietaTipoPersistence.find(dietaId);
        List<ClienteEntity> clientesList = clientePersistence.findAll();
        for (ClienteEntity cliente : clientesList) {
            if (clientes.contains(cliente)) {
                cliente.setDietaTipo(dietaTipoEntity);
            } else if (cliente.getDietaTipo()!= null && cliente.getDietaTipo().equals(dietaTipoEntity)) {
                cliente.setDietaTipo(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la dieta con id = {0}", dietaId);
        return clientes;
    }
    
    
    
}
