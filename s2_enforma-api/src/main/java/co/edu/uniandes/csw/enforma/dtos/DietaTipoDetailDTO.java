/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.dtos;

import co.edu.uniandes.csw.enforma.entities.CalificacionEntity;
import co.edu.uniandes.csw.enforma.entities.ClienteEntity;
import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Julio Morales
 */
public class DietaTipoDetailDTO extends DietaTipoDTO implements Serializable{
    
    private List<CalificacionDTO> calificaciones;
    
    private List<ComidaTipoDTO> comidas;
    
    private List<ClienteDTO> clientes;
    
    
    
    public DietaTipoDetailDTO(){
        super();
    }
    
    
    
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param DietaTipoEntity La entidad de la cual se construye el DTO
     */
    public DietaTipoDetailDTO(DietaTipoEntity dietaTipoEntity) {
        super(dietaTipoEntity);
        if (dietaTipoEntity.getCalificaciones() != null) {
            calificaciones = new ArrayList<>();
            for (CalificacionEntity entityCalificacion : dietaTipoEntity.getCalificaciones()) {
                calificaciones.add(new CalificacionDTO(entityCalificacion));
            }
        }
        if (dietaTipoEntity.getComidas() != null) {
            comidas = new ArrayList<>();
            for (ComidaTipoEntity entityComida: dietaTipoEntity.getComidas()) {
                comidas.add(new ComidaTipoDTO(entityComida));
            }
        }

        if (dietaTipoEntity.getClientes() != null) {
            clientes = new ArrayList<>();
            for (ClienteEntity clienteEntity: dietaTipoEntity.getClientes()) {
                clientes.add(new ClienteDTO(clienteEntity));
            }
        }
    }
    
    
    
    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa la DietaTipo.
     */
    @Override
    public DietaTipoEntity toEntity() {
        DietaTipoEntity dietaTipoEntity = super.toEntity();
        if (calificaciones != null) {
            List<CalificacionEntity> calificacionesEntity = new ArrayList<>();
            for (CalificacionDTO dtoCalificacion : getCalificaciones()) {
                calificacionesEntity.add(dtoCalificacion.toEntity());
            }
            dietaTipoEntity.setCalificaciones(calificacionesEntity);
        }
        if (comidas != null) {
            List<ComidaTipoEntity> comidasEntity = new ArrayList<>();
            for (ComidaTipoDTO dtoComida : getComidas()) {
                comidasEntity.add(dtoComida.toEntity());
            }
            dietaTipoEntity.setComidas(comidasEntity);
        }
        if (clientes != null) {
            List<ClienteEntity> clientesEntity = new ArrayList<>();
            for (ClienteDTO dtoCliente : getClientes()) {
                clientesEntity.add(dtoCliente.toEntity());
            }
            dietaTipoEntity.setClientes(clientesEntity);
        }

        return dietaTipoEntity;
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

    /**
     * @return the clientes
     */
    public List<ClienteDTO> getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(List<ClienteDTO> clientes) {
        this.clientes = clientes;
    }
    
    
    
    
}
