package com.tercerotest.controller.dao;

import java.util.Arrays;
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

    public Boolean delete(int index) throws Exception { // Elimina un objeto Familia por su índice
        this.supreme(index);
        this.listAll = listAll(); // Actualiza la lista de objetos
        return true; // Retorna verdadero si se eliminó correctamente
    }

    public int FamiliasConGenerador() {
        int contador = 0;
        LinkedList<Familia> familias = listAll(); // lista todas las familias
        Familia[] familiasArray = familias.toArray(); // Convierte la lista enlazada en un arreglo

        for (Familia familia : familiasArray) { // Usa el bucle for-each en el arreglo
            if (familia.getHCpdGnrd()) { // Verifica si la familia tiene generador
                contador++;
            }
        }
        return contador;
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
            System.out.println("Uno de los objetos Familia es nulo.");
            return false;
        }
        System.out.println("Comparando atributo: " + atributo);
        System.out.println("Familia A: " + a);
        System.out.println("Familia B: " + b);

        String atributoA = "";
        String atributoB = "";

        switch (atributo.toLowerCase()) {
            case "apellidoFamilia":
                atributoA = a.getApellidoFamilia() != null ? a.getApellidoFamilia().trim() : null;
                atributoB = b.getApellidoFamilia() != null ? b.getApellidoFamilia().trim() : null;
                break;
            case "Direccion":
                atributoA = a.getDireccion() != null ? a.getDireccion().trim() : null;
                atributoB = b.getDireccion() != null ? b.getDireccion().trim() : null;
                break;
            default:
                System.out.println("Atributo no reconocido.");
                return false;
        }

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
    public LinkedList<Familia> buscar_APellidoFamiila(String texto){
        LinkedList<Familia> lista = new LinkedList<>();
        LinkedList<Familia> listita = listAll();
        if (!listAll().isEmpty()) {
            Familia[] aux = (Familia[]) listita.toArray();
            for (int i= 0; i<aux.length; i++){
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

    //QuickSort
    public LinkedList<Familia> OrderMetQuick(Integer type_order, String atributo) {
        LinkedList<Familia> listita = new LinkedList<>();
        
        // Crear una nueva lista con los elementos actuales
        for (int i = 0; i < listAll().getSize(); i++) {
            try {
                listita.add(listAll().get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if (!listita.isEmpty()) {
            Familia[] lista = (Familia[]) listita.toArray(); // Convertir la lista enlazada a un arreglo
            Quicksort(lista, 0, lista.length - 1, type_order, atributo); // Aplicar QuickSort
            listita = new LinkedList<>(); // Reiniciar la lista enlazada
            listita.toList(lista); // Convertir el arreglo ordenado de vuelta a la lista enlazada
        }
        
        return listita;
    }
    
    // Método QuickSort para un arreglo de Familias
    private void Quicksort(Familia[] lista, int lOl, int def, Integer type_order, String atributo) {
        if (lOl < def) {
            int pivotIndex = partition(lista, lOl, def, type_order, atributo); // Particionar el arreglo
            Quicksort(lista, lOl, pivotIndex - 1, type_order, atributo); // Ordenar la parte izquierda
            Quicksort(lista, pivotIndex + 1, def, type_order, atributo); // Ordenar la parte derecha
        }
    }
    
    // Método de partición para el QuickSort
    private int partition(Familia[] lista, int lOl, int def, Integer type_order, String atributo) {
        Familia pivot = lista[def]; // Elegir el último elemento como pivote
        int i = lOl - 1; // Índice del menor elemento
    
        for (int j = lOl; j < def; j++) {
            if (verify(lista[j], pivot, type_order, atributo)) {
                i++;
                Familia die = lista[i];
                lista[i] = lista[j];
                lista[j] = die;
            }
        }
    
        // Colocar el pivote en su lugar correcto
        Familia die = lista[i + 1];
        lista[i + 1] = lista[def];
        lista[def] = die;
    
        return i + 1;
    }
    
    public LinkedList<Familia> OrderMerge(Integer type_order, String atributo) {
        LinkedList<Familia> listita = new LinkedList<>();
    
        // Crear una nueva lista con los elementos actuales
        for (int i = 0; i < listAll().getSize(); i++) {
            try {
                listita.add(listAll().get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        if (!listita.isEmpty()) {
            Familia[] lista = (Familia[]) listita.toArray(); // Convertir a arreglo
            lista = Mergesort(lista, atributo, type_order); // Aplicar MergeSort
            listita = new LinkedList<>(); // Reiniciar la lista enlazada
            listita.toList(lista); // Convertir el arreglo ordenado de vuelta a la lista enlazada
        }
    
        return listita;
    }
    
    // Método MergeSort para ordenar un arreglo de Familias
    private Familia[] Mergesort(Familia[] lista, String atributo, Integer type_order) {
        if (lista.length <= 1) {
            return lista;
        }
    
        int middle = lista.length / 2;
        Familia[] izquierda = Arrays.copyOfRange(lista, 0, middle); // Dividir en parte izquierda
        Familia[] derecha = Arrays.copyOfRange(lista, middle, lista.length); // Dividir en parte derecha
    
        izquierda = Mergesort(izquierda, atributo, type_order); // Ordenar la parte izquierda
        derecha = Mergesort(derecha, atributo, type_order); // Ordenar la parte derecha
    
        return merge(izquierda, derecha, atributo, type_order); // Combinar las partes ordenadas
    }
    
    // Método para combinar dos subarreglos ordenados
    private Familia[] merge(Familia[] izquierda, Familia[] derecha, String atributo, Integer type_order) {
        Familia[] result = new Familia[izquierda.length + derecha.length];
        int e = 0, d = 0, m = 0;
        int i = 0, j = 0, k = 0;
    
        while (e < izquierda.length && d < derecha.length) {
            if (verify(izquierda[i], derecha[j], type_order, atributo)) {
                result[m++] = izquierda[e++];
            } else {
                result[m++] = derecha[d++];
            }
        }
    
        while (e < izquierda.length) {
            result[m++] = izquierda[e++];
        }
    
        while (d < derecha.length) {
            result[m++] = derecha[d++];
        }
    
        return result;
    }
    

    
    public LinkedList<Familia> OrderShell(Integer type_order, String atributo) {
        LinkedList<Familia> listita = new LinkedList<>();
    
        // Crear una nueva lista con los elementos actuales
        for (int i = 0; i < listAll().getSize(); i++) {
            try {
                listita.add(listAll().get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        if (!listita.isEmpty()) {
            Familia[] lista = (Familia[]) listita.toArray(); // Convertir a arreglo
            lista = ShellSort(lista, atributo, type_order); // Aplicar Shell Sort
            listita = new LinkedList<>(); // Reiniciar la lista enlazada
            listita.toList(lista); // Convertir el arreglo ordenado de vuelta a la lista enlazada
        }
    
        return listita;
    }
    
    // Método Shell Sort para ordenar un arreglo de Familias
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
        public LinkedList<Familia> BuscarApellidoBinario(String texto) {
            LinkedList<Familia> lista = new LinkedList<>();
            LinkedList<Familia> listita = listAll();
        
            try {
                
                listita.shellsort("apellidoFamilia", 1);
            } catch (Exception e) {
                e.printStackTrace(); // Manejo de excepciones
                return lista; // Devuelve una lista vacía si hay un error
            }
        
            if (!listita.isEmpty()) {
                // Convertir LinkedList a arreglo
                Familia[] aux = new Familia[listita.getSize()];
        
                int izq = 0, dere = aux.length - 1;
                while (izq <= dere) {
                    int mitad = izq + (dere - izq) / 2;
                    Familia familiaMid = aux[mitad];
        
                    if (familiaMid != null && familiaMid.getApellidoFamilia() != null) {
                        String nombreMid = familiaMid.getApellidoFamilia().toLowerCase();
        
                        if (nombreMid.contains(texto.toLowerCase())) {
                            lista.add(familiaMid);
        
                            // Buscar elementos adyacentes que también coincidan
                            int i = mitad - 1;
                            while (i >= 0 && aux[i].getApellidoFamilia().toLowerCase().contains(texto.toLowerCase())) {
                                lista.add(aux[i]);
                                i--;
                            }
                            int j = mitad + 1;
                            while (j < aux.length && aux[j].getApellidoFamilia().toLowerCase().contains(texto.toLowerCase())) {
                                lista.add(aux[j]);
                                j++;
                            }
                            break;
                        } else if (nombreMid.compareTo(texto.toLowerCase()) < 0) {
                            izq = mitad + 1;
                        } else {
                            dere = mitad - 1;
                        }
                    } else {
                        break;
                    }
                }
            }
            return lista;
        }
        

    public LinkedList<Familia> BuscarDireccionFamiliaLineal(String texto) {
        LinkedList<Familia> lista = new LinkedList<>();
        LinkedList<Familia> listita = listAll(); // Supone que listAll() devuelve todos los familias.
    
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

    public LinkedList<Familia> BuscarDireccionFamiliaBinario(String texto) {
        LinkedList<Familia> lista = new LinkedList<>();
        LinkedList<Familia> listita = listAll();
    
        if (!listita.isEmpty() && texto != null) {
            // Convertir la lista enlazada manualmente a un array.
            Familia[] aux = new Familia[listita.getSize()];
            int index = 0;
            for (Familia familia : listita.toArray()) {
                aux[index++] = familia;
            }
    
            // Realizar la búsqueda binaria.
            int izquierda = 0;
            int derecha = aux.length - 1;
    
            while (izquierda <= derecha) {
                int medio = izquierda + (derecha - izquierda) / 2;
                Familia familiaMedio = aux[medio];
    
                if (familiaMedio != null && familiaMedio.getDireccion() != null) {
                    String nombreMedio = familiaMedio.getDireccion().toLowerCase();
    
                    if (nombreMedio.contains(texto.toLowerCase())) {
                        lista.add(familiaMedio);
                        // Expandir hacia ambos lados en caso de múltiples coincidencias.
                        int i = medio - 1;
                        while (i >= 0 && aux[i].getDireccion().toLowerCase().contains(texto.toLowerCase())) {
                            lista.add(aux[i]);
                            i--;
                        }
                        int j = medio + 1;
                        while (j < aux.length && aux[j].getDireccion().toLowerCase().contains(texto.toLowerCase())) {
                            lista.add(aux[j]);
                            j++;
                        }
                        break;
                    } else if (nombreMedio.compareTo(texto.toLowerCase()) < 0) {
                        izquierda = medio + 1;
                    } else {
                        derecha = medio - 1;
                    }
                } else {
                    break;
                }
            }
        }
        return lista;
    }
    
}
    



