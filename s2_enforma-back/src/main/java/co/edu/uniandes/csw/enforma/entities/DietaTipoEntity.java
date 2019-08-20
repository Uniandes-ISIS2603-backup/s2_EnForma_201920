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
 * @author Julio Alejandro Morales
 */
@Entity
public class DietaTipoEntity extends BaseEntity implements Serializable {
    
    
    private String nombre;
    private Integer caloriasMax;
    private Integer caloriasMin;
    private Integer cantidadGrasa;
    private Integer cantidadAzucar;
    private Integer cantidadFibra;
    private Boolean tieneGluten;
    private Boolean tieneLactosa;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the caloriasMax
     */
    public Integer getCaloriasMax() {
        return caloriasMax;
    }

    /**
     * @param caloriasMax the caloriasMax to set
     */
    public void setCaloriasMax(Integer caloriasMax) {
        this.caloriasMax = caloriasMax;
    }

    /**
     * @return the caloriasMin
     */
    public Integer getCaloriasMin() {
        return caloriasMin;
    }

    /**
     * @param caloriasMin the caloriasMin to set
     */
    public void setCaloriasMin(Integer caloriasMin) {
        this.caloriasMin = caloriasMin;
    }

    /**
     * @return the cantidadGrasa
     */
    public Integer getCantidadGrasa() {
        return cantidadGrasa;
    }

    /**
     * @param cantidadGrasa the cantidadGrasa to set
     */
    public void setCantidadGrasa(Integer cantidadGrasa) {
        this.cantidadGrasa = cantidadGrasa;
    }

    /**
     * @return the cantidadAzucar
     */
    public Integer getCantidadAzucar() {
        return cantidadAzucar;
    }

    /**
     * @param cantidadAzucar the cantidadAzucar to set
     */
    public void setCantidadAzucar(Integer cantidadAzucar) {
        this.cantidadAzucar = cantidadAzucar;
    }

    /**
     * @return the cantidadFibra
     */
    public Integer getCantidadFibra() {
        return cantidadFibra;
    }

    /**
     * @param cantidadFibra the cantidadFibra to set
     */
    public void setCantidadFibra(Integer cantidadFibra) {
        this.cantidadFibra = cantidadFibra;
    }

    /**
     * @return the tieneGluten
     */
    public Boolean getTieneGluten() {
        return tieneGluten;
    }

    /**
     * @param tieneGluten the tieneGluten to set
     */
    public void setTieneGluten(Boolean tieneGluten) {
        this.tieneGluten = tieneGluten;
    }

    /**
     * @return the tieneLactosa
     */
    public Boolean getTieneLactosa() {
        return tieneLactosa;
    }

    /**
     * @param tieneLactosa the tieneLactosa to set
     */
    public void setTieneLactosa(Boolean tieneLactosa) {
        this.tieneLactosa = tieneLactosa;
    }
    
}
