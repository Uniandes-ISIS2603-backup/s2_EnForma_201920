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
    
    @Test 
    public void createComidaTipoTest () throws BusinessLogicException
    {
        ComidaTipoEntity newEntidad = factory.manufacturePojo(ComidaTipoEntity.class);
        ComidaTipoEntity result = comidaTipoLogic.createComidaTipo(newEntidad);
        Assert.assertNotNull(result);
        
        ComidaTipoEntity entity = em.find(ComidaTipoEntity.class, result.getId());
        Assert.assertNotNull(result);
                
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createComidaTipoNombreNullTest () throws BusinessLogicException
    {
        ComidaTipoEntity newEntidad = factory.manufacturePojo(ComidaTipoEntity.class);
        newEntidad.setNombre(null);
        ComidaTipoEntity result = comidaTipoLogic.createComidaTipo(newEntidad);
        
    }
    
}
