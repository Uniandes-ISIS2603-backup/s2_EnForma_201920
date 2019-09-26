/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.ejb.CalificacionLogic;
import co.edu.uniandes.csw.enforma.entities.CalificacionEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Jimmy Pepinosa
 */
@RunWith(Arquillian.class)
public class CalificacionLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @PersistenceContext
    private EntityManager em;
            
    @Inject
    private CalificacionLogic calificacionLogic;
    
    @Inject
    private UserTransaction utx;
    
    private List<CalificacionEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionLogic.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createCalificacionTest() throws BusinessLogicException
    {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity result = calificacionLogic.createCalificacion(newEntity);
        Assert.assertNotNull(result);
        
        CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());
        Assert.assertEquals(entity.getId(), result.getId());
        Assert.assertEquals(entity.getPuntaje(), result.getPuntaje());
        Assert.assertEquals(entity.getComentario(), result.getComentario());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionPuntajeNull() throws BusinessLogicException
    {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setPuntaje(null);
        CalificacionEntity result = calificacionLogic.createCalificacion(newEntity);
    }
    
    @Test 
    public void getCalificacionesTest()
    {
        List<CalificacionEntity> list = calificacionLogic.getCalificaciones();
        
        Assert.assertEquals(data.size(), list.size());
        for(CalificacionEntity entity: list)
        {
            boolean found = false;
            for (CalificacionEntity storedEntity : data) 
            {
                if (entity.getId().equals(storedEntity.getId())) 
                {
                    found = true;
                }
                
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getCalificacionTest()
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity result = calificacionLogic.getCalificacion(entity.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(entity.getId(), result.getId());
        Assert.assertEquals(entity.getPuntaje(), result.getPuntaje());
        Assert.assertEquals(entity.getComentario(), result.getComentario());
        Assert.assertEquals(entity.getFecha(), result.getFecha());
    }
    
    @Test
    public void updateCalificacionTest()
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity pojoEntity = factory.manufacturePojo(CalificacionEntity.class);
        pojoEntity.setId(entity.getId());
        
        calificacionLogic.updateCalificacion(pojoEntity);
        CalificacionEntity result = em.find(CalificacionEntity.class, entity.getId());
        
         Assert.assertEquals(pojoEntity.getId(), result.getId());
         Assert.assertEquals(pojoEntity.getPuntaje(), result.getPuntaje());
         Assert.assertEquals(pojoEntity.getComentario(), result.getComentario());
         Assert.assertEquals(pojoEntity.getFecha(), result.getFecha());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateCalificacionPuntajeNullTest() throws BusinessLogicException
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setId(null);
        newEntity.setId(entity.getId());
        calificacionLogic.updateCalificacion(newEntity);
    }
    
}
