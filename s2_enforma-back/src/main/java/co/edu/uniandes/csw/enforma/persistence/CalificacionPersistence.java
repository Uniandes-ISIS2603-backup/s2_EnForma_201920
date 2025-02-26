/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.CalificacionEntity;
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
 * @author Jimmy Pepinosa
 */
@Stateless
public class CalificacionPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());
    
    @PersistenceContext(unitName = "enformaPU")
    protected EntityManager em;
    
    /**
     * Crea un autor en la base de datos
     * @param calificacion objeto calificacion que se creara en la base de datos
     * @return retorna la calificacion creada con un id dado por la base de datos
     */
    public CalificacionEntity create(CalificacionEntity calificacion)
    {
        LOGGER.log(Level.INFO,"Creando una calificacion nueva");
        em.persist(calificacion);
        LOGGER.log(Level.INFO,"Calificacion Creada");
        return calificacion;
    }
    
    /**
     * Devuelve todas las calificaciones asociadas a una dieta de la base de datos
     * @return una lista con todas las calificaciones que encuentre en la base datos de la dieta que se quiere
     */
    public List<CalificacionEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultando todas las calificaciones");
        // Se crea un query para buscar todas las quejas y relcamos en la base de datos.
        TypedQuery<CalificacionEntity> query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de quejas y reclamos.
        return query.getResultList();
    }
    
    public CalificacionEntity find(Long calificacionId)
    {
        LOGGER.log(Level.INFO, "Consultando la calificaion con id= {0}", calificacionId);
        return em.find(CalificacionEntity.class, calificacionId);
    }
    
    
    /**
     * Busca si hay alguna calificacion con el puntjae de la calificacion que se envía de argumento
     *
     * @param puntaje: puntaje Domicilio del domicilio que se está buscando
     * @return null si no existe ninguna calificacion con el puntaje del argumento. Si
     * existe alguno devuelve el primero.
     */
    public CalificacionEntity findByPuntaje(int puntaje) {
        LOGGER.log(Level.INFO, "Consultando calificaciones por puntaje {0}", puntaje);
        // Se crea un query para buscar calificaciones con el puntjae que recibe el método como argumento. ":puntjae" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From CalificacionEntity e where e.puntaje = :puntaje", CalificacionEntity.class);
        // Se remplaza el placeholder ":puntjae" con el valor del argumento 
        query = query.setParameter("puntaje", puntaje);
        // Se invoca el query se obtiene la lista resultado
        List<CalificacionEntity> samePuntaje = query.getResultList();
        CalificacionEntity result;
        if (samePuntaje == null) 
        {
            result = null;
        } 
        else if (samePuntaje.isEmpty()) 
        {
            result = null;
        } 
        else 
        {
            result = samePuntaje.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar puntajeCalificacion por calificacion {0}", puntaje);
        return result;
    }
    
    /**
     * Busca si hay alguna calificacion con la fecha de la calificacion que se envía de argumento
     *
     * @param fecha: fecha de la Calificacion que se está buscando
     * @return null si no existe ninguna calificacion con la fecha del argumento. Si
     * existe alguno devuelve el primero.
     */
    public CalificacionEntity findByFecha(Date fecha) {
        LOGGER.log(Level.INFO, "Consultando calificaciones por fecha {0}", fecha);
        // Se crea un query para buscar calificaciones por la fecha que recibe el método como argumento. ":fecha" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From CalificacionEntity e where e.fecha = :fecha", CalificacionEntity.class);
        // Se remplaza el placeholder ":fecha" con el valor del argumento 
        query = query.setParameter("fecha", fecha);
        // Se invoca el query se obtiene la lista resultado
        List<CalificacionEntity> sameDate = query.getResultList();
        CalificacionEntity result;
        if (sameDate == null) 
        {
            result = null;
        } 
        else if (sameDate.isEmpty()) 
        {
            result = null;
        } 
        else 
        {
            result = sameDate.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar las calificaciones por fecha {0}", fecha);
        return result;
    }
    

    
    /**
     * Actualiza a la calificacion dada
     * @param calificacionEntity la calificacion que viene con el o los nuevos cambios
     * @return una calificacion con los cambios aplicados
     */
    public CalificacionEntity update(CalificacionEntity calificacionEntity)
    {
        LOGGER.log(Level.INFO, "Actualizando la calificacion con Id={0}", calificacionEntity.getId());
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
        LOGGER.log(Level.INFO, "Borrando la calificacion con el Id={0}", calificacionId);
        // Se hace uso del mismo método que esta explicado en public CalificacionEntity findByClienteYDietaTipo(Long id) para obtener la calificacion a borrar.
        CalificacionEntity calificacionEntity = em.find(CalificacionEntity.class, calificacionId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from  CalificacionEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(calificacionEntity);
    }
}