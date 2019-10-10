/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import co.edu.uniandes.csw.enforma.entities.CalificacionEntity;
import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.ClientePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;
import java.util.logging.Level;


/**
 *
 * @cliente Sofia Vargas
 */
@Stateless
public class ClienteLogic 
{
    private static final Logger LOGGER = Logger.getLogger(DomicilioLogic.class.getName());
    @Inject 
    private ClientePersistence persistence;
    
    
    
   public ClienteEntity crearCliente(ClienteEntity cliente) throws BusinessLogicException
   {
       if(cliente.getNombre() == null)
           throw new BusinessLogicException("El nombre del cliente está vacio.");
       if(cliente.getEdad() <= 0)
           throw new BusinessLogicException("La edad del cliente no puede ser menor o igual a cero.");
       if(cliente.getObjetivos() == null)
           throw new BusinessLogicException("Los objetivos del cliente están vacio.");
        if(cliente.getPeso() <= 0.0)
           throw new BusinessLogicException("El peso del cliente tiene que ser mayor a cero.");
       if(cliente.getGluten() == null)
           throw new BusinessLogicException("El gluten no puede ser null.");
       if(cliente.getLactosa() == null)
           throw new BusinessLogicException("La lactosa no puede ser null.");
       if(cliente.getUserName() == null)
           throw new BusinessLogicException("El userName no puede ser vacio.");
       if(cliente.getContrasenia() == null)
           throw new BusinessLogicException("La contraseña no puede ser nula.");
       
       cliente = persistence.create(cliente);
       return cliente;
   }
   
   /**
     * Devuelve todos los clientes de la base de datos.
     *
     * @return Lista de entidades de tipo Cliente.
     */
     public List<ClienteEntity> getClientes() 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los clientes.");
        List<ClienteEntity> clientes = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los clientes.");
        return clientes;
    }
    
      /**
     * Busca un Cliente por el id
     *
     * @param clienteId El id del cliente a buscar
     * @return El cliente encontrado, null si no lo encuentra.
     */
    public ClienteEntity getCliente(Long clienteId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el cliente con id = {0}", clienteId);
        ClienteEntity clienteEntity = persistence.find(clienteId);
        if (clienteEntity == null) 
        {
            LOGGER.log(Level.SEVERE, "El cliente con el id = {0} no existe", clienteId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el cliente con id = {0}",clienteId);
        return clienteEntity;
    }
    
     /**
     * Actualizar un cliente por ID
     *
     * @param clienteId El ID del cliente a actualizar
     * @param cliente La entidad del cliente con los cambios deseados
     * @return La entidad del cliente luego de actualizarla
     * @throws BusinessLogicException Si la fecha o el precio es invalido de la actualización es inválido
     */
    public ClienteEntity updateCliente(Long clienteId, ClienteEntity cliente) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el cliente con id = {0}", clienteId);
        
        if(cliente.getNombre() == null)
           throw new BusinessLogicException("El nombre del cliente está vacio.");
       if(cliente.getEdad() <= 0)
           throw new BusinessLogicException("La edad del cliente no puede ser menor o igual a cero.");
       if(cliente.getObjetivos() == null)
           throw new BusinessLogicException("Los objetivos del cliente están vacio.");
        if(cliente.getPeso() <= 0.0)
           throw new BusinessLogicException("El peso del cliente tiene que ser mayor a cero.");
       if(cliente.getGluten() == null)
           throw new BusinessLogicException("El gluten no puede ser null.");
       if(cliente.getLactosa() == null)
           throw new BusinessLogicException("La lactosa no puede ser null.");
       if(cliente.getUserName() == null)
           throw new BusinessLogicException("El userName no puede ser vacio.");
       if(cliente.getContrasenia() == null)
           throw new BusinessLogicException("La contraseña no puede ser nula.");
       
        ClienteEntity newEntity = persistence.update(cliente);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el cliente con id = {0}", cliente.getId());
        return newEntity;
        
    }
    
    /**
     * Elimina una instancia de Cliente de la base de datos.
     *
     * @param clientesId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el autor tiene libros asociados.
     */
    public void deleteCliente(Long clienteId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el cliente con id = {0}", clienteId);
        
        List<QuejasYReclamosEntity> quejas  = getCliente(clienteId).getQuejasYReclamos();
        if(quejas != null && !quejas.isEmpty())
        {
            throw new BusinessLogicException("No se puede borrar el autor con id= " + clienteId + "porque tiene quejas asociadas.");
        }
        List<DomicilioEntity> domicilios  = getCliente(clienteId).getDomicilios();
        if(quejas != null && !quejas.isEmpty())
        {
            throw new BusinessLogicException("No se puede borrar el autor con id= " + clienteId + "porque tiene quejas asociadas.");
        }
       
        persistence.delete(clienteId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el autor con id = {0}", clienteId);
    }


}
