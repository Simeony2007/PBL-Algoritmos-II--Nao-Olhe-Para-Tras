package src.model.customExceptions;

public class SaveNotFoundException extends Exception {

    public SaveNotFoundException(int slot) {
        super("Save do slot " + slot + " nunca criado ou já deletado!");
    }
}
