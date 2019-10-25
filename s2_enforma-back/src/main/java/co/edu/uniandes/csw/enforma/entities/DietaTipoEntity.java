/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamIntValue;

/**
 *
 * @author Julio Morales
 */
@Entity
public class DietaTipoEntity extends BaseEntity implements Serializable {
    
    private String nombre;
    
    @PodamIntValue(minValue = 3 )
    private Integer caloriasMax;
    
    @PodamIntValue(minValue = 1, maxValue = 2)
    private Integer caloriasMin;
    
    @PodamIntValue(minValue = 1)
    private Integer cantidadGrasa;
    
    @PodamIntValue(minValue = 1)
    private Integer cantidadAzucar;
    
    @PodamIntValue(minValue = 1)
    private Integer cantidadFibra;
    
    private Boolean tieneGluten;
    private Boolean tieneLactosa;
    

    
    @PodamExclude
    @ManyToOne
    private AdministradorEntity administrador;
    
    @PodamExclude
    @OneToMany(mappedBy = "dietaTipo")
    private List<CalificacionEntity> calificaciones = new ArrayList<CalificacionEntity>();
    
    @PodamExclude
    @OneToMany
    private List<ComidaTipoEntity> comidas = new ArrayList<ComidaTipoEntity>();
    
    @PodamExclude
    @OneToMany
    private List<ClienteEntity> clientes = new ArrayList<ClienteEntity>();

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

    

    /**
     * @return the administrador
     */
    public AdministradorEntity getAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(AdministradorEntity administrador) {
        this.administrador = administrador;
    }

    /**
     * @return the calificaciones
     */
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * @return the comidas
     */
    public List<ComidaTipoEntity> getComidas() {
        return comidas;
    }

    /**
     * @param comidas the comidas to set
     */
    public void setComidas(List<ComidaTipoEntity> comidas) {
        this.comidas = comidas;
    }

    /**
     * @return the clientes
     */
    public List<ClienteEntity> getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(List<ClienteEntity> clientes) {
        this.clientes = clientes;
    }
    
    
    
    
}
