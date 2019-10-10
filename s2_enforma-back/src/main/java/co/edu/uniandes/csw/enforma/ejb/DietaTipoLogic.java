/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.AdministradorPersistence;
import co.edu.uniandes.csw.enforma.persistence.DietaTipoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Julio Morales
 */
@Stateless
public class DietaTipoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(DietaTipoLogic.class.getName());
    
    
    @Inject
    private DietaTipoPersistence persistence;
    
    @Inject
    private AdministradorPersistence administradorPersistence;
    
    public DietaTipoEntity createDietaTipo(DietaTipoEntity dietaTipo) throws BusinessLogicException
    {
       if (dietaTipo.getAdministrador()== null || administradorPersistence.find(dietaTipo.getAdministrador().getId()) == null) {
            throw new BusinessLogicException("El administrador es inválida");
        }
        
        if(dietaTipo.getNombre()==null){
           throw new BusinessLogicException("El nombre de la Dieta está vacío.");
       } 
       if(dietaTipo.getCantidadGrasa()==null){
           throw new BusinessLogicException("La cantidad de grasa de la Dieta está vacío.");
       }
       if(dietaTipo.getCantidadAzucar()==null){
           throw new BusinessLogicException("La cantidad de azucar de la Dieta está vacío.");
       }
       if(dietaTipo.getCantidadFibra()==null){
           throw new BusinessLogicException("La cantidad de fibra de la Dieta está vacío.");
       }
       if(dietaTipo.getCaloriasMax()==null){
           throw new BusinessLogicException("Las calorías máximas de la Dieta está vacío.");
       }
       if(dietaTipo.getCaloriasMin()==null){
           throw new BusinessLogicException("Las calorias mínimas de la Dieta está vacío.");
       }
       if(dietaTipo.getCaloriasMin()<=0){
           throw new BusinessLogicException("Las calorías mínimas no pueden ser menores o iguales a cero.");
       }
       if(dietaTipo.getCaloriasMax()<=0){
           throw new BusinessLogicException("Las calorías máximas no pueden ser menores o iguales a cero.");
       }
       if(dietaTipo.getCantidadAzucar()<=0){
           throw new BusinessLogicException("La cantidad de azucar no puede ser menor o igual a cero.");
       }
       if(dietaTipo.getCantidadFibra()<=0){
           throw new BusinessLogicException("La cantidad de fibra no puede ser menor o igual a cero.");
       }
       if(dietaTipo.getCantidadGrasa()<=0){
           throw new BusinessLogicException("La cantidad de grasa no puede ser menor o igual a cero.");
       }
       
        
        dietaTipo = persistence.create(dietaTipo);
        return dietaTipo;
    }
    
    
    
    public List<DietaTipoEntity> getDietasTipo(){
        List<DietaTipoEntity> dietasTipo = persistence.findAll();
        return dietasTipo;
    } 
    
    
    public DietaTipoEntity getDietaTipo(Long dietaTipoId){
        DietaTipoEntity dietaTipoEntity = persistence.find(dietaTipoId);
        if(dietaTipoEntity == null){
           LOGGER.log(Level.SEVERE, "La DietaTipo con el id = {0} no existe", dietaTipoId);
        }
        return dietaTipoEntity;
    }
    
    
    
    
    
    public DietaTipoEntity updateDietaTipo(Long dietaTIpoId, DietaTipoEntity dietaTipo){
        DietaTipoEntity newEntity = persistence.update(dietaTipo);
        return newEntity;
    }
    
    
    public void deleteDietaTipo(Long dietaTipoId){
        persistence.delete(dietaTipoId);
    }
    
    
    
    
}
