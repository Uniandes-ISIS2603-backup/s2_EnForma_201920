/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import java.util.List;
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
    
    
    public DietaTipoEntity update(DietaTipoEntity dietaTipoEntity){
        return em.merge(dietaTipoEntity);
    }
    
    public void delete(Long dietaId){
        DietaTipoEntity entity = em.find(DietaTipoEntity.class, dietaId);
        em.remove(entity);
    }
    
}
