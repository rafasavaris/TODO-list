package todolist.service;

import todolist.repository.TaskRepository;
import todolist.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void addTask(Task task) {
        repository.addTask(task);
    }

    public void removeTask(Task task) {
        repository.removeTask(task);
    }

    public List<Task> listTasks() {
        return repository.listTasks();
    }

    public List<Task> listCategory(String cat) {
        List<Task> tasks = new ArrayList<>();

        for(Task task : repository.listTasks()) {
            if(task.getCategory().equalsIgnoreCase(cat)) tasks.add(task);
        }

        return tasks;
    }

    public List<Task> listPriority(int prior) {
        List<Task> tasks = new ArrayList<>();

        for(Task task : repository.listTasks()) {
            if(task.getPriority() == prior) tasks.add(task);
        }

        return tasks;
    }

    public List<Task> listStatus(String status) {
        List<Task> tasks = new ArrayList<>();

        for(Task task : repository.listTasks()) {
            if(task.getCategory().equalsIgnoreCase(status)) tasks.add(task);
        }

        return tasks;
    }

    public void altStatus(Task task, String newStatus) {
        task.setStatus(newStatus);
    }

    public List<Task> list() { return repository.listTasks(); }
}