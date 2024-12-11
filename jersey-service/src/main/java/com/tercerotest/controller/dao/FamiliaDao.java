package com.tercerotest.controller.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToIntBiFunction;

import com.tercerotest.controller.dao.implement.AdapterDao;
import com.tercerotest.controller.tda.list.LinkedList;
import com.tercerotest.models.Familia;

public class FamiliaDao extends AdapterDao<Familia> {
    private Familia familia;
    private LinkedList listAll;

    public FamiliaDao() {
        super(Familia.class);
    }

    public Familia getFamilia() {
        if (familia == null) {
            familia = new Familia();
        }
        return this.familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public LinkedList getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        familia.setIdFamilia(id);
        this.persist(this.familia);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {

        this.merge(getFamilia(), getFamilia().getIdFamilia() - 1);
        this.listAll = listAll();
        return true;
    }

    public Boolean delete(int index) throws Exception {
        this.supreme(index);
        this.listAll = listAll();
        return true;
    }

    public LinkedList order(Integer type_order, String atributo) {
        LinkedList listita = listAll();
        if (!listAll.isEmpty()) {
            Familia[] lista = (Familia[]) listita.toArray();
            listita.reset();
            for (int i = 1; i < lista.length; i++) {
                Familia aux = lista[i];
                int j = i - 1;
                while (j >= 0 && (verify(lista[j], aux, type_order, atributo))) {
                    lista[j + 1] = lista[j--];
                }
                lista[j + 1] = aux;
            }
            listita.toList(lista);
        }
        return listita;
    }

    private Boolean verify(Familia a, Familia b, Integer type_order, String atributo) {
        if (a == null || b == null) {
            System.out.println("Un objeto es nulo.");
            return false;
        }
        System.out.println("Comparando atributo: " + atributo);
        System.out.println("Familia A: " + a);
        System.out.println("Familia B: " + b);

        // map para asociar atributos con funciones
        Map<String, Function<Familia, String>> atributoMap = new HashMap<>();
        atributoMap.put("apellido", Familia::getApellidoFamilia);
        atributoMap.put("direccion", Familia::getDireccion);
        atributoMap.put("telefono", Familia::getTelefono);

        // obtener la funcion asociada
        Function<Familia, String> getter = atributoMap.get(atributo.toLowerCase());
        if (getter == null) {
            System.out.println("Atributo no reconocido.");
            return false;
        }

        // obtener el valor del atributo para cada familia
        String atributoA = getter.apply(a) != null ? getter.apply(a).trim() : null;
        String atributoB = getter.apply(b) != null ? getter.apply(b).trim() : null;
        if (atributoA == null || atributoB == null) {
            System.out.println("Uno de los atributos es nulo: AtributoA = " + atributoA + ", AtributoB = " + atributoB);
            return false;
        }

        int result = atributoA.compareToIgnoreCase(atributoB);

        System.out.println("Comparación resultante: " + result);

        if (type_order == 1) { // Ascendente
            return result > 0;
        } else { // Descendente
            return result < 0;
        }
    }

    // metodo 19/11/2024
    public LinkedList<Familia> buscarAPellidoFamiila(String texto) {
        LinkedList<Familia> lista = new LinkedList<>();
        LinkedList<Familia> listita = listAll();
        if (!listAll().isEmpty()) {
            Familia[] aux = (Familia[]) listita.toArray();
            for (int i = 0; i < aux.length; i++) {
                if (aux[i].getApellidoFamilia().toLowerCase().startsWith(texto.toLowerCase())) {
                    // System.out.println("+++++++"+aux[i].getApellidoPaterno());
                    lista.add(aux[i]);
                }
            }
        }
        return lista;
    }

    public LinkedList<Familia> buscarDireccion(String texto) {
        LinkedList<Familia> lista = new LinkedList<>();
        LinkedList<Familia> listita = listAll();
        if (!listita.isEmpty()) {
            Familia[] aux = listita.toArray();
            for (int i = 0; i < aux.length; i++) {
                if (aux[i].getDireccion().toLowerCase().startsWith(texto.toLowerCase())) {
                    lista.add(aux[i]);
                }
            }
        }
        return lista;
    }

    public LinkedList<Familia> buscarTelefonno(String texto){
        LinkedList<Familia> lista = new LinkedList<>();
        LinkedList<Familia> listita = listAll();
        if (!listita.isEmpty()) {
            Familia[] aux = listita.toArray();
            for (int i = 0; i < aux.length; i++) {
                if (aux[i].getTelefono().toLowerCase().startsWith(texto.toLowerCase())) {
                    lista.add(aux[i]);
                }
            }
        }
        return lista;
    }

    public int FamiliasConGenerador() {
        int contador = 0;
        LinkedList<Familia> familias = listAll();
        Familia[] familiasArray = familias.toArray();

        for (Familia familia : familiasArray) {
            if (familia.getHCpdGnrd()) {
                contador++;
            }
        }
        return contador;
    }

    //quicksort
    public LinkedList<Familia> OrderMetQuick(Integer type_order, String atributo) {
        LinkedList<Familia> listita = new LinkedList<>();
        for (int i = 0; i < listAll().getSize(); i++) {
            try {
                listita.add(listAll().get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!listita.isEmpty()) {
            Familia[] lista = (Familia[]) listita.toArray(); 
            Quicksort(lista, 0, lista.length - 1, type_order, atributo); 
            listita = new LinkedList<>(); 
            listita.toList(lista); 
        }
        return listita;
    }

    // quicksort para ordenar un arreglo de familias
    private void Quicksort(Familia[] lista, int bj, int alt, Integer type_order, String atributo) {
        if (bj < alt) {
            int PI = particion(lista, bj, alt, type_order, atributo); // Particionar el arreglo
            Quicksort(lista, bj, PI - 1, type_order, atributo); // Ordenar la parte izquierda
            Quicksort(lista, PI + 1, alt, type_order, atributo); // Ordenar la parte der
        }
    }

    // particion de quicksort
    private int particion(Familia[] lista, int bj, int alt, Integer type_order, String atributo) {
        Familia pivote = lista[alt]; // Elemento pivote
        int i = bj - 1; // Índice del elemento más pequeño

        for (int j = bj; j < alt; j++) {
            if (verify(lista[j], pivote, type_order, atributo)) {
                i++;
                Familia die = lista[i];
                lista[i] = lista[j];
                lista[j] = die;
            }
        }
        // Intercambiar el pivote con el elemento en la posición correcta
        Familia die = lista[i + 1];
        lista[i + 1] = lista[alt];
        lista[alt] = die;
        return i + 1;
    }

   // mergesort

     public LinkedList<Familia> OrderMerge(Integer type_order, String atributo) {
        LinkedList<Familia> listita = new LinkedList<>();
        for (int i = 0; i < listAll().getSize(); i++) {
            try {
                listita.add(listAll().get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!listita.isEmpty()) {
            Familia[] lista = (Familia[]) listita.toArray(); 
            lista = MergeSort(lista, atributo, type_order); 
            listita = new LinkedList<>(); 
            listita.toList(lista); 
        }
        return listita;
    }

    private Familia[] MergeSort(Familia[] lista, String atributo, Integer type_order) {
        if (lista.length <= 1) {
            return lista;
        }
        int aux = lista.length / 2;
        Familia[] izq = Arrays.copyOfRange(lista, 0, aux); 
        Familia[] der = Arrays.copyOfRange(lista, aux, lista.length); 
        izq = MergeSort(izq, atributo, type_order); 
        der = MergeSort(der, atributo, type_order); 
        return merge(izq, der, atributo, type_order); 
    }

    private Familia[] merge(Familia[] izq, Familia[] der, String atributo, Integer type_order) {
        Familia[] result = new Familia[izq.length + der.length];
        int e = 0, d = 0, m = 0;
        int i = 0, j = 0, k = 0;

        while (e < izq.length && d < der.length) {
            if (verify(izq[i], der[j], type_order, atributo)) {
                result[m++] = izq[e++];
            } else {
                result[m++] = der[d++];
            }
        }
        while (e < izq.length) {
            result[m++] = izq[e++];
        }
        while (d < der.length) {
            result[m++] = der[d++];
        }
        return result;
    }

    //shelsort
    private Familia[] ShellSort(Familia[] lista, String atributo, Integer type_order) {
        int a = lista.length;
        // Inicialización del intervalo de Shell Sort
        for (int mel = a / 2; mel > 0; mel /= 2) {
            for (int i = mel; i < a; i++) {
                Familia die = lista[i];
                int j;
                // Comparar elementos en el intervalo
                for (j = i; j >= mel && verify(lista[j - mel], die, type_order, atributo); j -= mel) {
                    lista[j] = lista[j - mel];
                }
                lista[j] = die;
            }
        }
        return lista;
    }

    public LinkedList<Familia> OrderShell(Integer type_order, String atributo) {
        LinkedList<Familia> listita = new LinkedList<>();
        for (int i = 0; i < listAll().getSize(); i++) {
            try {
                listita.add(listAll().get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!listita.isEmpty()) {
            Familia[] lista = (Familia[]) listita.toArray(); 
            lista = ShellSort(lista, atributo, type_order); 
            listita = new LinkedList<>(); 
            listita.toList(lista); 
        }
        return listita;
    }


    // Busqueda Lineal
    public LinkedList<Familia> BuscarApellidoLIneal(String texto) {
        LinkedList<Familia> lista = new LinkedList<>();
        LinkedList<Familia> listita = listAll();

        if (!listita.isEmpty()) {
            for (Familia familia : listita.toArray()) {
                if (familia != null && familia.getApellidoFamilia() != null &&
                        familia.getApellidoFamilia().toLowerCase().contains(texto.toLowerCase())) {
                    lista.add(familia);
                }
            }
        }
        return lista;
    }

    // Busqueda binaria
    public LinkedList<Familia> buscarApellidoBinario(String texto) {
        LinkedList<Familia> listaResultado = new LinkedList<>();
        LinkedList<Familia> listaCompleta = listAll();
        try {
            // Ordenar la lista completa por el campo "apellidoFamilia"
            listaCompleta.shellSort("apellidoFamilia", 1);
        } catch (Exception e) {
            e.printStackTrace(); 
            return listaResultado;
        } 
        if (!listaCompleta.isEmpty()) {
            // Convertir LinkedList a un arreglo usando el método toArray
            Familia[] arregloAuxiliar;
            try {
                arregloAuxiliar = listaCompleta.toArray();
            } catch (ClassCastException e) {
                e.printStackTrace();
                return listaResultado;
            }
            int izquierda = 0, derecha = arregloAuxiliar.length - 1;
            while (izquierda <= derecha) {
                int mitad = izquierda + (derecha - izquierda) / 2;
                Familia familiaMitad = arregloAuxiliar[mitad];
                if (familiaMitad != null && familiaMitad.getApellidoFamilia() != null) {
                    String apellidoMitad = familiaMitad.getApellidoFamilia().toLowerCase();
                    if (apellidoMitad.contains(texto.toLowerCase())) {
                        listaResultado.add(familiaMitad);
                        // Buscar coincidencias hacia la izquierda
                        for (int i = mitad - 1; i >= 0; i--) {
                            Familia izquierdaFamilia = arregloAuxiliar[i];
                            if (izquierdaFamilia.getApellidoFamilia().toLowerCase().contains(texto.toLowerCase())) {
                                listaResultado.add(izquierdaFamilia);
                            } else {
                                break;
                            }
                        }  
                        // Buscar coincidencias hacia la derecha
                        for (int j = mitad + 1; j < arregloAuxiliar.length; j++) {
                            Familia derechaFamilia = arregloAuxiliar[j];
                            if (derechaFamilia.getApellidoFamilia().toLowerCase().contains(texto.toLowerCase())) {
                                listaResultado.add(derechaFamilia);
                            } else {
                                break;
                            }
                        }   
                        break;
                    } else if (apellidoMitad.compareTo(texto.toLowerCase()) < 0) {
                        izquierda = mitad + 1;
                    } else {
                        derecha = mitad - 1;
                    }
                } else {
                    break;
                }
            }
        }
    
        return listaResultado;
    }

    public LinkedList<Familia> BuscarDireccionFamiliaLineal(String texto) {
        LinkedList<Familia> lista = new LinkedList<>();
        LinkedList<Familia> listita = listAll(); 
        if (!listita.isEmpty() && texto != null) {
            for (Familia familia : listita.toArray()) {
                if (familia != null && familia.getDireccion() != null &&
                        familia.getDireccion().toLowerCase().contains(texto.toLowerCase())) {
                    lista.add(familia);
                }
            }
        }
        return lista;
    }

    public LinkedList<Familia> buscarDireccionFamiliaBinario(String texto) {
        LinkedList<Familia> listaResultado = new LinkedList<>();
        LinkedList<Familia> listaCompleta = listAll();
        try {
            // Ordenar la lista completa por el campo "direccionFamilia"
            listaCompleta.shellSort("direccionFamilia", 1);
        } catch (Exception e) {
            e.printStackTrace(); 
            return listaResultado;
        }
        if (!listaCompleta.isEmpty()) {
            // Convertir LinkedList a un arreglo usando el método toArray
            Familia[] arregloAuxiliar;
            try {
                arregloAuxiliar = listaCompleta.toArray();
            } catch (ClassCastException e) {
                e.printStackTrace();
                return listaResultado;
            }
            int izquierda = 0, derecha = arregloAuxiliar.length - 1;
            while (izquierda <= derecha) {
                int mitad = izquierda + (derecha - izquierda) / 2;
                Familia familiaMitad = arregloAuxiliar[mitad];
                if (familiaMitad != null && familiaMitad.getDireccion() != null) {
                    String apellidoMitad = familiaMitad.getDireccion().toLowerCase();
                    if (apellidoMitad.contains(texto.toLowerCase())) {
                        listaResultado.add(familiaMitad);
                        // Buscar coincidencias hacia la izquierda
                        for (int i = mitad - 1; i >= 0; i--) {
                            Familia izquierdaFamilia = arregloAuxiliar[i];
                            if (izquierdaFamilia.getDireccion().toLowerCase().contains(texto.toLowerCase())) {
                                listaResultado.add(izquierdaFamilia);
                            } else {
                                break;
                            }
                        }
                        // Buscar coincidencias hacia la derecha
                        for (int j = mitad + 1; j < arregloAuxiliar.length; j++) {
                            Familia derechaFamilia = arregloAuxiliar[j];
                            if (derechaFamilia.getDireccion().toLowerCase().contains(texto.toLowerCase())) {
                                listaResultado.add(derechaFamilia);
                            } else {
                                break;
                            }
                        }
                        break;
                    } else if (apellidoMitad.compareTo(texto.toLowerCase()) < 0) {
                        izquierda = mitad + 1;
                    } else {
                        derecha = mitad - 1;
                    }
                } else {
                    break;
                }
            }
        }
    
        return listaResultado;
    }
    
    public LinkedList<Familia> BusquedaTelefonoFamiliaLineal(String texto) {
        LinkedList<Familia> lista = new LinkedList<>();
        LinkedList<Familia> listita = listAll(); 
        if (!listita.isEmpty() && texto != null) {
            for (Familia familia : listita.toArray()) {
                if (familia != null && familia.getTelefono() != null &&
                        familia.getTelefono().toLowerCase().contains(texto.toLowerCase())) {
                    lista.add(familia);
                }
            }
        }
        return lista;
    }

    public LinkedList<Familia> buscarTelefonoFamiliaBinario(String texto) {
        LinkedList<Familia> listaResultado = new LinkedList<>();
        LinkedList<Familia> listaCompleta = listAll();  
        try {
            // Ordenar la lista completa por el campo "telefonoFamilia"
            listaCompleta.shellSort("telefonoFamilia", 1);
        } catch (Exception e) {
            e.printStackTrace(); 
            return listaResultado;
        } 
        if (!listaCompleta.isEmpty()) {
            // Convertir LinkedList a un arreglo usando el método toArray
            Familia[] arregloAuxiliar;
            try {
                arregloAuxiliar = listaCompleta.toArray();
            } catch (ClassCastException e) {
                e.printStackTrace();
                return listaResultado;
            }
            int izquierda = 0, derecha = arregloAuxiliar.length - 1;
            while (izquierda <= derecha) {
                int mitad = izquierda + (derecha - izquierda) / 2;
                Familia familiaMitad = arregloAuxiliar[mitad];
                if (familiaMitad != null && familiaMitad.getTelefono() != null) {
                    String apellidoMitad = familiaMitad.getTelefono().toLowerCase();
                    if (apellidoMitad.contains(texto.toLowerCase())) {
                        listaResultado.add(familiaMitad);
                        // Buscar coincidencias hacia la izquierda
                        for (int i = mitad - 1; i >= 0; i--) {
                            Familia izquierdaFamilia = arregloAuxiliar[i];
                            if (izquierdaFamilia.getTelefono().toLowerCase().contains(texto.toLowerCase())) {
                                listaResultado.add(izquierdaFamilia);
                            } else {
                                break;
                            }
                        }
                        // Buscar coincidencias hacia la derecha
                        for (int j = mitad + 1; j < arregloAuxiliar.length; j++) {
                            Familia derechaFamilia = arregloAuxiliar[j];
                            if (derechaFamilia.getTelefono().toLowerCase().contains(texto.toLowerCase())) {
                                listaResultado.add(derechaFamilia);
                            } else {
                                break;
                            }
                        }
                        break;
                    } else if (apellidoMitad.compareTo(texto.toLowerCase()) < 0) {
                        izquierda = mitad + 1;
                    } else {
                        derecha = mitad - 1;
                    }
                } else {
                    break;
                }
            }
        }
    
        return listaResultado;
    }
}
