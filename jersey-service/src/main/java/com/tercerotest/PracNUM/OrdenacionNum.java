package com.tercerotest.PracNUM;

import java.util.Random;
import com.tercerotest.controller.tda.list.LinkedList;
public class OrdenacionNum {
    public static void main(String[] args) throws Exception {
        // Arreglo de tamaños
        int[] sizes = { 10000, 20000, 25000 };

        for (int size : sizes) {
            // Crear lista aleatoria
            LinkedList<Integer> list = generateRandomList(size);

            // Medir el tiempo de QuickSort
            long startTime = System.nanoTime();
            list.QuikShor(0); // Asumiendo que 0 es el tipo de orden
            long endTime = System.nanoTime();
            long quickSortTime = endTime - startTime;

            // Crear nueva lista para MergeSort
            list = generateRandomList(size);
            startTime = System.nanoTime();
            list.mergesort(0); // Asumiendo que 0 es el tipo de orden
            endTime = System.nanoTime();
            long mergeSortTime = endTime - startTime;

            // Crear nueva lista para ShellSort
            list = generateRandomList(size);
            startTime = System.nanoTime();
            list.shellSort("atributo", 1);// Usando el orderType que es 1 o -1, dependiendo de si es ascendente o descendente
            endTime = System.nanoTime();
            long shellSortTime = endTime - startTime;


            // Mostrar los tiempos
            System.out.println("Tamaño: " + size);
            System.out.println("QuickSort: " + quickSortTime + " ns");
            System.out.println("MergeSort: " + mergeSortTime + " ns");
            System.out.println("ShellSort: " + shellSortTime + " ns");
            System.out.println();
        }
    }

    // Método para generar una lista aleatoria de números enteros
    private static LinkedList<Integer> generateRandomList(int size) {
        LinkedList<Integer> list = new LinkedList<>();
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            list.add(rand.nextInt());
        }
        return list;
    }
}
