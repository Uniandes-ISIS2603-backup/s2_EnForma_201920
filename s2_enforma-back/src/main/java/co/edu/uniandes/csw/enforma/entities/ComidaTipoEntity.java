/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import uk.co.jemos.podam.common.PodamIntValue;

/**
 *
 * @author Estudiante: Jose Manuel Flórez Castro Código:
 */
@Entity
public class ComidaTipoEntity extends BaseEntity implements Serializable
{

    private String momentoDelDia;
    
    
    @PodamIntValue (minValue = 100)
    private Integer calorias;
    
    
    private String menu;
    
    private String nombre;
    
    /**
     * @return the calorias
     */
    public Integer getCalorias() {
        return calorias;
    }

    /**
     * @param calorias the calorias to set
     */
    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    /**
     * @return the menu
     */
    public String getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(String menu) {
        this.menu = menu;
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
     * @return the momentoDelDia
     */
    public String getMomentoDelDia() {
        return momentoDelDia;
    }

    /**
     * @param momentoDelDia the momentoDelDia to set
     */
    public void setMomentoDelDia(String momentoDelDia) {
        this.momentoDelDia = momentoDelDia;
    }
    

    
    
    
}
