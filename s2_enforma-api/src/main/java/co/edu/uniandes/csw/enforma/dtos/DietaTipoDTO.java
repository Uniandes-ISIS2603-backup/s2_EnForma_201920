/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.dtos;

import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import java.io.Serializable;

/**
 *
 * @author Julio Morales
 */
public class DietaTipoDTO implements Serializable{
    
    private Long id;
    private String nombre;
    private Integer caloriasMax;
    private Integer caloriasMin;
    private Integer cantidadGrasa;
    private Integer cantidadAzucar;
    private Integer cantidadFibra;
    private Boolean tieneGluten;
    private Boolean tieneLactosa;
    
    private ClienteDTO clientes;
    private AdministradorDTO administrador;
    
    public DietaTipoDTO(){
        
    }
    
    
    
    
    public DietaTipoDTO(DietaTipoEntity dietaTipoEntity) 
{
        if (dietaTipoEntity != null)
       {
            this.id = dietaTipoEntity.getId();
            this.nombre = dietaTipoEntity.getNombre();
            this.caloriasMax = dietaTipoEntity.getCaloriasMax();
            this.caloriasMin = dietaTipoEntity.getCaloriasMin();
            this.cantidadGrasa = dietaTipoEntity.getCantidadGrasa();
            this.cantidadAzucar = dietaTipoEntity.getCantidadAzucar();
            this.cantidadFibra = dietaTipoEntity.getCantidadFibra();
            this.tieneGluten = dietaTipoEntity.getTieneGluten();
            this.tieneLactosa = dietaTipoEntity.getTieneLactosa();
            
            
            
            
        }
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * Convierte un objeto DietaTipoDTO a DietaTipoEntity.
     *
     * @return Nueva objeto DietaTipoEntity.
     *
     */
    public DietaTipoEntity toEntity() 
    {
        DietaTipoEntity dietaTipoEntity = new DietaTipoEntity();
        dietaTipoEntity.setId(this.getId());
        dietaTipoEntity.setNombre(this.getNombre());
        dietaTipoEntity.setCaloriasMax(this.getCaloriasMax());
        dietaTipoEntity.setCaloriasMin(this.getCaloriasMin());
        dietaTipoEntity.setCantidadAzucar(this.getCantidadAzucar());
        dietaTipoEntity.setCantidadFibra(this.getCantidadFibra());
        dietaTipoEntity.setCantidadGrasa(this.getCantidadGrasa());
        dietaTipoEntity.setTieneGluten(this.getTieneGluten());
        dietaTipoEntity.setTieneLactosa(this.getTieneLactosa());
        
        return dietaTipoEntity;
    }

    /**
     * @return the clientes
     */
    public ClienteDTO getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(ClienteDTO clientes) {
        this.clientes = clientes;
    }

    /**
     * @return the administrador
     */
    public AdministradorDTO getAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(AdministradorDTO administrador) {
        this.administrador = administrador;
    }
    
    
    
  
}
