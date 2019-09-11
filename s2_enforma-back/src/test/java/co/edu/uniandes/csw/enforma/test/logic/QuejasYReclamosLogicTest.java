/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.ejb.QuejasYReclamosLogic;
import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.QuejasYReclamosPersistence;
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
 * @author Jimmy Pepinosa
 */
@RunWith(Arquillian.class)
public class QuejasYReclamosLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @PersistenceContext
    private EntityManager em;
            
    @Inject
    private QuejasYReclamosLogic quejasYReclamosLogic;
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(QuejasYReclamosEntity.class.getPackage())
                .addPackage(QuejasYReclamosLogic.class.getPackage())
                .addPackage(QuejasYReclamosPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test 
    public void createQuejasYReclamosLogicTest() throws BusinessLogicException
    {
        QuejasYReclamosEntity newEntity = factory.manufacturePojo(QuejasYReclamosEntity.class);
        QuejasYReclamosEntity result = quejasYReclamosLogic.createQuejasYReclamos(newEntity);
        Assert.assertNotNull(result);
        
        QuejasYReclamosEntity entity = em.find(QuejasYReclamosEntity.class, result.getId());
        Assert.assertEquals(entity.getId(), result.getId());
        Assert.assertEquals(entity.getAsusnto(), result.getAsusnto());
        Assert.assertEquals(entity.getDescripcion(), result.getDescripcion());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionPuntajeNull() throws BusinessLogicException
    {
        QuejasYReclamosEntity newEntity = factory.manufacturePojo(QuejasYReclamosEntity.class);
        newEntity.setAsusnto(null);
        QuejasYReclamosEntity result = quejasYReclamosLogic.createQuejasYReclamos(newEntity);
    }
}
