package todolist.service;

import todolist.repository.TaskRepository;
import todolist.model.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private TaskRepository repository;

    public TaskService(TaskRepository repository) { this.repository = repository; }

    public void addTask(Task task) {
        repository.addTask(task);
        reorderTasks();
    }

    public void removeTask(Task task) { repository.removeTask(task); }
    public List<Task> listTasks() { return repository.listTasks(); }
    public List<Task> list() { return repository.listTasks(); }

    public List<Task> listCategory(String cat) {
        List<Task> tasks = new ArrayList<>();

        for (Task task : repository.listTasks()) {
            if (task.getCategory().equalsIgnoreCase(cat)) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public List<Task> listPriority(int prior) {
        List<Task> tasks = new ArrayList<>();

        for (Task task : repository.listTasks()) {
            if (task.getPriority() == prior) { tasks.add(task); }
        }
        return tasks;
    }

    public List<Task> listStatus(String status) {
        List<Task> tasks = new ArrayList<>();

        for (Task task : repository.listTasks()) {
            if (task.getStatus().equalsIgnoreCase(status)) { tasks.add(task); }
        }
        return tasks;
    }

    public void updateStatus(Task task, String status) {
        task.setStatus(status);
        reorderTasks();
    }

    private void reorderTasks() {
        List<Task> tasks = repository.listTasks();
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < tasks.size() - 1; i++) {
            for (int j = i + 1; j < tasks.size(); j++) {
                Task task1 = tasks.get(i);
                Task task2 = tasks.get(j);

                boolean done1 =  task1.getStatus().equalsIgnoreCase("DONE");
                boolean done2 =  task2.getStatus().equalsIgnoreCase("DONE");
                boolean shouldSwap = false;

                if (done1 && !done2) { shouldSwap = true; }

                else if (done1 && done2) {
                    LocalDate date1 = LocalDate.parse(task1.getDoneDate(), formatter);
                    LocalDate date2 = LocalDate.parse(task2.getDoneDate(), formatter);

                    if (date1.isAfter(date2)) { shouldSwap = true; }
                }

                else if (!done1 && !done2) {
                    if (task1.getPriority() > task2.getPriority()) { shouldSwap = true; }
                }

                if (shouldSwap) {
                    tasks.set(i, task2);
                    tasks.set(j, task1);
                }
            }
        }
    }

    public void saveTasks() { repository.saveTasks(); }

    public int countStatus(String status) {
        int count = 0;

        for (Task task : repository.listTasks()) {
            if (task.getStatus().equalsIgnoreCase(status)) count++;
        }
        return count;
    }
}