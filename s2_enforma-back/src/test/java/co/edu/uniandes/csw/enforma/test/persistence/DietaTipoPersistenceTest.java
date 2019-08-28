/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;

import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.persistence.DietaTipoPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
 * @author Julio Morales
 */
@RunWith(Arquillian.class)
public class DietaTipoPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DietaTipoEntity.class)
                .addClass(DietaTipoPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private DietaTipoPersistence  dietaTipoPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createTest()
    {
        
        PodamFactory factory = new PodamFactoryImpl();
        
        DietaTipoEntity dietaTipo = factory.manufacturePojo(DietaTipoEntity.class);
        
        DietaTipoEntity result = dietaTipoPersistence.create(dietaTipo);
        Assert.assertNotNull(result);
        
        DietaTipoEntity entity = em.find(DietaTipoEntity.class, result.getId());
        Assert.assertEquals(dietaTipo.getNombre(), entity.getNombre());
    }
}
