/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.dtos;

import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
import co.edu.uniandes.csw.enforma.entities.DietaTipoEntity;
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
//        if (administradorEntity != null) {
//            dietas = new ArrayList<>();
////            for (DietaTipoEntity entityDietaTipo : administradorEntity.getDietaTipos()) {
////                dietas.add(new DietaTipoDTO(entityDietaTipo));
////            }
//        }
//        if (administradorEntity.getComidaTipos() != null) {
//            comidas = new ArrayList<>();
//            for (ComidaTipoEntity entityComidaTipo : administradorEntity.getComidaTipos()) {
//                comidas.add(new ComidaTipoDTO(entityComidaTipo));
//            }
//        }
    }

    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el libro.
     */
    @Override
    public AdministradorEntity toEntity() {
        AdministradorEntity administradorEntity = super.toEntity();
//        if (dietas != null) {
//            List<DietaTipoEntity> dietasEntity = new ArrayList<>();
//            for (DietaTipoDTO dtoDietaTipo : dietas) {
//                dietasEntity.add(dtoDietaTipo.toEntity());
//            }
//            administradorEntity.setDietaTipo(dietasEntity);
//        }
//        if (comidas != null) {
//            List<ComidaTipoEntity> comidasEntity = new ArrayList<>();
//            for (ComidaTipoDTO dtoComidaTipo : comidas) {
//                comidasEntity.add(dtoComidaTipo.toEntity());
//            }
//            administradorEntity.setComidasTipo(comidasEntity);
//        }
        return administradorEntity;
    }

    /**
     * Devuelve las reseñas asociadas a este libro
     *
     * @return Lista de DTOs de Reseñas
     */
    public List<DietaTipoDTO> getDietaTipos() {
        return dietas;
    }

    /**
     * Modifica las reseñas de este libro.
     *
     * @param dietas Las nuevas reseñas
     */
    public void setDietaTipos(List<DietaTipoDTO> dietas) {
        this.dietas = dietas;
    }

    /**
     * Devuelve los autores del libro
     *
     * @return DTO de Autores
     */
    public List<ComidaTipoDTO> getComidaTipos() {
        return comidas;
    }

    /**
     * Modifica los autores del libro
     *
     * @param comidas Lista de autores
     */
    public void setComidaTipos(List<ComidaTipoDTO> comidas) {
        this.comidas = comidas;
    }

    
}
