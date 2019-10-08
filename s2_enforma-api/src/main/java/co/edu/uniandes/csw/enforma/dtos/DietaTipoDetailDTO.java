/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.dtos;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Estudiante
 */
public class DietaTipoDetailDTO extends DietaTipoDTO implements Serializable{
    
    private List<CalificacionDTO> calificaciones;
    
    private List<ComidaTipoDTO> comidas;
    
    
    
    public DietaTipoDetailDTO(){
        super();
    }

    /**
     * @return the calificaciones
     */
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * @return the comidas
     */
    public List<ComidaTipoDTO> getComidas() {
        return comidas;
    }

    /**
     * @param comidas the comidas to set
     */
    public void setComidas(List<ComidaTipoDTO> comidas) {
        this.comidas = comidas;
    }
    
    
    
    
    
    
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    
    
    
}
