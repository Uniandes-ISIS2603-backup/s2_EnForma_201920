 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.ejb.QuejasYReclamosLogic;
import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.QuejasYReclamosPersistence;
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
 * @author Jimmy Pepinosa
 */
@RunWith(Arquillian.class)
public class QuejasYReclamosLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @PersistenceContext
    private EntityManager em;
            
    @Inject
    private QuejasYReclamosLogic quejasYReclamosLogic;
    
    @Inject
    private UserTransaction utx;
    
    private List<QuejasYReclamosEntity> data = new ArrayList<QuejasYReclamosEntity>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(QuejasYReclamosEntity.class.getPackage())
                .addPackage(QuejasYReclamosLogic.class.getPackage())
                .addPackage(QuejasYReclamosPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
         /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() 
    {
        try {
            utx.begin();
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
        em.createQuery("delete from QuejasYReclamosEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        for (int i = 0; i < 3; i++) {
            QuejasYReclamosEntity entity = factory.manufacturePojo(QuejasYReclamosEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    @Test 
    public void createQuejasYReclamosLogicTest() throws BusinessLogicException
    {
        QuejasYReclamosEntity newEntity = factory.manufacturePojo(QuejasYReclamosEntity.class);
        QuejasYReclamosEntity result = quejasYReclamosLogic.createQuejasYReclamos(newEntity);
        Assert.assertNotNull(result);
        
        QuejasYReclamosEntity entity = em.find(QuejasYReclamosEntity.class, result.getId());
        Assert.assertEquals(entity.getId(), result.getId());
        Assert.assertEquals(entity.getAsunto(), result.getAsunto());
        Assert.assertEquals(entity.getDescripcion(), result.getDescripcion());
        Assert.assertEquals(entity.getFecha(), result.getFecha());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createQuejasYReclamosAsuntoNull() throws BusinessLogicException
    {
        QuejasYReclamosEntity newEntity = factory.manufacturePojo(QuejasYReclamosEntity.class);
        newEntity.setAsunto(null);
        QuejasYReclamosEntity result = quejasYReclamosLogic.createQuejasYReclamos(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createQuejasYReclamosDescripcionNull() throws BusinessLogicException
    {
        QuejasYReclamosEntity newEntity = factory.manufacturePojo(QuejasYReclamosEntity.class);
        newEntity.setDescripcion(null);
        QuejasYReclamosEntity result = quejasYReclamosLogic.createQuejasYReclamos(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createQuejasYReclamosFechaNull() throws BusinessLogicException
    {
        QuejasYReclamosEntity newEntity = factory.manufacturePojo(QuejasYReclamosEntity.class);
        newEntity.setFecha(null);
        QuejasYReclamosEntity result = quejasYReclamosLogic.createQuejasYReclamos(newEntity);
    }
    
    @Test 
    public void getQuejasYReclamosTest()
    {
        List<QuejasYReclamosEntity> list = quejasYReclamosLogic.getQuejasYReclamos();
        
        Assert.assertEquals(data.size(), list.size());
        for(QuejasYReclamosEntity entity: list)
        {
            boolean found = false;
            for (QuejasYReclamosEntity storedEntity : data) 
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
    public void getQuejaYReclamoTest()
    {
        QuejasYReclamosEntity entity = data.get(0);
        QuejasYReclamosEntity result = quejasYReclamosLogic.getQuejaOReclamo(entity.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(entity.getId(), result.getId());
        Assert.assertEquals(entity.getAsunto(), result.getAsunto());
        Assert.assertEquals(entity.getDescripcion(), result.getDescripcion());
        Assert.assertEquals(entity.getFecha(), result.getFecha());
    }
    
    @Test
    public void updateQuejasYReclamosTest() throws BusinessLogicException
    {
        QuejasYReclamosEntity entity = data.get(0);
        QuejasYReclamosEntity pojoEntity = factory.manufacturePojo(QuejasYReclamosEntity.class);
        pojoEntity.setId(entity.getId());
        
        quejasYReclamosLogic.updateQuejasYReclamos(pojoEntity);
        QuejasYReclamosEntity result = em.find(QuejasYReclamosEntity.class, entity.getId());
        
         Assert.assertEquals(pojoEntity.getId(), result.getId());
         Assert.assertEquals(entity.getAsunto(), result.getAsunto());
         Assert.assertEquals(entity.getDescripcion(), result.getDescripcion());
         Assert.assertEquals(pojoEntity.getFecha(), result.getDescripcion());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateQuejasYReclamosAsuntoNullTest1() throws BusinessLogicException
    {
        QuejasYReclamosEntity entity = data.get(0);
        QuejasYReclamosEntity newEntity = factory.manufacturePojo(QuejasYReclamosEntity.class);
        newEntity.setAsunto(null);
        newEntity.setId(entity.getId());
        quejasYReclamosLogic.updateQuejasYReclamos(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateQuejasYReclamosAsuntoNullTest2() throws BusinessLogicException
    {
        QuejasYReclamosEntity entity = data.get(0);
        QuejasYReclamosEntity newEntity = factory.manufacturePojo(QuejasYReclamosEntity.class);
        newEntity.setAsunto("");
        newEntity.setId(entity.getId());
        quejasYReclamosLogic.updateQuejasYReclamos(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateQuejasYReclamosDescripcionNullTest1() throws BusinessLogicException
    {
        QuejasYReclamosEntity entity = data.get(0);
        QuejasYReclamosEntity newEntity = factory.manufacturePojo(QuejasYReclamosEntity.class);
        newEntity.setDescripcion(null);
        newEntity.setId(entity.getId());
        quejasYReclamosLogic.updateQuejasYReclamos(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateQuejasYReclamosDescripcionNullTest2() throws BusinessLogicException
    {
        QuejasYReclamosEntity entity = data.get(0);
        QuejasYReclamosEntity newEntity = factory.manufacturePojo(QuejasYReclamosEntity.class);
        newEntity.setDescripcion("");
        newEntity.setId(entity.getId());
        quejasYReclamosLogic.updateQuejasYReclamos(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateQuejasYReclamosFechaNullTest() throws BusinessLogicException
    {
        QuejasYReclamosEntity entity = data.get(0);
        QuejasYReclamosEntity newEntity = factory.manufacturePojo(QuejasYReclamosEntity.class);
        newEntity.setFecha(null);
        newEntity.setId(entity.getId());
        quejasYReclamosLogic.updateQuejasYReclamos(newEntity);
    }
}
