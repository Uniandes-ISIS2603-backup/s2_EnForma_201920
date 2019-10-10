/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.persistence.ComidaTipoPersistence;
import co.edu.uniandes.csw.enforma.persistence.DietaTipoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Jose Manuel Flórez
 */

@Stateless
public class ComidaTipoDietaTipoLogic
{
    private static final Logger LOGGER = Logger.getLogger(ComidaTipoDietaTipoLogic.class.getName());
    
    @Inject
    private ComidaTipoPersistence comidaTipoPersistence;

    @Inject
    private DietaTipoPersistence dietaTipoPersistence;

    
     /**
     * Remplazar la dieta tipo de una comidaTipo.
     *
     * @param comidaTipoId id de la comida Tipo que se quiere actualizar.
     * @param dietaTipoId El id de la DietaTipo que será de la ComidaTipo.
     * @return el nuevo libro.
     */
    public ComidaTipoEntity replaceDietaTipo(Long comidaTipoId, Long dietaTipoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar libro con id = {0}", comidaTipoId);
        DietaTipoEntity dietaTipoEntity = dietaTipoPersistence.find(dietaTipoId);
        ComidaTipoEntity comidaTipoEntity = comidaTipoPersistence.find(comidaTipoId);
        comidaTipoEntity.setDietaTipo(dietaTipoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar comidaTipo con id = {0}", comidaTipoEntity.getId());
        return comidaTipoEntity;
    }
    
      /**
     * Borrar un book de una editorial. Este metodo se utiliza para borrar la
     * relacion de un libro.
     *
     * @param booksId El libro que se desea borrar de la editorial.
     */
    public void removeDietaTipo(Long comidaTipoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la DietaTipo de la comidatipo con id = {0}", comidaTipoId);
        ComidaTipoEntity comidaTipoEntity = comidaTipoPersistence.find(comidaTipoId);
        DietaTipoEntity dietaTipoEntity = dietaTipoPersistence.find(comidaTipoEntity.getDietaTipo().getId());
        comidaTipoEntity.setDietaTipo(null);
        //esto lo documento hasta que en dieta tipo esté el método getComidaTipo
//        dietaTipoEntity.getComidaTipo().remove(comidaTipoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la dieta Tipo de la comida tipo con id = {0}", comidaTipoEntity.getId());
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
