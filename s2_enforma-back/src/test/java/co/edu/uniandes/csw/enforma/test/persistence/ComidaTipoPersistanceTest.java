

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;


import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import co.edu.uniandes.csw.enforma.persistence.ComidaTipoPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Jose Manuel Flórez
 */
@RunWith(Arquillian.class)
public class ComidaTipoPersistanceTest {
    
    @Deployment
    public static JavaArchive createDeployement ()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ComidaTipoEntity.class)
                .addClass(ComidaTipoPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private ComidaTipoPersistence comidaTipoPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createTest()
    {
       


        
        PodamFactory factory = new PodamFactoryImpl();
        
        ComidaTipoEntity comidaTipo = factory.manufacturePojo( ComidaTipoEntity.class);
        
       ComidaTipoEntity result = comidaTipoPersistence.create(comidaTipo);
       Assert.assertNotNull(result);
       
       
       ComidaTipoEntity entity = em.find(ComidaTipoEntity.class, result.getId());
       
       Assert.assertEquals(comidaTipo.getCalorias(), entity.getCalorias());
    }
}
