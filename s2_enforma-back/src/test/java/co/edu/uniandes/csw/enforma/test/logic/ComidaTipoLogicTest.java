/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.ejb.ComidaTipoLogic;
import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.ComidaTipoPersistence;
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
 * @author Jose Manuel Flórez
 */

@RunWith(Arquillian.class)
public class ComidaTipoLogicTest
{
    
    private PodamFactory factory  =new PodamFactoryImpl();

    @Inject
    private ComidaTipoLogic comidaTipoLogic;

    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<ComidaTipoEntity> dataComidaTipo = new ArrayList<ComidaTipoEntity>();
    
    @Deployment
    public static JavaArchive createDeployement ()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComidaTipoEntity.class.getPackage())
                .addPackage(ComidaTipoLogic.class.getPackage())
                .addPackage(ComidaTipoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void configTest() 
    {
        try {
            
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        }
        catch(Exception e)
        {
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
        em.createQuery("delete from ComidaTipoEntity").executeUpdate();

    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        
        for (int i = 0; i < 3; i++) 
        {
            ComidaTipoEntity entidad = factory.manufacturePojo(ComidaTipoEntity.class);
            em.persist(entidad);
            
            dataComidaTipo.add(entidad);
        }
        
        
    }
    
    
    
    
    @Test 
    public void createComidaTipoTest () throws BusinessLogicException
    {
        ComidaTipoEntity newEntidad = factory.manufacturePojo(ComidaTipoEntity.class);
        newEntidad.setCalorias(200);
        ComidaTipoEntity result = comidaTipoLogic.createComidaTipo(newEntidad);
        Assert.assertNotNull(result);
        
        ComidaTipoEntity entity = em.find(ComidaTipoEntity.class, result.getId());
        Assert.assertNotNull(result);
                
    }



     /**
     *  Prueba para crear una Comida Tipo con nombre vacío
     * 
     * @throws BusinessLogicException 
     */    @Test (expected = BusinessLogicException.class)
    public void crearComidaTipoConMomentoDelDiaVacioTest () throws BusinessLogicException
    {
        ComidaTipoEntity newEntidad = factory.manufacturePojo(ComidaTipoEntity.class);
        newEntidad.setMomentoDelDia("");
        ComidaTipoEntity result = comidaTipoLogic.createComidaTipo(newEntidad);
        
    }
    
    
     
    /**
     *  Prueba para crear una Comida Tipo con menú vacío
     * 
     * @throws BusinessLogicException 
     */
    @Test (expected = BusinessLogicException.class)
    
    public void crearComidaTipoConMenuVacio () throws BusinessLogicException
    {
        ComidaTipoEntity newEntity = factory.manufacturePojo(ComidaTipoEntity.class);
        newEntity.setMenu("");
       // Assert.assertEquals(newEntity.getNombre(), "");
        
        comidaTipoLogic.createComidaTipo(newEntity);
    }
   
    /**
     *  Prueba para crear una Comida Tipo con menú nulo
     * 
     * @throws BusinessLogicException 
     */
    @Test (expected = BusinessLogicException.class)
    
    public void crearComidaTipoConMenuNulo () throws BusinessLogicException
    {
        ComidaTipoEntity newEntity = factory.manufacturePojo(ComidaTipoEntity.class);
        newEntity.setMenu(null);
        
        comidaTipoLogic.createComidaTipo(newEntity);
    }
    
    
    /**
     *  Prueba para crear una Comida Tipo con nombre vacío
     * 
     * @throws BusinessLogicException 
     */
    @Test (expected = BusinessLogicException.class)
    
    public void crearComidaTipoConNombreVacio () throws BusinessLogicException
    {
        ComidaTipoEntity newEntity = factory.manufacturePojo(ComidaTipoEntity.class);
        newEntity.setNombre("");
        comidaTipoLogic.createComidaTipo(newEntity);
    }
    
    /**
     *  Prueba para crear una Comida Tipo con nombre nulo
     * 
     * @throws BusinessLogicException 
     */
    @Test (expected = BusinessLogicException.class)
    
    public void crearComidaTipoConNombreNulo () throws BusinessLogicException
    {
        ComidaTipoEntity newEntity = factory.manufacturePojo(ComidaTipoEntity.class);
        newEntity.setNombre(null);
        comidaTipoLogic.createComidaTipo(newEntity);
    }
    
    
    /**
     *  Prueba para crear una Comida Tipo con calorias vacías
     * 
     * @throws BusinessLogicException 
     */
    @Test (expected = BusinessLogicException.class)
    
    public void crearComidaTipoConCaloriasVacias () throws BusinessLogicException
    {
        ComidaTipoEntity newEntity = factory.manufacturePojo(ComidaTipoEntity.class);
        newEntity.setCalorias(null);
        comidaTipoLogic.createComidaTipo(newEntity);
    }
    
    /**
     *  Prueba para crear una Comida Tipo con calorias menores a 100
     * 
     * @throws BusinessLogicException 
     */
    @Test (expected = BusinessLogicException.class)
    
    public void crearComidaTipoConCaloriasMenoresAlPermitido () throws BusinessLogicException
    {
        ComidaTipoEntity newEntity = factory.manufacturePojo(ComidaTipoEntity.class);
        newEntity.setCalorias(90);
        comidaTipoLogic.createComidaTipo(newEntity);
    }
    
    
     /**
     * Prueba para consultar la lista de Comidas Tipo.
     */
    @Test
    public void getComidasTipoTest() {
        List<ComidaTipoEntity> list = comidaTipoLogic.getComidasTipo();
        Assert.assertEquals(dataComidaTipo.size(), list.size());
        for (ComidaTipoEntity entity : list) {
            boolean found = false;
            for (ComidaTipoEntity storedEntity : dataComidaTipo) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    
    
     /**
     * Prueba para consultar una ComidaTipo.
     */
    @Test
    public void getComidaTipoTest() {
        ComidaTipoEntity entityComidaTipo = dataComidaTipo.get(0);
        ComidaTipoEntity resultEntity = comidaTipoLogic.getComidaTipo(entityComidaTipo.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entityComidaTipo.getId(), resultEntity.getId());
        Assert.assertEquals(entityComidaTipo.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entityComidaTipo.getCalorias(), resultEntity.getCalorias());
        Assert.assertEquals(entityComidaTipo.getMenu(), resultEntity.getMenu());
        
    }
    
    /**
     * Prueba para actualizar una ComidaTipo.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void updateComidaTipoTest() throws BusinessLogicException 
    {
        ComidaTipoEntity entity = dataComidaTipo.get(0);
        ComidaTipoEntity pojoEntity = factory.manufacturePojo(ComidaTipoEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setCalorias(700);
        comidaTipoLogic.updateComidaTipoEntity(pojoEntity.getId(), pojoEntity);
        ComidaTipoEntity resp = em.find(ComidaTipoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getCalorias(), resp.getCalorias());
        Assert.assertEquals(pojoEntity.getMenu(), resp.getMenu());
        
    }
    
     /**
     * Prueba para actualizar un ComidaTipo con Nombre vacio.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateComidaTipoConNombreVacioTest() throws BusinessLogicException {
        ComidaTipoEntity entityComidaTipo = dataComidaTipo.get(0);
        ComidaTipoEntity pojoEntity = factory.manufacturePojo(ComidaTipoEntity.class);
        pojoEntity.setNombre("");
        pojoEntity.setId(entityComidaTipo.getId());
        comidaTipoLogic.updateComidaTipoEntity(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para actualizar un ComidaTipo con Nombre nulo.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateComidaTipoConNombreNuloTest() throws BusinessLogicException {
        ComidaTipoEntity entityComidaTipo = dataComidaTipo.get(0);
        ComidaTipoEntity pojoEntity = factory.manufacturePojo(ComidaTipoEntity.class);
        pojoEntity.setNombre(null);
        pojoEntity.setId(entityComidaTipo.getId());
        comidaTipoLogic.updateComidaTipoEntity(pojoEntity.getId(), pojoEntity);
    }
    
       /**
     * Prueba para actualizar un ComidaTipo con Menú Vacío.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateComidaTipoConMenuVacioTest() throws BusinessLogicException {
        ComidaTipoEntity entityComidaTipo = dataComidaTipo.get(0);
        ComidaTipoEntity pojoEntity = factory.manufacturePojo(ComidaTipoEntity.class);
        pojoEntity.setMenu("");
        pojoEntity.setId(entityComidaTipo.getId());
        comidaTipoLogic.updateComidaTipoEntity(pojoEntity.getId(), pojoEntity);
    }
    
       /**
     * Prueba para actualizar un ComidaTipo con Menu nulo.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateComidaTipoConMenuNuloTest() throws BusinessLogicException {
        ComidaTipoEntity entityComidaTipo = dataComidaTipo.get(0);
        ComidaTipoEntity pojoEntity = factory.manufacturePojo(ComidaTipoEntity.class);
        pojoEntity.setMenu(null);
        pojoEntity.setId(entityComidaTipo.getId());
        comidaTipoLogic.updateComidaTipoEntity(pojoEntity.getId(), pojoEntity);
    }
    
       /**
     * Prueba para actualizar un ComidaTipo con Calorias nulas.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateComidaTipoConCaloriasNulasTest() throws BusinessLogicException {
        ComidaTipoEntity entityComidaTipo = dataComidaTipo.get(0);
        ComidaTipoEntity pojoEntity = factory.manufacturePojo(ComidaTipoEntity.class);
        pojoEntity.setCalorias(null);
        pojoEntity.setId(entityComidaTipo.getId());
        comidaTipoLogic.updateComidaTipoEntity(pojoEntity.getId(), pojoEntity);
    }
    
       /**
     * Prueba para actualizar un ComidaTipo con calorias no permitidas.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateComidaTipoConCaloriasBajasTest() throws BusinessLogicException {
        ComidaTipoEntity entityComidaTipo = dataComidaTipo.get(0);
        ComidaTipoEntity pojoEntity = factory.manufacturePojo(ComidaTipoEntity.class);
        pojoEntity.setCalorias(90);
        pojoEntity.setId(entityComidaTipo.getId());
        comidaTipoLogic.updateComidaTipoEntity(pojoEntity.getId(), pojoEntity);
    }
    
    
    
    
}
