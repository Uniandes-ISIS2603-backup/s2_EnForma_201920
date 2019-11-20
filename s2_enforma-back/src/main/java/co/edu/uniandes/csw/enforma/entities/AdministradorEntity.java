/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Elina Jaimes
 */
@Entity
public class AdministradorEntity extends BaseEntity implements Serializable {

    private String nombre;
    private String contrasena;
    private String username;

    @PodamExclude
    @OneToMany(mappedBy = "administrador")
    private List<DietaTipoEntity> dietaTipo;

    @PodamExclude
    @OneToMany(mappedBy = "administrador")
    private List<ComidaTipoEntity> comidasTipo;

    public AdministradorEntity() {
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
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the dietaTipo
     */
    public List<DietaTipoEntity> getDietaTipo() {
        return dietaTipo;
    }

    /**
     * @param dietaTipo the dietaTipo to set
     */
    public void setDietaTipo(List<DietaTipoEntity> dietaTipo) {
        this.dietaTipo = dietaTipo;
    }

    /**
     * @return the comidasTipo
     */
    public List<ComidaTipoEntity> getComidasTipo() {
        return comidasTipo;
    }

    /**
     * @param comidasTipo the comidasTipo to set
     */
    public void setComidasTipo(List<ComidaTipoEntity> comidasTipo) {
        this.comidasTipo = comidasTipo;
    }

}
