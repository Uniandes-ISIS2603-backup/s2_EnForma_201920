/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
import co.edu.uniandes.csw.enforma.persistence.AdministradorPersistence;
import co.edu.uniandes.csw.enforma.ejb.AdministradorLogic;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.junit.Assert;


/**
 *
 * @author Elina Jaimes
 */
@RunWith(Arquillian.class)
public class AdministradorLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private AdministradorLogic pagoLogica;

    @PersistenceContext
    protected EntityManager em;

//Le decimos lo que vamos a probar. 
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addClass(AdministradorEntity.class).addClass(AdministradorPersistence.class).addClass(AdministradorLogic.class).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createTest() throws BusinessLogicException {
        
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        AdministradorEntity result = pagoLogica.crearAdministrador(newEntity);

        Assert.assertNotNull(result);

        AdministradorEntity entity = em.find(AdministradorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    @Test(expected = BusinessLogicException.class)
    public void createFailTest() throws BusinessLogicException {
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        newEntity.setUsername("");
        AdministradorEntity result = pagoLogica.crearAdministrador(newEntity);
    }

}
