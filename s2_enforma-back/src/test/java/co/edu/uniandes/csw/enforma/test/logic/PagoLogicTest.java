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
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Elina Jaimes
 */

@RunWith(Arquillian.class)
public class PagoLogicTest {
   
private PodamFactory factory= new PodamFactoryImpl();

@Inject
private PagoLogic pagoLogica;

@PersistenceContext
protected EntityManager em;
        

//Le decimos lo que vamos a probar. 
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addClass(PagoEntity.class).addClass(PagoPersistence.class).addClass(PagoLogic.class).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
   
   @Test 
   public void createTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        PagoEntity result = pagoLogica.crearPago(newEntity);

        Assert.assertNotNull(result);

        PagoEntity entity = em.find(PagoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
  
     @Test(expected= BusinessLogicException.class)
     public void createFailTest() throws BusinessLogicException
     {
         PagoEntity newEntity= factory.manufacturePojo(PagoEntity.class);
         newEntity.setMonto(-20000.00);
         PagoEntity result= pagoLogica.crearPago(newEntity);
     }
    
    
    
}
