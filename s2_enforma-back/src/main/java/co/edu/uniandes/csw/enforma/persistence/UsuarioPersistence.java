/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.persistence;

import co.edu.uniandes.csw.enforma.entities.UsuarioEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Paula Sofía Vargas 
 */
@Stateless
public class UsuarioPersistence
{
    @PersistenceContext (unitName = "UniversidadPU")
    protected EntityManager em;
     
    public UsuarioEntity create(UsuarioEntity usuario)
    {
        em.persist(usuario);
        return usuario;
    }
}
