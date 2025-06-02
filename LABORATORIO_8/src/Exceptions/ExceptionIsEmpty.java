package Exceptions;

// Clase para lanzar una excepción cuando la estructura está vacía
public class ExceptionIsEmpty extends Exception {
    public ExceptionIsEmpty(String mensaje) {
        super(mensaje);
    }

    public ExceptionIsEmpty() {
        super("El arbol esta vacio.");
    }
}