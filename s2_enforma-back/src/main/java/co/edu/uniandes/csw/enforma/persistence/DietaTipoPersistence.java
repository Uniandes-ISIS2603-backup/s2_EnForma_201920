/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Julio Morales
 */
@Stateless
public class DietaTipoPersistence {
    
        private static final Logger LOGGER = Logger.getLogger(DietaTipoPersistence.class.getName());

    
    
    @PersistenceContext (unitName = "enformaPU")
    protected EntityManager em;
    
    public DietaTipoEntity create(DietaTipoEntity dietaTipo)
    {
        em.persist(dietaTipo);
        return dietaTipo;
    }
    
    
    public List<DietaTipoEntity> findAll() {
        TypedQuery query = em.createQuery("select u from DietaTipoEntity u", DietaTipoEntity.class);
        return query.getResultList();
    }
    
    
    
    
    public DietaTipoEntity find(Long dietaId){
        return em.find(DietaTipoEntity.class, dietaId);
    }
    
    
    public DietaTipoEntity findByNombre (String nombre){
        LOGGER.log(Level.INFO, "Consultando dietas por nombre {0} ", nombre);
        // Se crea un query para buscar dietas con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From DietaTipoEntity e where e.nombre = :nombre", DietaTipoEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<DietaTipoEntity> sameNombre = query.getResultList();
        DietaTipoEntity result;
        if (sameNombre == null) {
            result = null;
        } else if (sameNombre.isEmpty()) {
            result = null;
        } else {
            result = sameNombre.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar dietas por nombre {0} ", nombre);
        return result;
    }
    
    
    public DietaTipoEntity update(DietaTipoEntity dietaTipoEntity){
        return em.merge(dietaTipoEntity);
    }
    
    public void delete(Long dietaId){
        DietaTipoEntity entity = em.find(DietaTipoEntity.class, dietaId);
        em.remove(entity);
    }
    
}
