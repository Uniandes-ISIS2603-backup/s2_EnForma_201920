/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.ejb;
import co.edu.uniandes.csw.enforma.entities.AdministradorEntity;
import co.edu.uniandes.csw.enforma.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.enforma.persistence.AdministradorPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Elina Jaimes
 */

@Stateless
public class AdministradorLogic {

    @Inject
    private AdministradorPersistence persistence;

    public AdministradorEntity crearAdministrador(AdministradorEntity admin) throws BusinessLogicException {
        if (admin.getNombre().equals("") || admin.getNombre() == null) {
            throw new BusinessLogicException("El nombre no puede estar vacío");
        } else if (admin.getUsername().equals("") || admin.getUsername() == null) {
            throw new BusinessLogicException("El username no puede estar vacío");
        } else {
            admin = persistence.create(admin);
            return admin;
        }
    }

}
