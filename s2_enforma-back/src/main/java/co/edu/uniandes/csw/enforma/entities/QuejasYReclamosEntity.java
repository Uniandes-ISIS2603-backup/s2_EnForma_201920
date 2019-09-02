/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Jimmy Pepinosa
 */
@Entity
public class QuejasYReclamosEntity extends BaseEntity implements Serializable
{
    private String descripcion;
    
    private String asusnto;

    /**
     * Devuelve la descripcion de la queja o reclamo
     * @return the restriccion
     */
    public String getDescripcion() 
    {
        return descripcion;
    }

    /**
     * Modifica la descripcion de la queja o el reclamo
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) 
    {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve el asunto de la queja o reclamo
     * @return the asusnto
     */
    public String getAsusnto() 
    {
        return asusnto;
    }

    /**
     * Modifica el asunto de la queja o reclamo
     * @param asusnto the asusnto to set
     */
    public void setAsusnto(String asusnto) 
    {
        this.asusnto = asusnto;
    }
    
    
}
