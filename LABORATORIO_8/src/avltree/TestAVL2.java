package avltree;

import java.util.Queue;
import java.util.LinkedList;

public class TestAVL2 {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();

        // Ejercicio 6: Inserciones para provocar rotaciones
        int[] datos = {30, 15, 20, 50, 40, 60, 70, 10, 25, 45, 55, 65, 75};
        for (int val : datos) avl.insert(val);

        System.out.println("\n Ejercicio 3: Recorrido por niveles (recursivo) ");
        avl.recorridoPorNivelesRecursivo();

        System.out.println("\n Ejercicio 4: Recorrido por niveles (BFS) ");
        avl.bfs();

        System.out.println("\n Ejercicio 5: Recorrido en Preorden ");
        avl.preOrder();

        // Ejercicio 6: Eliminaciones con rotaciones
        System.out.println("\n Ejercicio 6: Eliminaciones ");
        avl.delete(15);
        avl.delete(50);
        avl.delete(70);

        System.out.println("\narbol después de eliminar 15, 50 y 70:");
        avl.bfs();

        // Repetir recorridos para ver efectos
        System.out.println("\nPreorden final:");
        avl.preOrder();
        System.out.println("\nRecorrido por niveles (recursivo) final:");
        avl.recorridoPorNivelesRecursivo();
    }
}
