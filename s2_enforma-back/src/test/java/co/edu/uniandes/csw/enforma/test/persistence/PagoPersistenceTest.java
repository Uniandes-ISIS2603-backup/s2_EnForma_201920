/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;

import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import co.edu.uniandes.csw.enforma.entities.PagoEntity;
import co.edu.uniandes.csw.enforma.persistence.PagoPersistence;
import com.gs.collections.impl.list.fixed.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


/**
 *
 * @author ev.jaimes
 */
@RunWith(Arquillian.class)
public class PagoPersistenceTest {

    @Inject
    PagoPersistence pp;

    @PersistenceContext
    protected EntityManager em;
    
    @Inject
    UserTransaction utx;

    private List<PagoEntity> data = new ArrayList<PagoEntity>();

    private List<DomicilioEntity> dataDomicilio= new ArrayList<DomicilioEntity>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class).addPackage(PagoEntity.class.getPackage()).addPackage(PagoPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

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
        em.createQuery("delete from PagoEntity").executeUpdate();

    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        
     
        for (int i = 0; i < 3; i++) {
            PagoEntity entity = factory.manufacturePojo(PagoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Pago.
     */
    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        PagoEntity result = pp.create(newEntity);

        Assert.assertNotNull(result);

        PagoEntity entity = em.find(PagoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de Pagos.
     */
    @Test
    public void getListTest() {
        List<PagoEntity> list = pp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PagoEntity ent : list) {
            boolean found = false;
            for (PagoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Pago.
     */
    @Test
    public void getTest() {
        PagoEntity entity = data.get(0);
        PagoEntity newEntity = pp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNumeroTarjeta(), newEntity.getNumeroTarjeta());
        Assert.assertEquals(entity.getMonto(), newEntity.getMonto());
    }

    /**
     * Prueba para eliminar un Pago.
     */
    @Test
    public void deleteTest() {
        PagoEntity entity = data.get(0);
        pp.delete(entity.getId());
        PagoEntity deleted = em.find(PagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Pago.
     */
    @Test
    public void updateTest() {
        PagoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);

        newEntity.setId(entity.getId());

        pp.update(newEntity);

        PagoEntity resp = em.find(PagoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
}
