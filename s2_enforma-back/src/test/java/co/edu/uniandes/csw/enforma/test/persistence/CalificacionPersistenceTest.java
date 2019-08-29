/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;

import co.edu.uniandes.csw.enforma.entities.CalificacionEntity;
import co.edu.uniandes.csw.enforma.persistence.CalificacionPersistence;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.junit.Before;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Jimmy Pepinosa
 */
@RunWith(Arquillian.class)
public class CalificacionPersistenceTest 
{ 
    @Inject
    CalificacionPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<CalificacionEntity> data = new ArrayList<>();
    
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(CalificacionEntity.class)
                .addClass(CalificacionPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
     /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() 
    {
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
    private void clearData() 
    {
        em.createQuery("delete from CalificacionEntity").executeUpdate();
    }
    
     /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }    
    
    /**
     * Prueba para crear una calificacion
     */
    @Test
    public void createCalifiacionTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity calificacion = factory.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity result =cp.create(calificacion);
        
        Assert.assertNotNull(result);
        
        CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());
        
        Assert.assertEquals(calificacion.getPuntaje(), entity.getPuntaje());
    }
    
    /**
     * Prueba para consultar la lista de Calificacion
     */
    @Test
    public void getCalificacionesTest()
    {
        List<CalificacionEntity> list = cp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(CalificacionEntity ent : list)
        {
            boolean  encontrada = false;
            for(CalificacionEntity entity : data)
            {
                if(ent.getId().equals(entity.getId()))
                {
                    encontrada = true;
                }
            }
            Assert.assertTrue(encontrada);
        }
    }
    
    /**
     * Prueba para consultar una calificacion
     */
    public void getCalificacionTest()
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getPuntaje(), newEntity.getPuntaje());
        Assert.assertEquals(entity.getComentario(), newEntity.getComentario());
    }
    
    /**
     * Prueba para actualizar una calificacion
     */
    @Test
    public void updateCalificacionTest()
    {
        CalificacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);

        newEntity.setId(entity.getId());

        cp.update(newEntity);

        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    /**
     * Prueba para eliminar una calificacion
     */
    @Test
    public void deletelificacionTest()
    {
        CalificacionEntity entity = data.get(0);
        cp.delete(entity.getId());
        CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

            
}
