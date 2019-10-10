/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.enforma.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamDoubleValue;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamIntValue;

/**
 *
 * @author Sofia Vargas
 */

@Entity 
public class ClienteEntity extends BaseEntity implements Serializable
{
 private String nombre;
    
 @PodamIntValue(minValue = 1)
 private Integer edad;
 
 @PodamDoubleValue(minValue = 1.0)
 private Double peso;
 
 private String objetivos;
 private Boolean gluten;
 private Boolean lactosa;
 private String userName;
 private String contrasenia;
 
@PodamExclude
 @ManyToOne
 private DietaTipoEntity dietaTipo;
 
// 
@PodamExclude
@OneToMany(mappedBy = "cliente")
private List<DomicilioEntity> domicilios;
// 
@PodamExclude
 @OneToMany//(mappedBy = "usuario")//, fetch = FetchType.LAZY)
 private List<QuejasYReclamosEntity> quejas;
 
@PodamExclude
@OneToOne(mappedBy = "cliente")
private TarjetaPrepagoEntity tarjetaPrepago;
 

 public ClienteEntity(){
    
}
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the edad
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /**
     * @return the peso
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * @return the objetivos
     */
    public String getObjetivos() {
        return objetivos;
    }

    /**
     * @param objetivos the objetivos to set
     */
    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    /**
     * @return the gluten
     */
    public Boolean getGluten() {
        return gluten;
    }

    /**
     * @param gluten the gluten to set
     */
    public void setGluten(Boolean gluten) {
        this.gluten = gluten;
    }

    /**
     * @return the lactosa
     */
    public Boolean getLactosa() {
        return lactosa;
    }

    /**
     * @param lactosa the lactosa to set
     */
    public void setLactosa(Boolean lactosa) {
        this.lactosa = lactosa;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    /**
     * @return the dietaTipo
     */
    public DietaTipoEntity getDietaTipo() {
        return dietaTipo;
    }

    /**
     * @param dietaTipo the dietaTipo to set
     */
    public void setDietaTipo(DietaTipoEntity dietaTipo) {
       this.dietaTipo = dietaTipo;
    }

    /**
     * @return the domicilios
     */
    public List<DomicilioEntity> getDomicilios() 
    {
        return domicilios;
    }

    /**
     * @param domicilio the domicilios to set
     */
   public void setDomicilios(List<DomicilioEntity> domicilios) {
        this.domicilios = domicilios;
    }

    /**
     * @return the quejas
     */
    public List<QuejasYReclamosEntity> getQuejasYReclamos() {
        return quejas;
   }

    /**
     * @param quejas the quejas to set
     */
    public void setQuejasYReclamos(List<QuejasYReclamosEntity> quejas)
   {
        this.quejas = quejas;
    }

    /**
     * @return the tarjetaPrepago
     */
    public TarjetaPrepagoEntity getTarjetaPrepago() {
        return tarjetaPrepago;
    }

    /**
     * @param tarjetaPrepago the tarjetaPrepago to set
     */
    public void setTarjetaPrepago(TarjetaPrepagoEntity tarjetaPrepago)
    {
      this.tarjetaPrepago = tarjetaPrepago;
    }
}
