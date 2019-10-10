/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;

import co.edu.uniandes.csw.enforma.entities.CalificacionEntity;
import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.enforma.persistence.ClientePersistence;
import co.edu.uniandes.csw.enforma.persistence.DietaTipoPersistence;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.junit.Before;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Jimmy Pepinosa
 */
@RunWith(Arquillian.class)
public class CalificacionPersistenceTest 
{ 
    @Inject
    CalificacionPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<CalificacionEntity> data = new ArrayList<CalificacionEntity>();
    
    private List<ClienteEntity> dataCliente = new ArrayList<ClienteEntity>();
    
    private List<DietaTipoEntity> dataDieta = new ArrayList<DietaTipoEntity>();
    
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
                .addPackage(DietaTipoEntity.class.getPackage())
                .addPackage(DietaTipoPersistence.class.getPackage())
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
        em.createQuery("delete from CalificacionEntity").executeUpdate();
//        em.createQuery("delete from ClienteEntity").executeUpdate();
//        em.createQuery("delete from DietaTipoEntity").executeUpdate();
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
            DietaTipoEntity entity = factory.manufacturePojo(DietaTipoEntity.class);
            em.persist(entity);
            dataDieta.add(entity);
        }
        for (int i = 0; i < 3; i++) 
        {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            if(i == 0)
            {
                entity.setUsuario(dataCliente.get(0));
                entity.setDietaTipo(dataDieta.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
    }    
    
    /**
     * Prueba para crear una calificacion
     */
    @Test
    public void createCalifiacionTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity calificacion = factory.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity result = cp.create(calificacion);
        
        Assert.assertNotNull(result);
        
        CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());
        
        Assert.assertEquals(calificacion.getPuntaje(), entity.getPuntaje());
        Assert.assertEquals(calificacion.getComentario(), entity.getComentario());
        Assert.assertEquals(calificacion.getFecha(), entity.getFecha());
    }
    
    /**
     * Prueba para consultar la lista de Calificacion
     */
    @Test
    public void getCalificacionesTest()
    {
        List<CalificacionEntity> list = cp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(CalificacionEntity ent : list)
        {
            boolean  encontrada = false;
            for(CalificacionEntity entity : data)
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
     * Prueba para consultar una calificacion
     */
    @Test
    public void getCalificacionTest()
    {
        CalificacionEntity entity = data.get(0);
//        ClienteEntity clienteEntity = dataCliente.get(0);
//        DietaTipoEntity dietaEntity = dataDieta.get(0);
        CalificacionEntity newEntity = cp.find(entity.getUsuario().getId(),entity.getDietaTipo().getId(),entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getPuntaje(), newEntity.getPuntaje());
        Assert.assertEquals(entity.getComentario(), newEntity.getComentario());
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
    }
    
    /**
     * Prueba para actualizar una calificacion
     */
    @Test
    public void updateCalificacionTest()
    {
        CalificacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);

        newEntity.setId(entity.getId());

        cp.update(newEntity);

        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
        Assert.assertEquals(newEntity.getPuntaje(), resp.getPuntaje());
        Assert.assertEquals(newEntity.getComentario(), resp.getComentario());
        Assert.assertEquals(newEntity.getFecha(), resp.getFecha());
    }
    
    /**
     * Prueba para eliminar una calificacion
     */
    @Test
    public void deletelificacionTest()
    {
        CalificacionEntity entity = data.get(0);
        cp.delete(entity.getId());
        CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

     /**
     * Prueba para consultar una calificacion por puntaje
     */
    @Test
    public void findCalificacionByPuntajeTest() 
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = cp.findByPuntaje(entity.getPuntaje());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPuntaje(), newEntity.getPuntaje());
    }
    
     /**
     * Prueba para consultar una calificacion por fecha
     */
    @Test
    public void findCalificacionByFechaTest() 
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = cp.findByFecha(entity.getFecha());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());

        newEntity = cp.findByFecha(null);
        Assert.assertNull(newEntity);
    } 
    
    /**
     * Prueba para consultar una calificacion por el id de la dieta a la que pertenece
     */
    @Test
    public void findCalificacionByDietaTipoIdTest()
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = cp.findByDietaTipoId(entity.getDietaTipo().getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getDietaTipo(), newEntity.getDietaTipo());
        
        newEntity = cp.findByDietaTipoId(null);
        Assert.assertNull(newEntity);
    }
    
}
