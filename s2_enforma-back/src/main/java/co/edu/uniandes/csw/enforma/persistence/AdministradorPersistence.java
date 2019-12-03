/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;
import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Elina Jaimes
 */

@Stateless
public class AdministradorPersistence {
  
     @PersistenceContext(unitName="enformaPU")
    protected EntityManager em;
    
    
    public AdministradorEntity create (AdministradorEntity admin)
    {
        em.persist(admin);
        return admin;
    }
    
    
    public AdministradorEntity find(Long adminID)
    {
        return em.find(AdministradorEntity.class, adminID);
    }
    
    
    public List<AdministradorEntity> findAll()
    {
        // Se crea un query para buscar todas los admins en la base de datos.
        TypedQuery<AdministradorEntity> query = em.createQuery("select u from AdministradorEntity u", AdministradorEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de admins.
        return query.getResultList();
    }
    
    public AdministradorEntity update(AdministradorEntity admin)
    {
        //Es equivalente a un comando de SQL que permite actualizar la info
        return em.merge(admin);
    }
    
    
    public void delete(Long adminID)
    {
        AdministradorEntity d= em.find(AdministradorEntity.class, adminID);
        em.remove(d);
    }
    
    public AdministradorEntity findByUserName(String user) 
    {
        // Se crea un query para buscar clientes con el user que recibe el método como argumento. ":user" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From AdministradorEntity e where e.username = :user", AdministradorEntity.class);
        // Se remplaza el placeholder ":user" con el valor del argumento 
        query = query.setParameter("user", user);
        // Se invoca el query se obtiene la lista resultado
        List<AdministradorEntity> sameUser = query.getResultList();
        AdministradorEntity result;
        if (sameUser == null)
        {
            result = null;
        } else if (sameUser.isEmpty()) 
        {
            result = null;
        } else 
        {
            result = sameUser.get(0);
        }
        return result; 
    }
    
}
