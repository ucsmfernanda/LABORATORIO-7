package avltree;

public class BSTree<E extends Comparable<E>> {
    protected Node<E> root;

    public BSTree() {
        root = null;
    }

    public void insert(E value) {
        root = insert(root, value);
    }

    private Node<E> insert(Node<E> node, E value) {
        if (node == null) return new Node<>(value);

        int cmp = value.compareTo(node.data);
        if (cmp < 0) node.left = insert(node.left, value);
        else if (cmp > 0) node.right = insert(node.right, value);
        return node;
    }
}