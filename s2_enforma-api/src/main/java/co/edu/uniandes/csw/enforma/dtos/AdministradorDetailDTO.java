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

// relación  uno o muchos reviews 
    private List<DietaTipoDTO> reviews;

    // relación  cero o muchos author
    private List<ComidaTipoDTO> authors;

  

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param bookEntity La entidad de la cual se construye el DTO
     */
    public AdministradorDetailDTO(AdministradorEntity bookEntity) {
        super(bookEntity);
        if (bookEntity != null) {
            reviews = new ArrayList<>();
//            for (DietaTipoEntity entityDietaTipo : bookEntity.getDietaTipos()) {
//                reviews.add(new DietaTipoDTO(entityDietaTipo));
//            }
        }
//        if (bookEntity.getComidaTipos() != null) {
//            authors = new ArrayList<>();
//            for (ComidaTipoEntity entityComidaTipo : bookEntity.getComidaTipos()) {
//                authors.add(new ComidaTipoDTO(entityComidaTipo));
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
        AdministradorEntity bookEntity = super.toEntity();
//        if (reviews != null) {
//            List<DietaTipoEntity> reviewsEntity = new ArrayList<>();
//            for (DietaTipoDTO dtoDietaTipo : getDietaTipos()) {
//                reviewsEntity.add(dtoDietaTipo.toEntity());
//            }
//            bookEntity.setDietaTipos(reviewsEntity);
//        }
//        if (authors != null) {
//            List<ComidaTipoEntity> authorsEntity = new ArrayList<>();
//            for (ComidaTipoDTO dtoComidaTipo : authors) {
//                authorsEntity.add(dtoComidaTipo.toEntity());
//            }
//            bookEntity.setComidaTipos(authorsEntity);
//        }
        return bookEntity;
    }

    /**
     * Devuelve las reseñas asociadas a este libro
     *
     * @return Lista de DTOs de Reseñas
     */
    public List<DietaTipoDTO> getDietaTipos() {
        return reviews;
    }

    /**
     * Modifica las reseñas de este libro.
     *
     * @param reviews Las nuevas reseñas
     */
    public void setDietaTipos(List<DietaTipoDTO> reviews) {
        this.reviews = reviews;
    }

    /**
     * Devuelve los autores del libro
     *
     * @return DTO de Autores
     */
    public List<ComidaTipoDTO> getComidaTipos() {
        return authors;
    }

    /**
     * Modifica los autores del libro
     *
     * @param authors Lista de autores
     */
    public void setComidaTipos(List<ComidaTipoDTO> authors) {
        this.authors = authors;
    }

    
}
