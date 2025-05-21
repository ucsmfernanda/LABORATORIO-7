package Test;
import bstreelinklistinterfgeneric.LinkedBST;
import Exceptions.*;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;
import Exceptions.ExceptionIsEmpty;

public class PruebaNew {
    public static void main(String[] args) {
        LinkedBST<Integer> bst = new LinkedBST<>();

        try {
            // Insertar elementos
            bst.insert(8);
            bst.insert(3);
            bst.insert(10);
            bst.insert(1);
            bst.insert(6);
            bst.insert(14);
            bst.insert(4);
            bst.insert(7);
            bst.insert(13); 

            // Recorridos
            System.out.println(" InOrder:");
            bst.recorrerInOrden();

            System.out.println("\n PreOrder:");
            bst.recorrerPreOrden();

            System.out.println("\n PostOrder:");
            bst.recorrerPostOrden();

            // Cantidad de nodos
            System.out.println("\n Total de nodos:");
            System.out.println(bst.countNodes());

            System.out.println("\n Total de nodos NO hoja:");
            System.out.println(bst.countAllNodes());

            // Altura desde un nodo
            System.out.println("\n Altura del subárbol con raiz 3:");
            System.out.println(bst.height(3));

            System.out.println("\n Altura del subárbol con raiz 10:");
            System.out.println(bst.height(10));

            // Amplitud 
            System.out.println("\n Amplitud máxima del arbol:");
            System.out.println(bst.amplitude());

            // Area del árbol
            System.out.println("\n Área aproximada del arbol:");
            System.out.println(bst.areaBST());

            // Mostrar el arbol
            System.out.println("\n Dibujar el arbol:");
            bst.drawBST();

            // Destruir e
            System.out.println("\n Se destruyo el arbol ");
            bst.destroyNodes();

            System.out.println(" arbol destruido correctamente.");

            // Intentar contar nodos luego de destruirlo
            System.out.println("\n Intentar contar nodos despues de destruir:");
            System.out.println("Cantidad de nodos: " + bst.countNodes());

        } catch (ExceptionIsEmpty e) {
            System.out.println(" Arbol vacio: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}
