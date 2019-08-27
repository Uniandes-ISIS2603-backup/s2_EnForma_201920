/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Sebastián Clavijo
 */
@Stateless
public class DomicilioPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(DomicilioPersistence.class.getName());
    
    @PersistenceContext (unitName = "enformaPU")
    protected EntityManager em; 
    
    public DomicilioEntity create(DomicilioEntity domicilio)
    {
        
        
        //throw new java.lang.UnsupportedOperationException("Not supported yet.");
        em.persist(domicilio);
        return domicilio;
        
    }
    
    /**
     * Devuelve todas las editoriales de la base de datos.
     *
     * @return una lista con todas las editoriales que encuentre en la base de
     * datos, "select u from EditorialEntity u" es como un "select * from
     * EditorialEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<DomicilioEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas los domicilios");
        // Se crea un query para buscar todas las editoriales en la base de datos.
        TypedQuery query = em.createQuery("select u from DomicilioEntity u", DomicilioEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de editoriales.
        return query.getResultList();
    }
    
     /**
     * Busca si hay alguna editorial con el id que se envía de argumento
     *
     * @param domicilioId: id correspondiente a la editorial buscada.
     * @return una editorial.
     */
    public DomicilioEntity find(Long domicilioId) {
        LOGGER.log(Level.INFO, "Consultando domicilio con id={0}", domicilioId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EditorialEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(DomicilioEntity.class, domicilioId);
    }
    
        /**
     *
     * Borra un domicilio de la base de datos recibiendo como argumento el id
     * del domicilio
     *
     * @param domicilioId: id correspondiente al domicilio a borrar.
     */
    public void delete(Long domicilioId) {
        LOGGER.log(Level.INFO, "Borrando domicilio con id = {0}", domicilioId);
        // Se hace uso de mismo método que esta explicado en public EditorialEntity find(Long id) para obtener la editorial a borrar.
        DomicilioEntity entity = em.find(DomicilioEntity.class, domicilioId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el domicilio con id = {0}", domicilioId);
    }
    
    /**
     * Actualiza un domicilio.
     *
     * @param domicilioEntity: el domicilio que viene con los nuevos cambios.
     * Por ejemplo el precio pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un domicilio con los cambios aplicados.
     */
    public DomicilioEntity update(DomicilioEntity domicilioEntity) {
        LOGGER.log(Level.INFO, "Actualizando domicilio con id = {0}", domicilioEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar el domicilio con id = {0}", domicilioEntity.getId());
        return em.merge(domicilioEntity);
    }
    
     /**
     * Busca si hay algun domicilio con el id del domicilio que se envía de argumento
     *
     * @param idD: id Domicilio del domicilio que se está buscando
     * @return null si no existe ningun domicilio con el idD del argumento. Si
     * existe alguno devuelve el primero.
     */
    public DomicilioEntity findByIdDomicilio(String idD) {
        LOGGER.log(Level.INFO, "Consultando domicilios por idDomicilio ", idD);
        // Se crea un query para buscar domicilios con el id que recibe el método como argumento. ":idD" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From DomicilioEntity e where e.idDomicilio = :idD", DomicilioEntity.class);
        // Se remplaza el placeholder ":idD" con el valor del argumento 
        query = query.setParameter("idD", idD);
        // Se invoca el query se obtiene la lista resultado
        List<DomicilioEntity> sameIdD = query.getResultList();
        DomicilioEntity result;
        if (sameIdD == null) {
            result = null;
        } else if (sameIdD.isEmpty()) {
            result = null;
        } else {
            result = sameIdD.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar ldomicilios por id ", idD);
        return result;
    }
    
     /**
     * Busca si hay algun domicilio con el id del domicilio que se envía de argumento
     *
     * @param date: date Domicilio del domicilio que se está buscando
     * @return null si no existe ningun domicilio con el idD del argumento. Si
     * existe alguno devuelve el primero.
     */
    public DomicilioEntity findByDate(Date date) {
        LOGGER.log(Level.INFO, "Consultando domicilios por fecha ", date);
        // Se crea un query para buscar domicilios con el id que recibe el método como argumento. ":date" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From DomicilioEntity e where e.fecha = :date", DomicilioEntity.class);
        // Se remplaza el placeholder ":idD" con el valor del argumento 
        query = query.setParameter("date", date);
        // Se invoca el query se obtiene la lista resultado
        List<DomicilioEntity> sameDate = query.getResultList();
        DomicilioEntity result;
        if (sameDate == null) {
            result = null;
        } else if (sameDate.isEmpty()) {
            result = null;
        } else {
            result = sameDate.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar los domicilios por fecha ", date);
        return result;
    }
   
}