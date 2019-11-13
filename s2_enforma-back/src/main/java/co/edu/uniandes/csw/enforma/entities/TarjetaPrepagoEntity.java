/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamDoubleValue;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Sebastián Clavijo
 */
@Entity
public class TarjetaPrepagoEntity extends BaseEntity implements Serializable
{
    private String numTarjetaPrepago;
    
    @PodamDoubleValue(minValue = 1.0)
    private Double saldo;
    
    @PodamDoubleValue(minValue = 1.0)
    private Double puntos;
    
    @PodamExclude
    @OneToMany(mappedBy="tarjetaPrepago")
    private List<PagoEntity> pagos= new ArrayList<PagoEntity>();
    
    @PodamExclude
    @OneToOne
    private ClienteEntity cliente;
    
    /**
     * @return idTarjetaPrepago
     */
    public String getNumTarjetaPrepago()
    {
        return this.numTarjetaPrepago;
    }
    
    /**
     * @param tarjeta
     */
    public void setNumTarjetaPrepago(String tarjeta)
    {
        this.numTarjetaPrepago = tarjeta;
    }
    
     /**
     * @return saldo
     */
    public Double getSaldo()
    {
        return this.saldo;
    }
    
    /**
     * @param saldo
     */
    public void setSaldo(Double saldo)
    {
        this.saldo = saldo;
    }
    
     /**
     * @return puntos
     */
    public Double getPuntos()
    {
        return this.puntos;
    }
    
    /**
     * @param puntos
     */
    public void setPuntos(Double puntos)
    {
        this.puntos = puntos;
    }
    
    /**
     * da los pagos asociados a la tarjeta
     * @return pagos
     */
    public List<PagoEntity> getPagos()
    {
        return pagos;
    }
    
    /**
     * modifica los pagos de la tarjeta
     * @param pagos 
     */
    public void setPagos(List<PagoEntity> pagos)
    {
        this.pagos = pagos;
    }
    
    /**
     * da el cliente
     * @return cliente
     */
    public ClienteEntity getCliente()
    {
        return cliente;
    }
    
    /**
     * modifica el cliente
     * @param cliente
     */
    public void setCliente(ClienteEntity cliente)
    {
        this.cliente = cliente;
    }
    
}
