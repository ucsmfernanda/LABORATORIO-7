package avltree;

import Exceptions.ItemDuplicated;
import java.util.LinkedList;
import java.util.Queue;

public class AVLTree<E extends Comparable<E>> extends BSTree<E> {

    public void insert(E value) {
        try {
            root = insert((NodeAVL<E>) root, value);
        } catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }
    }

    private NodeAVL<E> insert(NodeAVL<E> node, E value) throws ItemDuplicated {
        if (node == null) return new NodeAVL<>(value);

        int cmp = value.compareTo(node.data);
        if (cmp < 0) {
            node.left = insert((NodeAVL<E>) node.left, value);
            node.bf--;
            if (node.bf == -2) {
                if (((NodeAVL<E>) node.left).bf <= 0) return rotateSR(node);
                else return rotateDR(node);
            }
        } else if (cmp > 0) {
            node.right = insert((NodeAVL<E>) node.right, value);
            node.bf++;
            if (node.bf == 2) {
                if (((NodeAVL<E>) node.right).bf >= 0) return rotateSL(node);
                else return rotateDL(node);
            }
        } else {
            throw new ItemDuplicated();
        }

        return node;
    }

    public void delete(E value) {
        root = delete((NodeAVL<E>) root, value);
    }

    private NodeAVL<E> delete(NodeAVL<E> node, E value) {
        if (node == null) return null;

        int cmp = value.compareTo(node.data);

        if (cmp < 0) {
            node.left = delete((NodeAVL<E>) node.left, value);
            node.bf++;
        } else if (cmp > 0) {
            node.right = delete((NodeAVL<E>) node.right, value);
            node.bf--;
        } else {
            if (node.left == null) return (NodeAVL<E>) node.right;
            if (node.right == null) return (NodeAVL<E>) node.left;

            NodeAVL<E> min = findMin((NodeAVL<E>) node.right);
            node.data = min.data;
            node.right = delete((NodeAVL<E>) node.right, min.data);
            node.bf--;
        }

        if (node.bf == -2) {
            if (getBF((NodeAVL<E>) node.left) <= 0) return rotateSR(node);
            else return rotateDR(node);
        } else if (node.bf == 2) {
            if (getBF((NodeAVL<E>) node.right) >= 0) return rotateSL(node);
            else return rotateDL(node);
        }

        return node;
    }

    private int getBF(NodeAVL<E> node) {
        return (node == null) ? 0 : node.bf;
    }

    private NodeAVL<E> findMin(NodeAVL<E> node) {
        while (node.left != null) node = (NodeAVL<E>) node.left;
        return node;
    }

    // Rotaciones
    private NodeAVL<E> rotateSR(NodeAVL<E> b) {
        NodeAVL<E> a = (NodeAVL<E>) b.left;
        b.left = a.right;
        a.right = b;
        b.bf = 0;
        a.bf = 0;
        return a;
    }

    private NodeAVL<E> rotateSL(NodeAVL<E> a) {
        NodeAVL<E> b = (NodeAVL<E>) a.right;
        a.right = b.left;
        b.left = a;
        a.bf = 0;
        b.bf = 0;
        return b;
    }

    private NodeAVL<E> rotateDR(NodeAVL<E> c) {
        c.left = rotateSL((NodeAVL<E>) c.left);
        return rotateSR(c);
    }

    private NodeAVL<E> rotateDL(NodeAVL<E> c) {
        c.right = rotateSR((NodeAVL<E>) c.right);
        return rotateSL(c);
    }

    // Recorrido BFS (Ejercicio 4)
    public void bfs() {
        if (root == null) {
            System.out.println("El árbol está vacío.");
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

    // Recorrido por niveles recursivo (Ejercicio 3)
    public void recorridoPorNivelesRecursivo() {
        int h = altura((NodeAVL<E>) root);
        System.out.print("Recorrido por niveles (recursivo): ");
        for (int i = 1; i <= h; i++) {
            mostrarNivel((NodeAVL<E>) root, i);
        }
        System.out.println();
    }

    private void mostrarNivel(NodeAVL<E> node, int nivel) {
        if (node == null) return;
        if (nivel == 1) {
            System.out.print(node.data + " ");
        } else {
            mostrarNivel((NodeAVL<E>) node.left, nivel - 1);
            mostrarNivel((NodeAVL<E>) node.right, nivel - 1);
        }
    }

    private int altura(NodeAVL<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(altura((NodeAVL<E>) node.left), altura((NodeAVL<E>) node.right));
    }

    // Recorrido en preorden (Ejercicio 5)
    public void preOrder() {
        System.out.print("Preorden: ");
        preOrder((NodeAVL<E>) root);
        System.out.println();
    }

    private void preOrder(NodeAVL<E> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder((NodeAVL<E>) node.left);
            preOrder((NodeAVL<E>) node.right);
        }
    }
}

