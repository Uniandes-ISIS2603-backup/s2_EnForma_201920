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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Jimmy Pepinosa
 */
public class QuejasYReclamosDTO implements Serializable
{
    private static Logger LOGGER = Logger.getLogger(QuejasYReclamosDTO.class.getName());
    private Long id;
    
    private String descripcion;
    
    private String asusnto;
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fecha;
    
    private ClienteDTO cliente;
    
    private DomicilioDTO domicilio;
    
    
    public QuejasYReclamosDTO()
    {
        
    }
    
    
    public QuejasYReclamosDTO(QuejasYReclamosEntity quejasYReclamosEntity)
    {
        if(quejasYReclamosEntity != null)
        {
            this.id = quejasYReclamosEntity.getId();
            this.descripcion = quejasYReclamosEntity.getDescripcion();
            this.asusnto = quejasYReclamosEntity.getAsunto();
            this.fecha = quejasYReclamosEntity.getFecha();
            if(quejasYReclamosEntity.getCliente() != null)
            {
                this.cliente = new ClienteDTO(quejasYReclamosEntity.getCliente()); 
            }
            else
            {
                this.cliente = null;
            }
            if(quejasYReclamosEntity.getDomicilio() != null)
            {
                this.domicilio = new DomicilioDTO(quejasYReclamosEntity.getDomicilio());
            }
            else
            {
                this.domicilio = null;
            }
            
        }
    }
    
    
    public QuejasYReclamosEntity toEntity()
    {
        LOGGER.log(Level.INFO, "Inicia el toEntity");
        QuejasYReclamosEntity quejasYReclamosEntity = new QuejasYReclamosEntity();
        LOGGER.log(Level.INFO, "id = {0}", this.getId());
        quejasYReclamosEntity.setId(this.getId());
        LOGGER.log(Level.INFO, "asunto = {0}", this.getAsusnto());
        quejasYReclamosEntity.setAsunto(this.getAsusnto());
        LOGGER.log(Level.INFO, "descripcion = {0}", this.getDescripcion());
        quejasYReclamosEntity.setDescripcion(this.getDescripcion());
        LOGGER.log(Level.INFO, "fecha = {0}", this.getFecha());
        quejasYReclamosEntity.setFecha(this.getFecha());
//        if(this.cliente != null)
//        {
//            LOGGER.log(Level.INFO, "cliente = {0}", this.getCliente());
//            quejasYReclamosEntity.setCliente(this.cliente.toEntity());
//        }
        if(this.domicilio != null)
        {
            LOGGER.log(Level.INFO, "domicilio = {0}", this.getDomicilio());
            quejasYReclamosEntity.setDomicilio(this.domicilio.toEntity());
        }
        LOGGER.log(Level.INFO, "Termina el toEntity");
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
     * @return the domicilio
     */
    public DomicilioDTO getDomicilio() {
        return domicilio;
    }

    /**
     * @param domicilio the domicilio to set
     */
    public void setDomicilio(DomicilioDTO domicilio) {
        this.domicilio = domicilio;
    }
    
    
    
}
