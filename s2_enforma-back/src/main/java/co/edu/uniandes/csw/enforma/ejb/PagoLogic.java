/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;

import co.edu.uniandes.csw.enforma.entities.PagoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.PagoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Elina Jaimes
 */

 @Stateless
public class PagoLogic {
    
     //Inicializa cosas del container, nos provee los objetos, los inyecta.
     //Evitar dependencias, crear un objeto puede ser muy complejo
     //Buscamos no tener que hacerlo, inyectamos las dependencias.
     //Lo hace Payara
     
     @Inject
     private PagoPersistence persistence;
     
     
     
     public PagoEntity crearPago(PagoEntity pago) throws BusinessLogicException
     {
         if(pago.getMonto()<0)
         {
             throw new BusinessLogicException("El valor del pago es inválido");
         }
         pago=persistence.create(pago);
         return pago;
     }         
    
}
