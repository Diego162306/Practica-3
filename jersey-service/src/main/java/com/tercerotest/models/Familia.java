package com.tercerotest.models;

public class Familia {
    private int idFamilia;
    private String apellidoFamilia;
    private String direccion;
    private Boolean HCpdGnrd; //Ha comprado Gnerador
    
    // Constructor
    public Familia(int idFamilia, String apellidoFamilia, String direccion, Boolean HCpdGnrd) {
        this.idFamilia = idFamilia;
        this.apellidoFamilia = apellidoFamilia;
        this.direccion = direccion;
        this.HCpdGnrd = HCpdGnrd;
    }

    public Familia() {
    }
    
    // Getters and Setters
    public int getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(int idFamilia) {
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

    @Override
    public String toString() {
        return "Familia {idFamilia=" + idFamilia + ", direccion=" + direccion + ", apellidoFamilia=" + apellidoFamilia + ", HCpdGnrd=" + HCpdGnrd + "}";
    }
  
}
