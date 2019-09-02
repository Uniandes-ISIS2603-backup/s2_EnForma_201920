/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author Jose Manuel Flórez
 */

@Stateless


public class ComidaTipoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ComidaTipoPersistence.class.getName());
    
    @PersistenceContext (unitName = "enformaPU")
    
    protected EntityManager em;
    
     /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param ComidaTipoEntity objeto Comida Tipo que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ComidaTipoEntity create(ComidaTipoEntity comidaTipo)
    {
        LOGGER.log(Level.INFO, "Creando una comida tipo nueva");
        em.persist(comidaTipo);
          LOGGER.log(Level.INFO, "Comida Tipo creada");
        return comidaTipo;
        
        
    
    }
    
     /**
     * Devuelve todas las Comida Tipo de la base de datos.
     *
     * @return una lista con todos las Comida Tipo que encuentre en la base de datos,
     * "select u from ComidaTipoEntity u" es como un "select * from ComidaTipoEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    
    public List <ComidaTipoEntity> findAll ()
    {
        LOGGER.log(Level.INFO, "Consultando todas las Comidas Tipo");
        Query q  = em.createQuery("select u from ComidaTipoEntity u");
        return q.getResultList();
        
    }
    
     /**
     * Busca si hay alguna Comida Tipo  con el id que se envía de argumento
     *
     * @param ComidaTipoId: id correspondiente de la ComidaTipo buscada.
     * @return una Comida Tipo.
     */
    
    public ComidaTipoEntity find (Long ComidaTipoID)
    {
        LOGGER.log(Level.INFO, " Consultando el libro con id =" + ComidaTipoID, ComidaTipoID);
        return em.find(ComidaTipoEntity.class, ComidaTipoID);
   
    
    }
    
     /**
     * Actualiza una Comida Tipo.
     *
     * @param ComidaTipoEntity: La Comida Tipo que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return Una Comida Tipo con los cambios aplicados.
     */
    public ComidaTipoEntity update (ComidaTipoEntity comidaTipoEnt)
    {
        LOGGER.log(Level.INFO, "Actualizando la ComidaTipo con id = " + comidaTipoEnt.getId(), comidaTipoEnt.getId());
        return em.merge(comidaTipoEnt);
    }
    
     /**
     *
     * Borra una Comida Tipo de la base de datos recibiendo como argumento el id de la
     * Comida Tipo.
     *
     * @param comidaTipoId: id correspondiente a la Comida Tipo a borrar.
     */
    public void delete(Long comidaTipoId) 
    {
        LOGGER.log(Level.INFO, "Se Está Borrando la Comida tipo con id =" + comidaTipoId, comidaTipoId);
        ComidaTipoEntity comidaTipoEntity = em.find(ComidaTipoEntity.class, comidaTipoId);
        
        em.remove(comidaTipoEntity);
    }
    
    
    
}
