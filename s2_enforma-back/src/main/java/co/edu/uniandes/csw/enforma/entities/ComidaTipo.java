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
 * @author Estudiante: Jose Manuel Flórez Castro Código:
 */
@Entity
public class ComidaTipo extends BaseEntity implements Serializable
{
    
    
    
    private Integer calorias;
    
    
    private String menu;

   
    

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
    
    
    
}
