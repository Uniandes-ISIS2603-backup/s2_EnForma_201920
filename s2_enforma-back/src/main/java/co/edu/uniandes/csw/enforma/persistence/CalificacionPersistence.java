/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.CalificacionEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jimmy Pepinosa
 */
@Stateless
public class CalificacionPersistence 
{
    @PersistenceContext(unitName = "enformaPU")
    protected EntityManager em;
    
    public CalificacionEntity create(CalificacionEntity calificacion)
    {
        //throw new java.lang.UnsupportedOperationException("Not supported yet.");
        em.persist(calificacion);
        return calificacion;
    }
}
