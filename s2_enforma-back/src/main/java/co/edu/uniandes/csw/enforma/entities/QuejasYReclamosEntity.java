/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.entities;

import co.edu.uniandes.csw.enforma.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Jimmy Pepinosa
 */
@Entity
public class QuejasYReclamosEntity extends BaseEntity implements Serializable
{
    private String descripcion;
    
    private String asusnto;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecha;
    
    @PodamExclude
    @OneToOne
    private DomicilioEntity domicilio;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity usuario;

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
    
     /**
     * @return the date
     */
    public Date getDate() {
        return fecha;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.fecha = date;
    }
    
}
