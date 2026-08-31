package todolist.repository;

import todolist.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    // atributo
    private List<Task> tasks = new ArrayList<>(); // ArrayList para guardar tasks

    public void addTask(Task task) { tasks.add(task); }
    public void removeTask(Task task) { tasks.remove(task); }
    public List<Task> listTasks() { return tasks; }
}
