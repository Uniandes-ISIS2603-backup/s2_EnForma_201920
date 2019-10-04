/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
import co.edu.uniandes.csw.enforma.persistence.AdministradorPersistence;
import co.edu.uniandes.csw.enforma.ejb.AdministradorLogic;
import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
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
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.junit.Assert;
import org.junit.Before;


/**
 *
 * @author Elina Jaimes
 */
@RunWith(Arquillian.class)
public class AdministradorLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private AdministradorLogic administradorLogica;

    @PersistenceContext
    protected EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<AdministradorEntity> data = new ArrayList<AdministradorEntity>();

//Le decimos lo que vamos a probar. 
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(AdministradorEntity.class.getPackage()).addPackage(AdministradorPersistence.class.getPackage()).addClass(AdministradorLogic.class).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void configTest() {
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
    private void clearData() {
        em.createQuery("delete from AdministradorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        
        for (int i = 0; i < 3; i++) {
            AdministradorEntity entity = factory.manufacturePojo(AdministradorEntity.class);
    

            em.persist(entity);
            data.add(entity);
        }
       
    }
    
    
    
    @Test
    public void createTest() throws BusinessLogicException {
        
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        AdministradorEntity result = administradorLogica.crearAdministrador(newEntity);

        Assert.assertNotNull(result);

        AdministradorEntity entity = em.find(AdministradorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    @Test(expected = BusinessLogicException.class)
    public void createFailUsernameTest() throws BusinessLogicException {
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        newEntity.setUsername("");
        AdministradorEntity result = administradorLogica.crearAdministrador(newEntity);
    }
    
    
    @Test(expected = BusinessLogicException.class)
    public void createFailNombreTest() throws BusinessLogicException {
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        newEntity.setNombre("");
        AdministradorEntity result = administradorLogica.crearAdministrador(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createFailContrasenaTest() throws BusinessLogicException {
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        newEntity.setContrasena("");
        AdministradorEntity result = administradorLogica.crearAdministrador(newEntity);
    }
    
    
    
    
    @Test
    public void getAdministradorsTest() {
        List<AdministradorEntity> list = administradorLogica.getAdministradors();
        Assert.assertEquals(data.size(), list.size());
        for (AdministradorEntity entity : list) {
            boolean found = false;
            for (AdministradorEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Administrador.
     */
    @Test
    public void getAdministradorTest() {
        AdministradorEntity entity = data.get(0);
        AdministradorEntity resultEntity = administradorLogica.getAdministrador(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getUsername(), resultEntity.getUsername());
        Assert.assertEquals(entity.getContrasena(), resultEntity.getContrasena());
    }
     
      @Test
    public void updateAdministradorTest() throws BusinessLogicException {
        AdministradorEntity entity = data.get(0);
        AdministradorEntity pojoEntity = factory.manufacturePojo(AdministradorEntity.class);
        pojoEntity.setId(entity.getId());
        administradorLogica.updateAdministrador(pojoEntity.getId(), pojoEntity);
        AdministradorEntity resp = em.find(AdministradorEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getUsername(), resp.getUsername());
        Assert.assertEquals(pojoEntity.getContrasena(), resp.getContrasena());   
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateAdministradorConUserNameFailTest() throws BusinessLogicException {
        AdministradorEntity entity = data.get(0);
        AdministradorEntity pojoEntity = factory.manufacturePojo(AdministradorEntity.class);
        pojoEntity.setUsername("");
        pojoEntity.setId(entity.getId());
        administradorLogica.updateAdministrador(pojoEntity.getId(), pojoEntity);
    }
    
    
     @Test(expected = BusinessLogicException.class)
    public void updateAdministradorConContrasenaFailTest() throws BusinessLogicException {
        AdministradorEntity entity = data.get(0);
        AdministradorEntity pojoEntity = factory.manufacturePojo(AdministradorEntity.class);
        pojoEntity.setContrasena("");
        pojoEntity.setId(entity.getId());
        administradorLogica.updateAdministrador(pojoEntity.getId(), pojoEntity);
    }
    
    
    @Test
    public void deleteAdministradorTest() throws BusinessLogicException {
        AdministradorEntity entity = data.get(0);
        administradorLogica.deleteAdministrador(entity.getId());
        AdministradorEntity deleted = em.find(AdministradorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    

}
