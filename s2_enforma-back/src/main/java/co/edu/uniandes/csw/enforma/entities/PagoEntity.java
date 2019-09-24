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
 * @author ev.jaimes
 */

@Entity
public class PagoEntity extends BaseEntity implements Serializable
{


    private Double monto;
    private Integer numeroTarjeta;
    private Boolean esPrepago;
    private String estadoPago;
    
    
    public PagoEntity()
    {
    
    }
    
    

    /**
     * @return the monto
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * @return the numeroTarjeta
     */
    public Integer getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * @param numeroTarjeta the numeroTarjeta to set
     */
    public void setNumeroTarjeta(Integer numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * @return the esPrepago
     */
    public Boolean getEsPrepago() {
        return esPrepago;
    }

    /**
     * @param esPrepago the esPrepago to set
     */
    public void setEsPrepago(Boolean esPrepago) {
        this.esPrepago = esPrepago;
    }

    /**
     * @return the estadoPago
     */
    public String getEstadoPago() {
        return estadoPago;
    }

    /**
     * @param estadoPago the estadoPago to set
     */
    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }
}
