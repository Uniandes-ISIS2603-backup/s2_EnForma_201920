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
 * @author Juan Sebastián Clavijo
 */
@Entity
public class TarjetaPrepagoEntity extends BaseEntity implements Serializable
{
    private String idTarjetaPrepago;
    
    private double saldo;
    
    private double puntos;
    
    
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
    
    
    
}
