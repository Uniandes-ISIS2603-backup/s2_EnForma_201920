/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;

import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.persistence.DietaTipoPersistence;
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
 * @author Julio Morales
 */
@RunWith(Arquillian.class)
public class DietaTipoPersistenceTest {
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DietaTipoEntity.class.getPackage())
                .addPackage(DietaTipoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private DietaTipoPersistence  dietaTipoPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    
    private List<DietaTipoEntity> data = new ArrayList<DietaTipoEntity>();
    
    
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
        em.createQuery("delete from DietaTipoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            DietaTipoEntity entity = factory.manufacturePojo(DietaTipoEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    
    
    
    /**
     * Prueba para crear una DietaTipo.
     */
    @Test
    public void createDietaTipoTest()
    {
        
        PodamFactory factory = new PodamFactoryImpl();
        
        DietaTipoEntity dietaTipo = factory.manufacturePojo(DietaTipoEntity.class);
        
        DietaTipoEntity result = dietaTipoPersistence.create(dietaTipo);
        Assert.assertNotNull(result);
        
        DietaTipoEntity entity = em.find(DietaTipoEntity.class, result.getId());
        Assert.assertEquals(dietaTipo.getNombre(), entity.getNombre());
    }
    
    /**
     * Prueba para consultar la lista de DietasTipo.
     */
    @Test
    public void getDietasTipoTest(){
        List<DietaTipoEntity> list = dietaTipoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(DietaTipoEntity ent : list)
        {
            boolean found = false;
            for(DietaTipoEntity entity : data)
            {
                if(ent.getId().equals(entity.getId()))
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    
    
    
    /**
     * Prueba para consultar una DietaTipo.
     */
    @Test
    public void getDietaTipoTest(){
        DietaTipoEntity entity = data.get(0);
        DietaTipoEntity newEntity = dietaTipoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }
    
    
    
    /**
     * Prueba para actualizar una DietaTipo
     */
    @Test
    public void updateDietaTipoTest(){
        DietaTipoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DietaTipoEntity newEntity = factory.manufacturePojo(DietaTipoEntity.class);
        
        newEntity.setId(entity.getId());
        dietaTipoPersistence.update(newEntity);
        
        DietaTipoEntity resp = em.find(DietaTipoEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        
    }
    
    
    
    /**
     * Prueba para eliminar una DietaTipo
     */
    @Test
    public void deleteDietaTipoTest(){
        DietaTipoEntity entity = data.get(0);
        dietaTipoPersistence.delete(entity.getId());
        DietaTipoEntity deleted = em.find(DietaTipoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
    
}
