/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.TarjetaPrepagoEntity;
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
public class TarjetaPrepagoPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(TarjetaPrepagoPersistence.class.getName());
    
    @PersistenceContext (unitName = "enformaPU")
    protected EntityManager em; 
    
    public TarjetaPrepagoEntity create(TarjetaPrepagoEntity tarjetaPrepago)
    {
        
        
        //throw new java.lang.UnsupportedOperationException("Not supported yet.");
        em.persist(tarjetaPrepago);
        return tarjetaPrepago;
        
    }
    
    /**
     * Devuelve todas las tarjetas prepago de la base de datos.
     *
     * @return una lista con todas las tarjetas prepago que encuentre en la base de
     * datos, "select u from TarjetaPrepagoEntity u" es como un "select * from
     * TarjetaPrepagoEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<TarjetaPrepagoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las tarjetas prepago");
        // Se crea un query para buscar todas las tarjetas prepago en la base de datos.
        TypedQuery query = em.createQuery("select u from TarjetaPrepagoEntity u", TarjetaPrepagoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de editoriales.
        return query.getResultList();
    }
    
     /**
     * Busca si hay alguna tarjeta con el id que se envía de argumento
     *
     * @param tarjetaPrepagoId: id correspondiente a la tarjeta buscada.
     * @return una tarjeta.
     */
    public TarjetaPrepagoEntity find(Long tarjetaPrepagoId) {
        LOGGER.log(Level.INFO, "Consultando tarjeta prepago con id={0}", tarjetaPrepagoId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EditorialEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(TarjetaPrepagoEntity.class, tarjetaPrepagoId);
    }
    
     /**
     *
     * Borra un domicilio de la base de datos recibiendo como argumento el id
     * del domicilio
     *
     * @param tarjetaPrepagoId: id correspondiente al domicilio a borrar.
     */
    public void delete(Long tarjetaPrepagoId) {
        LOGGER.log(Level.INFO, "Borrando tarjetaPrepago con id = {0}", tarjetaPrepagoId);
        // Se hace uso de mismo método que esta explicado en public EditorialEntity find(Long id) para obtener la editorial a borrar.
        TarjetaPrepagoEntity entity = em.find(TarjetaPrepagoEntity.class, tarjetaPrepagoId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la tarjeta prepago con id = {0}", tarjetaPrepagoId);
    }
    
    /**
     * Actualiza una tarjeta prepago.
     *
     * @param tarjetaPrepagoEntity: el domicilio que viene con los nuevos cambios.
     * Por ejemplo el precio pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un domicilio con los cambios aplicados.
     */
    public TarjetaPrepagoEntity update(TarjetaPrepagoEntity tarjetaPrepagoEntity) {
        LOGGER.log(Level.INFO, "Actualizando tarjeta prepago con id = {0}", tarjetaPrepagoEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar la tarjeta prepago con id = {0}", tarjetaPrepagoEntity.getId());
        return em.merge(tarjetaPrepagoEntity);
    }
    
     /**
     * Busca si hay alguna tarjeta prepago con el id de la tarjeta que se envía de argumento
     *
     * @param idTP: id tarjeta de la tarjeta que se está buscando
     * @return null si no existe ninguna tarjeta con el idD del argumento. Si
     * existe alguno devuelve el primero.
     */
    public TarjetaPrepagoEntity findByIdTarjetaPrepago(String idTP) {
        LOGGER.log(Level.INFO, "Consultando tarjetas prepago por idTarjetaPrepago {0}", idTP);
        // Se crea un query para buscar domicilios con el id que recibe el método como argumento. ":idD" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From TarjetaPrepagoEntity e where e.numTarjetaPrepago = :idTP", TarjetaPrepagoEntity.class);
        // Se remplaza el placeholder ":idD" con el valor del argumento 
        query = query.setParameter("idTP", idTP);
        // Se invoca el query se obtiene la lista resultado
        List<TarjetaPrepagoEntity> sameIdTP = query.getResultList();
        TarjetaPrepagoEntity result;
        if (sameIdTP == null) {
            result = null;
        } else if (sameIdTP.isEmpty()) {
            result = null;
        } else {
            result = sameIdTP.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar tarjetas prepago por id {0}", idTP);
        return result;
    }
    
     /**
     * Busca si hay alguna tarjeta prepago con el saldo de la tarjeta que se envía de argumento
     *
     * @param saldo: saldo de la tarjeta que se está buscando
     * @return null si no existe ninguna tarjeta con el saldo del argumento. Si
     * existe alguno devuelve el primero.
     */
    public TarjetaPrepagoEntity findBySaldo(double saldo) {
        LOGGER.log(Level.INFO, "Consultando tarjetas prepago por saldo {0}", saldo);
        // Se crea un query para buscar domicilios con el id que recibe el método como argumento. ":date" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From TarjetaPrepagoEntity e where e.saldo = :saldo", TarjetaPrepagoEntity.class);
        // Se remplaza el placeholder ":idD" con el valor del argumento 
        query = query.setParameter("saldo", saldo);
        // Se invoca el query se obtiene la lista resultado
        List<TarjetaPrepagoEntity> sameSaldo = query.getResultList();
        TarjetaPrepagoEntity result;
        if (sameSaldo == null) {
            result = null;
        } else if (sameSaldo.isEmpty()) {
            result = null;
        } else {
            result = sameSaldo.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar tarjetas prepago por saldo {0}", saldo);
        return result;
    }
    
     /**
     * Busca si hay alguna tarjeta prepago con los puntos de la tarjeta que se envía de argumento
     *
     * @param puntos: puntos de la tarjeta que se está buscando
     * @return null si no existe ninguna tarjeta con los puntos del argumento. Si
     * existe alguno devuelve el primero.
     */
    public TarjetaPrepagoEntity findByPoints(double puntos) {
        LOGGER.log(Level.INFO, "Consultando tarjetas prepago por puntos {0}", puntos);
        // Se crea un query para buscar domicilios con el id que recibe el método como argumento. ":date" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From TarjetaPrepagoEntity e where e.puntos = :puntos", TarjetaPrepagoEntity.class);
        // Se remplaza el placeholder ":idD" con el valor del argumento 
        query = query.setParameter("puntos", puntos);
        // Se invoca el query se obtiene la lista resultado
        List<TarjetaPrepagoEntity> sameSaldo = query.getResultList();
        TarjetaPrepagoEntity result;
        if (sameSaldo == null) {
            result = null;
        } else if (sameSaldo.isEmpty()) {
            result = null;
        } else {
            result = sameSaldo.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar tarjetas prepago por puntos {0}", puntos);
        return result;
    }

}
