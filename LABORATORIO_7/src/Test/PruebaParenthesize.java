package Test;

import bstreelinklistinterfgeneric.LinkedBST;
import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;

public class PruebaParenthesize {
    public static void main(String[] args) {
        LinkedBST<Integer> bst = new LinkedBST<>();

        try {
            bst.insert(8);
            bst.insert(3);
            bst.insert(10);
            bst.insert(1);
            bst.insert(6);
            bst.insert(14);
            bst.insert(4);
            bst.insert(7);
            bst.insert(13);

            System.out.println("\nVisualización del árbol con parenthesize:");
            bst.parenthesize();

        } catch (ItemDuplicated e) {
            System.out.println("Error: Valor duplicado -> " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Error inesperado -> " + e.getMessage());
        }
    }
}
