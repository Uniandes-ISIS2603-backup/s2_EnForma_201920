/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;

import co.edu.uniandes.csw.enforma.entities.PagoEntity;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import co.edu.uniandes.csw.enforma.persistence.PagoPersistence;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import reactor.util.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author Estudiante
 */

@RunWith(Arquillian.class)

public class PagoPersistenceTest {
    
    
    
    @Deployment
    public static JavaArchive createDeployment()
            {
                return ShrinkWrap.create(JavaArchive.class).addClass(PagoEntity.class).addClass(PagoPersistence.class).addAsManifestResource("META-INF/beans.xml", "beans.xml");   
            }
    
    @Inject
    PagoPersistence pp;
    
    @PersistenceContext
    
    EntityManager em;
    
    
    @Test
    public void createTest()
    {
        //Crear el pago
        PodamFactory factory= new PodamFactoryImpl();
        PagoEntity pago= factory.manufacturePojo(PagoEntity.class);
        PagoEntity r= pp.create(pago);
        Assert.notNull(r);
        
        PagoEntity entity= em.find(PagoEntity.class, r.getId());
        
        
        Assert.assertEquals(pago.getId(), entity.getId());
        
    }
    
    
    
}
    
