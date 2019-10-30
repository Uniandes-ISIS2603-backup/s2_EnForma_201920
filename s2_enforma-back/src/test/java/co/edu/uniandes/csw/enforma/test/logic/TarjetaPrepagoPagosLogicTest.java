/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.logic;

import co.edu.uniandes.csw.enforma.ejb.TarjetaPrepagoLogic;
import co.edu.uniandes.csw.enforma.ejb.TarjetaPrepagoPagosLogic;
import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Juan Sebastián Clavijo
 */
@RunWith(Arquillian.class)  
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TarjetaPrepagoPagosLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private TarjetaPrepagoLogic tarjetaPrepagoLogic;
    @Inject
    private TarjetaPrepagoPagosLogic tarjetaPrepagoPagosLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private List<TarjetaPrepagoEntity> data = new ArrayList<TarjetaPrepagoEntity>();

    private List<PagoEntity> pagosData = new ArrayList<>();
    
    private List<DomicilioEntity> domiciliosData = new ArrayList<DomicilioEntity>();
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaPrepagoEntity.class.getPackage())
                .addPackage(TarjetaPrepagoLogic.class.getPackage())
                .addPackage(TarjetaPrepagoPersistence.class.getPackage())
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
        em.createQuery("delete from PagoEntity").executeUpdate();
        em.createQuery("delete from TarjetaPrepagoEntity").executeUpdate();
        em.createQuery("delete from DomicilioEntity").executeUpdate();

    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        
        for (int i = 0; i < 3; i++) 
        {
            PagoEntity pagos = factory.manufacturePojo(PagoEntity.class);
            em.persist(pagos);
            pagosData.add(pagos);
        }
        for (int i = 0; i < 3; i++) 
        {
            TarjetaPrepagoEntity entity = factory.manufacturePojo(TarjetaPrepagoEntity.class);
            em.persist(entity);
            data.add(entity);
            pagosData.get(i).setTarjetaPrepago(entity);
               

        }
        
        
    }
    
    /**
     * Prueba para asociar un pagos existente a un tarjeta prepago.
     */
    @Test 
    public void addPagosTest() 
    {
        TarjetaPrepagoEntity entity = data.get(0);
        PagoEntity pagoEntity = pagosData.get(1);
        PagoEntity response = tarjetaPrepagoPagosLogic.addPago(pagoEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(pagoEntity.getId(), response.getId());
    }

    /**
     * Prueba para obtener una colección de instancias de pagos asociadas a una
     * instancia tarjeta prepago.
     */
    @Test
    public void getPagosTest() 
    {
        List<PagoEntity> list = tarjetaPrepagoPagosLogic.getPagos(data.get(1).getId());

        Assert.assertEquals(1, list.size());
    }
    
     /**
     * Prueba para obtener una instancia de pagos asociada a una instancia
     * tarjeta prepago.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void getPagoTest() throws BusinessLogicException 
    {
        TarjetaPrepagoEntity entity = data.get(1);
        PagoEntity pagoEntity = pagosData.get(1);
        PagoEntity response = tarjetaPrepagoPagosLogic.getPago(entity.getId(), pagoEntity.getId());

        Assert.assertEquals(pagoEntity.getId(), response.getId());
        Assert.assertEquals(pagoEntity.getEstadoPago(), response.getEstadoPago());
        Assert.assertEquals(pagoEntity.getMonto(), response.getMonto());
        Assert.assertEquals(pagoEntity.getNumeroTarjeta(), response.getNumeroTarjeta());
        Assert.assertEquals(pagoEntity.getOrden(), response.getOrden());
    }
    
     /**
     * Prueba para obtener una instancia de pagos asociada a una instancia
     * tarjeta prepago que no le pertenece.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getPagoNoAsociadoTest() throws BusinessLogicException 
    {
        TarjetaPrepagoEntity entity = data.get(0);
        PagoEntity pagoEntity = pagosData.get(1);
        tarjetaPrepagoPagosLogic.getPago(entity.getId(), pagoEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de pagos asociadas a una instancia
     * de tarjeta prepago.
     */
    @Test
    public void replacePagosTest() 
    {
        TarjetaPrepagoEntity entity = data.get(1);
        List<PagoEntity> list = pagosData.subList(1, 3);
        tarjetaPrepagoPagosLogic.replacePagos(entity.getId(), list);

        entity = tarjetaPrepagoLogic.getTarjetaPrepago(entity.getId());
        Assert.assertFalse(entity.getPagos().contains(pagosData.get(0)));
        Assert.assertTrue(entity.getPagos().contains(pagosData.get(1)));
        Assert.assertTrue(entity.getPagos().contains(pagosData.get(2)));
    }
    
}
