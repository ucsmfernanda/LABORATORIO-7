package Exceptions;

public class ItemDuplicated extends Exception {
    public ItemDuplicated(String mensaje) {
        super(mensaje);
    }

    public ItemDuplicated() {
        super("El ítem ya existe en la estructura.");
    }
}
