package com.tercerotest.controller.dao.services;

import com.tercerotest.controller.dao.GeneradorDao;
import com.tercerotest.controller.tda.list.LinkedList;
import com.tercerotest.models.Generador;

public class GeneradorServices {
    private GeneradorDao obj;
    public GeneradorServices() {
        obj = new GeneradorDao();
    }
    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public LinkedList listAll() {
        return obj.getListAll();
    }

    public Generador getGenerador() {
        return obj.getGenerador();
    }

    public void setGenerador(Generador Generador) {
        obj.setGenerador(Generador);
    }

    public Generador get(Integer id) throws Exception {
        return obj.get(id);
    }

    public LinkedList order_object(Integer type, String atributo) throws Exception {
        return obj.listAll().order(atributo, type);
    }
}
