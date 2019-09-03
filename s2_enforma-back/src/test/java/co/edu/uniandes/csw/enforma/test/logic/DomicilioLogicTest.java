/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.ejb.DomicilioLogic;
import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.DomicilioPersistence;
import javax.inject.Inject;
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
 * @author Juan Sebastián Clavijo
 */
@RunWith(Arquillian.class)
public class DomicilioLogicTest 
{
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DomicilioEntity.class.getPackage())
                .addPackage(DomicilioPersistence.class.getPackage())
                .addPackage(DomicilioLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private DomicilioLogic domicilioLogic;
    
    @Test
    public void createDomicilioTest() throws BusinessLogicException
    {
        DomicilioEntity newEntity = factory.manufacturePojo(DomicilioEntity.class);
        DomicilioEntity result = domicilioLogic.createDomicilio(newEntity);
        Assert.assertNotNull(result);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createDomicilioLugarEntregaNull() throws BusinessLogicException
    {
        DomicilioEntity newEntity = factory.manufacturePojo(DomicilioEntity.class);
        newEntity.setLugarEntrega(null);
        DomicilioEntity result = domicilioLogic.createDomicilio(newEntity);
    }
    
}
