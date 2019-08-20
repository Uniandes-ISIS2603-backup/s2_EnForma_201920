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
public class UsuarioEntity  extends BaseEntity implements Serializable
{
 private String nombre;
 private Integer edad;
 private Double peso;
 private String objetivos;
 

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
