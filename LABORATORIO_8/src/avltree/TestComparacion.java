package avltree;

import java.util.Queue;
import java.util.LinkedList;

import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;

public class TestComparacion {
    public static void main(String[] args) {
        BSTree<Integer> bst = new BSTree<>();
        AVLTree<Integer> avl = new AVLTree<>();

        // Caso 1: Inserción ordenada
        int[] datosOrdenados = {10, 20, 30, 40, 50};

        System.out.println(" CASO 1: Datos ordenados ");

        for (int val : datosOrdenados) {
            try {
                bst.insert(val);         
                avl.insert(val);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("BST (BFS): ");
        bfsPrint(bst.root);

        System.out.print("AVL (BFS): ");
        try {
            avl.bfs();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Altura BST: " + height(bst.root));
        System.out.println("Altura AVL: " + height(avl.root));
        System.out.println();

        // Caso 2: Inserción aleatoria
        BSTree<Integer> bst2 = new BSTree<>();
        AVLTree<Integer> avl2 = new AVLTree<>();
        int[] datosRandom = {25, 10, 40, 30, 5, 35};

        System.out.println(" CASO 2: Datos aleatorios ");

        for (int val : datosRandom) {
            try {
                bst2.insert(val);        
                avl2.insert(val);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("BST (BFS): ");
        bfsPrint(bst2.root);

        System.out.print("AVL (BFS): ");
        try {
            avl2.bfs();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Altura BST: " + height(bst2.root));
        System.out.println("Altura AVL: " + height(avl2.root));
    }

    // Método para calcular la altura de un árbol
    public static <E> int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // Método auxiliar de recorrido BFS para BST
    public static <E> void bfsPrint(Node<E> root) {
        if (root == null) {
            System.out.println("(vacío)");
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<E> current = queue.poll();
            System.out.print(current.data + " ");
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        System.out.println();
    }
}

