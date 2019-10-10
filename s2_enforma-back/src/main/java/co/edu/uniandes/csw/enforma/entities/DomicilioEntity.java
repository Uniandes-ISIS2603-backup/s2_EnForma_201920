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
 * @author juan sebastián clavijo
 */
@Entity
public class DomicilioEntity extends BaseEntity implements Serializable 
{
 
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecha;

    private String lugarEntrega;

    private Double costo;
    
    @PodamExclude
    @OneToOne
    private QuejasYReclamosEntity quejasYReclamos;
    
    @PodamExclude
    @OneToOne
    private PagoEntity pago;
    
    @PodamExclude
    @OneToOne
    private ComidaTipoEntity comidaTipo;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;
    
    
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
    
    /**
     * @return the queja
     */
    public QuejasYReclamosEntity getQuejasYReclamos() {
        return quejasYReclamos;
    }

    /**
     * @param queja the queja to set
     */
    public void setQuejasYReclamos(QuejasYReclamosEntity queja) {
        this.quejasYReclamos = queja;
    }

    /**
     * @return the pago
     */
    public PagoEntity getPago() {
        return pago;
    }

    /**
     * @param pago the pago to set
     */
    public void setPago(PagoEntity pago) {
        this.pago = pago;
    }

    /**
     * @return the comidaTipo
     */
    public ComidaTipoEntity getComidaTipo() {
        return comidaTipo;
    }

    /**
     * @param comidaTipo the comidaTipo to set
     */
    public void setComidaTipo(ComidaTipoEntity comidaTipo) {
        this.comidaTipo = comidaTipo;
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
