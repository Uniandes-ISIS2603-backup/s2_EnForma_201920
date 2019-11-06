/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.dtos;

import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Sebastián Clavijo
 */
public class DomicilioDetailDTO extends DomicilioDTO implements Serializable
{
    /**
     * relacion uno a uno con quejas y reclamos
     */
    private QuejasYReclamosDTO quejasYReclamos;
    
    /**
     * relacion uno a uno con pago
     */
    private PagoDTO pago;
    
     /**
     * relacion uno a uno comida tipo
     */
    private ComidaTipoDTO comidaTipo;
    

    
    /**
     * constructor vacio
     */
    public DomicilioDetailDTO() 
    {
        super();
    }
    
     /**
     * Crea un objeto DomicilioDetailDTO a partir de un objeto
     * DomicilioEntity incluyendo los atributos de DomicilioDTO.
     *
     * @param domicilioEntity Entidad DomicilioEntity desde la cual se va
     * a crear el nuevo objeto.
     *
     */
    public DomicilioDetailDTO(DomicilioEntity domicilioEntity) 
    {
        super(domicilioEntity);
        if (domicilioEntity.getQuejasYReclamos() != null) 
        {
            this.quejasYReclamos = new QuejasYReclamosDTO(domicilioEntity.getQuejasYReclamos());
        }
        if(domicilioEntity.getPago() != null)
        {
            this.pago = new PagoDTO(domicilioEntity.getPago());
        }
        if(domicilioEntity.getComidaTipo() != null)
        {
            this.comidaTipo = new ComidaTipoDTO(domicilioEntity.getComidaTipo());
        }

    }

    /**
     * Convierte un objeto DomicilioDetailDTO a DomicilioEntity incluyendo
     * los atributos de DomicilioDTO.
     *
     * @return Nueva objeto DomicilioEntity.
     *
     */
    @Override
    public DomicilioEntity toEntity() 
    {
        DomicilioEntity entity = super.toEntity();
        if (this.getQuejasYReclamos() != null) 
        {
            entity.setQuejasYReclamos(this.getQuejasYReclamos().toEntity());
        }
        if (this.getPago() != null) 
        {
            entity.setPago(this.getPago().toEntity());
        }
        if (this.getComidaTipo() != null) 
        {
            entity.setComidaTipo(this.getComidaTipo().toEntity());
        }
        
        return entity;
    }
    

    /**
     * @return the quejasYReclamos
     */
    public QuejasYReclamosDTO getQuejasYReclamos() {
        return quejasYReclamos;
    }

    /**
     * @param quejasYReclamos the quejasYReclamos to set
     */
    public void setQuejasYReclamos(QuejasYReclamosDTO quejasYReclamos) {
        this.quejasYReclamos = quejasYReclamos;
    }

    /**
     * @return the pago
     */
    public PagoDTO getPago() {
        return pago;
    }

    /**
     * @param pago the pago to set
     */
    public void setPago(PagoDTO pago) {
        this.pago = pago;
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


    
        
    @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
