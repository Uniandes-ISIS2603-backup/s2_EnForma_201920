/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.AdministradorPersistence;
import co.edu.uniandes.csw.enforma.persistence.DietaTipoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Elina Jaimes
 */
@Stateless
public class AdministradorDietaTipoLogic {
    
    
     private static final Logger LOGGER = Logger.getLogger(AdministradorDietaTipoLogic.class.getName());

    @Inject
    private DietaTipoPersistence dietaTipoPersistence;

    @Inject
    private AdministradorPersistence administradorPersistence;

    /**
     * Agregar un dietaTipo a la administrador
     *
     * @param dietaTiposId El id libro a guardar
     * @param administradorsId El id de la administrador en la cual se va a guardar el
     * libro.
     * @return El libro creado.
     */
    public DietaTipoEntity addDietaTipo(Long dietaTiposId, Long administradorsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un libro a la administrador con id = {0}", administradorsId);
        AdministradorEntity administradorEntity = administradorPersistence.find(administradorsId);
        DietaTipoEntity dietaTipoEntity = dietaTipoPersistence.find(dietaTiposId);
        dietaTipoEntity.setAdministrador(administradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un libro a la administrador con id = {0}", administradorsId);
        return dietaTipoEntity;
    }

    /**
     * Retorna todos los dietaTipos asociados a una administrador
     *
     * @param administradorsId El ID de la administrador buscada
     * @return La lista de libros de la administrador
     */
    public List<DietaTipoEntity> getDietaTipos(Long administradorsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los libros asociados a la administrador con id = {0}", administradorsId);
        return administradorPersistence.find(administradorsId).getDietaTipo();
    }

    /**
     * Retorna un dietaTipo asociado a una administrador
     *
     * @param administradorsId El id de la administrador a buscar.
     * @param dietaTiposId El id del libro a buscar
     * @return El libro encontrado dentro de la administrador.
     * @throws BusinessLogicException Si el libro no se encuentra en la
     * administrador
     */
    public DietaTipoEntity getDietaTipo(Long administradorsId, Long dietaTiposId) throws BusinessLogicException {
        List<DietaTipoEntity> dietaTipos = administradorPersistence.find(administradorsId).getDietaTipo();
        DietaTipoEntity dietaTipoEntity = dietaTipoPersistence.find(dietaTiposId);
        int index = dietaTipos.indexOf(dietaTipoEntity);
        if (index >= 0) {
            return dietaTipos.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado a la administrador");
    }

    /**
     * Remplazar dietaTipos de una administrador
     *
     * @param dietaTipos Lista de libros que serán los de la administrador.
     * @param administradorsId El id de la administrador que se quiere actualizar.
     * @return La lista de libros actualizada.
     */
    public List<DietaTipoEntity> replaceDietaTipos(Long administradorsId, List<DietaTipoEntity> dietaTipos) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la administrador con id = {0}", administradorsId);
        AdministradorEntity administradorEntity = administradorPersistence.find(administradorsId);
        List<DietaTipoEntity> dietaTipoList = dietaTipoPersistence.findAll();
        for (DietaTipoEntity dietaTipo : dietaTipoList) {
            if (dietaTipos.contains(dietaTipo)) {
                dietaTipo.setAdministrador(administradorEntity);
            } else if (dietaTipo.getAdministrador() != null && dietaTipo.getAdministrador().equals(administradorEntity)) {
                dietaTipo.setAdministrador(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la administrador con id = {0}", administradorsId);
        return dietaTipos;
    }
    
}
