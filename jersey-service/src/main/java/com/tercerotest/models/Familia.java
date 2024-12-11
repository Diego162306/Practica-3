package com.tercerotest.models;

public class Familia {
    private Integer idFamilia;
    private String apellidoFamilia;
    private String direccion;
    private String telefono;
    private Boolean HCpdGnrd; //Ha comprado Gnerador
    
    // Constructor
    public Familia(Integer idFamilia, String apellidoFamilia, String direccion, Boolean HCpdGnrd) {
        this.idFamilia = idFamilia;
        this.apellidoFamilia = apellidoFamilia;
        this.direccion = direccion;
        this.HCpdGnrd = HCpdGnrd;
        this.telefono = telefono;
    }

    public Familia() {
    }
    
    // Getters and Setters
    public int getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getApellidoFamilia() {
        return apellidoFamilia;
    }

    public void setApellidoFamilia(String apellidoFamilia) {
        this.apellidoFamilia = apellidoFamilia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getHCpdGnrd() {
        return HCpdGnrd;
    }

    public void setHCpdGnrd(Boolean HCpdGnrd) {
        this.HCpdGnrd = HCpdGnrd;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Familia {idFamilia=" + idFamilia + ", direccion=" + direccion + ", apellidoFamilia=" + apellidoFamilia + ", HCpdGnrd=" + HCpdGnrd + "}";
    }
  
}
