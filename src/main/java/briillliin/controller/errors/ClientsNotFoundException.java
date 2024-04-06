package briillliin.controller.errors;


public class ClientsNotFoundException extends RuntimeException {
    public ClientsNotFoundException(Long id) {
        super("Клиент с id " + id + " не найден.");
    }
}
