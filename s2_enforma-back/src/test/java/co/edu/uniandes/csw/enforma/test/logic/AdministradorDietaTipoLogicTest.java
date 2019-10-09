/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;


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
import co.edu.uniandes.csw.enforma.persistence.AdministradorPersistence;
import co.edu.uniandes.csw.enforma.ejb.AdministradorLogic;
import co.edu.uniandes.csw.enforma.ejb.AdministradorDietaTipoLogic;
import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;

/**
 *
 * @author Elina Jaimes
 */
@RunWith(Arquillian.class)
public class AdministradorDietaTipoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private AdministradorLogic administradorLogic;
    @Inject
    private AdministradorDietaTipoLogic administradorDietaTiposLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<AdministradorEntity> data = new ArrayList<AdministradorEntity>();

    private List<DietaTipoEntity> comidaTiposData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(AdministradorLogic.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
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
    private void clearData() {
        em.createQuery("delete from DietaTipoEntity").executeUpdate();
        em.createQuery("delete from AdministradorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            DietaTipoEntity comidaTipos = factory.manufacturePojo(DietaTipoEntity.class);
            em.persist(comidaTipos);
            comidaTiposData.add(comidaTipos);
        }
        for (int i = 0; i < 3; i++) {
            AdministradorEntity entity = factory.manufacturePojo(AdministradorEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                comidaTiposData.get(i).setAdministrador(entity);
            }
        }
    }

    /**
     * Prueba para asociar un DietaTipos existente a un Administrador.
     */
    @Test
    public void addDietaTiposTest() {
        AdministradorEntity entity = data.get(0);
        DietaTipoEntity comidaTipoEntity = comidaTiposData.get(1);
        DietaTipoEntity response = administradorDietaTiposLogic.addDietaTipo(comidaTipoEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(comidaTipoEntity.getId(), response.getId());
    }

    /**
     * Prueba para obtener una colección de instancias de DietaTipos asociadas a una
     * instancia Administrador.
     */
    @Test
    public void getDietaTiposTest() {
        List<DietaTipoEntity> list = administradorDietaTiposLogic.getDietaTipos(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }

    /**
     * Prueba para obtener una instancia de DietaTipos asociada a una instancia
     * Administrador.
     *
     * @throws co.edu.uniandes.csw.comidaTipostore.exceptions.BusinessLogicException
     */
    @Test
    public void getDietaTipoTest() throws BusinessLogicException {
        AdministradorEntity entity = data.get(0);
        DietaTipoEntity comidaTipoEntity = comidaTiposData.get(0);
        DietaTipoEntity response = administradorDietaTiposLogic.getDietaTipo(entity.getId(), comidaTipoEntity.getId());

        Assert.assertEquals(comidaTipoEntity.getId(), response.getId());
        Assert.assertEquals(comidaTipoEntity.getNombre(), response.getNombre());
    }

    /**
     * Prueba para obtener una instancia de DietaTipos asociada a una instancia
     * Administrador que no le pertenece.
     *
     * @throws co.edu.uniandes.csw.comidaTipostore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getDietaTipoNoAsociadoTest() throws BusinessLogicException {
        AdministradorEntity entity = data.get(0);
        DietaTipoEntity comidaTipoEntity = comidaTiposData.get(1);
        administradorDietaTiposLogic.getDietaTipo(entity.getId(), comidaTipoEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de DietaTipos asociadas a una instancia
     * de Administrador.
     */
    @Test
    public void replaceDietaTiposTest() {
        AdministradorEntity entity = data.get(0);
        List<DietaTipoEntity> list = comidaTiposData.subList(1, 3);
        administradorDietaTiposLogic.replaceDietaTipos(entity.getId(), list);

        entity = administradorLogic.getAdministrador(entity.getId());
        Assert.assertFalse(entity.getDietaTipo().contains(comidaTiposData.get(0)));
        Assert.assertTrue(entity.getDietaTipo().contains(comidaTiposData.get(1)));
        Assert.assertTrue(entity.getDietaTipo().contains(comidaTiposData.get(2)));
    }
}



