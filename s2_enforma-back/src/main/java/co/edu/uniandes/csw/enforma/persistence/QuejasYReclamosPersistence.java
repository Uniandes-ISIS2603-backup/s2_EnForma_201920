/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
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
public class QuejasYReclamosPersistence 
{
    @PersistenceContext(unitName = "enformaPU")
    protected EntityManager em;
    
    /**
     * Crea una queja o reclamo en la base de datos
     * @param quejasYReclamos un objeto quejas y reclamos que se creara en la base de datos
     * @return retorna la queja o reclamo creada con un id dado pro la base de datos
     */
    public QuejasYReclamosEntity create(QuejasYReclamosEntity quejasYReclamos)
    {
        em.persist(quejasYReclamos);
        return quejasYReclamos;
    }
    
    /**
     * Devuelve todas las quejas y reclamos de la base de datos
     * @return una lista con todas las quejas y reclamos que encuentre en la base de datos
     * "select u from QuejasYReclamosEntity u" es como un "select * from QuejasYReclamosEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<QuejasYReclamosEntity> findAll()
    {
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
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
         *el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
         *Suponga que es algo similar a "select * from QuejasYReclamosEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(QuejasYReclamosEntity.class, quejasYReclamosId);
    }
    
    /**
     * Actualiza la queja o reclamo dada
     * @param quejasYReclamos la queja o reclamo que viene con el o los nuevos cambios
     * @return una queja o reclamo con los cambios aplicados
     */
    public QuejasYReclamosEntity updte(QuejasYReclamosEntity quejasYReclamos)
    {
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
        // Se hace uso del mismo método que esta explicado en public QuejasYReclamosEntity find(Long id) para obtener la queja o reclamo a borrar.
        QuejasYReclamosEntity quejasYReclamosEntity = em.find(QuejasYReclamosEntity.class, quejasYReclamosId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from  QuejasYReclamosEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(quejasYReclamosEntity);
    }
    
}
