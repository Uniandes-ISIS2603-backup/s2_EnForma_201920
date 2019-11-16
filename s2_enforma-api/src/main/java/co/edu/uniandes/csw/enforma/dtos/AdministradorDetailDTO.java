/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.dtos;

import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elina Jaimes
 */
public class AdministradorDetailDTO extends AdministradorDTO implements Serializable {

// relación  uno o muchos DietaTipo 
    private List<DietaTipoDTO> dietas;

    // relación  cero o muchos ComidaTipo
    private List<ComidaTipoDTO> comidas;

  

    
    
    
    public AdministradorDetailDTO()
    {
        super();
    }
    
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param administradorEntity La entidad de la cual se construye el DTO
     */
    public AdministradorDetailDTO(AdministradorEntity administradorEntity) {
        super(administradorEntity);
        if (administradorEntity != null) {
            dietas = new ArrayList<>();
            for (DietaTipoEntity entityDietaTipo : administradorEntity.getDietaTipo()) {
                dietas.add(new DietaTipoDTO(entityDietaTipo));
            }
            
            comidas = new ArrayList<>();
            for (ComidaTipoEntity entityComidaTipo : administradorEntity.getComidasTipo()) {
                comidas.add(new ComidaTipoDTO(entityComidaTipo));
        }
      
        }
    }

    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el libro.
     */
    @Override
    public AdministradorEntity toEntity() {
        AdministradorEntity administradorEntity = super.toEntity();
        if (getDietas() != null) {
            List<DietaTipoEntity> dietasEntity = new ArrayList<>();
            for (DietaTipoDTO dtoDietaTipo : getDietas()) {
                dietasEntity.add(dtoDietaTipo.toEntity());
            }
            administradorEntity.setDietaTipo(dietasEntity);
        }
        if (comidas != null) {
            List<ComidaTipoEntity> comidasEntity = new ArrayList<>();
            for (ComidaTipoDTO dtoComidaTipo : comidas) {
                comidasEntity.add(dtoComidaTipo.toEntity());
            }
            administradorEntity.setComidasTipo(comidasEntity);
        }
        return administradorEntity;
    }

    
    /**
     * @param dietas the dietas to set
     */
    public void setDietas(List<DietaTipoDTO> dietas) {
        this.dietas = dietas;
    }
    
    /**
     * @return the dietas
     */
    public List<DietaTipoDTO> getDietas() {
        return dietas;
    }

     /**
     * @param comidas the comidas to set
     */
    public void setComidas(List<ComidaTipoDTO> comidas) {
        this.comidas = comidas;
    }

    /**
     * @return the comidas
     */
    public List<ComidaTipoDTO> getComidas() {
        return comidas;
    }

   

    
    
}
