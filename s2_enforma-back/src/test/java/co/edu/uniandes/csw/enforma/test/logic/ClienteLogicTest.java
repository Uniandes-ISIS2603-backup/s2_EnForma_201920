/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.ejb.ClienteLogic;
import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.ClientePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Sofia Vargas
 */
@RunWith(Arquillian.class)
public class ClienteLogicTest 
{
    private final PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ClienteLogic usuarioLogic;
    
    @PersistenceContext
    private EntityManager em;
    @Inject
    private UserTransaction utx;

    private List<ClienteEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDevelopment()
    {
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClienteLogic.class.getPackage())
                 .addPackage(ClientePersistence.class.getPackage()) 
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
   
    @Test
    public void createClienteTest() throws BusinessLogicException
    {
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        ClienteEntity result = usuarioLogic.crearCliente(newEntity);
        Assert.assertNotNull(result);
        
        ClienteEntity entity = em.find(ClienteEntity.class, result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre()); 
        Assert.assertEquals(entity.getEdad(), result.getEdad()); 
       
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createClienteNombreNullTest() throws BusinessLogicException
    {
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setNombre(null);
        ClienteEntity result = usuarioLogic.crearCliente(newEntity);
    }
     
    
}
    