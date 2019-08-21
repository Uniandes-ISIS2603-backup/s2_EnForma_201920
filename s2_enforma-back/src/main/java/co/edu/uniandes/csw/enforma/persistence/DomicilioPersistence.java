/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Sebastián Clavijo
 */
@Stateless
public class DomicilioPersistence 
{
    @PersistenceContext (unitName = "DomicilioPU")
    protected EntityManager em; 
    
    public DomicilioEntity create(DomicilioEntity domicilio)
    {
        //throw new java.lang.UnsupportedOperationException("Not supported yet.");
        em.persist(domicilio);
        return domicilio;
        
    }
}