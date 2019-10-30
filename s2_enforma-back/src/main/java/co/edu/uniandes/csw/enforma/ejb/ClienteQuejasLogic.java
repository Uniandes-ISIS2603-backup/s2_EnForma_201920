/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.ClientePersistence;
import co.edu.uniandes.csw.enforma.persistence.QuejasYReclamosPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Sofía Vargas
 */
@Stateless
public class ClienteQuejasLogic 
{
     private static final Logger LOGGER = Logger.getLogger(ClienteQuejasLogic.class.getName());

    @Inject
    private QuejasYReclamosPersistence quejasPersistence;

    @Inject
    private ClientePersistence clientePersistence;

    /**
     * Agregar un quejas a la cliente
     *
     * @param quejassId El id quejas a guardar
     * @param clientesId El id de la cliente en la cual se va a guardar el
     * quejas.
     * @return El quejas creado.
     */
    public QuejasYReclamosEntity addQueja(Long quejassId, Long clientesId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un queja a la cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        QuejasYReclamosEntity quejasEntity = quejasPersistence.find(quejassId);
        quejasEntity.setCliente(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un quejas a la cliente con id = {0}", clientesId);
        return quejasEntity;
    }

    /**
     * Retorna todos los quejass asociados a una cliente
     *
     * @param clientesId El ID de la cliente buscada
     * @return La lista de quejass de la cliente
     */
    public List<QuejasYReclamosEntity> getQuejas(Long clientesId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los quejass asociados a la cliente con id = {0}", clientesId);
        return clientePersistence.find(clientesId).getQuejas();
    }

    /**
     * Retorna un queja asociado a una cliente
     *
     * @param clientesId El id de la cliente a buscar.
     * @param quejassId El id del quejas a buscar
     * @return El quejas encontrado dentro de la cliente.
     * @throws BusinessLogicException Si el quejas no se encuentra en la
     * cliente
     */
    public QuejasYReclamosEntity getQuejas(Long clientesId, Long quejasId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el quejas con id = {0} de la cliente con id = " + clientesId, quejasId);
        List<QuejasYReclamosEntity> quejas = clientePersistence.find(clientesId).getQuejas();
        QuejasYReclamosEntity quejasEntity = quejasPersistence.find(quejasId);
        int index = quejas.indexOf(quejasEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el quejas con id = {0} de la cliente con id = " + clientesId, quejasId);
        if (index >= 0) {
            return quejas.get(index);
        }
        throw new BusinessLogicException("El quejas no está asociado a la cliente");
    }

   
}


