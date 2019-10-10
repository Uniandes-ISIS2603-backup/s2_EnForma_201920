/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.persistence.AdministradorPersistence;
import co.edu.uniandes.csw.enforma.persistence.DietaTipoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Julio Morales
 */
public class DietaTipoAdministradorLogic {
    private static final Logger LOGGER = Logger.getLogger(DietaTipoAdministradorLogic.class.getName());

    @Inject
    private DietaTipoPersistence dietaTipoPersistence;

    @Inject
    private AdministradorPersistence administradorPersistence;

    /**
     * Remplazar al administrador de una DietaTipo.
     *
     * @param dietaTipoId id de la dietaTipo que se quiere actualizar.
     * @param adminId El id del Administrador que se será del dietaTipo.
     * @return el nuevo dietaTipo.
     */
    public DietaTipoEntity replaceEditorial(Long dietaTipoId, Long adminId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar dietaTipo con id = {0}", dietaTipoId);
        AdministradorEntity administradorEntity = administradorPersistence.find(adminId);
        DietaTipoEntity dietaTipoEntity = dietaTipoPersistence.find(dietaTipoId);
        dietaTipoEntity.setAdministrador(administradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar dietaTipo con id = {0}", dietaTipoEntity.getId());
        return dietaTipoEntity;
    }

    /**
     * Borrar un DietaTipo de un administrador. Este metodo se utiliza para borrar la
     * relacion de un DietaTipo.
     *
     * @param dietaTipoId El DietaTipo que se desea borrar de la administrador.
     */
    public void removeEditorial(Long dietaTipoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la Editorial del libro con id = {0}", dietaTipoId);
        DietaTipoEntity dietaTipoEntity = dietaTipoPersistence.find(dietaTipoId);
        AdministradorEntity administradorEntity = administradorPersistence.find(dietaTipoEntity.getAdministrador().getId());
        dietaTipoEntity.setAdministrador(null);
        administradorEntity.getDietaTipo().remove(dietaTipoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la Editorial del libro con id = {0}", dietaTipoEntity.getId());
    }
    
    
    
    
    
}
