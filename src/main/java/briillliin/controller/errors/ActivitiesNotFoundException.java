package briillliin.controller.errors;

public class ActivitiesNotFoundException extends RuntimeException {
    public ActivitiesNotFoundException(Long id) {
        super("Занятие с id " + id + " не найдено.");
    }
}
