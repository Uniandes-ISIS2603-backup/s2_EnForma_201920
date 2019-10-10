/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.entities.PagoEntity;
import co.edu.uniandes.csw.enforma.persistence.PagoPersistence;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import co.edu.uniandes.csw.enforma.ejb.PagoLogic;
import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Before;
/**
 *
 * @author Elina Jaimes
 */

@RunWith(Arquillian.class)
public class PagoLogicTest {
   
private PodamFactory factory= new PodamFactoryImpl();

@Inject
private PagoLogic pagoLogica;

@Inject
    private UserTransaction utx;

@PersistenceContext
protected EntityManager em;

private List<PagoEntity> data = new ArrayList<PagoEntity>();
        
private List<DomicilioEntity> dataDomicilio= new ArrayList<DomicilioEntity>();


//Le decimos lo que vamos a probar. 
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(PagoEntity.class.getPackage()).addPackage(PagoPersistence.class.getPackage()).addClass(PagoLogic.class).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
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
        em.createQuery("delete from PagoEntity").executeUpdate();
        em.createQuery("delete from DomicilioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        
        for (int i = 0; i < 3; i++) {
            DomicilioEntity entity = factory.manufacturePojo(DomicilioEntity.class);
            em.persist(entity);
            dataDomicilio.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            PagoEntity entity = factory.manufacturePojo(PagoEntity.class);
            entity.setOrden(dataDomicilio.get(1));
            em.persist(entity);
            data.add(entity);
        }
       
    }
    
   
   @Test 
   public void createTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        newEntity.setOrden(dataDomicilio.get(1));
        newEntity.setMonto(100.00);
        newEntity.setNumeroTarjeta(354567);
        PagoEntity result = pagoLogica.crearPago(dataDomicilio.get(1).getId(),newEntity);
        Assert.assertNotNull(result);

        PagoEntity entity = em.find(PagoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
  
     @Test(expected= BusinessLogicException.class)
     public void createFailTest1() throws BusinessLogicException
     {
         PagoEntity newEntity= factory.manufacturePojo(PagoEntity.class);
         newEntity.setOrden(dataDomicilio.get(1));
         newEntity.setMonto(-20000.00);
         PagoEntity result= pagoLogica.crearPago(dataDomicilio.get(1).getId(),newEntity);
     }
     
     @Test(expected = BusinessLogicException.class)
    public void createPagoTestConMontoInvalido() throws BusinessLogicException {
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        newEntity.setOrden(dataDomicilio.get(1));
        newEntity.setMonto(0.0);
        pagoLogica.crearPago(newEntity.getOrden().getId(),newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createPagoTestConTarjetaInvalido() throws BusinessLogicException {
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        newEntity.setNumeroTarjeta(0);
        newEntity.setOrden(dataDomicilio.get(0));
        pagoLogica.crearPago(newEntity.getOrden().getId(),newEntity);
    }
    
     @Test(expected = BusinessLogicException.class)
    public void createPagoTestConTarjetaInvalido2() throws BusinessLogicException {
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        newEntity.setNumeroTarjeta(-1111111);
        newEntity.setOrden(dataDomicilio.get(1));
        pagoLogica.crearPago(newEntity.getOrden().getId(),newEntity);
    }

    /**
     * Prueba para crear un Pago con editorial en null.
     *
     * @throws co.edu.uniandes.csw.pagostore.exceptions.BusinessLogicException
     */
    
    
//     @Test
//    public void getPagosTest() {
//        List<PagoEntity> list = pagoLogica.getPagos();
//        Assert.assertEquals(data.size(), list.size());
//        for (PagoEntity entity : list) {
//            boolean found = false;
//            for (PagoEntity storedEntity : data) {
//                if (entity.getId().equals(storedEntity.getId())) {
//                    found = true;
//                }
//            }
//            Assert.assertTrue(found);
//        }
//    }

    /**
     * Prueba para consultar un Pago.
     */
    @Test
    public void getPagoTest() {
        PagoEntity entity = data.get(0);
        PagoEntity resultEntity = pagoLogica.getPago(entity.getOrden().getId(),entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getMonto(), resultEntity.getMonto());
        Assert.assertEquals(entity.getNumeroTarjeta(), resultEntity.getNumeroTarjeta());
    }
     
      @Test
    public void updatePagoTest() throws BusinessLogicException {
        PagoEntity entity = data.get(0);
        PagoEntity pojoEntity = factory.manufacturePojo(PagoEntity.class);
        pojoEntity.setId(entity.getId());
        pagoLogica.updatePago(pojoEntity.getId(), pojoEntity);
        PagoEntity resp = em.find(PagoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getMonto(), resp.getMonto());
        Assert.assertEquals(pojoEntity.getNumeroTarjeta(), resp.getNumeroTarjeta());   
    }
    
    
    @Test(expected = BusinessLogicException.class)
    public void updatePagoConMontoInvalidoTest() throws BusinessLogicException {
        PagoEntity entity = data.get(0);
        PagoEntity pojoEntity = factory.manufacturePojo(PagoEntity.class);
        pojoEntity.setMonto(-10.0);
        pojoEntity.setId(entity.getId());
        pagoLogica.updatePago(pojoEntity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updatePagoConMontoInvalidoTest2() throws BusinessLogicException {
        PagoEntity entity = data.get(0);
        PagoEntity pojoEntity = factory.manufacturePojo(PagoEntity.class);
        pojoEntity.setMonto(0.0);
        pojoEntity.setId(entity.getId());
        pagoLogica.updatePago(pojoEntity.getId(), pojoEntity);
    }
     
    
     
     
     @Test
    public void deletePagoTest() throws BusinessLogicException {
        PagoEntity entity = data.get(0);
        pagoLogica.deletePago(entity.getOrden().getId(),entity.getId());
        PagoEntity deleted = em.find(PagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     
     
     
    
}
