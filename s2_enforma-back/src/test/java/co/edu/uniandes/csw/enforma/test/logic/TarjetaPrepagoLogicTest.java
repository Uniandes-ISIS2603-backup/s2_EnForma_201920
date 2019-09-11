/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.ejb.TarjetaPrepagoLogic;
import co.edu.uniandes.csw.enforma.entities.TarjetaPrepagoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.TarjetaPrepagoPersistence;
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
 * @author Juan Sebastián Clavijo
 */
@RunWith(Arquillian.class)
public class TarjetaPrepagoLogicTest 
{
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaPrepagoEntity.class.getPackage())
                .addPackage(TarjetaPrepagoPersistence.class.getPackage())
                .addPackage(TarjetaPrepagoLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private TarjetaPrepagoLogic tarjetaPrepagoLogic;
    
    @PersistenceContext
    EntityManager em;
    
    @Test
    public void createTarjetaPrepagoTest() throws BusinessLogicException
    {
        TarjetaPrepagoEntity newEntity = factory.manufacturePojo(TarjetaPrepagoEntity.class);
        TarjetaPrepagoEntity result = tarjetaPrepagoLogic.createTarjetaPrepago(newEntity);
        Assert.assertNotNull(result);
        
        TarjetaPrepagoEntity entity = em.find(TarjetaPrepagoEntity.class, result.getId());
        Assert.assertEquals(entity.getIdTarjetaPrepago(), result.getIdTarjetaPrepago());
        Assert.assertEquals(entity.getPuntos(), result.getPuntos(), 0.001);
        Assert.assertEquals(entity.getSaldo(), result.getSaldo(), 0.001);
        
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createTarjetaPrepagoIdNullTest() throws BusinessLogicException
    {
        TarjetaPrepagoEntity newEntity = factory.manufacturePojo(TarjetaPrepagoEntity.class);
        newEntity.setIdTarjetaPrepago(null);
        TarjetaPrepagoEntity result = tarjetaPrepagoLogic.createTarjetaPrepago(newEntity);
    }
}
