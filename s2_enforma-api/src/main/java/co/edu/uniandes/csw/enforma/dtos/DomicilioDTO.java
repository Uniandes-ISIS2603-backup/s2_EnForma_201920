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
     * relacion uno a uno comida tipo
     */
    private ComidaTipoDTO comidaTipo;
    
     /**
     * relacion uno a uno con cliente
     */
    private ClienteDTO cliente;
    
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
            if(domicilioEntity.getCliente() != null)
            {
                this.cliente = new ClienteDTO(domicilioEntity.getCliente());
            }
            if(domicilioEntity.getComidaTipo()!= null)
            {
                this.comidaTipo = new ComidaTipoDTO(domicilioEntity.getComidaTipo());
            }
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
        if(this.cliente != null)
        {
            domicilioEntity.setCliente(this.cliente.toEntity());
        }
        if(this.comidaTipo != null)
        {
            domicilioEntity.setComidaTipo(this.comidaTipo.toEntity());
        }
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
    public Double getCosto()
    {
        return this.costo;
    }
    
    /**
     * modifica el costo del domicilio
     * @param costo2 nuevo
     */
    public void setCosto(Double costo2)
    {
        this.costo = costo2;
    }
    
    
    @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the cliente
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the comidaTipo
     */
    public ComidaTipoDTO getComidaTipo() {
        return comidaTipo;
    }

    /**
     * @param comidaTipo the comidaTipo to set
     */
    public void setComidaTipo(ComidaTipoDTO comidaTipo) {
        this.comidaTipo = comidaTipo;
    }
    
}
