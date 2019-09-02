/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.CalificacionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jimmy Pepinosa
 */
@Stateless
public class CalificacionPersistence 
{
    @PersistenceContext(unitName = "enformaPU")
    protected EntityManager em;
    
    /**
     * Crea un autor en la base de datos
     * @param calificacion objeto calificacion que se creara en la base de datos
     * @return retorna la calificacion creada con un id dado por la base de datos
     */
    public CalificacionEntity create(CalificacionEntity calificacion)
    {
        em.persist(calificacion);
        return calificacion;
    }
    
    /**
     * Devuelve todas las calificaciones de la base de datos
     * @return una lista con todas las calificaciones que encuentre en la base datos, 
     * "select u from QuejasYReclamosEntity u" es como un "select * from QuejasYReclamosEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<CalificacionEntity> findAll()
    {
        // Se crea un query para buscar todas las calificaciones en la base de datos.
        TypedQuery query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de calificaciones.
        return query.getResultList();
    }
    
    /**
     * Busca si hay alguna calificacion con el id que se envia
     * @param calificacionId id correspondiente a la calificacion buscada
     * @return la calificacion buscada
     */
    public CalificacionEntity find(Long calificacionId)
    {
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
         *el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
         *Suponga que es algo similar a "select * from CalificacionEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(CalificacionEntity.class, calificacionId);
    }
    
    /**
     * Actualiza a la calificacion dada
     * @param calificacionEntity la calificacion que viene con el o los nuevos cambios
     * @return una calificacion con los cambios aplicados
     */
    public CalificacionEntity update(CalificacionEntity calificacionEntity)
    {
         /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
          *la calificacion con los cambios, esto es similar a 
          *"UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
          */
        return em.merge(calificacionEntity);
    }
    
    /**
     * Borra la calificacion dada por el id de la base de datos
     * @param calificacionId id correspondiente de la calificacion a borrar
     */
    public void delete(Long calificacionId)
    {
        // Se hace uso del mismo método que esta explicado en public CalificacionEntity find(Long id) para obtener la calificacion a borrar.
        CalificacionEntity calificacionEntity = em.find(CalificacionEntity.class, calificacionId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from  CalificacionEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(calificacionEntity);
    }
}

