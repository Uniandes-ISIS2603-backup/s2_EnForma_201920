

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;


import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import co.edu.uniandes.csw.enforma.persistence.ComidaTipoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.container.test.api.Deployment;
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
public class ComidaTipoPersistanceTest {
    
    @Deployment
    public static JavaArchive createDeployement ()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ComidaTipoEntity.class)
                .addClass(ComidaTipoPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private ComidaTipoPersistence comidaTipoPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    
    
    private List<ComidaTipoEntity> data = new ArrayList<ComidaTipoEntity>();
    
    
        /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
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
    private void clearData() {
        em.createQuery("delete from ComidaTipoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ComidaTipoEntity entity = factory.manufacturePojo(ComidaTipoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    
    @Test
    public void createTest()
    {
         
        PodamFactory factory = new PodamFactoryImpl();
        
        ComidaTipoEntity comidaTipo = factory.manufacturePojo( ComidaTipoEntity.class);
        
       ComidaTipoEntity result = comidaTipoPersistence.create(comidaTipo);
       Assert.assertNotNull(result);
       
       
       ComidaTipoEntity entity = em.find(ComidaTipoEntity.class, result.getId());
       
       Assert.assertEquals(comidaTipo.getCalorias(), entity.getCalorias());
    }
    
    /**
     * Prueba para consultar la lista de Comidas Tipo.
     */
     @Test
     public void getComidasTipoTest()
     {
         
         List<ComidaTipoEntity> lista = comidaTipoPersistence.findAll();
         
         Assert.assertEquals(data.size(), lista.size());
         
         for ( ComidaTipoEntity enti: lista)
         {
             boolean found = false;
             for (ComidaTipoEntity entity : data)
             {
                 if (enti.getId().equals(entity.getId()))
                 {
                     found = true;
                 }
             }
             Assert.assertTrue(found);
         }
     }
    
      /**
     * Prueba para consultar una ComidaTipo.
     */
    @Test
    public void getComidaTipoTest() {
        ComidaTipoEntity entity = data.get(0);
        ComidaTipoEntity newEntity = comidaTipoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getCalorias(), newEntity.getCalorias());
        Assert.assertEquals(entity.getMenu(), newEntity.getMenu());
   
    }
    
    
        /**
     * Prueba para eliminar una Comida Tipo.
     */
    @Test
    public void deleteComidaTipoTest() {
        ComidaTipoEntity entity = data.get(0);
        comidaTipoPersistence.delete(entity.getId());
        ComidaTipoEntity borrado = em.find(ComidaTipoEntity.class, entity.getId());
        Assert.assertNull(borrado);
    }
    
     /**
     * Prueba para actualizar una Comida Tipo.
     */
    @Test
    public void updateComidaTipoTest() {
        ComidaTipoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ComidaTipoEntity nuevaEntidad = factory.manufacturePojo(ComidaTipoEntity.class);

        nuevaEntidad.setId(entity.getId());

        comidaTipoPersistence.update(nuevaEntidad);

        ComidaTipoEntity resp = em.find(ComidaTipoEntity.class, entity.getId());

        Assert.assertEquals(nuevaEntidad.getNombre(), resp.getNombre());
        Assert.assertEquals(nuevaEntidad.getMenu(), resp.getMenu());

    }
     
     
     /**
     * Prueba para consultar una ComidaTipo por MomentoDelDia.
     */
    @Test
    public void findComidaTipoByMomentoDelDiaTest() {
        ComidaTipoEntity entity = data.get(0);
        ComidaTipoEntity newEntity = comidaTipoPersistence.findByMomentoDelDia(entity.getMomentoDelDia());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getMomentoDelDia(), newEntity.getMomentoDelDia());

        newEntity = comidaTipoPersistence.findByMomentoDelDia(null);
        Assert.assertNull(newEntity);
    }
    
     /**
     * Prueba para consultar una ComidaTipo por Calorias.
     */
    @Test
    public void findComidaTipoByCaloriasTest() {
        ComidaTipoEntity entity = data.get(0);
        ComidaTipoEntity newEntity = comidaTipoPersistence.findByCalorias(entity.getCalorias());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getCalorias(), newEntity.getCalorias());

        newEntity = comidaTipoPersistence.findByCalorias(null);
        Assert.assertNull(newEntity);
    }
    
    
      /**
     * Prueba para consultar una ComidaTipo por Menu.
     */
    @Test
    public void findComidaTipoByMenuTest() {
        ComidaTipoEntity entity = data.get(0);
        ComidaTipoEntity newEntity = comidaTipoPersistence.findByMenu(entity.getMenu());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getMenu(), newEntity.getMenu());

        newEntity = comidaTipoPersistence.findByMenu(null);
        Assert.assertNull(newEntity);
    }
    
      /**
     * Prueba para consultar una ComidaTipo por Nombre.
     */
    @Test
    public void findComidaTipoByNombreTest() {
        ComidaTipoEntity entity = data.get(0);
        ComidaTipoEntity newEntity = comidaTipoPersistence.findByNombre(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());

        newEntity = comidaTipoPersistence.findByNombre(null);
        Assert.assertNull(newEntity);
    }
    
    
}
