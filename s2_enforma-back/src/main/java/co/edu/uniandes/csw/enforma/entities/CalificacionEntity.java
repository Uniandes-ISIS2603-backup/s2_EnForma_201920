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
 * @author Jimmy Pepinosa
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable
{
    private Integer puntaje;
    
    private String comentario;

    /**
     * Devuelve el puntaje de la calificacion
     * @return the puntaje
     */
    public Integer getPuntaje() 
    {
        return puntaje;
    }

    /**
     * Modifica el puntaje de calificacion
     * @param puntaje the puntaje to set
     */
    public void setPuntaje(Integer puntaje) 
    {
        this.puntaje = puntaje;
    }

    /**
     * Devuelve el comentario de la calificacion
     * @return the comentario
     */
    public String getComentario() 
    {
        return comentario;
    }

    /**
     * Modifica el comentario de la calificacion
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) 
    {
        this.comentario = comentario;
    }
     
    
}
