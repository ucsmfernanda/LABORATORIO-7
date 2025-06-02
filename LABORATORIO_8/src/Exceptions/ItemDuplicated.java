package Exceptions;

public class ItemDuplicated extends Exception {
    public ItemDuplicated() {
        super("El ítem ya existe en la estructura.");
    }
}