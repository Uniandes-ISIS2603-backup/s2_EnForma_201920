/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;

import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
import co.edu.uniandes.csw.enforma.persistence.ClientePersistence;
import co.edu.uniandes.csw.enforma.persistence.DomicilioPersistence;
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
    
    private List<QuejasYReclamosEntity> data = new ArrayList<QuejasYReclamosEntity>();
    
    private List<ClienteEntity> dataCliente = new ArrayList<ClienteEntity>();
    
    private List<DomicilioEntity> dataDomicilios = new ArrayList<DomicilioEntity>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
                .addPackage(DomicilioEntity.class.getPackage())
                .addPackage(DomicilioPersistence.class.getPackage())
                .addPackage(QuejasYReclamosEntity.class.getPackage())
                .addPackage(QuejasYReclamosPersistence.class.getPackage())
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
        em.createQuery("delete from ClienteEntity").executeUpdate();
        em.createQuery("delete from DomicilioEntity").executeUpdate();
    }
    
     /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
           ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
           em.persist(entity);
           dataCliente.add(entity);
        }
        for (int i = 0; i < 3; i++) 
        {
           DomicilioEntity entity = factory.manufacturePojo(DomicilioEntity.class);
           em.persist(entity);
           dataDomicilios.add(entity);
        }
        for (int i = 0; i < 3; i++) 
        {
           QuejasYReclamosEntity entity = factory.manufacturePojo(QuejasYReclamosEntity.class);
           if(i == 0)
           {
               entity.setUsuario(dataCliente.get(0));
               entity.setDomicilio(dataDomicilios.get(0));
           }
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
        Assert.assertEquals(quejasYReclamos.getAsunto(), entity.getAsunto());
    }
    
    /**
     * Prueba para consultar la lista de quejas y reclamos
     */
    @Test
    public void getQuejasYReclamosTest()
    {
        List<QuejasYReclamosEntity> list = qrp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(QuejasYReclamosEntity ent : list)
        {
            boolean encontrada = false;
            for(QuejasYReclamosEntity entity : data)
            {
                if(ent.getId().equals(entity.getId()))
                {
                    encontrada = true;
                }
            }
            Assert.assertTrue(encontrada);
        }
    }
    
    /**
     * Prueba para consultar una queja o reclamo
     */
    @Test
    public void getQuejaOReclamoTest()
    {
        QuejasYReclamosEntity entity = data.get(0);
        ClienteEntity clienteEntity = dataCliente.get(0);
        DomicilioEntity domicilioEntity = dataDomicilios.get(0);
        QuejasYReclamosEntity newEntity = qrp.find(clienteEntity.getId(), domicilioEntity.getId(),entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getAsunto(), newEntity.getAsunto());
        Assert.assertEquals(entity.getDescripcion(), newEntity.getDescripcion());
    }
    
    /**
     * Prueba para actualizar una queja o reclamo
     */
    @Test
    public void updateQuejasYReclamosTest()
    {
        QuejasYReclamosEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        QuejasYReclamosEntity newEntity = factory.manufacturePojo(QuejasYReclamosEntity.class);
        
        newEntity.setId(entity.getId());
        
        qrp.update(newEntity);
        
        QuejasYReclamosEntity resp = em.find(QuejasYReclamosEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    /**
     * Prueba para eliminar una queja o reclamo
     */
    @Test
    public void deleteQuejasYReclamosTest()
    {
        QuejasYReclamosEntity entity = data.get(0);
        qrp.delete(entity.getId());
        QuejasYReclamosEntity deleted = em.find(QuejasYReclamosEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para consultar una queja o reclamo por asunto
     */
    @Test
    public void findQuejasYReclamosByAsuntoTest() 
    {
        QuejasYReclamosEntity entity = data.get(0);
        QuejasYReclamosEntity newEntity = qrp.findByAsunto(entity.getAsunto());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getAsunto(), newEntity.getAsunto());

        newEntity = qrp.findByAsunto(null);
        Assert.assertNull(newEntity);
    }
    
     /**
     * Prueba para consultar una queja o reclamo por fecha
     */
    @Test
    public void findQuejasYReclamosByFechaTest() 
    {
        QuejasYReclamosEntity entity = data.get(0);
        QuejasYReclamosEntity newEntity = qrp.findByFecha(entity.getFecha());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());

        newEntity = qrp.findByFecha(null);
        Assert.assertNull(newEntity);
    }
    
    /**
     * Prueba para consultar una calificacion por el id del cliente al que pertenece
     */
    @Test
    public void findCalificacionByClienteIdTest()
    {
        QuejasYReclamosEntity entity = data.get(0);
        QuejasYReclamosEntity newEntity = qrp.findByClienteId(entity.getUsuario().getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getUsuario(), newEntity.getUsuario());
        
        newEntity = qrp.findByClienteId(null);
        Assert.assertNull(newEntity);
    }
    
    /**
     * Prueba para consultar una calificacion por el id del domicilio al que pertenece
     */
    @Test
    public void findCalificacionByDomicilioIdTest()
    {
        QuejasYReclamosEntity entity = data.get(0);
        QuejasYReclamosEntity newEntity = qrp.findByDomicilioId(entity.getDomicilio().getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getDomicilio(), newEntity.getDomicilio());
        
        newEntity = qrp.findByDomicilioId(null);
        Assert.assertNull(newEntity);
    }
    
}
