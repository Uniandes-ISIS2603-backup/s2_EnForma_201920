/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;

import co.edu.uniandes.csw.enforma.entities.PagoEntity;
import org.jboss.arquillian.container.test.api.Deployment;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import co.edu.uniandes.csw.enforma.persistence.PagoPersistence;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import org.junit.Assert;

/**
 *
 * @author ev.jaimes
 */
@RunWith(Arquillian.class)
public class PagoPersistenceTest {

    @Inject
    PagoPersistence pp;

    @PersistenceContext
    protected EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addClass(PagoEntity.class).addClass(PagoPersistence.class).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createTest() {
        //Crear el pago
        PodamFactory factory = new PodamFactoryImpl();
        PagoEntity pago = factory.manufacturePojo(PagoEntity.class);
        //Error 
        PagoEntity r = pp.create(pago);
        Assert.assertNotNull(r);

        //Aqui de una ya está el test de find
        PagoEntity entity = em.find(PagoEntity.class, r.getId());

        Assert.assertEquals(pago.getId(), entity.getId());

    }

    @Test
    public void findAllTest() {

    }

    @Test

    public void updateTest() {
        
        PodamFactory factory = new PodamFactoryImpl();
        PagoEntity entity = factory.manufacturePojo(PagoEntity.class);
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);

        
        pp.create(entity);
        newEntity.setId(entity.getId());
        pp.update(newEntity);

        PagoEntity resp = em.find(PagoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNumeroTarjeta(), resp.getNumeroTarjeta());
    }

}
