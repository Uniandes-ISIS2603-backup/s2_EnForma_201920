/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.entities;

import co.edu.uniandes.csw.enforma.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Jimmy Pepinosa
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable
{
    private Integer puntaje;
    
    private String comentario;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecha;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;

    @PodamExclude
    @ManyToOne
    private DietaTipoEntity dietaTipo;

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
     
     /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setfecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the cliente
     */
    public ClienteEntity getCliente() {
        return cliente;         
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the dietaTipo
     */
    public DietaTipoEntity getDietaTipo() {
        return dietaTipo;
    }

    /**
     * @param dietaTipo the dietaTipo to set
     */
    public void setDietaTipo(DietaTipoEntity dietaTipo) {
        this.dietaTipo = dietaTipo;
    }
}
