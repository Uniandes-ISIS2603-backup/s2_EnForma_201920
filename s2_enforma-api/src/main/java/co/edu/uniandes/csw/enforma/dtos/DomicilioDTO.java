/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.dtos;

import co.edu.uniandes.csw.enforma.adapters.DateAdapter;
import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Sebastián Clavijo
 */
public class DomicilioDTO implements Serializable 
{
    private Long id;
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fecha;
    
    private String lugarEntrega;
    private Double costo;
    
    
     /**
     * Constructor por defecto
     */
    public DomicilioDTO() 
    {
        
    }

    /**
     * Constructor a partir de la entidad
     *
     * @param domicilioEntity La entidad del domicilio
     */
    public DomicilioDTO(DomicilioEntity domicilioEntity) 
    {
        if (domicilioEntity != null) 
        {
            this.id = domicilioEntity.getId();
            this.fecha = domicilioEntity.getFecha();
            this.lugarEntrega = domicilioEntity.getLugarEntrega();
            this.costo = domicilioEntity.getCosto();
            
        }
    }
    
     /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del domicilio asociado.
     */
    public DomicilioEntity toEntity() 
    {
        DomicilioEntity domicilioEntity = new DomicilioEntity();
        domicilioEntity.setId(this.id);
        domicilioEntity.setFecha(this.fecha);
        domicilioEntity.setLugarEntrega(this.lugarEntrega);
        domicilioEntity.setCosto(this.costo);

        return domicilioEntity;
    }
    
    /**
     * Devuelve el id del domicilio
     * @return id
     */
    public Long getId()
    {
        return this.id;
    }
    
    /**
     * modifica el id del domicilio
     * @param idd nuevo
     */
    public void setId(Long idd)
    {
        this.id = idd;
    }
    
    /**
     * Devuelve la fecha del domicilio
     * @return fecha
     */
    public Date getFecha()
    {
        return this.fecha;
    }
    
    /**
     * modifica la fecha del domicilio
     * @param fecha2 nuevo
     */
    public void setFecha(Date fecha2)
    {
        this.fecha = fecha2;
    }
    
     /**
     * Devuelve el lugar de entrega del domicilio
     * @return lugarEntrega
     */
    public String getLugarEntrega()
    {
        return this.lugarEntrega;
    }
    
    /**
     * modifica el lugar de entrega del domicilio
     * @param lugar nuevo
     */
    public void setLugarEntrega(String lugar)
    {
        this.lugarEntrega = lugar;
    }
    
        /**
     * Devuelve el costo del domicilio
     * @return costo
     */
    public double getCosto()
    {
        return this.costo;
    }
    
    /**
     * modifica el costo del domicilio
     * @param costo2 nuevo
     */
    public void setCosto(double costo2)
    {
        this.costo = costo2;
    }
    
    
    @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
