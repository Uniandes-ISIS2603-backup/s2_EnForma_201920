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
public class DietaTipoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private DietaTipoLogic dietaTipoLogic;
    
    @PersistenceContext
    private EntityManager em;

    
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DietaTipoEntity.class.getPackage())
                .addPackage(DietaTipoLogic.class.getPackage())
                .addPackage(DietaTipoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
    
    
    @Test
    public void createDietaTipo() throws BusinessLogicException
    {
        DietaTipoEntity newEntity = factory.manufacturePojo(DietaTipoEntity.class);
        DietaTipoEntity result = dietaTipoLogic.createDietaTipo(newEntity);
        Assert.assertNotNull(result);
    }
    
    
    @Test (expected = BusinessLogicException.class)
    public void createDietaTipoNombreNull() throws BusinessLogicException{
        DietaTipoEntity newEntity = factory.manufacturePojo(DietaTipoEntity.class);
        newEntity.setNombre(null);
        DietaTipoEntity result = dietaTipoLogic.createDietaTipo(newEntity);
        
    }
    
    
}
