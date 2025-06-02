package Exceptions;

public class ItemNoFound extends Exception {
    public ItemNoFound(String mensaje) {
        super(mensaje);
    }

    public ItemNoFound() {
        super("El ítem no se encontró en la estructura.");
    }
}