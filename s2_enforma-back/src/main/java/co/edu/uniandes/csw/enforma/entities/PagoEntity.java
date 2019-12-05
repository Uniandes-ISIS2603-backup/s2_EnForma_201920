/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamDoubleValue;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamIntValue;

/**
 *
 * @author ev.jaimes
 */

@Entity
public class PagoEntity extends BaseEntity implements Serializable
{

    @PodamDoubleValue(minValue = 1.0)
    private Double monto;
    
    @PodamIntValue(minValue = 1)
    private Integer numeroTarjeta;
    
    
    private Boolean esPrepago;
    private String estadoPago;
    
    @PodamExclude
    @ManyToOne
    private TarjetaPrepagoEntity tarjetaPrepago;
    
    @PodamExclude
    @OneToOne(mappedBy="pago")
    private DomicilioEntity orden;
 
    
    

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

    /**
     * @return the tarjetaPrepago
     */
    public TarjetaPrepagoEntity getTarjetaPrepago() {
        return tarjetaPrepago;
    }

    /**
     * @param tarjetaPrepago the tarjetaPrepago to set
     */
    public void setTarjetaPrepago(TarjetaPrepagoEntity tarjetaPrepago) {
        this.tarjetaPrepago = tarjetaPrepago;
    }

    /**
     * @return the orden
     */
    public DomicilioEntity getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(DomicilioEntity orden) {
        this.orden = orden;
    }
}
