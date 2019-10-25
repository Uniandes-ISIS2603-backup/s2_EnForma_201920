/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

import co.edu.uniandes.csw.enforma.dtos.ComidaTipoDTO;
import co.edu.uniandes.csw.enforma.ejb.AdministradorComidaTipoLogic;
import co.edu.uniandes.csw.enforma.ejb.ComidaTipoLogic;
import co.edu.uniandes.csw.enforma.entities.ComidaTipoEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Elina Jaimes
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdministradorComidaTipoResource {
    
    
    private static final Logger LOGGER = Logger.getLogger(AdministradorComidaTipoResource.class.getName());

    @Inject
    private AdministradorComidaTipoLogic administradorComidaTiposLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ComidaTipoLogic comidaTipoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

  

    /**
     * Busca y devuelve todos los libros que existen en la administrador.
     *
     * @param administradorsId Identificador de la administrador que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link ComidaTipoDetailDTO} - Los libros encontrados en la
     * administrador. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ComidaTipoDTO> getComidaTipos(@PathParam("administradorsId") Long administradorsId) {
        LOGGER.log(Level.INFO, "AdministradorComidaTiposResource getComidaTipos: input: {0}", administradorsId);
        List<ComidaTipoDTO> listaDetailDTOs = comidaTiposListEntity2DTO(administradorComidaTiposLogic.getComidasTipos(administradorsId));
        LOGGER.log(Level.INFO, "AdministradorComidaTiposResource getComidaTipos: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Busca el libro con el id asociado dentro de la administrador con id asociado.
     *
     * @param administradorsId Identificador de la administrador que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param comidaTiposId Identificador del libro que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link ComidaTipoDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro en la
     * administrador.
     */
    @GET
    @Path("{comidaTiposId: \\d+}")
    public ComidaTipoDTO getComidaTipo(@PathParam("administradorsId") Long administradorsId, @PathParam("comidaTiposId") Long comidaTiposId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorComidaTiposResource getComidaTipo: input: administradorsID: {0} , comidaTiposId: {1}", new Object[]{administradorsId, comidaTiposId});
        if (comidaTipoLogic.getComidaTipo(comidaTiposId) == null) {
            throw new WebApplicationException("El recurso /administradors/" + administradorsId + "/comidaTipos/" + comidaTiposId + " no existe.", 404);
        }
        ComidaTipoDTO comidaTipoDetailDTO = new ComidaTipoDTO(administradorComidaTiposLogic.getComidaTipo(administradorsId, comidaTiposId));
        LOGGER.log(Level.INFO, "AdministradorComidaTiposResource getComidaTipo: output: {0}", comidaTipoDetailDTO);
        return comidaTipoDetailDTO;
    }

    

    /**
     * Convierte una lista de ComidaTipoEntity a una lista de ComidaTipoDetailDTO.
     *
     * @param entityList Lista de ComidaTipoEntity a convertir.
     * @return Lista de ComidaTipoDTO convertida.
     */
    private List<ComidaTipoDTO> comidaTiposListEntity2DTO(List<ComidaTipoEntity> entityList) {
        List<ComidaTipoDTO> list = new ArrayList();
        for (ComidaTipoEntity entity : entityList) {
            list.add(new ComidaTipoDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de ComidaTipoDetailDTO a una lista de ComidaTipoEntity.
     *
     * @param dtos Lista de ComidaTipoDetailDTO a convertir.
     * @return Lista de ComidaTipoEntity convertida.
     */
    private List<ComidaTipoEntity> comidaTiposListDTO2Entity(List<ComidaTipoDTO> dtos) {
        List<ComidaTipoEntity> list = new ArrayList<>();
        for (ComidaTipoDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    
    
}
