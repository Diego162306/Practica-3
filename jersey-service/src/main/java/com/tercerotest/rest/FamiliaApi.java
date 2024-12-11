package com.tercerotest.rest;

import java.util.HashMap;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.tercerotest.controller.tda.list.LinkedList;
import com.tercerotest.models.Familia;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.FileInputStream;
import com.tercerotest.controller.dao.services.FamiliaServices;

@Path("familia")
public class FamiliaApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        HashMap map = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        map.put("msg", "OK");
        map.put("data", ps.listAll().toArray());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }

        return Response.ok(map).build();
    }

    @Path("/list/OrderQuick/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamiliaApellido(@PathParam("attribute") String atributo, @PathParam("type") Integer tipoOrden) {
        HashMap<String, Object> map = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        map.put("msg", "OK");

        // Llamada a OrderMetQuick con los parámetros correctos
        LinkedList<Familia> lista = ps.OrderMetQuick(tipoOrden, atributo);

        // Si la lista está vacía, devolver un array vacío
        if (lista.isEmpty()) {
            map.put("data", new Object[] {});
        } else {
            map.put("data", lista.toArray());
        }

        return Response.ok(map).build();
    }

    @Path("/list/OrderMer/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamiliaApe(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap map = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        map.put("msg", "OK");
        // pd.order_object(LinkedList.ASC, "apellidos")
        try {
            LinkedList lsita = ps.OrderMerge(type, attribute);
            map.put("data", lsita.toArray());
            if (lsita.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return Response.ok(map).build();
    }

    @Path("/list/OrderShell/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamiliaLastName(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap map = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        map.put("msg", "OK");
        // pd.order_object(LinkedList.ASC, "apellidos")
        try {
            LinkedList lsita = ps.OrderShell(type, attribute);
            map.put("data", lsita.toArray());
            if (lsita.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return Response.ok(map).build();
    }

    @Path("/list/search/apellidoFa/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApellidoFa(@PathParam("texto") String texto) {
        HashMap map = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        map.put("msg", "Ok");
        LinkedList lista = ps.buscarAPellidoFamiila(texto);
        map.put("data", lista.toArray());
        if (lista.isEmpty()) {
            map.put("data", new Object[] {});

        }
        return Response.ok(map).build();
    }

    @Path("/list/search/Direccion/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDireccionFa(@PathParam("texto") String texto) {
        HashMap map = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        map.put("msg", "Ok");
        LinkedList lista = ps.buscarDireccion(texto);
        map.put("data", lista.toArray());
        if (lista.isEmpty()) {
            map.put("data", new Object[] {});

        }
        return Response.ok(map).build();
    }

    @Path("/list/search/telefono/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTelefonoFa(@PathParam("texto") String texto) {
        HashMap map = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        map.put("msg", "Ok");
        LinkedList lista = ps.buscarTelefonno(texto);
        map.put("data", lista.toArray());
        if (lista.isEmpty()) {
            map.put("data", new Object[] {});

        }
        return Response.ok(map).build();
    }

    @Path("/list/search/lineal/Apellido/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApellidoFaLi(@PathParam("texto") String texto) {
        HashMap map = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        map.put("msg", "Ok");
        LinkedList lista = ps.BuscarApellidoLIneal(texto);
        map.put("data", lista.toArray());
        if (lista.isEmpty()) {
            map.put("data", new Object[] {});

        }
        return Response.ok(map).build();
    }

    @Path("/list/search/lineal/direcccion/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDireccionFaLi(@PathParam("texto") String texto) {
        HashMap map = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        map.put("msg", "Ok");
        LinkedList lista = ps.BuscarDireccionFamiliaLineal(texto);
        map.put("data", lista.toArray());
        if (lista.isEmpty()) {
            map.put("data", new Object[] {});

        }
        return Response.ok(map).build();
    }

    @Path("/list/search/lineal/Telefono/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTelefonoFaLi(@PathParam("texto") String texto) {
        HashMap map = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        map.put("msg", "Ok");
        LinkedList lista = ps.BusquedaTelefonoFamiliaLineal(texto);
        map.put("data", lista.toArray());
        if (lista.isEmpty()) {
            map.put("data", new Object[] {});

        }
        return Response.ok(map).build();
    }

    @Path("/list/search/binario/Apellido/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApellidoFaBi(@PathParam("texto") String texto) {
        HashMap map = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        map.put("msg", "Ok");
        LinkedList lista = ps.BuscarApellidoBinario(texto);
        map.put("data", lista.toArray());
        if (lista.isEmpty()) {
            map.put("data", new Object[] {});

        }
        return Response.ok(map).build();
    }

    @Path("/list/search/binario/direcccion/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDireccionFaBi(@PathParam("texto") String texto) {
        HashMap map = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        map.put("msg", "Ok");
        LinkedList lista = ps.BuscarDireccionFamiliaBinario(texto);
        map.put("data", lista.toArray());
        if (lista.isEmpty()) {
            map.put("data", new Object[] {});

        }
        return Response.ok(map).build();
    }

    @Path("/list/search/binario/Telefono/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTelefonoFaBi(@PathParam("texto") String texto) {
        HashMap map = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        map.put("msg", "Ok");
        LinkedList lista = ps.BusquedaTelefonoFamiliaBinario(texto);
        map.put("data", lista.toArray());
        if (lista.isEmpty()) {
            map.put("data", new Object[] {});

        }
        return Response.ok(map).build();
    }

    @Path("/listType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        HashMap map = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        map.put("msg", "OK");
        map.put("data", ps.getFamilia());
        return Response.ok(map).build();
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap map) {
        // TODO
        // VALIDATION ---- BAD REQUEST

        HashMap res = new HashMap<>();
        Gson g = new Gson();
        String a = g.toJson(map);
        System.out.println("******* " + a);
        try {
            FamiliaServices ps = new FamiliaServices();
            ps.getFamilia().setApellidoFamilia(map.get("apellidoFamilia").toString());
            ps.getFamilia().setDireccion(map.get("direccion").toString());
            ps.getFamilia().setHCpdGnrd(Boolean.parseBoolean(map.get("HCpdGnrd").toString()));
            ps.getFamilia().setTelefono(map.get("telefono").toString());

            ps.save();
            res.put("msg", "OK");
            res.put("data", "Familia registrada correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en sav data " + e.toString());
            res.put("msg", "Error");
            // res.put("msg", "ERROR");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
            // TODO: handle exception
        }

    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap map) {
        // TODO
        // VALIDATION ---- BAD REQUEST

        HashMap res = new HashMap<>();

        try {

            FamiliaServices ps = new FamiliaServices();
            ps.getFamilia().setApellidoFamilia(map.get("apellidoFamilia").toString());
            ps.getFamilia().setDireccion(map.get("direccion").toString());
            ps.getFamilia().setHCpdGnrd(Boolean.parseBoolean(map.get("HCpdGnrd").toString()));
            ps.getFamilia().setTelefono(map.get("telefono").toString());
            ps.update();
            res.put("msg", "OK");
            res.put("data", "Familia registrada correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en sav data " + e.toString());
            res.put("msg", "Error");
            // res.put("msg", "ERROR");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
            // TODO: handle exception
        }

    }

}
