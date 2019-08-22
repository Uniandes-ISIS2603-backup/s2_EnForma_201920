/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jose Manuel Flórez
 */

@Stateless


public class ComidaTipoPersistance {
    
    private static final Logger LOGGER = Logger.getLogger(ComidaTipoPersistance.class.getName());
    
    @PersistenceContext (unitName = "ComidasTipoPU")
    
    protected EntityManager em;
    
    public ComidaTipoEntity create(ComidaTipoEntity comidaTipo)
    {
        LOGGER.log(Level.INFO, "Creando una comida tipo nueva");
        em.persist(comidaTipo);
          LOGGER.log(Level.INFO, "Comida Tipo creada");
        return comidaTipo;
        
        
        //throw new java.lang.UnsupportedOperationException("Not supported yet.");
    
    }
    
    
}
