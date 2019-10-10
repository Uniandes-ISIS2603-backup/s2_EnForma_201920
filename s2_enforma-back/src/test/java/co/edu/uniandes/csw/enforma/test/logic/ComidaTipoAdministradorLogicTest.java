/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.ejb.ComidaTipoAdministradorLogic;
import co.edu.uniandes.csw.enforma.ejb.ComidaTipoLogic;
import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.AdministradorPersistence;
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
 * @author Jose Manuel Flórez
 */

@RunWith(Arquillian.class)
public class ComidaTipoAdministradorLogicTest 
{
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    
    @Inject 
    private ComidaTipoLogic comidaTipoLogic;
    
            
    @Inject
    private ComidaTipoAdministradorLogic comidaTipoAdministradorLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    
     @Inject
    private UserTransaction utx;
     
     
     private List<AdministradorEntity> data = new ArrayList<AdministradorEntity>();

    private List<ComidaTipoEntity> comidaTipoData = new ArrayList();
     
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(ComidaTipoLogic.class.getPackage())
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
        em.createQuery("delete from ComidaTipoEntity").executeUpdate();
        em.createQuery("delete from AdministradorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ComidaTipoEntity comidasTipo = factory.manufacturePojo(ComidaTipoEntity.class);
            em.persist(comidasTipo);
            comidaTipoData.add(comidasTipo);
        }
        for (int i = 0; i < 3; i++) {
            AdministradorEntity entity = factory.manufacturePojo(AdministradorEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                comidaTipoData.get(i).setAdministrador(entity);
            }
        }
    }

    
     /**
     * Prueba para crear un Review.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void createComidaTipoAdminTest() throws BusinessLogicException {
        ComidaTipoEntity newEntity = factory.manufacturePojo(ComidaTipoEntity.class);
        newEntity.setAdministrador(data.get(1));
        ComidaTipoEntity result = comidaTipoAdministradorLogic.createComidaTipoAdmin(data.get(1).getId(), newEntity);
        Assert.assertNotNull(result);
        ComidaTipoEntity entity = em.find(ComidaTipoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getCalorias(), entity.getCalorias());
        Assert.assertEquals(newEntity.getMenu(), entity.getMenu());
         Assert.assertEquals(newEntity.getMomentoDelDia(), entity.getMomentoDelDia());
    }
     /**
     * Prueba para remplazar las instancias de comidaTipo asociadas a una instancia
     * de Administrador.
     */
    @Test
    public void replaceAdministradorTest() {
        //cojemos la comida tipo que habíamos creado en el método insert
        ComidaTipoEntity entity = comidaTipoData.get(0);
        //implementamos el replace editorial del método replaceDietatipo de la clase ComidaTipoAdministradorLogic
        comidaTipoAdministradorLogic.replaceAdministrador(entity.getId(), data.get(1).getId());
       
        entity = comidaTipoLogic.getComidaTipo(entity.getId());
        //aquí probamos si de verdad se actualizó
        Assert.assertEquals(entity.getAdministrador(), data.get(1));
    }

    /**
     * Prueba para desasociar una comidatipo  existente de una Administrador existente
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void removeAdministradoresTest() throws BusinessLogicException {
        comidaTipoAdministradorLogic.removeAdministrador(comidaTipoData.get(0).getId());
        ComidaTipoEntity response = comidaTipoLogic.getComidaTipo(comidaTipoData.get(0).getId());
        Assert.assertNull(response.getAdministrador());
    }
    
    
    
}
