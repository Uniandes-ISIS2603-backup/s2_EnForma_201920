/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.ejb.ClienteLogic;
import co.edu.uniandes.csw.enforma.ejb.ComidaTipoLogic;
import co.edu.uniandes.csw.enforma.ejb.DomicilioLogic;
import co.edu.uniandes.csw.enforma.ejb.PagoLogic;
import co.edu.uniandes.csw.enforma.ejb.QuejasYReclamosLogic;
import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import co.edu.uniandes.csw.enforma.entities.PagoEntity;
import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.ClientePersistence;
import co.edu.uniandes.csw.enforma.persistence.ComidaTipoPersistence;
import co.edu.uniandes.csw.enforma.persistence.DomicilioPersistence;
import co.edu.uniandes.csw.enforma.persistence.PagoPersistence;
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
 * @author Juan Sebastián Clavijo
 */
@RunWith(Arquillian.class)
public class DomicilioLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private DomicilioLogic domicilioLogic;

    @PersistenceContext
    EntityManager em;  
    
    @Inject
    private UserTransaction utx;
    
    private List<DomicilioEntity> data = new ArrayList<DomicilioEntity>();
    
    
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DomicilioEntity.class.getPackage())
                .addPackage(DomicilioPersistence.class.getPackage())
                .addPackage(DomicilioLogic.class.getPackage())
                .addPackage(QuejasYReclamosEntity.class.getPackage())
                .addPackage(QuejasYReclamosPersistence.class.getPackage())
                .addPackage(QuejasYReclamosLogic.class.getPackage())
                .addPackage(ComidaTipoEntity.class.getPackage())
                .addPackage(ComidaTipoPersistence.class.getPackage())
                .addPackage(ComidaTipoLogic.class.getPackage())
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
                .addPackage(PagoLogic.class.getPackage())
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
                .addPackage(ClienteLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() 
    {
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
    private void clearData() 
    {
        em.createQuery("delete from DomicilioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        for (int i = 0; i < 3; i++) {
            DomicilioEntity entity = factory.manufacturePojo(DomicilioEntity.class);
            em.persist(entity);
            data.add(entity);
        }
        
    }

          
    
    /**
     * prueba para crear un domicilio
     * @throws BusinessLogicException 
     */
    @Test
    public void createDomicilioTest() throws BusinessLogicException
    {
        DomicilioEntity newEntity = factory.manufacturePojo(DomicilioEntity.class);
        DomicilioEntity result = domicilioLogic.createDomicilio(newEntity);
        Assert.assertNotNull(result);
        
        DomicilioEntity entity = em.find(DomicilioEntity.class, result.getId());
        Assert.assertEquals(entity.getCosto(), result.getCosto(), 0.001);
        Assert.assertEquals(entity.getFecha(), result.getFecha());
        Assert.assertEquals(entity.getLugarEntrega(), result.getLugarEntrega());
    }
    
    /**
     * Prueba para crear un domicilio con un lugar de entra invalido
     * @throws BusinessLogicException 
     */
    @Test (expected = BusinessLogicException.class)
    public void createDomicilioLugarEntregaNullTest() throws BusinessLogicException
    {
        DomicilioEntity newEntity = factory.manufacturePojo(DomicilioEntity.class);
        newEntity.setLugarEntrega(null);
        DomicilioEntity result = domicilioLogic.createDomicilio(newEntity);
    }
    
    /**
     * Prueba para crear un domicilio con un costo negativo
     * @throws BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createDomicilioCostoNegativoTest() throws BusinessLogicException
    {
        DomicilioEntity newEntity = factory.manufacturePojo(DomicilioEntity.class);
        newEntity.setCosto(-50.0);
        DomicilioEntity result = domicilioLogic.createDomicilio(newEntity);
    }
    
     /**
     * Prueba para crear un domicilio con una fecha invalida
     * @throws BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createDomicilioFechaInvalidaTest() throws BusinessLogicException
    {
        DomicilioEntity newEntity = factory.manufacturePojo(DomicilioEntity.class);
        newEntity.setFecha(null);
        DomicilioEntity result = domicilioLogic.createDomicilio(newEntity);
    }
    
     /**
     * Prueba para consultar la lista de domicilios.
     */
    @Test
    public void getDomiciliosTest() {
        List<DomicilioEntity> list = domicilioLogic.getDomicilios();
        Assert.assertEquals(data.size(), list.size());
        for (DomicilioEntity entity : list) {
            boolean found = false;
            for (DomicilioEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
     /**
     * Prueba para consultar un domicilio.
     */
    @Test
    public void getDomicilioTest()
    {
        DomicilioEntity entity = data.get(0);
        DomicilioEntity resultEntity = domicilioLogic.getDomicilio(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getFecha(), resultEntity.getFecha());
        Assert.assertEquals(entity.getCosto(), resultEntity.getCosto(), 0.001);
    }
    
    
    /**
     * Prueba para actualizar un domicilio
     *
     * @throws BusinessLogicException
     */
    @Test
    public void updateDomicilioTest() throws BusinessLogicException 
    {
        DomicilioEntity entity = data.get(0);
        DomicilioEntity pojoEntity = factory.manufacturePojo(DomicilioEntity.class);
        pojoEntity.setId(entity.getId());
        domicilioLogic.updateDomicilio(pojoEntity.getId(), pojoEntity);
        DomicilioEntity resp = em.find(DomicilioEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getFecha(), resp.getFecha());
        Assert.assertEquals(pojoEntity.getCosto(), resp.getCosto(), 0.001);
    }
    
     /**
     * Prueba para actualizar un domicilio con fecha inválida.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateDomicilioConFECHAInvalidaTest() throws BusinessLogicException 
    {
        DomicilioEntity entity = data.get(0);
        DomicilioEntity pojoEntity = factory.manufacturePojo(DomicilioEntity.class);
        pojoEntity.setFecha(null);
        pojoEntity.setId(entity.getId());
        domicilioLogic.updateDomicilio(pojoEntity.getId(), pojoEntity);
    }
    
        
     /**
     * Prueba para actualizar un domicilio con precio inválido.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateDomicilioConPRECIOInvalidoTest() throws BusinessLogicException 
    {
        DomicilioEntity entity = data.get(0);
        DomicilioEntity pojoEntity = factory.manufacturePojo(DomicilioEntity.class);
        pojoEntity.setCosto(-245.0);
        pojoEntity.setId(entity.getId());
        domicilioLogic.updateDomicilio(pojoEntity.getId(), pojoEntity);
    }
    
     /**
     * Prueba para actualizar un domicilio con precio inválido.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateDomicilioConLUGARInvalidoTest() throws BusinessLogicException 
    {
        DomicilioEntity entity = data.get(0);
        DomicilioEntity pojoEntity = factory.manufacturePojo(DomicilioEntity.class);
        pojoEntity.setLugarEntrega(null);
        pojoEntity.setId(entity.getId());
        domicilioLogic.updateDomicilio(pojoEntity.getId(), pojoEntity);
    }
    
     /**
     * Prueba para actualizar un domicilio con precio inválido 2.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateDomicilioConLUGARInvalidoTest2() throws BusinessLogicException 
    {
        DomicilioEntity entity = data.get(0);
        DomicilioEntity pojoEntity = factory.manufacturePojo(DomicilioEntity.class);
        pojoEntity.setLugarEntrega("");
        pojoEntity.setId(entity.getId());
        domicilioLogic.updateDomicilio(pojoEntity.getId(), pojoEntity);
    }
    
     /**
     * Prueba para eliminar un domicilio
     *
     * @throws BusinessLogicException
     */
    @Test
    public void deleteDomicilioTest() throws BusinessLogicException 
    {
       DomicilioEntity entity = data.get(2);
       domicilioLogic.deleteDomicilio(entity.getId());
       DomicilioEntity deleted = em.find(DomicilioEntity.class, entity.getId());
       Assert.assertNull(deleted);
    }
    
}
