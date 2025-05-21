package Test;
import bstreelinklistinterfgeneric.LinkedBST;
import Exceptions.*;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;
import Exceptions.ExceptionIsEmpty;


public class Prueba {
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
            
            System.out.println("InOrder:");
            bst.recorrerInOrden();

            System.out.println("\nPreOrder:");
            bst.recorrerPreOrden();

            System.out.println("\nPostOrder:");
            bst.recorrerPostOrden();
            
            System.out.println(" ");
            bst.mostrarMinimoDesde(3);
            
            System.out.println(" ");
            bst.mostrarMinimoDesde(10);

            System.out.println(" ");
            bst.mostrarMaximoDesde(3);
            
            System.out.println(" ");
            bst.mostrarMaximoDesde(10);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }      
}