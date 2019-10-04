/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.dtos;

import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import co.edu.uniandes.csw.enforma.entities.DomicilioEntity;
import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @cliente Estudiante
 */
public class ClienteDetailDTO extends ClienteDTO implements Serializable 
{

    // relación  cero o muchos domicilios 
    private List<DomicilioDTO> domicilios;

    // relación  cero o muchas quejas y reclamos 
    private List<QuejasYReclamosDTO> quejas;

    public ClienteDetailDTO()
    {
        super();
    }

    /**
     * Crea un objeto ClienteDetailDTO a partir de un objeto ClienteEntity
     * incluyendo los atributos de ClienteDTO.
     *
     * @param clienteEntity Entidad ClienteEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     *
    public ClienteDetailDTO(ClienteEntity clienteEntity)
    {
        super(clienteEntity);
        if (clienteEntity != null) 
        {
            domicilios = new ArrayList<>();
            for (DomicilioEntity entityDomicilios : clienteEntity.getDomicilios()) 
            {
                domicilios.add(new DomicilioDTO(entityDomicilios));
            }
            quejas = new ArrayList();
            for (QuejasYReclamosEntity entityQuejasYReclamos : clienteEntity.getQuejasYReclamos()) 
            {
                quejas.add(new QuejasYReclamosDTO(entityQuejasYReclamos));
            }
        }
    }/

    /**
     * Convierte un objeto ClienteDetailDTO a ClienteEntity incluyendo los
     * atributos de ClienteDTO.
     *
     * @return Nueva objeto ClienteEntity.
     *
     *
    @Override
    public ClienteEntity toEntity() 
    {
        ClienteEntity clienteEntity = super.toEntity();
        if (domicilios != null) {
            List<DomicilioEntity> domiciliosEntity = new ArrayList<>();
            for (DomicilioDTO dtoDomicilio : domicilios) 
            {
                domiciliosEntity.add(dtoDomicilio.toEntity());
            }
            clienteEntity.setDomicilios(domiciliosEntity);
        }
        if (quejas != null) {
            List<QuejasYReclamosEntity> quejasEntity = new ArrayList<>();
            for (QuejasYReclamosDTO dtoQuejasYReclamos : quejas)
            {
                quejasEntity.add(dtoQuejasYReclamos.toEntity());
            }
            clienteEntity.setQuejasYReclamos(quejasEntity);
        }
        return clienteEntity;
    }/

    /**
     * Obtiene la lista de libros del cliente
     *
     * @return the domicilios
     */
    public List<DomicilioDTO> getDomicilios() {
        return domicilios;
    }

    /**
     * Modifica la lista de domicilios para el cliente
     *
     * @param domicilios the domicilios to set
     */
    public void setDomicilios(List<DomicilioDTO> domicilios) 
    {
        this.domicilios = domicilios;
    }

    /**
     * Obtiene la lista de quejas del cliente
     *
     * @return the quejas
     */
    public List<QuejasYReclamosDTO> getQuejasYReclamos() 
    {
        return quejas;
    }

    /**
     * Modifica la lista de quejas para el cliente
     *
     * @param quejas the quejas to set
     */
    public void setQuejasYReclamos(List<QuejasYReclamosDTO> quejas) 
    {
        this.quejas = quejas;
    }

    @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

