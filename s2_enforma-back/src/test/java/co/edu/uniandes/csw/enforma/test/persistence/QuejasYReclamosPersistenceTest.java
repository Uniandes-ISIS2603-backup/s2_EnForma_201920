/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;

import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
import co.edu.uniandes.csw.enforma.persistence.QuejasYReclamosPersistence;
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
 * @author Jimmy Pepinosa
 */
@RunWith(Arquillian.class)
public class QuejasYReclamosPersistenceTest 
{
    @Inject
    QuejasYReclamosPersistence qrp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<QuejasYReclamosEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(QuejasYReclamosEntity.class)
                .addClass(QuejasYReclamosPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
     /**
     * Configuración inicial de la prueba.
     */
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
    
     /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() 
    {
        em.createQuery("delete from QuejasYReclamosEntity").executeUpdate();
    }
    
     /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
           QuejasYReclamosEntity entity = factory.manufacturePojo(QuejasYReclamosEntity.class);
           em.persist(entity);
           data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una queja o reclamo
     */
    @Test
    public void createQuejasYReclamosTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        QuejasYReclamosEntity quejasYReclamos = factory.manufacturePojo(QuejasYReclamosEntity.class);
        QuejasYReclamosEntity result = qrp.create(quejasYReclamos);
        
        Assert.assertNotNull(result);
        
        QuejasYReclamosEntity entity = em.find(QuejasYReclamosEntity.class, result.getId());
        //Assert.assertEquals(quejasYReclamos.get)
    }
    
}
