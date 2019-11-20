/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.ejb.DietaTipoLogic;
import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
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
public class DietaTipoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private DietaTipoLogic dietaTipoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;

    private List<DietaTipoEntity> data = new ArrayList<>();

    
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
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
        em.createQuery("delete from DietaTipoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        for (int i = 0; i < 3; i++) {
            DietaTipoEntity entity = factory.manufacturePojo(DietaTipoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    
    
    
    @Test
    public void createDietaTipo() throws BusinessLogicException
    {
        DietaTipoEntity newEntity = factory.manufacturePojo(DietaTipoEntity.class);
        DietaTipoEntity result = dietaTipoLogic.createDietaTipo(newEntity);
        Assert.assertNotNull(result);
        
        DietaTipoEntity entity = em.find(DietaTipoEntity.class, result.getId());
        Assert.assertEquals(entity.getId(), result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
        Assert.assertEquals(entity.getCaloriasMax(), result.getCaloriasMax());
        Assert.assertEquals(entity.getCaloriasMin(), result.getCaloriasMin());
        Assert.assertEquals(entity.getCantidadGrasa(), result.getCantidadGrasa());        
        Assert.assertEquals(entity.getCantidadAzucar(), result.getCantidadAzucar());
        Assert.assertEquals(entity.getCantidadFibra(), result.getCantidadFibra());
        Assert.assertEquals(entity.getTieneGluten(), result.getTieneGluten());
        Assert.assertEquals(entity.getTieneLactosa(), result.getTieneLactosa());
        
    }
    
    
    @Test (expected = BusinessLogicException.class)
    public void createDietaTipoNombreNull() throws BusinessLogicException{
        DietaTipoEntity newEntity = factory.manufacturePojo(DietaTipoEntity.class);
        newEntity.setNombre(null);
        DietaTipoEntity result = dietaTipoLogic.createDietaTipo(newEntity);
        
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createDietaTipoCantidadGrasaNull() throws BusinessLogicException{
        DietaTipoEntity newEntity = factory.manufacturePojo(DietaTipoEntity.class);
        newEntity.setCantidadGrasa(null);
        DietaTipoEntity result = dietaTipoLogic.createDietaTipo(newEntity);
        
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createDietaTipoCantidadAzucarNull() throws BusinessLogicException{
        DietaTipoEntity newEntity = factory.manufacturePojo(DietaTipoEntity.class);
        newEntity.setCantidadAzucar(null);
        DietaTipoEntity result = dietaTipoLogic.createDietaTipo(newEntity);
        
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createDietaTipoCantidadFibraNull() throws BusinessLogicException{
        DietaTipoEntity newEntity = factory.manufacturePojo(DietaTipoEntity.class);
        newEntity.setCantidadFibra(null);
        DietaTipoEntity result = dietaTipoLogic.createDietaTipo(newEntity);
        
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createDietaTipoCaloriasMaxNull() throws BusinessLogicException{
        DietaTipoEntity newEntity = factory.manufacturePojo(DietaTipoEntity.class);
        newEntity.setCaloriasMax(null);
        DietaTipoEntity result = dietaTipoLogic.createDietaTipo(newEntity);
        
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createDietaTipoCaloriasMinNull() throws BusinessLogicException{
        DietaTipoEntity newEntity = factory.manufacturePojo(DietaTipoEntity.class);
        newEntity.setCaloriasMin(null);
        DietaTipoEntity result = dietaTipoLogic.createDietaTipo(newEntity);
        
    }
    
    
    @Test (expected = BusinessLogicException.class)
    public void createDietaTipoCantidadGrasaNegativa() throws BusinessLogicException{
        DietaTipoEntity newEntity = factory.manufacturePojo(DietaTipoEntity.class);
        newEntity.setCantidadGrasa(-1);
        DietaTipoEntity result = dietaTipoLogic.createDietaTipo(newEntity);
        
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createDietaTipoCantidadAzucarNegativa() throws BusinessLogicException{
        DietaTipoEntity newEntity = factory.manufacturePojo(DietaTipoEntity.class);
        newEntity.setCantidadAzucar(-1);
        DietaTipoEntity result = dietaTipoLogic.createDietaTipo(newEntity);
        
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createDietaTipoCantidadFibraNegativa() throws BusinessLogicException{
        DietaTipoEntity newEntity = factory.manufacturePojo(DietaTipoEntity.class);
        newEntity.setCantidadFibra(-1);
        DietaTipoEntity result = dietaTipoLogic.createDietaTipo(newEntity);
        
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createDietaTipoCaloriasMaxNegativa() throws BusinessLogicException{
        DietaTipoEntity newEntity = factory.manufacturePojo(DietaTipoEntity.class);
        newEntity.setCaloriasMax(-1);
        DietaTipoEntity result = dietaTipoLogic.createDietaTipo(newEntity);
        
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createDietaTipoCaloriasMinNegativa() throws BusinessLogicException{
        DietaTipoEntity newEntity = factory.manufacturePojo(DietaTipoEntity.class);
        newEntity.setCaloriasMin(-1);
        DietaTipoEntity result = dietaTipoLogic.createDietaTipo(newEntity);
        
    }
    
    
    
    @Test
    public void getDietasTipoTest() 
    {
        List<DietaTipoEntity> list = dietaTipoLogic.getDietasTipo();
        
        Assert.assertEquals(data.size(), list.size());
        for (DietaTipoEntity entity : list) {
            boolean found = false;
            for (DietaTipoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    
    
    @Test
    public void getDietaTipoTest()
    {
        DietaTipoEntity entity = data.get(0);
        DietaTipoEntity result = dietaTipoLogic.getDietaTipo(entity.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(entity.getId(), result.getId()); 
        Assert.assertEquals(entity.getNombre(), result.getNombre()); 
        Assert.assertEquals(entity.getCaloriasMax(), result.getCaloriasMax());
        Assert.assertEquals(entity.getCaloriasMin(), result.getCaloriasMin());
        Assert.assertEquals(entity.getCantidadFibra(), result.getCantidadFibra());
        Assert.assertEquals(entity.getCantidadAzucar(), result.getCantidadAzucar());
        Assert.assertEquals(entity.getCantidadGrasa(), result.getCantidadGrasa());
        Assert.assertEquals(entity.getTieneGluten(), result.getTieneGluten());
        Assert.assertEquals(entity.getTieneLactosa(), result.getTieneLactosa());
    }
    
    
    
    
    @Test
    public void updateDietaTipoTest() throws BusinessLogicException 
    {
        DietaTipoEntity entity = data.get(0);
        DietaTipoEntity pojoEntity = factory.manufacturePojo(DietaTipoEntity.class);
        
       pojoEntity.setId(entity.getId());
         
        dietaTipoLogic.updateDietaTipo(pojoEntity);
        DietaTipoEntity result = em.find(DietaTipoEntity.class, entity.getId());
        
        
        Assert.assertEquals(pojoEntity.getId(), result.getId()); 
        Assert.assertEquals(pojoEntity.getNombre(), result.getNombre()); 
        Assert.assertEquals(pojoEntity.getCaloriasMax(), result.getCaloriasMax());
        Assert.assertEquals(pojoEntity.getCaloriasMin(), result.getCaloriasMin());
        Assert.assertEquals(pojoEntity.getCantidadFibra(), result.getCantidadFibra());
        Assert.assertEquals(pojoEntity.getCantidadAzucar(), result.getCantidadAzucar());
        Assert.assertEquals(pojoEntity.getCantidadGrasa(), result.getCantidadGrasa());
        Assert.assertEquals(pojoEntity.getTieneGluten(), result.getTieneGluten());
        Assert.assertEquals(pojoEntity.getTieneLactosa(), result.getTieneLactosa());
   }
    
    
    
    
    
    
    
    
    
}
