/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;

import co.edu.uniandes.csw.enforma.entities.TarjetaPrepagoEntity;
import co.edu.uniandes.csw.enforma.persistence.TarjetaPrepagoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Juan Sebastián Clavijo
 */
@RunWith(Arquillian.class)
public class TarjetaPrepagoPersistenceTest 
{
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(TarjetaPrepagoEntity.class)
                .addClass(TarjetaPrepagoPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    TarjetaPrepagoPersistence tpp;
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<TarjetaPrepagoEntity> data = new ArrayList<TarjetaPrepagoEntity>();
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaPrepagoEntity tarjetaPrepago = factory.manufacturePojo(TarjetaPrepagoEntity.class);
        TarjetaPrepagoEntity result = tpp.create(tarjetaPrepago);
        Assert.assertNotNull(result);
        
        TarjetaPrepagoEntity entity = em.find(TarjetaPrepagoEntity.class, result.getId());
        Assert.assertEquals(tarjetaPrepago.getId(), entity.getId());
    }
    
     /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
     /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from TarjetaPrepagoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            TarjetaPrepagoEntity entity = factory.manufacturePojo(TarjetaPrepagoEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    
    /**
     * prueba para consultar la lista de tarjetas prepago 
     */
    @Test 
    public void getTarjetasPrepagoTest()
    {
        List<TarjetaPrepagoEntity> list = tpp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TarjetaPrepagoEntity ent : list) {
            boolean found = false;
            for (TarjetaPrepagoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
     /**
     * Prueba para consultar una tarjeta prepago.
     */
    @Test
    public void getTarjetaPrepagoTest() {
       TarjetaPrepagoEntity entity = data.get(0);
       TarjetaPrepagoEntity newEntity = tpp.find(entity.getId());
       Assert.assertNotNull(newEntity);
       Assert.assertEquals(entity.getIdTarjetaPrepago(), newEntity.getIdTarjetaPrepago());
       Assert.assertEquals(entity.getPuntos(), newEntity.getPuntos(), 0.001);
       Assert.assertEquals(entity.getSaldo(), newEntity.getSaldo(), 0.001);
    }
    
     /**
     * Prueba para eliminar una tarjeta prepago.
     */
    @Test
    public void deletetarjetaPrepagoTest() {
        TarjetaPrepagoEntity entity = data.get(0);
        tpp.delete(entity.getId());
        TarjetaPrepagoEntity deleted = em.find(TarjetaPrepagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar una tarjeta prepago.
     */
    @Test
    public void updateTarjetaPrepagoTest() {
        TarjetaPrepagoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaPrepagoEntity newEntity = factory.manufacturePojo(TarjetaPrepagoEntity.class);

        newEntity.setId(entity.getId());

        tpp.update(newEntity);

        TarjetaPrepagoEntity resp = em.find(TarjetaPrepagoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getSaldo(), resp.getSaldo(), 0.001);
    }
    
     /**
     * Prueba para consultar una tarjeta prepago por idTarjetaPrepago
     */
    @Test
    public void findTarjetaPrepagoByIdTarjetaPrepagoTest() {
        TarjetaPrepagoEntity entity = data.get(0);
        TarjetaPrepagoEntity newEntity = tpp.findByIdTarjetaPrepago(entity.getIdTarjetaPrepago());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getIdTarjetaPrepago(), newEntity.getIdTarjetaPrepago());

        newEntity = tpp.findByIdTarjetaPrepago(null);
        Assert.assertNull(newEntity);
    }
    
     /**
     * Prueba para consultar una tarjeta por saldo
     */
    @Test
    public void findTarjetaPrepagoBySaldoTest() {
        TarjetaPrepagoEntity entity = data.get(0);
        TarjetaPrepagoEntity newEntity = tpp.findBySaldo(entity.getSaldo());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getSaldo(), newEntity.getSaldo(),0.001);

      
    }
    
     /**
     * Prueba para consultar una tarjeta por puntos
     */
    @Test
    public void findTarjetaPrepagoByPointsTest() {
        TarjetaPrepagoEntity entity = data.get(0);
        TarjetaPrepagoEntity newEntity = tpp.findByPoints(entity.getPuntos());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPuntos(), newEntity.getPuntos(),0.001);

        
    }
    
}
