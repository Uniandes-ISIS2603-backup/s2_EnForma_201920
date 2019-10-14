/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.dtos;

import co.edu.uniandes.csw.enforma.adapters.DateAdapter;
import co.edu.uniandes.csw.enforma.entities.CalificacionEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Jimmy Pepinosa
 */
public class CalificacionDTO implements Serializable
{
    private Long id;
    
    private Integer puntaje;
    
    private String comentario;
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fecha;
    
    private ClienteDTO cliente;
    
    private DietaTipoDTO dieta;
    
    /**
     * Constructor por defecto
     */
    public CalificacionDTO()
    {
        
    }
    
    /**
     * Constructor a partir de una entidad
     * @param calificacionEntity La entidad de la cual se construye el DTO
     */
    public CalificacionDTO(CalificacionEntity calificacionEntity)
    {
        if(calificacionEntity != null)
        {
            this.id = calificacionEntity.getId();
            this.puntaje = calificacionEntity.getPuntaje();
            this.comentario = calificacionEntity.getComentario();
            this.fecha = calificacionEntity.getFecha();
            if(calificacionEntity.getUsuario() != null)
            {
               this.cliente = new ClienteDTO(calificacionEntity.getUsuario()); 
            }
            else
            {
                this.cliente = null;
            }
            if(calificacionEntity.getDietaTipo()!= null)
            {
                this.dieta = new DietaTipoDTO(calificacionEntity.getDietaTipo());  
            }
            else
            {
                this.dieta = null;
            }
           
        }
    }
    
    
    public CalificacionEntity toEntity()
    {
        CalificacionEntity calificacionEntity = new CalificacionEntity();
        calificacionEntity.setId(this.getId());
        calificacionEntity.setPuntaje(this.getPuntaje());
        calificacionEntity.setComentario(this.getComentario());
        calificacionEntity.setfecha(this.getFecha());
        if(calificacionEntity.getUsuario() != null)
        {
            calificacionEntity.setUsuario(this.getCliente().toEntity());
        }
        if(calificacionEntity.getDietaTipo() != null)
        {                   
            calificacionEntity.setDietaTipo(this.getDieta().toEntity());
        }

        return calificacionEntity;
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
     * @return the puntaje
     */
    public Integer getPuntaje() {
        return puntaje;
    }

    /**
     * @param puntaje the puntaje to set
     */
    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
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
     * @return the dieta
     */
    public DietaTipoDTO getDieta() {
        return dieta;
    }

    /**
     * @param dieta the dieta to set
     */
    public void setDieta(DietaTipoDTO dieta) {
        this.dieta = dieta;
    }
    
    
     
}
