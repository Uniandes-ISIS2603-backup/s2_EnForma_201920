/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.resources;

import co.edu.uniandes.csw.enforma.dtos.QuejasYReclamosDTO;
import co.edu.uniandes.csw.enforma.ejb.QuejasYReclamosLogic;
import co.edu.uniandes.csw.enforma.entities.QuejasYReclamosEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Jimmy Pepinosa
 */
@Path("quejasyreclamos")
@Produces("application/json")
@Consumes("application/json")
public class QuejasYReclamosResource 
{
    private static final  Logger LOGGER = Logger.getLogger(QuejasYReclamosResource.class.getName());
    
    @Inject 
    private QuejasYReclamosLogic quejasYReclamosLogic;
    
    @POST
    public QuejasYReclamosDTO createQuejasYReclamos(QuejasYReclamosDTO quejaReclamo) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "QuejasYReclamosResource createQuejasYReclamos: input: {0}", quejaReclamo);
        LOGGER.log(Level.INFO, "Entra al toEntity");
        QuejasYReclamosEntity entity = quejaReclamo.toEntity();
        LOGGER.log(Level.INFO, "Sale del toEntity");
        QuejasYReclamosDTO nuevaQuejaYReclamoDTO = new QuejasYReclamosDTO(quejasYReclamosLogic.createQuejasYReclamos(entity));
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: output: {0}", nuevaQuejaYReclamoDTO);
        return nuevaQuejaYReclamoDTO;
    }
    
    @GET
    public List<QuejasYReclamosDTO> getQuejasYReclamos()
    {
        LOGGER.info("QuejasYReclamosResource getQuejasYReclamos: input: void");
        List<QuejasYReclamosDTO> listaQuejasYReclamos = listEntity2DTO(quejasYReclamosLogic.getQuejasYReclamos());
        LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: output: {0}", listaQuejasYReclamos);
        return listaQuejasYReclamos;
    }
    
    @GET
    @Path("{quejareclamoId: \\d+}")
    public QuejasYReclamosDTO getQuejaOReclamo(@PathParam("quejareclamoId") Long quejasYReclamosId)
    {
        LOGGER.log(Level.INFO, "QuejasYReclamosResource getQuejaOReclamo: input: {0}", quejasYReclamosId);
        QuejasYReclamosEntity quejasYReclamosEntity = quejasYReclamosLogic.getQuejaOReclamo(quejasYReclamosId);
        if(quejasYReclamosEntity == null)
        {
            throw new WebApplicationException("El recurso /quejasyreclamos/"+ quejasYReclamosId + " no exite", 404 );
        }
        QuejasYReclamosDTO quejasYReclamosDTO = new QuejasYReclamosDTO(quejasYReclamosEntity);
        LOGGER.log(Level.INFO, "QuejasYReclamosResource getQuejaOReclamo: input: {0}", quejasYReclamosDTO);
        return quejasYReclamosDTO;
    }
    
    @PUT
    @Path("{quejareclamoId: \\d+}")
    public void updateQuejasYReclamos(@PathParam("quejareclamoId") Long quejasYReclamosId, QuejasYReclamosDTO quejasYReclamos)
    {
        LOGGER.log(Level.INFO, "QuejasYReclamosResource updateQuejasYReclamos: input: QuejasYReclamosId: {0}, quejasYReclamos: {1}", new Object[]{quejasYReclamosId, quejasYReclamos});
        quejasYReclamos.setId(quejasYReclamosId);
        if(quejasYReclamosLogic.getQuejaOReclamo(quejasYReclamosId) == null)
        {
            throw new WebApplicationException("El recurso /quejasyreclamos/" + quejasYReclamosId + " no existe.", 404);
        }
        QuejasYReclamosDTO quejasYReclamosDTO = new QuejasYReclamosDTO(quejasYReclamos.toEntity());
        LOGGER.info("QuejasYReclamosResource updateQuejasYReclamos: output: void");
        
    }
    
    @DELETE
    @Path("{quejareclamoId: \\d+}")
    public void deleteQuejasYReclamos(@PathParam("quejareclamoId") Long quejasYReclamosId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "QuejasYReclamosResource deleteQuejasYReclamos: input: {0}", quejasYReclamosId);
        if(quejasYReclamosLogic.getQuejaOReclamo(quejasYReclamosId) == null)
        {
            throw new WebApplicationException("El recurso /quejasyreclamos/" + quejasYReclamosId + " no existe.", 404);
        }
        quejasYReclamosLogic.deleteQuejasYReclamos(quejasYReclamosId);
        LOGGER.info("CalificacionResource deleteCalificacion: output: void");
    }
    
    /**
     * Convierte una lista de entidades a DTO
     * @param entityList
     * @return 
     */
    private List<QuejasYReclamosDTO> listEntity2DTO(List<QuejasYReclamosEntity> entityList)
    {
        List<QuejasYReclamosDTO> list = new ArrayList<>();
        for(QuejasYReclamosEntity entity: entityList)
        {
            list.add(new QuejasYReclamosDTO(entity));
        }
        return list;
    }
}

