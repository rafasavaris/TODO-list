package todolist.ui;

import todolist.model.Task;
import todolist.service.TaskService;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private TaskService service;
    private Scanner scanner;

    public Menu(TaskService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int option;

        do {
            showMainMenu();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createTask();
                    break;

                case 2:
                    listTasks();
                    break;

                case 3:
                    System.out.println("Alterar status");
                    break;

                case 4:
                    removeTask();
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (option != 0);

        scanner.close();
    }

    private void listTasks() {
        int option;

        do {
            showListMenu();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Listando todas as tarefas...");
                    listAllTasks();
                    break;

                case 2:
                    System.out.println("Listando por prioridade...");
                    break;

                case 3:
                    System.out.println("Listando por categoria...");
                    break;

                case 4:
                    System.out.println("Listando por status...");
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (option != 0);
    }

    private void showMainMenu() {
        System.out.println("\n========== TODO LIST ==========");
        System.out.println("1. Cadastrar tarefa");
        System.out.println("2. Listar tarefas");
        System.out.println("3. Alterar tarefa");
        System.out.println("4. Remover tarefa");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void showListMenu() {
        System.out.println("\n========== LISTAR TAREFAS ==========");
        System.out.println("1. Listar todas");
        System.out.println("2. Listar por prioridade");
        System.out.println("3. Listar por categoria");
        System.out.println("4. Listar por status");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
    }

    private void createTask() {
        System.out.println("\n========== CADASTRAR TAREFA ==========");

        System.out.print("Nome: ");
        String name = scanner.nextLine();

        System.out.print("Descrição: ");
        String description = scanner.nextLine();

        System.out.print("Data de término: ");
        String dueDate = scanner.nextLine();

        System.out.print("Prioridade (1-5): ");
        int priority = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Categoria: ");
        String category = scanner.nextLine();

        System.out.print("Status (TODO/DOING/DONE): ");
        String status = scanner.nextLine();

        Task task = new Task(
                name,
                description,
                dueDate,
                priority,
                category,
                status
        );

        service.addTask(task);

        System.out.println("Tarefa cadastrada com sucesso!");
    }

    private void listAllTasks() {
        List<Task> tasks = service.list();

        if (tasks.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }

        System.out.println("\n========== TAREFAS ==========");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            System.out.println("\n" + (i + 1) + ". " + task.getName());
            System.out.println("Descrição: " + task.getDesc());
            System.out.println("Data de término: " + task.getDoneDate());
            System.out.println("Prioridade: " + task.getPriority());
            System.out.println("Categoria: " + task.getCategory());
            System.out.println("Status: " + task.getStatus());
        }
    }

    private void removeTask() {
        List<Task> tasks = service.list();

        if (tasks.isEmpty()) {
            System.out.println("\nNenhuma tarefa cadastrada.");
            return;
        }

        System.out.println("\n========== REMOVER TAREFA ==========");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getName());
        }

        System.out.print("\nDigite o número da tarefa que deseja remover: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        if (option < 1 || option > tasks.size()) {
            System.out.println("Tarefa inválida!");
            return;
        }

        Task task = tasks.get(option - 1);

        service.removeTask(task);

        System.out.println("Tarefa removida com sucesso!");
    }
}