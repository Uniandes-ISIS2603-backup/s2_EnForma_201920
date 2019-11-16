/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.dtos;

import co.edu.uniandes.csw.enforma.entities.PagoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Elina Jaimes
 */
public class PagoDTO implements Serializable{
    
    private Long id;
    private Double monto;
    private Integer numeroTarjeta;
    private Boolean esPrepago;
    private String estadoPago;
    
    private DomicilioDTO orden;
    
    public PagoDTO(){
        
    }
        
    public PagoDTO(PagoEntity pagoEntity)
    {
        if(pagoEntity!=null)
        {
            this.id=pagoEntity.getId();
            this.monto=pagoEntity.getMonto();
            this.esPrepago=pagoEntity.getEsPrepago();
            this.estadoPago=pagoEntity.getEstadoPago();
            this.numeroTarjeta=pagoEntity.getNumeroTarjeta();
            if(pagoEntity.getOrden()!=null)
            {
                orden=new DomicilioDTO(pagoEntity.getOrden());
            }
        }
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    
     /**
     * @param monto the monto to set
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }
    /**
     * @return the monto
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * @param numeroTarjeta the numeroTarjeta to set
     */
    public void setNumeroTarjeta(Integer numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * @return the numeroTarjeta
     */
    public Integer getNumeroTarjeta() {
        return numeroTarjeta;
    }

   /**
     * @param esPrepago the esPrepago to set
     */
    public void setEsPrepago(Boolean esPrepago) {
        this.esPrepago = esPrepago;
    }


    /**
     * @return the esPrepago
     */
    public Boolean getEsPrepago() {
        return esPrepago;
    }

    /**
     * @param estadoPago the estadoPago to set
     */
    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }
    
    /**
     * @return the estadoPago
     */
    public String getEstadoPago() {
        return estadoPago;
    }

    
    
     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    
    /**
     * @param orden the orden to set
     */
    public void setOrden(DomicilioDTO orden) {
        this.orden = orden;
    }
    /**
     * @return the orden
     */
    public DomicilioDTO getOrden() {
        return orden;
    }

    
    
     public PagoEntity toEntity() 
    {
        PagoEntity pagoEntity = new PagoEntity();
        pagoEntity.setId(this.getId());
        pagoEntity.setMonto(this.getMonto());
        pagoEntity.setNumeroTarjeta(this.getNumeroTarjeta());
        pagoEntity.setEsPrepago(this.getEsPrepago());
        pagoEntity.setEstadoPago(this.getEstadoPago());
        
        if(this.orden!=null)
        {
        pagoEntity.setOrden(this.getOrden().toEntity());
        }
        return pagoEntity;
    }
    
}
