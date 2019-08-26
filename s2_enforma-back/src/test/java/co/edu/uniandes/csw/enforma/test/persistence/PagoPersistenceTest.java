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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import org.junit.Assert;
import org.junit.Before;

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

    private List<PagoEntity> data = new ArrayList<PagoEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addClass(PagoEntity.class).addClass(PagoPersistence.class).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            limpiarDatosPrueba();
            insertarDatosPrueba();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void limpiarDatosPrueba() {
        em.createQuery("delete from PagoEntity").executeUpdate();
    }

    private void insertarDatosPrueba() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            PagoEntity entity = factory.manufacturePojo(PagoEntity.class);

            em.persist(entity);

            data.add(entity);
        }

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
//        List<PagoEntity> list = pp.findAll();
//
//        Assert.assertEquals(data.size(), list.size());
//        for (PagoEntity ent : list) {
//            boolean found = false;
//            for (PagoEntity entity : data) {
//                if (ent.getId().equals(entity.getId())) {
//                    found = true;
//                }
//            }
//            Assert.assertTrue(found);
//        }

    }

    @Test
    public void updateTest() {
        //Busco el dato que ya está guardado

        PodamFactory factory = new PodamFactoryImpl();

        PagoEntity entity = factory.manufacturePojo(PagoEntity.class);
        pp.create(entity);
        //Creo una nueva entidad
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        //Le doy a la nueva entidad el id que tiene la que ya fue creada
        newEntity.setId(entity.getId());
        //Le doy update a la nueva entidad pero???
        pp.update(newEntity);

        //Buscamos el primer dato
        PagoEntity resp = em.find(PagoEntity.class, entity.getId());
        //El id de la nueva debe ser el mismo al de la otra
        Assert.assertEquals(newEntity.getNumeroTarjeta(), resp.getNumeroTarjeta());
    }

    @Test
    public void deleteTest() {
        
        PodamFactory factory = new PodamFactoryImpl();
        PagoEntity entity = factory.manufacturePojo(PagoEntity.class);
        pp.create(entity);
        pp.delete(entity.getId());
        PagoEntity deleted = em.find(PagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
