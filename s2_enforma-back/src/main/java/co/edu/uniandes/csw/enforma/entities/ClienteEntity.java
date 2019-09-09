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
 * @author Paula Sofía Vargas Peña
 */
@Entity
public class ClienteEntity  extends BaseEntity implements Serializable
{

 private String nombre;
 private Integer edad;
 private Double peso;
 private String objetivos;
 private Boolean gluten;
 private Boolean lactosa;
 private String userName;
 private String contrasenia;
    /**
     * @return the gluten
     */
    public Boolean getGluten() {
        return gluten;
    }

    /**
     * @param gluten the gluten to set
     */
    public void setGluten(Boolean gluten) {
        this.gluten = gluten;
    }

    /**
     * @return the lactosa
     */
    public Boolean getLactosa() {
        return lactosa;
    }

    /**
     * @param lactosa the lactosa to set
     */
    public void setLactosa(Boolean lactosa) {
        this.lactosa = lactosa;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }


 public ClienteEntity(){
    
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
     * @return the edad
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /**
     * @return the peso
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * @return the objetivos
     */
    public String getObjetivos() {
        return objetivos;
    }

    /**
     * @param objetivos the objetivos to set
     */
    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }
    
}
