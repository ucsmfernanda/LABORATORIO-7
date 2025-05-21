package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;
import Exceptions.ExceptionIsEmpty;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    class Node {
        public E data;
        public Node left;
        public Node right;

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    public LinkedBST() {
        this.root = null;
    }

    @Override
    public void insert(E data) throws ItemDuplicated {
        if (root == null) {
            root = new Node(data);
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            int cmp = data.compareTo(current.data);
            if (cmp == 0) {
                throw new ItemDuplicated("El dato '" + data + "' ya existe en el árbol.");
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        int cmp = data.compareTo(parent.data);
        if (cmp < 0) {
            parent.left = new Node(data);
        } else {
            parent.right = new Node(data);
        }
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        if (root == null) {
            throw new ExceptionIsEmpty("No se puede eliminar. El árbol está vacío.");
        }

        Node current = root;
        Node parent = null;

        while (current != null && !current.data.equals(data)) {
            parent = current;
            if (data.compareTo(current.data) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) return; // No se encontró, no hace nada

        // Caso 1: sin hijos
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (parent.left == current) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }

        // Caso 2: un hijo
        else if (current.left == null || current.right == null) {
            Node child = (current.left != null) ? current.left : current.right;
            if (current == root) {
                root = child;
            } else if (parent.left == current) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }

        // Caso 3: dos hijos
        else {
            Node successorParent = current;
            Node successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.data = successor.data;

            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }
        }
    }

    @Override
    public E search(E data) throws ItemNoFound {
        Node current = root;

        while (current != null) {
            int cmp = data.compareTo(current.data);
            if (cmp == 0) {
                return current.data;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        throw new ItemNoFound("El dato '" + data + "' no se encontró en el árbol.");
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            sb.append(current.data).append(" ");
            current = current.right;
        }

        return sb.toString().trim();
    }


    private void inOrder() {
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            System.out.print(current.data + " ");
            current = current.right;
        }
    }

    private void preOrder() {
        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.data + " ");

            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
    }

    private void postOrder() {
        if (root == null) return;

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {
            Node current = stack1.pop();
            stack2.push(current);

            if (current.left != null) stack1.push(current.left);
            if (current.right != null) stack1.push(current.right);
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + " ");
        }
    }

    // Métodos públicos para invocar
    public void recorrerInOrden() {
        inOrder();
    }

    public void recorrerPreOrden() {
        preOrder();
    }

    public void recorrerPostOrden() {
        postOrder();
    }

    private E findMinNode(E fromData) throws ItemNoFound {
        Node current = root;

        search(fromData); // Validar que exista

        while (current != null) {
            int cmp = fromData.compareTo(current.data);
            if (cmp == 0) break;
            current = (cmp < 0) ? current.left : current.right;
        }

        if (current == null) {
            throw new ItemNoFound("No se encontró el nodo raíz desde donde buscar el mínimo.");
        }

        while (current.left != null) {
            current = current.left;
        }

        return current.data;
    }

    private E findMaxNode(E fromData) throws ItemNoFound {
        Node current = root;

        search(fromData);

        while (current != null) {
            int cmp = fromData.compareTo(current.data);
            if (cmp == 0) break;
            current = (cmp < 0) ? current.left : current.right;
        }

        if (current == null) {
            throw new ItemNoFound("No se encontró el nodo raíz desde donde buscar el máximo.");
        }

        while (current.right != null) {
            current = current.right;
        }

        return current.data;
    }

    public void mostrarMinimoDesde(E fromData) {
        try {
            E min = findMinNode(fromData);
            System.out.println("Mínimo desde " + fromData + " es: " + min);
        } catch (ItemNoFound e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMaximoDesde(E fromData) {
        try {
            E max = findMaxNode(fromData);
            System.out.println("Máximo desde " + fromData + " es: " + max);
        } catch (ItemNoFound e) {
            System.out.println(e.getMessage());
        }
    }


    public void destroyNodes() throws ExceptionIsEmpty {
        if (root == null) throw new ExceptionIsEmpty("El arbol ya esta vacio.");
        root = null; 
    }

    public int countNodes() {
        if (root == null) return 0;

        int count = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            count++;
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }

        return count;
    }

    public int countAllNodes() {
        if (root == null) return 0;

        int count = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // nodo no hoja si tiene al menos un hijo
            if (current.left != null || current.right != null) {
                count++;
            }

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }

        return count;
    }

    public int height(E x) {
        if (root == null) return -1;

        // Primero buscamos el nodo con dato x
        Node current = root;
        while (current != null) {
            int cmp = x.compareTo(current.data);
            if (cmp == 0) break;
            current = (cmp < 0) ? current.left : current.right;
        }

        if (current == null) return -1; // no existe el subárbol

        Queue<Node> queue = new LinkedList<>();
        queue.add(current);
        int height = -1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }

        return height;
    }

    public int amplitude() {
        if (root == null) return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            if (levelSize > maxWidth) maxWidth = levelSize;

            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }

        return maxWidth;
    }

    public int areaBST() {
        if (root == null) return 0;

        int height = this.height(root.data); // altura del árbol completo
        if (height < 0) return 0;

        int leafCount = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.left == null && current.right == null) {
                leafCount++;
            } else {
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }

        return leafCount * height;
    }

    public void drawBST() {
        System.out.println("Representación in-order del árbol:");
        System.out.println(this.toString());

        
    }
    
    public void parenthesize() {
        parenthesize(this.root, 0);
    }

    private void parenthesize(Node node, int level) {
        if (node == null) return;

        for (int i = 0; i < level; i++) {
            System.out.print("  "); // dos espacios por nivel
        }

        // imprime el valor del nodo
        System.out.println(node.data);

        // si tiene hijos para abrir parentesis
        if (node.left != null || node.right != null) {
            if (node.left != null) {
                for (int i = 0; i < level + 1; i++) {
                    System.out.print("  ");
                }
                System.out.print("(");
                System.out.println();
                parenthesize(node.left, level + 2);
                for (int i = 0; i < level + 1; i++) {
                    System.out.print("  ");
                }
                System.out.println(")");
            }

            if (node.right != null) {
                for (int i = 0; i < level + 1; i++) {
                    System.out.print("  ");
                }
                System.out.print("(");
                System.out.println();
                parenthesize(node.right, level + 2);
                for (int i = 0; i < level + 1; i++) {
                    System.out.print("  ");
                }
                System.out.println(")");
            }
        }
    }


}
