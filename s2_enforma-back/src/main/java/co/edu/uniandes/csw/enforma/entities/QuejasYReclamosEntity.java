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
    
    private String asunto;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecha;

    @PodamExclude
    @OneToOne
    private DomicilioEntity domicilio;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;

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
     * @return the asunto
     */
    public String getAsunto() 
    {
        return asunto;
    }

    /**
     * Modifica el asunto de la queja o reclamo
     * @param asunto the asunto to set
     */
    public void setAsunto(String asunto) 
    {
        this.asunto = asunto;
    }
    
     /**
     * @return the date
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the domicilio
     */
    public DomicilioEntity getDomicilio() {
        return domicilio;
    }

    /**
     * @param domicilio the domicilio to set
     */
    public void setDomicilio(DomicilioEntity domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * @return the cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
    
    
    
}