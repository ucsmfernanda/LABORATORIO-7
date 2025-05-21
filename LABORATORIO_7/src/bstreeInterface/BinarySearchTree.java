package bstreeInterface;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;
import Exceptions.ExceptionIsEmpty;

public interface BinarySearchTree<E extends Comparable<E>> {
	void insert(E data) throws Exceptions.ItemDuplicated;
	E search(E data) throws Exceptions.ItemNoFound;
	void delete(E data) throws Exceptions.ExceptionIsEmpty;
    boolean isEmpty();
}
