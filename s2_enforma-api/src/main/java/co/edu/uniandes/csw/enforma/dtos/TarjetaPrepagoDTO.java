/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.dtos;

import co.edu.uniandes.csw.enforma.entities.TarjetaPrepagoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Sebastián Clavijo
 */
public class TarjetaPrepagoDTO implements Serializable 
{
    private Long id;
    
    private String numTarjetaPrepago;
    
    private Double saldo;
    
    private Double puntos;
    
     /**
     * relacion uno a uno cliente
     */
    private ClienteDTO cliente;
    
    
    /**
     * constructor por defecto
     */
    public TarjetaPrepagoDTO()
    {
        
    }
    
    /**
     * Constructor a partir de la entidad
     *
     * @param tarjetaPrepagoEntity La entidad de la tarjeta prepago
     */
    public TarjetaPrepagoDTO(TarjetaPrepagoEntity tarjetaPrepagoEntity) 
    {
        if (tarjetaPrepagoEntity != null) 
        {
            this.id = tarjetaPrepagoEntity.getId();
            this.numTarjetaPrepago = tarjetaPrepagoEntity.getNumTarjetaPrepago();
            this.puntos = tarjetaPrepagoEntity.getPuntos();
            this.saldo = tarjetaPrepagoEntity.getSaldo();
            if(tarjetaPrepagoEntity.getCliente() != null)
            {
                this.cliente = new ClienteDTO(tarjetaPrepagoEntity.getCliente());
            }
        }
    }
    
     /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad de la tarjeta prepago asociada.
     */
    public TarjetaPrepagoEntity toEntity() 
    {
        TarjetaPrepagoEntity tarjetaPrepagoEntity = new TarjetaPrepagoEntity();
        tarjetaPrepagoEntity.setId(this.id);
        tarjetaPrepagoEntity.setNumTarjetaPrepago(this.numTarjetaPrepago);
        tarjetaPrepagoEntity.setSaldo(this.saldo);
        tarjetaPrepagoEntity.setPuntos(this.puntos);
        if(this.cliente != null)
        {
            tarjetaPrepagoEntity.setCliente(this.cliente.toEntity());
        }

        return tarjetaPrepagoEntity;
    }
    
     /**
     * Devuelve el ID de la tarjeta prepago.
     *
     * @return the id
     */
    public Long getId() 
    {
        return id;
    }

    /**
     * Modifica el ID de la tarjeta prepago.
     *
     * @param id the id to set
     */
    public void setId(Long id) 
    {
        this.id = id;
    }

    /**
     * Devuelve el numero de la tarjeta prepago.
     *
     * @return numero de la tarjeta prepago
     */
    public String getNumTarjetaPrepago() 
    {
        return numTarjetaPrepago;
    }

    /**
     * Modifica el numero de la tarjeta prepago.
     *
     * @param numTarjetaPrepago el nuevo
     */
    public void setNumTarjetaPrepago(String numTarjetaPrepago) 
    {
        this.numTarjetaPrepago = numTarjetaPrepago;
    }

    /**
     * devuelve los puntos de la tarjeta
     * @return puntos
     */
    public Double getPuntos()
    {
        return puntos;
    }
    
    /**
     * modifica los puntos
     * @param puntos 
     */
    public void setPuntos(Double puntos)
    {
        this.puntos = puntos;
    }
    
    /**
     * devuelve el saldo de la tarjeta
     * @return saldo
     */
    public Double getSaldo()
    {
        return saldo;
    }
    
    /**
     * modifica el saldo de la tarjeta
     * @param saldo el saldo
     */
    public void setSaldo(Double saldo)
    {
        this.saldo = saldo;
    }
    
    @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the cliente
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
 
}
