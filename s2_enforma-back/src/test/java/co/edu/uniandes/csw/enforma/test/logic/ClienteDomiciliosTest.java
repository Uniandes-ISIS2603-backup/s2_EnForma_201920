/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.ejb.ClienteDomiciliosLogic;
import co.edu.uniandes.csw.enforma.ejb.ClienteLogic;
import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
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
 * @author @Sofia Vargas
 */
@RunWith(Arquillian.class)
public class ClienteDomiciliosTest 
{
    
 private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ClienteLogic clienteLogic;
    @Inject
    private ClienteDomiciliosLogic clienteDomiciliosLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ClienteEntity> data = new ArrayList<ClienteEntity>();

    private List<DomicilioEntity> domiciliosData = new ArrayList();

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
        em.createQuery("delete from DomicilioEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            DomicilioEntity domicilios = factory.manufacturePojo(DomicilioEntity.class);
            em.persist(domicilios);
            domiciliosData.add(domicilios);
        }
        for (int i = 0; i < 3; i++) {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                domiciliosData.get(i).setCliente(entity);
            }
        }
    }

    /**
     * Prueba para asociar un Domicilios existente a un Cliente.
     */
    @Test
    public void addDomiciliosTest() {
        ClienteEntity entity = data.get(0);
        DomicilioEntity domicilioEntity = domiciliosData.get(1);
        DomicilioEntity response = clienteDomiciliosLogic.addDomicilio(domicilioEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(domicilioEntity.getId(), response.getId());
    }

    /**
     * Prueba para obtener una colección de instancias de Domicilios asociadas a una
     * instancia Cliente.
     */
    @Test
    public void getDomiciliosTest() {
        List<DomicilioEntity> list = clienteDomiciliosLogic.getDomicilios(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }

    /**
     * Prueba para obtener una instancia de Domicilios asociada a una instancia
     * Cliente.
     *
     * @throws co.edu.uniandes.csw.domiciliostore.exceptions.BusinessLogicException
     */
    @Test
    public void getDomicilioTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        DomicilioEntity domicilioEntity = domiciliosData.get(0);
        DomicilioEntity response = clienteDomiciliosLogic.getDomicilio(entity.getId(), domicilioEntity.getId());

        Assert.assertEquals(domicilioEntity.getId(), response.getId());
       Assert.assertEquals(domicilioEntity.getCosto(), response.getCosto(), 0.001);
        Assert.assertEquals(domicilioEntity.getFecha(), response.getFecha());
        Assert.assertEquals(domicilioEntity.getLugarEntrega(), response.getLugarEntrega());
    }

    /**
     * Prueba para obtener una instancia de Domicilios asociada a una instancia
     * Cliente que no le pertenece.
     *
     * @throws co.edu.uniandes.csw.domiciliostore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getDomicilioNoAsociadoTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        DomicilioEntity domicilioEntity = domiciliosData.get(1);
        clienteDomiciliosLogic.getDomicilio(entity.getId(), domicilioEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de Domicilios asociadas a una instancia
     * de Cliente.
     */
    @Test
    public void replaceDomiciliosTest() {
        ClienteEntity entity = data.get(0);
        List<DomicilioEntity> list = domiciliosData.subList(1, 3);
        clienteDomiciliosLogic.replaceDomicilios(entity.getId(), list);

        entity = clienteLogic.getCliente(entity.getId());
        Assert.assertFalse(entity.getDomicilios().contains(domiciliosData.get(0)));
        Assert.assertTrue(entity.getDomicilios().contains(domiciliosData.get(1)));
        Assert.assertTrue(entity.getDomicilios().contains(domiciliosData.get(2)));
    }
}


