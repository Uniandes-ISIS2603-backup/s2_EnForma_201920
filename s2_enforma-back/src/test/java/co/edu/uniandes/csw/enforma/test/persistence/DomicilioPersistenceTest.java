/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;

import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import co.edu.uniandes.csw.enforma.persistence.DomicilioPersistence;
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
 * @author Juan sebastian Clavijo
 */
@RunWith(Arquillian.class)
public class DomicilioPersistenceTest 
{
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DomicilioEntity.class)
                .addClass(DomicilioPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    DomicilioPersistence dp;
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<DomicilioEntity> data = new ArrayList<DomicilioEntity>();
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        DomicilioEntity domicilio = factory.manufacturePojo(DomicilioEntity.class);
        DomicilioEntity result = dp.create(domicilio);
        Assert.assertNotNull(result);
        
        DomicilioEntity entity = em.find(DomicilioEntity.class, result.getId());
        Assert.assertEquals(domicilio.getId(), entity.getId());
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
        em.createQuery("delete from DomicilioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            DomicilioEntity entity = factory.manufacturePojo(DomicilioEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    
    /**
     * prueba para consultar la lista de domicilios 
     */
    @Test 
    public void getDomiciliosTest()
    {
        List<DomicilioEntity> list = dp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (DomicilioEntity ent : list) {
            boolean found = false;
            for (DomicilioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
     /**
     * Prueba para consultar un domicilio.
     */
    @Test
    public void getDomicilioTest() {
       DomicilioEntity entity = data.get(0);
       DomicilioEntity newEntity = dp.find(entity.getId());
       Assert.assertNotNull(newEntity);
       Assert.assertEquals(entity.getLugarEntrega(), newEntity.getLugarEntrega());
       Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
    }
    
        /**
     * Prueba para eliminar una Editorial.
     */
    @Test
    public void deleteDomicilioTest() {
        DomicilioEntity entity = data.get(0);
        dp.delete(entity.getId());
        DomicilioEntity deleted = em.find(DomicilioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar un domicilio.
     */
    @Test
    public void updateEditorialTest() {
        DomicilioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DomicilioEntity newEntity = factory.manufacturePojo(DomicilioEntity.class);

        newEntity.setId(entity.getId());

        dp.update(newEntity);

        DomicilioEntity resp = em.find(DomicilioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getLugarEntrega(), resp.getLugarEntrega());
    }
    
     /**
     * Prueba para consultar un domicilio por idDomicilio
     */
    @Test
    public void findDomicilioByIdDomicilioTest() {
        DomicilioEntity entity = data.get(0);
        DomicilioEntity newEntity = dp.findByIdDomicilio(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getLugarEntrega(), newEntity.getLugarEntrega());

        newEntity = dp.findByIdDomicilio(null);
        Assert.assertNull(newEntity);
    }
    
     /**
     * Prueba para consultar un domicilio por idDomicilio
     */
    @Test
    public void findDomicilioByDateTest() {
        DomicilioEntity entity = data.get(0);
        DomicilioEntity newEntity = dp.findByDate(entity.getFecha());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());

        newEntity = dp.findByDate(null);
        Assert.assertNull(newEntity);
    }
}
