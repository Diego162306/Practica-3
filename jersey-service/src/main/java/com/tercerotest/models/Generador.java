package com.tercerotest.models;

public class Generador {
    private int idGenerador;
    private String marca;
    private Double costo;
    private Double CsmxHr; // Consumo por hora en litros de gasolina
    private Double PdGnrd; // Potencia del generador

    // Constructor
    public Generador(int idGenerador, String marca, Double costo, Double CsmxHr, Double PdGnrd) {
        this.idGenerador = idGenerador;
        this.marca = marca;
        this.costo = costo;
        this.CsmxHr = CsmxHr;
        this.PdGnrd = PdGnrd;
    }

    public Generador() {
    }

    // Getters and Setters  
    public int getIdGenerador() {
        return idGenerador;
    }

    public void setIdGenerador(int idGenerador) {
        this.idGenerador = idGenerador;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getCsmxHr() {
        return CsmxHr;
    }

    public void setCsmxHr(Double CsmxHr) {
        this.CsmxHr = CsmxHr;
    }

    public Double getPdGnrd() {
        return PdGnrd;
    }

    public void setPdGnrd(Double PdGnrd) {
        this.PdGnrd = PdGnrd;
    }


}
