package avltree;

import java.util.Queue;
import java.util.LinkedList;

public class TestAVL {
    public static void main(String[] args) {
        BSTree<Integer> bst = new BSTree<>();
        AVLTree<Integer> avl = new AVLTree<>();

        int[] datos = {10, 20, 30, 40, 50};

        System.out.println("BST vs AVL con datos ordenados ");

        for (int val : datos) {
            bst.insert(val);
            avl.insert(val);
        }

        System.out.print("BST (BFS): ");
        bfsPrint(bst.root);

        System.out.print("AVL (BFS): ");
        avl.bfs();

        // Prueba de eliminación
        System.out.println("\nEliminando 20 y 40 del AVL...");
        avl.delete(20);
        avl.delete(40);
        System.out.print("AVL después de eliminar (BFS): ");
        avl.bfs();
    }

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
