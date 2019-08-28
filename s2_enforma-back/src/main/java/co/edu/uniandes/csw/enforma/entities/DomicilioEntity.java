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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author juan sebastián clavijo
 */
@Entity
public class DomicilioEntity extends BaseEntity implements Serializable 
{
        private String idDomicilio;
        
        @Temporal(TemporalType.DATE)
        @PodamStrategyValue(DateStrategy.class)
        private Date fecha;
        
        private String lugarEntrega;
        
        private double costo;

    /**
     * @return the idDomicilio
     */
    public String getIdDomicilio() {
        return idDomicilio;
    }

    /**
     * @param idDomicilio the idDomicilio to set
     */
    public void setIdDomicilio(String idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    /**
     * @return the fecha
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
     * @return the lugarEntrega
     */
    public String getLugarEntrega() {
        return lugarEntrega;
    }

    /**
     * @param lugarEntrega the lugarEntrega to set
     */
    public void setLugarEntrega(String lugarEntrega) {
        this.lugarEntrega = lugarEntrega;
    }

    /**
     * @return the costo
     */
    public double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }
}
