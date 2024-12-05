package com.tercerotest.controller.dao;

import java.util.function.ToIntBiFunction;

import com.tercerotest.controller.dao.implement.AdapterDao;
import com.tercerotest.controller.tda.list.LinkedList;
import com.tercerotest.models.Generador;


public class GeneradorDao extends AdapterDao<Generador> {
    private Generador generador;
    private LinkedList listAll;

    public GeneradorDao() {
        super(Generador.class);
    }

    public Generador getGenerador() {
        if (generador == null) {
            generador = new Generador();
        }
        return this.generador;
    }

    public void setGenerador(Generador generador) {
        this.generador = generador;
    }

    public LinkedList getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        generador.setIdGenerador(id);
        this.persist(this.generador);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {

        this.merge(getGenerador(), getGenerador().getIdGenerador() - 1);
        this.listAll = listAll();
        return true;
    }

    public Boolean delete(int index) throws Exception { //Elimina un objeto Familia por su índice
        this.supreme(index);
        this.listAll = listAll(); // Actualiza la lista de objetos
        return true; // Retorna verdadero si se eliminó correctamente
    }

}