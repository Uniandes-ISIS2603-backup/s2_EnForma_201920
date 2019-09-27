/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
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
    
    private static final Logger LOGGER = Logger.getLogger(DomicilioPersistence.class.getName());
    
    @PersistenceContext (unitName = "enformaPU")
    protected EntityManager em;
     
    public ClienteEntity create(ClienteEntity usuarioEntity)
    {
        em.persist(usuarioEntity);
        return usuarioEntity;
    }
    
    public ClienteEntity find(Long usuarioId)
    {
         LOGGER.log(Level.INFO, "Consultando cliente con id={0}", usuarioId);
        return em.find(ClienteEntity.class, usuarioId);
    }
    
    public List<ClienteEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultando todos los clientes.");
        // Se crea un query para buscar todas los clientes en la base de datos.
        TypedQuery<ClienteEntity> query = em.createQuery("select u from ClienteEntity u", ClienteEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de clientes.
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
    
    public ClienteEntity findByUserName(String user) 
    {
        LOGGER.log(Level.INFO, "Consultando cliente por userName ", user);
        // Se crea un query para buscar clientes con el user que recibe el método como argumento. ":user" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ClienteEntity e where e.userName = :user", ClienteEntity.class);
        // Se remplaza el placeholder ":user" con el valor del argumento 
        query = query.setParameter("user", user);
        // Se invoca el query se obtiene la lista resultado
        List<ClienteEntity> sameUser = query.getResultList();
        ClienteEntity result;
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
        LOGGER.log(Level.INFO, "Saliendo de consultar clientes por userName ", user);
        return result; 
    }
    
     public ClienteEntity findByEdad(Integer edad) 
    {
        LOGGER.log(Level.INFO, "Consultando clientes por edad ", edad);
        // Se crea un query para buscar clientes con el edad que recibe el método como argumento. ":edad" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ClienteEntity e where e.edad = :edad", ClienteEntity.class);
        // Se remplaza el placeholder ":edad" con el valor del argumento 
        query = query.setParameter("edad", edad);
        // Se invoca el query se obtiene la lista resultado
        List<ClienteEntity> sameEdad = query.getResultList();
        ClienteEntity result;
        if (sameEdad == null)
        {
            result = null;
        } else if (sameEdad.isEmpty()) 
        {
            result = null;
        } else 
        {
            result = sameEdad.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar clientes por edad ", edad);
        return result; 
    }
     
      public ClienteEntity findByPeso(Double peso) 
    {
        LOGGER.log(Level.INFO, "Consultando cliente por peso ", peso);
        // Se crea un query para buscar clientes con el peso que recibe el método como argumento. ":peso" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ClienteEntity e where e.peso = :peso", ClienteEntity.class);
        // Se remplaza el placeholder ":peso" con el valor del argumento 
        query = query.setParameter("peso", peso);
        // Se invoca el query se obtiene la lista resultado
        List<ClienteEntity> samePeso = query.getResultList();
        ClienteEntity result;
        if (samePeso == null)
        {
            result = null;
        } else if (samePeso.isEmpty()) 
        {
            result = null;
        } else 
        {
            result = samePeso.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar clientes por peso ", peso);
        return result; 
    }
    
      
       public ClienteEntity findByGluten(Boolean gluten) 
    {
        LOGGER.log(Level.INFO, "Consultando cliente por gluten ", gluten);
        // Se crea un query para buscar clientes con el gluten que recibe el método como argumento. ":gluten" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ClienteEntity e where e.gluten = :gluten", ClienteEntity.class);
        // Se remplaza el placeholder ":gluten" con el valor del argumento 
        query = query.setParameter("gluten", gluten);
        // Se invoca el query se obtiene la lista resultado
        List<ClienteEntity> sameGluten = query.getResultList();
        ClienteEntity result;
        if (sameGluten == null)
        {
            result = null;
        } else if (sameGluten.isEmpty()) 
        {
            result = null;
        } else 
        {
            result = sameGluten.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar clientes por gluten ", gluten);
        return result; 
    }
       
        public ClienteEntity findByLactosa(Boolean lactosa) 
    {
        LOGGER.log(Level.INFO, "Consultando cliente por lactosa ", lactosa);
        // Se crea un query para buscar clientes con el lactosa que recibe el método como argumento. ":lactosa" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ClienteEntity e where e.lactosa = :lactosa", ClienteEntity.class);
        // Se remplaza el placeholder ":lactosa" con el valor del argumento 
        query = query.setParameter("lactosa", lactosa);
        // Se invoca el query se obtiene la lista resultado
        List<ClienteEntity> sameLactosa = query.getResultList();
        ClienteEntity result;
        if (sameLactosa == null)
        {
            result = null;
        } else if (sameLactosa.isEmpty()) 
        {
            result = null;
        } else 
        {
            result = sameLactosa.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar clientes por lactosa ", lactosa);
        return result; 
    }
        
        public ClienteEntity findByNombre(String nombre) 
    {
        LOGGER.log(Level.INFO, "Consultando cliente por nombreName ", nombre);
        // Se crea un query para buscar clientes con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ClienteEntity e where e.nombre = :nombre", ClienteEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<ClienteEntity> sameNombre = query.getResultList();
        ClienteEntity result;
        if (sameNombre == null)
        {
            result = null;
        } else if (sameNombre.isEmpty()) 
        {
            result = null;
        } else 
        {
            result = sameNombre.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar clientes por nombre ", nombre);
        return result; 
    }
           
        public ClienteEntity findByObjetivos(String objetivos) 
    {
        LOGGER.log(Level.INFO, "Consultando cliente por objetivosName ", objetivos);
        // Se crea un query para buscar clientes con el objetivos que recibe el método como argumento. ":objetivos" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ClienteEntity e where e.objetivos = :objetivos", ClienteEntity.class);
        // Se remplaza el placeholder ":objetivos" con el valor del argumento 
        query = query.setParameter("objetivos", objetivos);
        // Se invoca el query se obtiene la lista resultado
        List<ClienteEntity> sameObjetivos = query.getResultList();
        ClienteEntity result;
        if (sameObjetivos == null)
        {
            result = null;
        } else if (sameObjetivos.isEmpty()) 
        {
            result = null;
        } else 
        {
            result = sameObjetivos.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar clientes por objetivos ", objetivos);
        return result; 
    }
    
        
}
