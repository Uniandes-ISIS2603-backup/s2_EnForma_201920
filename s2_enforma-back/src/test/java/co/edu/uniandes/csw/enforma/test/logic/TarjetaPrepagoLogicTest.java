/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.ejb.TarjetaPrepagoLogic;
import co.edu.uniandes.csw.enforma.entities.PagoEntity;
import co.edu.uniandes.csw.enforma.entities.TarjetaPrepagoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.TarjetaPrepagoPersistence;
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
public class TarjetaPrepagoLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private TarjetaPrepagoLogic tarjetaPrepagoLogic;
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<TarjetaPrepagoEntity> data = new ArrayList<TarjetaPrepagoEntity>();
    
    private List<PagoEntity> pagosData = new ArrayList<PagoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaPrepagoEntity.class.getPackage())
                .addPackage(TarjetaPrepagoPersistence.class.getPackage())
                .addPackage(TarjetaPrepagoLogic.class.getPackage())
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
        em.createQuery("delete from TarjetaPrepagoEntity").executeUpdate();
        em.createQuery("delete from PagoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        for (int i = 0; i < 3; i++) {
            TarjetaPrepagoEntity entity = factory.manufacturePojo(TarjetaPrepagoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
        for(int i = 0; i < 3; i++)
        {
            PagoEntity pagos = factory.manufacturePojo(PagoEntity.class);
            em.persist(pagos);
            pagosData.add(pagos);

        }
        
        data.get(2).setSaldo(0.0);
        data.get(2).setPuntos(0.0);
        
        double valor = (Math.random()+1) *100;
        data.get(0).setSaldo(valor);
        data.get(0).setPuntos(valor);  
        data.get(1).setSaldo(valor);
        data.get(1).setPuntos(valor);
        
    }
    
    @Test
    public void createTarjetaPrepagoTest() throws BusinessLogicException
    {
        TarjetaPrepagoEntity newEntity = factory.manufacturePojo(TarjetaPrepagoEntity.class);
        TarjetaPrepagoEntity result = tarjetaPrepagoLogic.createTarjetaPrepago(newEntity);
        Assert.assertNotNull(result);
        
        TarjetaPrepagoEntity entity = em.find(TarjetaPrepagoEntity.class, result.getId());
        Assert.assertEquals(entity.getIdTarjetaPrepago(), result.getIdTarjetaPrepago());
        Assert.assertEquals(entity.getPuntos(), result.getPuntos(), 0.001);
        Assert.assertEquals(entity.getSaldo(), result.getSaldo(), 0.001);
       // Assert.assertEquals(entity.getPagos(), result.getPagos());
        
    }
    
    
    /**
     * pueba para crear una tarjeta prepago con un numero invalido
     * @throws BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createTarjetaPrepagoNumeroInvalidoTest() throws BusinessLogicException
    {
        TarjetaPrepagoEntity newEntity = factory.manufacturePojo(TarjetaPrepagoEntity.class);
        newEntity.setIdTarjetaPrepago(null);
        TarjetaPrepagoEntity result = tarjetaPrepagoLogic.createTarjetaPrepago(newEntity);
    }
    
    /**
     * prueba para crear una tarjeta prepago con numero vacio
     * @throws BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createTarjetaPrepagoNumeroVacioTest() throws BusinessLogicException
    {
        TarjetaPrepagoEntity newEntity = factory.manufacturePojo(TarjetaPrepagoEntity.class);
        newEntity.setIdTarjetaPrepago("");
        TarjetaPrepagoEntity result = tarjetaPrepagoLogic.createTarjetaPrepago(newEntity);
    }
    
     /**
     * Prueba para crear un tarjeta prepago con un saldo negativo
     * @throws BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createTarjetaPrepagtoSaldoNegativoTest() throws BusinessLogicException
    {
        TarjetaPrepagoEntity newEntity = factory.manufacturePojo(TarjetaPrepagoEntity.class);
        double valor = (Math.random()) * (-100);
        newEntity.setSaldo(valor);
        TarjetaPrepagoEntity result = tarjetaPrepagoLogic.createTarjetaPrepago(newEntity);
    }
    
     /**
     * Prueba para crear un tarjeta prepago con puntos negativos
     * @throws BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createTarjetaPrepagtoPutosNegativosTest() throws BusinessLogicException
    {
        TarjetaPrepagoEntity newEntity = factory.manufacturePojo(TarjetaPrepagoEntity.class);
        double valor = (Math.random()) * (-100);
        newEntity.setPuntos(valor);
        TarjetaPrepagoEntity result = tarjetaPrepagoLogic.createTarjetaPrepago(newEntity);
    }
    
     /**
     * Prueba para consultar la lista de tarjetas.
     */
    @Test
    public void getTarjetasPrepagoTest() {
        List<TarjetaPrepagoEntity> list = tarjetaPrepagoLogic.getTarjetasPrepago();
        Assert.assertEquals(data.size(), list.size());
        for (TarjetaPrepagoEntity entity : list) {
            boolean found = false;
            for (TarjetaPrepagoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
     /**
     * Prueba para consultar un tarjeta prepago.
     */
    @Test
    public void getTarjetaPrepagoTest()
    {
        TarjetaPrepagoEntity entity = data.get(0);
        TarjetaPrepagoEntity resultEntity = tarjetaPrepagoLogic.getTarjetaPrepago(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getIdTarjetaPrepago(), resultEntity.getIdTarjetaPrepago());
        Assert.assertEquals(entity.getSaldo(), resultEntity.getSaldo(), 0.001);
        Assert.assertEquals(entity.getPuntos(), resultEntity.getPuntos(),0.001);
    }
    
     /**
     * Prueba para actualizar una tarjeta prepago
     *
     * @throws BusinessLogicException
     */
    @Test
    public void updateTarjetaPrepagoTest() throws BusinessLogicException 
    {
        TarjetaPrepagoEntity entity = data.get(0);
        TarjetaPrepagoEntity pojoEntity = factory.manufacturePojo(TarjetaPrepagoEntity.class);
        pojoEntity.setId(entity.getId());
        tarjetaPrepagoLogic.updateTarjetaPrepago(pojoEntity.getId(), pojoEntity);
        TarjetaPrepagoEntity resp = em.find(TarjetaPrepagoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getSaldo(), resp.getSaldo(),0.001);
        Assert.assertEquals(pojoEntity.getPuntos(), resp.getPuntos(), 0.001);
        Assert.assertEquals(pojoEntity.getIdTarjetaPrepago(), resp.getIdTarjetaPrepago());
    }
    
     /**
     * Prueba para actualizar una tarjeta prepago con saldo inválido.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateTarjetaPrepagoConSaldoInvalidoTest() throws BusinessLogicException 
    {
        TarjetaPrepagoEntity entity = data.get(0);
        TarjetaPrepagoEntity pojoEntity = factory.manufacturePojo(TarjetaPrepagoEntity.class);
        double valor = (Math.random()) * (-100);
        pojoEntity.setSaldo(valor);
        pojoEntity.setId(entity.getId());
        tarjetaPrepagoLogic.updateTarjetaPrepago(pojoEntity.getId(), pojoEntity);
    }
    
        
     /**
     * Prueba para actualizar una tarjeta prepago con puntos invalidos.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateTarjetaPrepagoConPuntosInvalidoTest() throws BusinessLogicException 
    {
        TarjetaPrepagoEntity entity = data.get(0);
        TarjetaPrepagoEntity pojoEntity = factory.manufacturePojo(TarjetaPrepagoEntity.class);
        double valor = (Math.random()) * (-100);
        pojoEntity.setPuntos(valor);
        pojoEntity.setId(entity.getId());
        tarjetaPrepagoLogic.updateTarjetaPrepago(pojoEntity.getId(), pojoEntity);
    }
    
     /**
     * Prueba para actualizar una tarjeta prepago con numero invalido 2 (cadena null).
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateTarjetaPrepagoConNumeroInvalidoTest() throws BusinessLogicException 
    {
        TarjetaPrepagoEntity entity = data.get(0);
        TarjetaPrepagoEntity pojoEntity = factory.manufacturePojo(TarjetaPrepagoEntity.class);
        pojoEntity.setIdTarjetaPrepago(null);
        pojoEntity.setId(entity.getId());
        tarjetaPrepagoLogic.updateTarjetaPrepago(pojoEntity.getId(), pojoEntity);
    }
    
     /**
     * Prueba para actualizar una tarjeta prepago con numero invalido 2 (cadena vacía).
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateTarjetaPrepagoConNumeroInvalidoTest2() throws BusinessLogicException 
    {
        TarjetaPrepagoEntity entity = data.get(0);
        TarjetaPrepagoEntity pojoEntity = factory.manufacturePojo(TarjetaPrepagoEntity.class);
        pojoEntity.setIdTarjetaPrepago("");
        pojoEntity.setId(entity.getId());
        tarjetaPrepagoLogic.updateTarjetaPrepago(pojoEntity.getId(), pojoEntity);
    }
    
     /**
     * Prueba para eliminar una tarjeta prepago.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void deleteTarjetaPrepagoTest() throws BusinessLogicException 
    {
       TarjetaPrepagoEntity entity = data.get(2);
       tarjetaPrepagoLogic.deleteTarjetaPrepago(entity.getId());
       TarjetaPrepagoEntity deleted = em.find(TarjetaPrepagoEntity.class, entity.getId());
       Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para eliminar una tarjeta prepago con saldo 
     * @throws BusinessLogicException 
     */
    @Test (expected = BusinessLogicException.class)
    public void deleteTarjetaPrepagoWithSaldoTest() throws BusinessLogicException
    {
        TarjetaPrepagoEntity entity = data.get(0);
        double valor = (Math.random()+1) *100;
        entity.setSaldo(valor);
        tarjetaPrepagoLogic.deleteTarjetaPrepago(entity.getId());
    }
    
    /**
     * Prueba para eliminar una tarjeta prepago con puntos
     * @throws BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void deleteTarjetaPrepagoWithPuntosTest() throws BusinessLogicException
    {
        TarjetaPrepagoEntity entity = data.get(1);
        double valor = (Math.random()+1) *100;
        entity.setPuntos(valor);
        tarjetaPrepagoLogic.deleteTarjetaPrepago(entity.getId());
    }
    
}
