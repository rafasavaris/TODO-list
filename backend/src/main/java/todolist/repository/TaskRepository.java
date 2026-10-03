package todolist.repository;
import todolist.model.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();
    private Path FILE = Paths.get("tasks.txt");

    public void addTask(Task task) { tasks.add(task); }
    public void removeTask(Task task) { tasks.remove(task); }
    public List<Task> listTasks() { return tasks; }

    public TaskRepository() { loadTasks(); }

    public void saveTasks() {
        List<String> lines = new ArrayList<>();

        for (Task task : tasks) {
            String line = task.getName() + "|" +
                    task.getDesc() + "|" +
                    task.getDoneDate() + "|" +
                    task.getPriority() + "|" +
                    task.getCategory() + "|" +
                    task.getStatus();
            lines.add(line);
        }
        try {
            Files.write(FILE, lines);
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas: " + e.getMessage());
        }
    }

    private void loadTasks() {
        if (!Files.exists(FILE)) return;

        try {
            List<String> lines = Files.readAllLines(FILE);

            for (String line : lines) {
                String[] data = line.split("\\|");

                if (data.length != 6) continue;

                String name = data[0];
                String description = data[1];
                String dueDate = data[2];
                int priority = Integer.parseInt(data[3]);
                String category = data[4];
                String status = data[5];

                Task task = new Task(name, description, dueDate, priority, category, status);
                tasks.add(task);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao carregar tarefas: " + e.getMessage());
        }
    }
}