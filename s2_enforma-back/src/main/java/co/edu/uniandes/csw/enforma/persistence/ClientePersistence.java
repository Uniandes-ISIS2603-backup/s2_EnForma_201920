/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sofía Vargas 
 */
@Stateless
public class ClientePersistence
{
    @PersistenceContext (unitName = "enformaPU")
    protected EntityManager em;
     
    public ClienteEntity create(ClienteEntity usuarioEntity)
    {
        em.persist(usuarioEntity);
        return usuarioEntity;
    }
    
    public ClienteEntity find(Long usuarioId)
    {
        return em.find(ClienteEntity.class, usuarioId);
    }
    
    public List<ClienteEntity> findAll()
    {
        // Se crea un query para buscar todas los pagos en la base de datos.
        TypedQuery<ClienteEntity> query = em.createQuery("select u from ClienteEntity u", ClienteEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de pagos.
        return query.getResultList();
    }
    
    public ClienteEntity update(ClienteEntity usuario)
    {
        //Es equivalente a un comando de SQL que permite actualizar la info
        return em.merge(usuario);
    }
    
    
    public void delete(Long usuarioId)
    {
        ClienteEntity u = em.find(ClienteEntity.class, usuarioId);
        em.remove(u);
    }
    
}
