/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.dtos;

import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import java.io.Serializable;

/**
 *
 * @author Jose Manuel Flórez
 */
public class ComidaTipoDTO implements Serializable
{
    private String menu;
    private String momentoDelDia;
    private Integer calorias;
    private String nombre;
    
    
    /*
    *Relación a un Administrador DTO 
    * dado que tiene cardinalidad 1.
    */
 //   private AdministradorDTO administrador;
    
    
    /**
     *  El constructor por defecto 
     */
    public ComidaTipoDTO()
    {
        
    }
    
    
    /**
     * Constructor a partir de la entidad
     * 
     * @param ComidaTipoEntity la entidad Comida Tipo
     */
    public ComidaTipoDTO (ComidaTipoEntity comidaTipoEntity)
    {
    //    if()
        
    }
    
    
}
