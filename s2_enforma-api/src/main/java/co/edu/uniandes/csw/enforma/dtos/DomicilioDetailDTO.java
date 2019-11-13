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

        
        return entity;
    }
    



 



    
        
    @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
