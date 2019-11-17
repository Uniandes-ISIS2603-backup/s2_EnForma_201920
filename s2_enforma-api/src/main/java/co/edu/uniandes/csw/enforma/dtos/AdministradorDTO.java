/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.dtos;

import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 *
 * @author Elina Jaimes
 */
public class AdministradorDTO implements Serializable{
    
    private Long id;
    private String nombre;
    private String username;
    private String contrasena;

    
    public AdministradorDTO(){
        
    }
        
    public AdministradorDTO(AdministradorEntity administradorEntity)
    {
        if(administradorEntity!=null)
        {
            this.id=administradorEntity.getId();
            this.nombre=administradorEntity.getNombre();
            this.username=administradorEntity.getUsername();
            this.contrasena=administradorEntity.getContrasena();
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
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

      /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

   /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

   
    
    
     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    
     public AdministradorEntity toEntity() 
    {
        AdministradorEntity administradorEntity = new AdministradorEntity();
        administradorEntity.setId(this.getId());
        administradorEntity.setNombre(this.getNombre());
        administradorEntity.setUsername(this.getUsername());
        administradorEntity.setContrasena(this.getContrasena());
        return administradorEntity;
    }
}
