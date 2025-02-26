/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.AdministradorPersistence;
import co.edu.uniandes.csw.enforma.persistence.ComidaTipoPersistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author Jose Manuel Flórez
 */

@Stateless
public class ComidaTipoLogic {
    
    
    private static final Logger LOGGER = Logger.getLogger(ComidaTipoLogic.class.getName());
    //el inject permite que alguien venga y le inyecte los objetos a esta clase
    //n este caso payara (servidor de aplicaciones o contenedor) es el que crea los objetos.
    @Inject
    private ComidaTipoPersistence persistenceComidaTipo;
    
    //esta es la relación
     @Inject
    private AdministradorPersistence administradorPersistence;
    
    /**
     * 
     * @param comidaTipo la entidad de comida Tipo que se quiere persistir
     * @return la entidad Comida Tipo después de persistirla
     * @throws BusinessLogicException  Si el nombre es nulo o ya existe 
     * o su número de calorías es menor a 100 o el número de calorías es nulo 
     * o si el menú es nulo
     */
    public ComidaTipoEntity createComidaTipo  (ComidaTipoEntity comidaTipo) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "inicia proceso de creación de Comida Tipo");
        
        if(comidaTipo.getMenu() ==null || comidaTipo.getMenu().isEmpty())
        {
              throw new BusinessLogicException("el menpu de la comida tipo está vacío, no es posible crearlo");
        
        }
        
        if(comidaTipo.getNombre()==null || comidaTipo.getNombre().isEmpty())
        {
            throw new BusinessLogicException("el nombre de la comida tipo está vacío, no es posible crearlo");
        
        }
        
        if(comidaTipo.getCalorias() == null)
        {
            throw new BusinessLogicException("Las calorías son nulas, no es posible crearlo");
        }
        
        if(comidaTipo.getCalorias() < 100 )
        {
            throw new BusinessLogicException("Las calorías no pueden se menores a 100 por comida, no es posible crearlo");
        }
        
        
         if(comidaTipo.getMomentoDelDia() ==null || comidaTipo.getMomentoDelDia().isEmpty())
        {
                 throw new BusinessLogicException("el momento del día de la comida tipo es vacío, no es posible crearlo");
        
        }
//          if (comidaTipo.getImagenComida()==null)
//        {
//            throw new BusinessLogicException("La imagen no pueden ser vacia en la comida Tipo");
//        }
          LOGGER.log(Level.INFO, "Antes de comprobar si existe mismo nombre");
       if(persistenceComidaTipo.findByNombre(comidaTipo.getNombre()) != null)
       {
           LOGGER.log(Level.INFO, "Entró de comprobar si existe mismo nombre");
           throw new BusinessLogicException("Ya existe una comida con ese nombre");
       } 
        
         comidaTipo = persistenceComidaTipo.create(comidaTipo);
                return comidaTipo;
        
    }
    /**
    * Deveuve todas las Comidas Tipo que hay en la base de datos
    * @return Lista de las entidades Comida Tipo
    */
    public List<ComidaTipoEntity> getComidasTipo()
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las Comidas Tipo.");
        List<ComidaTipoEntity> comidaTipo = persistenceComidaTipo.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las comidas tipo.");
        
        return comidaTipo;
        
    }
    
    /** 
     * @param comidaTipoId es el id de la comida tipo a buscar.
     * @return la comida tipo encontrada,  null si no la encuentra.
     */
    
     public ComidaTipoEntity getComidaTipo(Long comidaTipoId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la comida tipo con id = {0}", comidaTipoId);
       ComidaTipoEntity comidaTipo = persistenceComidaTipo.find(comidaTipoId);
       if(comidaTipo == null)
       {
           LOGGER.log(Level.SEVERE, "La comida Tipo con el id = {0} no existe" , comidaTipoId);
       }
       
        LOGGER.log(Level.INFO, "Termina el proceso de consultar la comida tipo con id = {0}", comidaTipoId);
        return comidaTipo;
    }
    
     /**
     * Actualiza un libro Con ID específico
     *
     * @param comidaTipoId El ID de la comida Tipo a actualizar
     * @param comidaTipoEntity la entidad Comida Tipo con los cambios deseados
     * @return la entidad Comida Tipo luego de actualizarla
     * @throws BusinessLogicException Si el nombre es nulo o ya existe 
     * o su número de calorías es menor a 100 o el número de calorías es nulo 
     * o el menú es vacío no es posible actualizarlo.
     */
     public ComidaTipoEntity updateComidaTipoEntity (Long comidaTipoId, ComidaTipoEntity comidaTipoEntity) throws BusinessLogicException
     { 
         LOGGER.log(Level.INFO, "inicia proceso de actualizar la comida con id = {0} ", comidaTipoId);
         
         if(comidaTipoEntity.getMenu() ==null || comidaTipoEntity.getMenu().isEmpty())
        {
              throw new BusinessLogicException("el menu de la comida tipo está vacío, no es posible actializar comida Tipo con id = " + comidaTipoId);
        
        }
        
        if(comidaTipoEntity.getNombre()==null|| comidaTipoEntity.getNombre().isEmpty())
        {
            throw new BusinessLogicException("el nombre de la comida tipo está vacío, no es posible actializar comida Tipo con id = " + comidaTipoId);
        
        }
        
        if(comidaTipoEntity.getCalorias() == null)
        {
            throw new BusinessLogicException("Las calorías son nulas no es posible actializar comida Tipo con id = " +  comidaTipoId);
        }
        
        if(comidaTipoEntity.getCalorias() < 100 )
        {
            throw new BusinessLogicException("Las calorías no pueden se menores a 100 por comidano es posible actializar comida Tipo con id = " + comidaTipoId);
        }
//        if (comidaTipoEntity.getImagenComida()==null)
//        {
//            throw new BusinessLogicException("La imagen no pueden ser vacia en la comida Tipo con id = " + comidaTipoId);
//        }
        
        if(comidaTipoEntity.getMomentoDelDia() ==null || comidaTipoEntity.getMomentoDelDia().isEmpty())
        {
                 throw new BusinessLogicException("el momento del día de la comida tipo es vacío, no es posible actializar comida Tipo con id = " + comidaTipoId);
        
        }
        
        
       ComidaTipoEntity newComidaTipo = persistenceComidaTipo.update(comidaTipoEntity);
         
       
       LOGGER.log(Level.INFO, "Termina proceso de actualizar la comida tipo con id = {0} ", comidaTipoEntity.getId());
              
     return newComidaTipo;
     }
     
  
     
     /**
      * Eliminar una comida Tipo por ID
      * 
      * @param comidaTipoId
      * @throws BusinessLogicException
      */
     public void deleteComidaTipo (Long comidaTipoId) throws BusinessLogicException
     {
         LOGGER.log(Level.INFO, "inicia el proceso de borrar una Comida Tipocon id = {0} ", comidaTipoId);
    
         persistenceComidaTipo.delete(comidaTipoId);
         
         LOGGER.log(Level.INFO,"Termina el proceso de borrar una Comida Tipo con id = {0}", comidaTipoId);
     
     
     }

    
     
    
   
     
}
