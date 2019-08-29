/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;

import co.edu.uniandes.csw.enforma.entities.UsuarioEntity;
import co.edu.uniandes.csw.enforma.persistence.UsuarioPersistence;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
import javax.transaction.UserTransaction;
import java.util.ArrayList;

/**
 *
 * @author Sofía Vargas
 */
@RunWith(Arquillian.class)
public class UsuarioPersistanceTest 
{
    @Inject
    UsuarioPersistence up;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<UsuarioEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Before
    public void configTest() 
    {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData() 
    {
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }
    
     /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }    
    
     @Test
    public void createUsuarioTest()
    {
       PodamFactory factory = new PodamFactoryImpl();
       UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
       UsuarioEntity result = up.create(usuario);
       Assert.assertNotNull(result);
       
       UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
       
       Assert.assertEquals(usuario.getNombre(),entity.getNombre());
       
       
    }
    

    @Test
    public void getUsuarioListTest()
    {
        List<UsuarioEntity> list = up.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(UsuarioEntity ent : list)
        {
            boolean  encontrada = false;
            for(UsuarioEntity entity : data)
            {
                if(ent.getId().equals(entity.getId()))
                {
                    encontrada = true;
                }
            }
            Assert.assertTrue(encontrada);
        }
    }
    
  
    public void getUsuarioTest()
    {
        UsuarioEntity usuario = data.get(0);
        UsuarioEntity newEntity = up.find(usuario.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(usuario.getId(), newEntity.getId());
        Assert.assertEquals(usuario.getNombre(), newEntity.getNombre());
        Assert.assertEquals(usuario.getEdad(), newEntity.getEdad());
        Assert.assertEquals(usuario.getObjetivos(), newEntity.getObjetivos());
        Assert.assertEquals(usuario.getPeso(), newEntity.getPeso());
        
        
    }
    

    @Test
    public void updateCalificacionTest()
    {
        UsuarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setId(entity.getId());
        up.update(newEntity);
        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    @Test
    public void deleteUsuarioTest()
    {
        UsuarioEntity entity = data.get(0);
        up.delete(entity.getId());
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

            

    
}
