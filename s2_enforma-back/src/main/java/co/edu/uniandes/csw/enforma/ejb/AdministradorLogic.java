/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;
import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.AdministradorPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Elina Jaimes
 */

@Stateless
public class AdministradorLogic {

    private static final Logger LOGGER = Logger.getLogger(AdministradorLogic.class.getName());
    
    @Inject
    private AdministradorPersistence persistence;

    public AdministradorEntity crearAdministrador(AdministradorEntity admin) throws BusinessLogicException {
        if (admin.getNombre().equals("") || admin.getNombre() == null) {
            throw new BusinessLogicException("El nombre no puede estar vacío");
        } else if (admin.getUsername().equals("") || admin.getUsername() == null) {
            throw new BusinessLogicException("El username no puede estar vacío");
        } 
        else if(admin.getContrasena().equals("") || admin.getContrasena()==null) {
             throw new BusinessLogicException("La contraseña no puede estar vacía");
        }
        else
        {
            admin = persistence.create(admin);
            return admin;
        }
    }

    
     public List<AdministradorEntity> getAdministradors() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los administradors");
        List<AdministradorEntity> administradors = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los administradors");
        return administradors;
    }

    /**
     * Busca un administrador por ID
     *
     * @param administradorsId El id del administrador a buscar
     * @return El administrador encontrado, null si no lo encuentra.
     */
    public AdministradorEntity getAdministrador(Long administradorsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el administrador con id = {0}", administradorsId);
        AdministradorEntity administradorEntity = persistence.find(administradorsId);
        if (administradorEntity == null) {
            LOGGER.log(Level.SEVERE, "El administrador con el id = {0} no existe", administradorsId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el administrador con id = {0}", administradorsId);
        return administradorEntity;
    }

    /**
     * Actualizar un administrador por ID
     *
     * @param administradorsId El ID del administrador a actualizar
     * @param administradorEntity La entidad del administrador con los cambios deseados
     * @return La entidad del administrador luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public AdministradorEntity updateAdministrador(Long administradorsId, AdministradorEntity administradorEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el administrador con id = {0}", administradorsId);
        
        if(administradorEntity.getUsername().equals("") || administradorEntity.getUsername()==null)
        {
            throw new BusinessLogicException("Error para actualizar el administrador");
        }
        if(administradorEntity.getNombre().equals("") || administradorEntity.getNombre()==null)
        {
          throw new BusinessLogicException("Error para actualizar el administrador");

        }
        if(administradorEntity.getContrasena().equals("") || administradorEntity.getContrasena()==null)
        {
          throw new BusinessLogicException("Error para actualizar el administrador");
        }
        AdministradorEntity newEntity = persistence.update(administradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el administrador con id = {0}", administradorEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar un administrador por ID
     *
     * @param administradorsId El ID del administrador a eliminar
     * @throws BusinessLogicException si el administrador tiene autores asociados
     */
    public void deleteAdministrador(Long administradorsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el administrador con id = {0}", administradorsId);
        persistence.delete(administradorsId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el administrador con id = {0}", administradorsId);
    }
    
    
}
