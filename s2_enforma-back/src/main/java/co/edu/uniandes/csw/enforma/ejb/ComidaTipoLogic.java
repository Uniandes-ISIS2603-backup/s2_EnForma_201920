/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.ComidaTipoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Jose Manuel Flórez
 */

@Stateless
public class ComidaTipoLogic {
    
    //el inject permite que alguien venga y le inyecte los objetos a esta clase
    //n este caso payara (servidor de aplicaciones o contenedor) es el que crea los objetos.
    @Inject
    private ComidaTipoPersistence persistence;
    
    
    public ComidaTipoEntity createComidaTipo  (ComidaTipoEntity comidaTipo) throws BusinessLogicException
    {
        if(comidaTipo.getNombre()==null)
        {
            throw new BusinessLogicException("el nombre de la comida tipo está vacío");
        }
        comidaTipo = persistence.create(comidaTipo);
                return comidaTipo;
        
    }
    
}
