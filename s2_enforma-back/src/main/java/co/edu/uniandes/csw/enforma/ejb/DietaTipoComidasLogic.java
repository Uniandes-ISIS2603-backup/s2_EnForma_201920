/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.ComidaTipoPersistence;
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
public class DietaTipoComidasLogic {
    private static final Logger LOGGER = Logger.getLogger(DietaTipoComidasLogic.class.getName());

    @Inject
    private DietaTipoPersistence dietaTipoPersistence;

    @Inject
    private ComidaTipoPersistence comidaTipoPersistence;
    
    
    
    
    /**
     * Agregar una comida a la dieta
     *
     * @param comidaId La comidaTipo a guardar
     * @param dietaTipoId El id de la dieta en la cual se va a guardar la comida.
     * @return La comida creada.
     */
    public ComidaTipoEntity addComidaTipo(Long comidaId, Long dietaTipoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una comida a la dieta con id = {0}", dietaTipoId);
        DietaTipoEntity dietaTipoEntity = dietaTipoPersistence.find(dietaTipoId);
        ComidaTipoEntity comidaTipoEntity = comidaTipoPersistence.find(comidaId);
        comidaTipoEntity.setDietaTipo(dietaTipoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle una comida a la dieta con id = {0}", dietaTipoId);
        return comidaTipoEntity;
    }
    
    
    
    /**
     * Retorna todos las comidas asociadas a una dietaTipo
     *
     * @param dietaId El ID de la dieta buscada
     * @return La lista de comidas de la dieta
     */
    public List<ComidaTipoEntity> getComidas(Long dietaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las comidas asociadas a la dieta con id = {0}", dietaId);
        return dietaTipoPersistence.find(dietaId).getComidas();
    }
    
    
    /**
     * Retorna una comida asociada a una dieta
     *
     * @param comidaId El id de la comida a buscar.
     * @param dietaTipoId El id de la dieta a buscar
     * @return La comida encontrada dentro de la dieta.
     * @throws BusinessLogicException Si la comida no se encuentra en la
     * dieta.
     */
    public ComidaTipoEntity getComida(Long comidaId, Long dietaTipoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la comida con id = {0} de la dieta con id = " + dietaTipoId, comidaId);
        List<ComidaTipoEntity> comidas = dietaTipoPersistence.find(dietaTipoId).getComidas();
        ComidaTipoEntity comidaTipoEntity = comidaTipoPersistence.find(comidaId);
        int index = comidas.indexOf(comidaTipoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la comida con id = {0} de la dieta con id = " + dietaTipoId, comidaId);
        if (index >= 0) {
            return comidas.get(index);
        }
        throw new BusinessLogicException("La comida no está asociada a la dieta");
    }
    
    
    
    
    /**
     * Remplazar comidas de una dieta
     *
     * @param comidas Lista de comidas que serán los de la dieta.
     * @param dietaId El id de la dieta que se quiere actualizar.
     * @return La lista de comidas actualizada.
     */
    public List<ComidaTipoEntity> replaceComidas(Long dietaId, List<ComidaTipoEntity> comidas) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la dieta con id = {0}", dietaId);
        DietaTipoEntity dietaTipoEntity = dietaTipoPersistence.find(dietaId);
        List<ComidaTipoEntity> comidasList = comidaTipoPersistence.findAll();
        for (ComidaTipoEntity comida : comidasList) {
            if (comidas.contains(comida)) {
                comida.setDietaTipo(dietaTipoEntity);
            } else if (comida.getDietaTipo()!= null && comida.getDietaTipo().equals(dietaTipoEntity)) {
                comida.setDietaTipo(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la dieta con id = {0}", dietaId);
        return comidas;
    }
    
    
    
    
    
    
    
}
