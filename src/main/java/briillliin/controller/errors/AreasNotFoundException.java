package briillliin.controller.errors;


public class AreasNotFoundException extends RuntimeException {
    public AreasNotFoundException(Long id) {
        super("Зал с id " + id + " не найден.");
    }
}
