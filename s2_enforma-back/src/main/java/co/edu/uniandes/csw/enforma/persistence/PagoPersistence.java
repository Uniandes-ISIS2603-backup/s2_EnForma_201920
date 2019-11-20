/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.PagoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ev.jaimes
 */

@Stateless
public class PagoPersistence {


    
    @PersistenceContext(unitName="enformaPU")
    protected EntityManager em;
    
    
    public PagoEntity create (PagoEntity pago)
    {
        em.persist(pago);
        return pago;
    }
    
    
    public PagoEntity find(Long pagoID)
    {
        TypedQuery<PagoEntity> q = em.createQuery("select p from PagoEntity p where (p.id = :pagosId)", PagoEntity.class);
        q.setParameter("pagosId", pagoID);
        List<PagoEntity> results = q.getResultList();
        PagoEntity pago = null;
        if (!results.isEmpty()) {
            pago = results.get(0);
        }
        return pago;
    }
    
    
    public List<PagoEntity> findAll()
    {
        // Se crea un query para buscar todas los pagos en la base de datos.
        TypedQuery<PagoEntity> query = em.createQuery("select u from PagoEntity u", PagoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de pagos.
        return query.getResultList();
    }
    
    public PagoEntity update(PagoEntity pago)
    {
        //Es equivalente a un comando de SQL que permite actualizar la info
        return em.merge(pago);
    }
    
    
    public void delete(Long pagoID)
    {
        PagoEntity d= em.find(PagoEntity.class, pagoID);
        em.remove(d);
    }
    
}
