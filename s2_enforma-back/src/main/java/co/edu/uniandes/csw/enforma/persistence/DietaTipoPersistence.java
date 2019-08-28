/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
    
    
}
