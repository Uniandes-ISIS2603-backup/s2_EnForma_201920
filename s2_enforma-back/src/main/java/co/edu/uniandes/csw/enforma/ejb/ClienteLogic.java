/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.ClientePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Sofía Vargas
 */
@Stateless
public class ClienteLogic 
{
    @Inject 
    private ClientePersistence persistence;
    
    
   public ClienteEntity crearUsuario(ClienteEntity usuario) throws BusinessLogicException
   {
       if(usuario.getNombre() == null)
           throw new BusinessLogicException("El nombre del cliente está vacio.");
       if(usuario.getEdad() <= 0)
           throw new BusinessLogicException("La edad del cliente no puede ser menor o igual a cero.");
       if(usuario.getObjetivos() == null)
           throw new BusinessLogicException("Los objetivos del cliente están vacio.");
        if(usuario.getPeso() <= 0.0)
           throw new BusinessLogicException("El peso del cliente tiene que ser mayor a cero.");
       if(usuario.getGluten() == null)
           throw new BusinessLogicException("El gluten no puede ser null.");
       if(usuario.getLactosa() == null)
           throw new BusinessLogicException("La lactosa no puede ser null.");
       if(usuario.getUserName() == null)
           throw new BusinessLogicException("El userName no puede ser vacio.");
       if(usuario.getContrasenia() == null)
           throw new BusinessLogicException("La contraseña no puede ser nula.");
       
       usuario = persistence.create(usuario);
       return usuario;
   }
   
    
}
