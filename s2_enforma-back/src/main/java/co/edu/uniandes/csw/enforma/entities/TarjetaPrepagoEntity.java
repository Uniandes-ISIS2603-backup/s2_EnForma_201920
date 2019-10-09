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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Sebastián Clavijo
 */
@Entity
public class TarjetaPrepagoEntity extends BaseEntity implements Serializable
{
    private String idTarjetaPrepago;
    
    private Double saldo;
    
    private Double puntos;
    
    @PodamExclude
    @OneToMany
    private List<PagoEntity> pagos = new ArrayList<PagoEntity>();
    
    @PodamExclude
    @OneToOne
    private ClienteEntity cliente;
    
    /**
     * @return idTarjetaPrepago
     */
    public String getIdTarjetaPrepago()
    {
        return this.idTarjetaPrepago;
    }
    
    /**
     * @param tarjeta
     */
    public void setIdTarjetaPrepago(String tarjeta)
    {
        this.idTarjetaPrepago = tarjeta;
    }
    
     /**
     * @return saldo
     */
    public double getSaldo()
    {
        return this.saldo;
    }
    
    /**
     * @param saldo
     */
    public void setSaldo(double saldo)
    {
        this.saldo = saldo;
    }
    
     /**
     * @return puntos
     */
    public double getPuntos()
    {
        return this.puntos;
    }
    
    /**
     * @param puntos
     */
    public void setPuntos(double puntos)
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
