/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.CalificacionEntity;
import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.CalificacionPersistence;
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
public class DietaTipoCalificacionesLogic {
    
    
    private static final Logger LOGGER = Logger.getLogger(DietaTipoCalificacionesLogic.class.getName());

    @Inject
    private DietaTipoPersistence dietaTipoPersistence;

    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    
    
    
    /**
     * Agregar una calificacion a la dieta
     *
     * @param calificacionId La calificacion a guardar
     * @param dietaTipoId El id de la dieta en la cual se va a guardar la calificacion.
     * @return La calificacion creada.
     */
    public CalificacionEntity addCalificacion(Long calificacionId, Long dietaTipoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una calificacion a la dieta con id = {0}", dietaTipoId);
        DietaTipoEntity dietaTipoEntity = dietaTipoPersistence.find(dietaTipoId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionId);
        calificacionEntity.setDietaTipo(dietaTipoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle una calificacion a la dieta con id = {0}", dietaTipoId);
        return calificacionEntity;
    }
    
    
    
    
    
    /**
     * Retorna todos las calificaciones asociadas a una dietaTipo
     *
     * @param dietaId El ID de la dieta buscada
     * @return La lista de calificaciones de la dieta
     */
    public List<CalificacionEntity> getCalificaciones(Long dietaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las calificaciones asociadas a la dieta con id = {0}", dietaId);
        return dietaTipoPersistence.find(dietaId).getCalificaciones();
    }
    
    
    /**
     * Retorna una calificacion asociada a una dieta
     *
     * @param calificacionId El id de la calificacion a buscar.
     * @param dietaTipoId El id de la dieta a buscar
     * @return La calificacion encontrada dentro de la dieta.
     * @throws BusinessLogicException Si la calificacion no se encuentra en la
     * dieta.
     */
    public CalificacionEntity getCalificacion(Long calificacionId, Long dietaTipoId) throws BusinessLogicException {
        List<CalificacionEntity> calificaciones = dietaTipoPersistence.find(dietaTipoId).getCalificaciones();
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionId);
        int index = calificaciones.indexOf(calificacionEntity);
        if (index >= 0) {
            return calificaciones.get(index);
        }
        throw new BusinessLogicException("La calificiacion no está asociada a la dieta");
    }
    
    
    /**
     * Remplazar calificaciones de una dieta
     *
     * @param calificaciones Lista de calificaciones que serán los de la dieta.
     * @param dietaId El id de la dieta que se quiere actualizar.
     * @return La lista de calificaciones actualizada.
     */
    public List<CalificacionEntity> replaceCalificaciones(Long dietaId, List<CalificacionEntity> calificaciones) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la dieta con id = {0}", dietaId);
        DietaTipoEntity dietaTipoEntity = dietaTipoPersistence.find(dietaId);
        List<CalificacionEntity> calificacionesLista = calificacionPersistence.findAll();
        for (CalificacionEntity calificacion : calificacionesLista) {
            if (calificaciones.contains(calificacion)) {
                calificacion.setDietaTipo(dietaTipoEntity);
            } else if (calificacion.getDietaTipo()!= null && calificacion.getDietaTipo().equals(dietaTipoEntity)) {
                calificacion.setDietaTipo(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la dieta con id = {0}", dietaId);
        return calificaciones;
    }
    
    
}
