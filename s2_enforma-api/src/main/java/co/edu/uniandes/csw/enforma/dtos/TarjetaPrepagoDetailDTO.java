/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.dtos;

import co.edu.uniandes.csw.enforma.entities.PagoEntity;
import co.edu.uniandes.csw.enforma.entities.TarjetaPrepagoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Sebastián Clavijo
 */
public class TarjetaPrepagoDetailDTO extends TarjetaPrepagoDTO implements Serializable
{
    /**
     * lista de pagos asociados a una tarjeta prepago
     */
    private List<PagoDTO> pagos;
    
    /**
     * constructor por defecto
     */
    public TarjetaPrepagoDetailDTO()
    {
        
    }
    
    /**
     * constructor para transformar un entity a un DTO
     * @param tarjetaPrepagoEntity la entidad para transformar a DTO
     */
    public TarjetaPrepagoDetailDTO(TarjetaPrepagoEntity tarjetaPrepagoEntity)
    {
        super(tarjetaPrepagoEntity);
        if(tarjetaPrepagoEntity != null)
        {
            if(tarjetaPrepagoEntity.getPagos() != null)
            {
                pagos = new ArrayList<>();
                for(PagoEntity entityPago : tarjetaPrepagoEntity.getPagos())
                {
                    pagos.add(new PagoDTO(entityPago));
                }
            }
        }
    }
    
    /**
     * transformar un DTO a un entity
     * @return el DTO de la tarjeta prepago para transformar a Entity
     */
    @Override
    public TarjetaPrepagoEntity toEntity()
    {
        TarjetaPrepagoEntity tarjetaPrepagoEntity = super.toEntity();
        if(pagos != null)
        {
            List<PagoEntity> pagosEntity = new ArrayList<>();
            for(PagoDTO dtoPago : pagos)
            {
              //  pagosEntity.add(dtoPago.toEntity());
            }
            tarjetaPrepagoEntity.setPagos(pagosEntity);
        }
        return tarjetaPrepagoEntity;
    }
    
    /**
     * devuelve la lista de los pagos asociados a la tarjeta prepago
     * @return pagos
     */
    public List<PagoDTO> getPagos()
    {
        return pagos;
    }
    
    /**
     * modifica la lista de pagos
     * @param lista pagos
     */
    public void setPagos(List<PagoDTO> lista)
    {
        this.pagos = lista;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
