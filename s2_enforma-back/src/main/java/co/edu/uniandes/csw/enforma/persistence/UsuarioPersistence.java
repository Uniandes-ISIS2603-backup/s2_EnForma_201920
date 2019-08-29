/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.UsuarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Paula Sofía Vargas 
 */
@Stateless
public class UsuarioPersistence
{
    @PersistenceContext (unitName = "enformaPU")
    protected EntityManager em;
     
    public UsuarioEntity create(UsuarioEntity usuarioEntity)
    {
        em.persist(usuarioEntity);
        return usuarioEntity;
    }
    
    public UsuarioEntity find(Long usuarioId)
    {
        return em.find(UsuarioEntity.class, usuarioId);
    }
    
    public List<UsuarioEntity> findAll()
    {
        // Se crea un query para buscar todas los pagos en la base de datos.
        TypedQuery<UsuarioEntity> query = em.createQuery("select u from UsuarioEntity u", UsuarioEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de pagos.
        return query.getResultList();
    }
    
    public UsuarioEntity update(UsuarioEntity pago)
    {
        //Es equivalente a un comando de SQL que permite actualizar la info
        return em.merge(pago);
    }
    
    
    public void delete(Long pagoID)
    {
        UsuarioEntity d= em.find(UsuarioEntity.class, pagoID);
        em.remove(d);
    }
    
}
