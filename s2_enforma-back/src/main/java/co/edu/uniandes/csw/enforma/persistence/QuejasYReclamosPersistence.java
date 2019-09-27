/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
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
public class QuejasYReclamosPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(QuejasYReclamosPersistence.class.getName());
    
    @PersistenceContext(unitName = "enformaPU")
    protected EntityManager em;
    
    /**
     * Crea una queja o reclamo en la base de datos
     * @param quejasYReclamos un objeto quejas y reclamos que se creara en la base de datos
     * @return retorna la queja o reclamo creada con un id dado pro la base de datos
     */
    public QuejasYReclamosEntity create(QuejasYReclamosEntity quejasYReclamos)
    {
        LOGGER.log(Level.INFO,"Creando una quejaYReclamo nueva");
        em.persist(quejasYReclamos);
        LOGGER.log(Level.INFO,"QuejaYReclamo Creada");
        return quejasYReclamos;
    }
    
    /**
     * Devuelve todas las quejas y reclamos de la base de datos
     * @return una lista con todas las quejas y reclamos que encuentre en la base de datos
     * "select u from QuejasYReclamosEntity u" es como un "select * from QuejasYReclamosEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<QuejasYReclamosEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultando todas las quejas y reclamow");
        // Se crea un query para buscar todas las quejas y relcamos en la base de datos.
        TypedQuery query = em.createQuery("select u from QuejasYReclamosEntity u", QuejasYReclamosEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de quejas y reclamos.
        return query.getResultList();
    }
    
    /**
     * Busca si hay alguna queja o reclamo con el id que se envia
     * @param quejasYReclamosId id correspondiente a la queja o reclamo buscada
     * @return la queja o reclamo buscada
     */
    public QuejasYReclamosEntity find(Long quejasYReclamosId)
    {
        LOGGER.log(Level.INFO, "Consultando la queja y reclamos con id={0}", quejasYReclamosId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
         *el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
         *Suponga que es algo similar a "select * from QuejasYReclamosEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(QuejasYReclamosEntity.class, quejasYReclamosId);
    }
    
        /**
     * Busca si hay alguna queja o reclamo con el asunto de la queja o reclamo que se envía de argumento
     *
     * @param asunto: asunto de la queja o reclamo que se está buscando
     * @return null si no existe ninguna queja o reclamo con el asunto del argumento. Si
     * existe alguno devuelve el primero.
     */
    public QuejasYReclamosEntity findByAsunto(String asunto) {
        LOGGER.log(Level.INFO, "Consultando quejas y reclamos por asunto", asunto);
        // Se crea un query para buscar calificaciones con el puntjae que recibe el método como argumento. ":asunto" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From QuejasYReclamosEntity e where e.asunto = :asunto", QuejasYReclamosEntity.class);
        // Se remplaza el placeholder ":asunto" con el valor del argumento 
        query = query.setParameter("asunto", asunto);
        // Se invoca el query se obtiene la lista resultado
        List<QuejasYReclamosEntity> sameAsunto = query.getResultList();
        QuejasYReclamosEntity result;
        if (sameAsunto == null) {
            result = null;
        } else if (sameAsunto.isEmpty()) {
            result = null;
        } else {
            result = sameAsunto.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar asunto por quejas y reclamos ", asunto);
        return result;
    }
    
    /**
     * Busca si hay alguna queja o reclamo con la fecha de la queja o reclamo que se envía de argumento
     *
     * @param fecha: fecha de la queja o reclamo que se está buscando
     * @return null si no existe ninguna queja o reclamo con la fecha del argumento. Si
     * existe alguno devuelve el primero.
     */
    public QuejasYReclamosEntity findByFecha(Date fecha) {
        LOGGER.log(Level.INFO, "Consultando quejas y reclamos por fecha ", fecha);
        // Se crea un query para buscar quejas y reclamos por la fecha que recibe el método como argumento. ":fecha" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From QuejasYReclamosEntity e where e.fecha = :fecha", QuejasYReclamosEntity.class);
        // Se remplaza el placeholder ":fecha" con el valor del argumento 
        query = query.setParameter("fecha", fecha);
        // Se invoca el query se obtiene la lista resultado
        List<QuejasYReclamosEntity> sameDate = query.getResultList();
        QuejasYReclamosEntity result;
        if (sameDate == null) {
            result = null;
        } else if (sameDate.isEmpty()) {
            result = null;
        } else {
            result = sameDate.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar las quejas y reclamos por fecha ", fecha);
        return result;
    }
    
    /**
     * Actualiza la queja o reclamo dada
     * @param quejasYReclamos la queja o reclamo que viene con el o los nuevos cambios
     * @return una queja o reclamo con los cambios aplicados
     */
    public QuejasYReclamosEntity update(QuejasYReclamosEntity quejasYReclamos)
    {
        LOGGER.log(Level.INFO, "Actualizando la queja y relcamos con Id={0}", quejasYReclamos.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
          *la queja o reclamo con los cambios, esto es similar a 
          *"UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
          */
        return em.merge(quejasYReclamos);
    }
    
    /**
     * Borra la queja o reclamo dada por el id de la base de datos
     * @param quejasYReclamosId id correspondiente a la queja o reclamo a borrar
     */
    public void delete(Long quejasYReclamosId)
    {
        LOGGER.log(Level.INFO, "Borrando el autor con el Id={0}", quejasYReclamosId);
        // Se hace uso del mismo método que esta explicado en public QuejasYReclamosEntity find(Long id) para obtener la queja o reclamo a borrar.
        QuejasYReclamosEntity quejasYReclamosEntity = em.find(QuejasYReclamosEntity.class, quejasYReclamosId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from  QuejasYReclamosEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(quejasYReclamosEntity);
    }
    
}
