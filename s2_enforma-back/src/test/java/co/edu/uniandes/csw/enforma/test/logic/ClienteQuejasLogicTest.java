/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.ejb.ClienteQuejasLogic;
import co.edu.uniandes.csw.enforma.ejb.ClienteLogic;
import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
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
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class ClienteQuejasLogicTest 
{
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ClienteLogic clienteLogic;
    @Inject
    private ClienteQuejasLogic clienteQuejasLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ClienteEntity> data = new ArrayList<ClienteEntity>();

    private List<QuejasYReclamosEntity> quejasData = new ArrayList();

     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClienteLogic.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
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
        em.createQuery("delete from QuejasYReclamosEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            QuejasYReclamosEntity quejass = factory.manufacturePojo(QuejasYReclamosEntity.class);
            em.persist(quejass);
            quejasData.add(quejass);
        }
        for (int i = 0; i < 3; i++) {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                quejasData.get(i).setCliente(entity);
            }
        }
    }

    /**
     * Prueba para asociar un QuejasYReclamoss existente a un Cliente.
     */
    @Test
    public void addQuejasYReclamossTest() {
        ClienteEntity entity = data.get(0);
        QuejasYReclamosEntity quejasEntity = quejasData.get(1);
        QuejasYReclamosEntity response = clienteQuejasLogic.addQueja(quejasEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(quejasEntity.getId(), response.getId());
    }

    /**
     * Prueba para obtener una colección de instancias de QuejasYReclamoss asociadas a una
     * instancia Cliente.
     */
    @Test
    public void getQuejasYReclamosTest() {
        List<QuejasYReclamosEntity> list = clienteQuejasLogic.getQuejas(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }

    /**
     * Prueba para obtener una instancia de QuejasYReclamoss asociada a una instancia
     * Cliente.
     *
     * @throws co.edu.uniandes.csw.quejasstore.exceptions.BusinessLogicException
     */
    @Test
    public void getQuejasYReclamossTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        QuejasYReclamosEntity quejasEntity = quejasData.get(0);
        QuejasYReclamosEntity response = clienteQuejasLogic.getQuejas(entity.getId(), quejasEntity.getId());

        Assert.assertEquals(quejasEntity.getId(), response.getId());
    }

    /**
     * Prueba para obtener una instancia de QuejasYReclamoss asociada a una instancia
     * Cliente que no le pertenece.
     *
     * @throws co.edu.uniandes.csw.quejasstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getQuejasYReclamosNoAsociadoTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        QuejasYReclamosEntity quejasEntity = quejasData.get(1);
        clienteQuejasLogic.getQuejas(entity.getId(), quejasEntity.getId());
    }

   
}
