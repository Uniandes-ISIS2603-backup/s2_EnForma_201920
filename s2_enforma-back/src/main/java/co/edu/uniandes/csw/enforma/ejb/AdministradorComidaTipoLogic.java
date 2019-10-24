/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.AdministradorPersistence;
import co.edu.uniandes.csw.enforma.persistence.ComidaTipoPersistence;
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
public class AdministradorComidaTipoLogic {
    
     private static final Logger LOGGER = Logger.getLogger(AdministradorComidaTipoLogic.class.getName());

    @Inject
    private ComidaTipoPersistence comidaTipoPersistence;

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
    public ComidaTipoEntity addComidaTipo(Long dietaTiposId, Long administradorsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un libro a la administrador con id = {0}", administradorsId);
        AdministradorEntity administradorEntity = administradorPersistence.find(administradorsId);
        ComidaTipoEntity dietaTipoEntity = comidaTipoPersistence.find(dietaTiposId);
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
    public List<ComidaTipoEntity> getComidasTipos(Long administradorsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los libros asociados a la administrador con id = {0}", administradorsId);
        return administradorPersistence.find(administradorsId).getComidasTipo();
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
    public ComidaTipoEntity getComidaTipo(Long administradorsId, Long dietaTiposId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} de la administrador con id = " + administradorsId, dietaTiposId);
        List<ComidaTipoEntity> dietaTipos = administradorPersistence.find(administradorsId).getComidasTipo();
        ComidaTipoEntity dietaTipoEntity = comidaTipoPersistence.find(dietaTiposId);
        int index = dietaTipos.indexOf(dietaTipoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} de la administrador con id = " + administradorsId, dietaTiposId);
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
    public List<ComidaTipoEntity> replaceComidaTipos(Long administradorsId, List<ComidaTipoEntity> dietaTipos) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la administrador con id = {0}", administradorsId);
        AdministradorEntity administradorEntity = administradorPersistence.find(administradorsId);
        List<ComidaTipoEntity> dietaTipoList = comidaTipoPersistence.findAll();
        for (ComidaTipoEntity dietaTipo : dietaTipoList) {
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
