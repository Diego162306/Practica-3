package com.tercerotest.controller.dao.services;

import com.tercerotest.controller.dao.FamiliaDao;
import com.tercerotest.controller.tda.list.LinkedList;
import com.tercerotest.models.Familia;


public class FamiliaServices {

    private FamiliaDao obj;
    public FamiliaServices() {
        obj = new FamiliaDao();
    }
    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public Boolean delete(int index) throws Exception {
        return obj.delete(index);
    }

    public LinkedList listAll() {
        return obj.getListAll();
    }

    public Familia getFamilia() {
        return obj.getFamilia();
    }

    public void setFamilia(Familia Familia) {
        obj.setFamilia(Familia);
    }

    public Familia get(Integer id) throws Exception {
        return obj.get(id);
    }

    public LinkedList order_object(Integer type, String atributo) throws Exception {
        return obj.listAll().order(atributo, type);
    }

    public LinkedList<Familia> buscarAPellidoFamiila(String texto) {
        return obj.buscarAPellidoFamiila(texto);
    }

    public LinkedList<Familia> buscarDireccion(String texto) {
        return obj.buscarDireccion(texto);
    }

    public LinkedList<Familia> buscarTelefonno(String texto) {
        return obj.buscarTelefonno(texto);
    }

    public LinkedList OrderMetQuick(Integer type_order, String atributo) {
        return obj.OrderMetQuick(type_order, atributo);
    }

    public LinkedList OrderMerge(Integer type_order, String atributo) {
        return obj.OrderMerge(type_order, atributo);
    }

    public LinkedList OrderShell(Integer type_order, String atributo) {
        return obj.OrderShell(type_order, atributo);
    }
    
    public LinkedList<Familia> BuscarApellidoLIneal(String texto) {
        return obj.BuscarApellidoLIneal(texto);
    }

    public LinkedList<Familia> BuscarApellidoBinario(String texto) {
        return obj.buscarApellidoBinario(texto);
    }

    public LinkedList<Familia> BuscarDireccionFamiliaLineal(String texto) {
        return obj.BuscarDireccionFamiliaLineal(texto);
    }

    public LinkedList<Familia> BuscarDireccionFamiliaBinario(String texto) {
        return obj.buscarDireccionFamiliaBinario(texto);
    }

    public LinkedList<Familia> BusquedaTelefonoFamiliaLineal(String texto) {
        return obj.BusquedaTelefonoFamiliaLineal(texto);
    }

    public LinkedList<Familia> BusquedaTelefonoFamiliaBinario(String texto) {
        return obj.buscarTelefonoFamiliaBinario(texto);
    }
}
