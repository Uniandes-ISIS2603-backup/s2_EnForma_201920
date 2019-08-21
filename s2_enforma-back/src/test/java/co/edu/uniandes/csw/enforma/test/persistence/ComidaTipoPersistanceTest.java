/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.test.persistence;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

/**
 *
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class ComidaTipoPersistanceTest {
    
    @Deployement
    public static JavaArchive createDeployement ()
    {
        return shrinkWrao.create(JavaArchive.class).addClass(ComidaTipo.class).addClass(ComidaTipoPersistance.class).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addManifestResource("META-INF/beans.xml");
    }
    
    @Inject
    ComidaTipoPersistence ep;
    
    @Test
    
    public void createTest()
    {
        // Falta crear las comidas tipo
       ComidaTipo result = ep
    }
}
