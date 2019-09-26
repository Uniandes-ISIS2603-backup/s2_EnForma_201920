/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.dtos;

import co.edu.uniandes.csw.enforma.adapters.DateAdapter;
import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Jimmy Pepinosa
 */
public class QuejasYReclamosDTO implements Serializable
{
    private Long id;
    
    private String descripcion;
    
    private String asusnto;
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fecha;
    
    //private UsuarioDTO usuario;
    
    //private DomicilioDTO domicilio;
    
    
    public QuejasYReclamosDTO()
    {
        
    }
    
    
    public QuejasYReclamosDTO(QuejasYReclamosEntity quejasYReclamosEntity)
    {
        if(quejasYReclamosEntity != null)
        {
            this.id = quejasYReclamosEntity.getId();
            this.descripcion = quejasYReclamosEntity.getDescripcion();
            this.asusnto = quejasYReclamosEntity.getAsusnto();
            this.fecha = quejasYReclamosEntity.getDate();
            
            
            
        }
    }
    
    
    public QuejasYReclamosEntity toEntity()
    {
        QuejasYReclamosEntity quejasYReclamosEntity = new QuejasYReclamosEntity();
        quejasYReclamosEntity.setId(this.getId());
        quejasYReclamosEntity.setAsusnto(this.getAsusnto());
        quejasYReclamosEntity.setDescripcion(this.getDescripcion());
        quejasYReclamosEntity.setDate(this.getFecha());
        
        
        return quejasYReclamosEntity;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the asusnto
     */
    public String getAsusnto() {
        return asusnto;
    }

    /**
     * @param asusnto the asusnto to set
     */
    public void setAsusnto(String asusnto) {
        this.asusnto = asusnto;
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
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}
