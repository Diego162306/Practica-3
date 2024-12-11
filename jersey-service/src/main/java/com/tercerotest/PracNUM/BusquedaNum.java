package com.tercerotest.PracNUM;

import java.util.Arrays;
import java.util.Random;

public class BusquedaNum {
    public static void main(String[] args) {
        int[] sizes = { 10000, 20000, 25000 };

        // Encabezado de la tabla
        imprimirEncabezado();

        for (int size : sizes) {
            int[] array = generarArregloAleatorio(size);
            int target = array[array.length / 2]; // Valor del medio

            // Realizar y medir cada tipo de búsqueda
            long tiempoSecuencial = medirTiempo(() -> busquedaSecuencial(array, target));
            long tiempoBinaria = medirTiempo(() -> busquedaBinaria(array, target));

            Arrays.sort(array);

            long tiempoBinariaOrdenada = medirTiempo(() -> busquedaBinaria(array, target));
            long tiempoSecuencialOrdenado = medirTiempo(() -> busquedaSecuencial(array, target));

            imprimirResultados(size, tiempoSecuencial, tiempoBinaria, tiempoBinariaOrdenada, tiempoSecuencialOrdenado);
        }

        System.out.println("---------------------------------------------------------------");
    }

    private static void imprimirEncabezado() {
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-10s%-20s%-20s%-20s%-20s%n", "Tamaño", "Secuencial (ns)", "Binaria (ns)", "Binaria (Ordenada) (ns)", "Secuencial Ordenado (ns)");
        System.out.println("---------------------------------------------------------------");
    }

    private static void imprimirResultados(int size, long tiempoSecuencial, long tiempoBinaria, long tiempoBinariaOrdenada, long tiempoSecuencialOrdenado) {
        System.out.printf("%-10d%-20d%-20d%-20d%-20d%n", size, tiempoSecuencial, tiempoBinaria, tiempoBinariaOrdenada, tiempoSecuencialOrdenado);
    }

    private static int[] generarArregloAleatorio(int size) {
        Random rand = new Random();
        return rand.ints(size).toArray();
    }

    private static int busquedaSecuencial(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1; // No encontrado
    }

    private static int busquedaBinaria(int[] array, int target) {
        int izquierda = 0, derecha = array.length - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            if (array[medio] == target) {
                return medio;
            } else if (array[medio] < target) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return -1; // No encontrado
    }

    private static long medirTiempo(Runnable accion) {
        long inicio = System.nanoTime();
        accion.run();
        return System.nanoTime() - inicio;
    }
}
