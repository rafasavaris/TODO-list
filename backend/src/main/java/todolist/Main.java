package todolist;

import todolist.repository.TaskRepository;
import todolist.service.TaskService;
import todolist.ui.Menu;

public class Main {
    public static void main(String[] args) {

        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);
        Menu menu = new Menu(service);

        menu.start();
    }
}