/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
import co.edu.uniandes.csw.enforma.persistence.ComidaTipoPersistence;
import co.edu.uniandes.csw.enforma.persistence.AdministradorPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Jose Manuel Flórez
 */

@Stateless
public class ComidaTipoAdministradorLogic
{
    private static final Logger LOGGER = Logger.getLogger(ComidaTipoAdministradorLogic.class.getName());
    
    @Inject
    private ComidaTipoPersistence comidaTipoPersistence;

    @Inject
    private AdministradorPersistence administradorPersistence;

    
    public ComidaTipoEntity createComidaTipoAdmin(Long administradorId, ComidaTipoEntity comidaTipoEntity) {
          LOGGER.log(Level.INFO, "Inicia proceso de crear review");
        AdministradorEntity admin = administradorPersistence.find(administradorId);
        comidaTipoEntity.setAdministrador(admin);
        LOGGER.log(Level.INFO, "Termina proceso de creación del review");
        return comidaTipoPersistence.create(comidaTipoEntity);
    
    }

    
     /**
     * Remplazar la dieta tipo de una comidaTipo.
     *
     * @param comidaTipoId id de la comida Tipo que se quiere actualizar.
     * @param administradorId El id de la Administrador que será de la ComidaTipo.
     * @return el nuevo libro.
     */
    public ComidaTipoEntity replaceAdministrador(Long comidaTipoId, Long administradorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar comidaTipo con id = {0}", comidaTipoId);
        AdministradorEntity administradorEntity = administradorPersistence.find(administradorId);
        ComidaTipoEntity comidaTipoEntity = comidaTipoPersistence.find(comidaTipoId);
        comidaTipoEntity.setAdministrador(administradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar comidaTipo con id = {0}", comidaTipoEntity.getId());
        return comidaTipoEntity;
    }
    
      /**
     * Borrar un book de una editorial. Este metodo se utiliza para borrar la
     * relacion de un libro.
     *
     * @param booksId El libro que se desea borrar de la editorial.
     */
    public void removeAdministrador(Long comidaTipoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la Administrador de la comidatipo con id = {0}", comidaTipoId);
        ComidaTipoEntity comidaTipoEntity = comidaTipoPersistence.find(comidaTipoId);
        AdministradorEntity administradorEntity = administradorPersistence.find(comidaTipoEntity.getAdministrador().getId());
        comidaTipoEntity.setAdministrador(null);
        
        administradorEntity.getComidasTipo().remove(comidaTipoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el administrador de la comida tipo con id = {0}", comidaTipoEntity.getId());
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
