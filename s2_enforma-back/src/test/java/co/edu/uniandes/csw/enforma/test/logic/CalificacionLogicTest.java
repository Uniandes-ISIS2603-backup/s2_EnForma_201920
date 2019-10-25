/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.ejb.CalificacionLogic;
import co.edu.uniandes.csw.enforma.ejb.ClienteLogic;
import co.edu.uniandes.csw.enforma.ejb.DietaTipoLogic;
import co.edu.uniandes.csw.enforma.entities.CalificacionEntity;
import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.enforma.persistence.ClientePersistence;
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
    
    private List<CalificacionEntity> data = new ArrayList<CalificacionEntity>();
    
    private List<ClienteEntity> clienteData = new ArrayList<ClienteEntity>();
    
    private List<DietaTipoEntity> dietaData = new ArrayList<DietaTipoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionLogic.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClienteLogic.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
                .addPackage(DietaTipoEntity.class.getPackage())
                .addPackage(DietaTipoLogic.class.getPackage())
                .addPackage(DietaTipoPersistence.class.getPackage())
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
        em.createQuery("delete from CalificacionEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
        em.createQuery("delete from DietaTipoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        for (int i = 0; i < 3; i++) 
        {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
            em.persist(entity);
            clienteData.add(entity);
        }
        for (int i = 0; i < 3; i++) 
        {
            DietaTipoEntity entity = factory.manufacturePojo(DietaTipoEntity.class);
            em.persist(entity);
            dietaData.add(entity);
        }
        for (int i = 0; i < 3; i++) 
        {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            entity.setCliente(clienteData.get(0));
            entity.setDietaTipo(dietaData.get(0));
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createCalificacionTest() throws BusinessLogicException
    {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setCliente(clienteData.get(0));
        newEntity.setDietaTipo(dietaData.get(0));
        CalificacionEntity result = calificacionLogic.createCalificacion(newEntity);
        Assert.assertNotNull(result);
        
        CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());
        Assert.assertEquals(entity.getId(), result.getId());
        Assert.assertEquals(entity.getPuntaje(), result.getPuntaje());
        Assert.assertEquals(entity.getComentario(), result.getComentario());
        Assert.assertEquals(entity.getFecha(), result.getFecha());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionPuntajeNullTest() throws BusinessLogicException
    {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setCliente(clienteData.get(0));
        newEntity.setDietaTipo(dietaData.get(0));
        newEntity.setPuntaje(null);
        CalificacionEntity result = calificacionLogic.createCalificacion(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionFechaNullTest() throws BusinessLogicException
    {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setCliente(clienteData.get(0));
        newEntity.setDietaTipo(dietaData.get(0));
        newEntity.setfecha(null);
        CalificacionEntity result = calificacionLogic.createCalificacion(newEntity);
    }
    
//    @Test(expected = BusinessLogicException.class)
//    public void createCalificacionDietaTipoNullTest() throws BusinessLogicException
//    {
//        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
//        newEntity.setCliente(clienteData.get(0));
//        newEntity.setDietaTipo(null);
//        CalificacionEntity result = calificacionLogic.createCalificacion(newEntity);
//    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionClienteNullTest() throws BusinessLogicException
    {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setDietaTipo(dietaData.get(0));
        newEntity.setCliente(null);
        CalificacionEntity result = calificacionLogic.createCalificacion( newEntity);
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
    
//    @Test
//    public void getCalificacionByClienteIdYDietaTipoIdTest()
//    {
//        CalificacionEntity entity = data.get(0);
//        CalificacionEntity result = calificacionLogic.getCalificacionByClienteIdYDietaTipoId(clienteData.get(0).getId(), dietaData.get(0).getId(), entity.getId());
//        Assert.assertNotNull(result);
//        Assert.assertEquals(entity.getId(), result.getId());
//        Assert.assertEquals(entity.getPuntaje(), result.getPuntaje());
//        Assert.assertEquals(entity.getComentario(), result.getComentario());
//        Assert.assertEquals(entity.getFecha(), result.getFecha());
//    }
    
//    @Test
//    public void getCalificacionByDietaTipoTest(Long dietaId)
//    {
//        CalificacionEntity entity = data.get(0);
//        CalificacionEntity result = calificacionLogic.getCalificacionesByDietaId(dietaData.get(0).getId());
//        Assert.assertNotNull(result);
//        Assert.assertEquals(entity.getDietaTipo().getId(), result.getId());
//        Assert.assertEquals(entity.getPuntaje(), result.getPuntaje());
//        Assert.assertEquals(entity.getComentario(), result.getComentario());
//        Assert.assertEquals(entity.getFecha(), result.getFecha());
//    }
    
    @Test
    public void updateCalificacion()
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity pojoEntity = factory.manufacturePojo(CalificacionEntity.class);
        pojoEntity.setId(entity.getId());
        
        calificacionLogic.updateCalificacion(pojoEntity.getId(), pojoEntity);
        CalificacionEntity result = em.find(CalificacionEntity.class, entity.getId());
        
        Assert.assertEquals(pojoEntity.getId(), result.getId());
         Assert.assertEquals(pojoEntity.getPuntaje(), result.getPuntaje());
         Assert.assertEquals(pojoEntity.getComentario(), result.getComentario());
         Assert.assertEquals(pojoEntity.getFecha(), result.getFecha());
    }
    
    @Test
    public void updateCalificacionByClienteIdYDietaTipoIdTest() throws BusinessLogicException
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity pojoEntity = factory.manufacturePojo(CalificacionEntity.class);
        pojoEntity.setId(entity.getId());
        
        calificacionLogic.updateCalificacionByClienteIdYDietaTipoId(clienteData.get(0).getId(), dietaData.get(0).getId(), pojoEntity);
        CalificacionEntity result = em.find(CalificacionEntity.class, entity.getId());
        
         Assert.assertEquals(pojoEntity.getId(), result.getId());
         Assert.assertEquals(pojoEntity.getPuntaje(), result.getPuntaje());
         Assert.assertEquals(pojoEntity.getComentario(), result.getComentario());
         Assert.assertEquals(pojoEntity.getFecha(), result.getFecha());
    }
    
     @Test(expected = BusinessLogicException.class)
    public void updateCalificacionFechaNullTest() throws BusinessLogicException
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setfecha(null);
        newEntity.setId(entity.getId());
        calificacionLogic.updateCalificacionByClienteIdYDietaTipoId(clienteData.get(0).getId(), dietaData.get(0).getId(), newEntity);
    }
    
    @Test
    public void deleteCalificacionTest() throws BusinessLogicException
    {
        CalificacionEntity entity = data.get(0);
        calificacionLogic.deleteCalificacion(entity.getId());
        CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
