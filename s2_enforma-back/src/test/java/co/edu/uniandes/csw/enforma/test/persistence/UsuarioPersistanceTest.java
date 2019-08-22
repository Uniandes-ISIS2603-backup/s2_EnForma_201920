/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;

import co.edu.uniandes.csw.enforma.entities.UsuarioEntity;
import co.edu.uniandes.csw.enforma.persistence.UsuarioPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
 * @author Sofía Vargas
 */
@RunWith(Arquillian.class)
public class UsuarioPersistanceTest 
{
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(UsuarioEntity.class)
                .addClass(UsuarioPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    UsuarioPersistence up;
    
    protected EntityManager em;
    
    @Test
    public void createTest()
    {
       PodamFactory factory = new PodamFactoryImpl();
       UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
       UsuarioEntity result = up.create(usuario);
       Assert.assertNotNull(result);
       
       UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
       
       Assert.assertEquals(usuario.getNombre(),entity.getNombre());
       
       
    }
    
}
