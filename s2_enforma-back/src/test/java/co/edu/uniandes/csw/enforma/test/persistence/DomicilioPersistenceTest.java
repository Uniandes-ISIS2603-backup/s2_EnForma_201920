/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;

import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import co.edu.uniandes.csw.enforma.persistence.DomicilioPersistence;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class DomicilioPersistenceTest 
{
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class).addClass(DomicilioEntity.class)
                .addClass(DomicilioPersistence.class)
                .addAsManifestResource("META_INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META_INF/beans.xml", "beans.xml");
    }
    
    @Inject
    DomicilioPersistence dp;
    
    @Test
    public void createTest()
    {
        
        //DomicilioEntity result = dp.create(usuario);
    }
}
