/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.dtos;

import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Jose Manuel Flórez
 */
public class ComidaTipoDTO implements Serializable
{
    
    private Long idComidaTipoDTO;
   
    private String menu;
    private String momentoDelDia;
    private Integer calorias;
    private String nombre;
    
     
 
    // relación  muchos  a uno nistrador
    private AdministradorDTO administrador;

    
    // relación  muchos a uno DietaTipo
    private DietaTipoDTO dietaTipo;
    
    
    /**
     *  El constructor por defecto 
     */
    public ComidaTipoDTO()
    {
     
    }
    
    
    /**
     * Constructor a partir de la entidad
     * 
     * @param ComidaTipoEntity la entidad Comida Tipo
     */
    public ComidaTipoDTO (ComidaTipoEntity comidaTipoEntity)
    {
        if(comidaTipoEntity != null)
        {
            this.idComidaTipoDTO = comidaTipoEntity.getId();
            this.menu = comidaTipoEntity.getMenu();
            this.momentoDelDia = comidaTipoEntity.getMomentoDelDia();
            this.nombre = comidaTipoEntity.getNombre();
            this.calorias = comidaTipoEntity.getCalorias();
                
            if (comidaTipoEntity.getAdministrador() != null) 
            {
                this.administrador = new AdministradorDTO(comidaTipoEntity.getAdministrador());
            }
            if(comidaTipoEntity.getDietaTipo() != null)
            {
            this.dietaTipo = new DietaTipoDTO(comidaTipoEntity.getDietaTipo());
            } 
        }
        
        else
        {
            this.administrador = null;
            this.dietaTipo = null;
        }
    
        
    }
    
    
    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del libro asociado.
     */
    public ComidaTipoEntity toEntity() 
    {
        ComidaTipoEntity comidaTipoEntity = new ComidaTipoEntity();
        comidaTipoEntity.setCalorias(this.getCalorias());
        comidaTipoEntity.setNombre(this.getNombre());
        comidaTipoEntity.setMomentoDelDia(this.getMomentoDelDia());
        comidaTipoEntity.setMenu(this.getMenu());
        if(this.administrador != null)
        {
            comidaTipoEntity.setAdministrador(this.administrador.toEntity());
        }
        if(this.dietaTipo != null)
        {
            comidaTipoEntity.setDietaTipo(this.dietaTipo.toEntity());
        }
        
      return comidaTipoEntity;
      
    }

    /**
     * Devuelve el menú de la comidaTipo
     * @return the menu
     */
    public String getMenu() {
        return menu;
    }

    /**
     * Modifica el menú de la comidaTipo
     * @param menu the menu to set
     */
    public void setMenu(String menu) {
        this.menu = menu;
    }

    /**
     * Devuelve el momentoDelDía de la comidaTipo
     * @return the momentoDelDia
     */
    public String getMomentoDelDia() {
        return momentoDelDia;
    }

    /**
     * Modifica el momento del día de la comidaTipo
     * @param momentoDelDia the momentoDelDia to set
     */
    public void setMomentoDelDia(String momentoDelDia) {
        this.momentoDelDia = momentoDelDia;
    }

    /**
     * Devuelve las calorias de la comidaTipo
     * @return the calorias
     */
    public Integer getCalorias() {
        return calorias;
    }

    /**
     * Modifica las calorias de la comidaTipo
     * @param calorias the calorias to set
     */
    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    /**
     * Devuelve el nombre de la comidaTipo
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de la comidaTipo
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
       
        
     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    
}
