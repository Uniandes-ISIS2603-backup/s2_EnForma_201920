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
import javax.persistence.TypedQuery;


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
        LOGGER.log(Level.INFO, "Consultando la comida Tipo con id = {0}",ComidaTipoID);
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
        LOGGER.log(Level.INFO, "Actualizando la ComidaTipo con id = {0}", comidaTipoEnt.getId());
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
        LOGGER.log(Level.INFO, "Se Está Borrando la Comida tipo con id = {0}", comidaTipoId);
        ComidaTipoEntity comidaTipoEntity = em.find(ComidaTipoEntity.class, comidaTipoId);
        
        em.remove(comidaTipoEntity);
    }
    
    /**
     * Busca si hay alguna ComidaTipo por el momento del día que se envía de argumento
     *
     * @param momentoDelDia: momento del día de la comidaTipo que se está buscando
     * @return null si no existe ninguna ComidaTipo con el momentoDelDia del argumento. Si
     * existe alguno devuelve el primero.
     */
    public ComidaTipoEntity findByMomentoDelDia(String momentoDelDia) {
        LOGGER.log(Level.INFO, "Consultando libros por momentoDelDia {0}", momentoDelDia);
        // Se crea un query para buscar ComidaTipo con el momentoDelDia que recibe el método como argumento. ":momentoDelDia" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ComidaTipoEntity e where e.momentoDelDia = :momentoDelDia", ComidaTipoEntity.class);
        // Se remplaza el placeholder ":momentoDelDia" con el valor del argumento 
        query = query.setParameter("momentoDelDia", momentoDelDia);
        // Se invoca el query se obtiene la lista resultado
        List<ComidaTipoEntity> sameMomentoDelDia = query.getResultList();
        ComidaTipoEntity result;
        if (sameMomentoDelDia == null) {
            result = null;
        } else if (sameMomentoDelDia.isEmpty()) {
            result = null;
        } else {
            result = sameMomentoDelDia.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar libros por momentoDelDia {0}", momentoDelDia);
        return result;
    }
    
    
        /**
     * Busca si hay alguna ComidaTipo por el calorias que se envía de argumento
     *
     * @param calorias: momento del día de la comidaTipo que se está buscando
     * @return null si no existe ningun ComidaTipo con el calorias del argumento. Si
     * existe alguno devuelve el primero.
     */
    public ComidaTipoEntity findByCalorias(Integer calorias) {
        LOGGER.log(Level.INFO, "Consultando libros por calorias {0}", calorias);
        // Se crea un query para buscar ComidasTipo con el calorias que recibe el método como argumento. ":calorias" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ComidaTipoEntity e where e.calorias = :calorias", ComidaTipoEntity.class);
        // Se remplaza el placeholder ":calorias" con el valor del argumento 
        query = query.setParameter("calorias", calorias);
        // Se invoca el query se obtiene la lista resultado
        List<ComidaTipoEntity> sameCalorias = query.getResultList();
        ComidaTipoEntity result;
        if (sameCalorias == null) {
            result = null;
        } else if (sameCalorias.isEmpty()) {
            result = null;
        } else {
            result = sameCalorias.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar libros por calorias {0} ", calorias);
        return result;
    }
    
        /**
     * Busca si hay alguna ComidaTipo por el menú que se envía de argumento
     *
     * @param menu: menu de la comidaTipo que se está buscando
     * @return null si no existe ninguna ComidaTipo con el menu del argumento. Si
     * existe alguno devuelve el primero.
     */
    public ComidaTipoEntity findByMenu(String menu) {
        LOGGER.log(Level.INFO, "Consultando comidas por menu {0}", menu);
        // Se crea un query para buscar libros con el menu que recibe el método como argumento. ":menu" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ComidaTipoEntity e where e.menu = :menu", ComidaTipoEntity.class);
        // Se remplaza el placeholder ":menu" con el valor del argumento 
        query = query.setParameter("menu", menu);
        // Se invoca el query se obtiene la lista resultado
        List<ComidaTipoEntity> sameMenu = query.getResultList();
        ComidaTipoEntity result;
        if (sameMenu == null) {
            result = null;
        } else if (sameMenu.isEmpty()) {
            result = null;
        } else {
            result = sameMenu.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar comidas por menu {0} ", menu);
        return result;
    }
    
    
         /**
     * Busca si hay alguna ComidaTipo por el nombre que se envía de argumento
     *
     * @param nombre: nombre de la comidaTipo que se está buscando
     * @return null si no existe ningun libro con el nombre del argumento. Si
     * existe alguno devuelve el primero.
     */
    public ComidaTipoEntity findByNombre(String nombre) {
        LOGGER.log(Level.INFO, "Consultando comidas por nombre {0} ", nombre);
        // Se crea un query para buscar libros con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ComidaTipoEntity e where e.nombre = :nombre", ComidaTipoEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<ComidaTipoEntity> sameNombre = query.getResultList();
        ComidaTipoEntity result;
        if (sameNombre == null) {
            result = null;
        } else if (sameNombre.isEmpty()) {
            result = null;
        } else {
            result = sameNombre.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar comidas por nombre {0} ", nombre);
        return result;
    }
    
}
