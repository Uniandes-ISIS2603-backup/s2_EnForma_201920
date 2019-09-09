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
           throw new BusinessLogicException("El nombre del usuario está vacio.");
       if(usuario.getEdad() == null)
           throw new BusinessLogicException("La edad del usuario está vacio.");
        if(usuario.getObjetivos() == null)
           throw new BusinessLogicException("Los objetivos del usuario están vacio.");
       
       usuario = persistence.create(usuario);
       return usuario;
   }
   
    
}
