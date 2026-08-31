package todolist.repository;

import todolist.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) { tasks.add(task); }
    public void removeTask(Task task) { tasks.remove(task); }

    public List<Task> listTasks() {
        return tasks;
    }
}
