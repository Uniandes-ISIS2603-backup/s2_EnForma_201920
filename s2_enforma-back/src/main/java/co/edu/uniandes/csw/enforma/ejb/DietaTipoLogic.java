/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.DietaTipoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Julio Morales
 */
@Stateless
public class DietaTipoLogic {
    
    @Inject
    private DietaTipoPersistence persistence;
    
    public DietaTipoEntity createDietaTipo(DietaTipoEntity dietaTipo) throws BusinessLogicException
    {
       if(dietaTipo.getNombre()==null)
       {
           throw new BusinessLogicException("El nombre de la Dieta está vacío.");
       }
        
        dietaTipo = persistence.create(dietaTipo);
        return dietaTipo;
    }
}
