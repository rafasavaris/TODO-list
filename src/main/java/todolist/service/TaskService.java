package todolist.service;

import todolist.repository.TaskRepository;

public class TaskService {

    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

}