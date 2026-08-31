package todolist.ui;

import todolist.model.Task;
import todolist.service.TaskService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private TaskService service;
    private Scanner scanner;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Menu(TaskService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    // primeiro menu
    public void start() {
        int option;
        do {
            showMainMenu();
            option = readInt("Escolha uma opção: ");

            switch (option) {
                case 1:
                    createTask();
                    break;
                case 2:
                    listTasks();
                    break;
                case 3:
                    updateTask();
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

    // mostra o menu principal
    private void showMainMenu() {
        System.out.println("\n* ********** TO-DO LIST ********** *");
        System.out.println("* 1. Cadastrar tarefa              *");
        System.out.println("* 2. Listar tarefas                *");
        System.out.println("* 3. Alterar tarefa                *");
        System.out.println("* 4. Remover tarefa                *");
        System.out.println("* 0. Sair                          *");
        System.out.println("* ******************************** *");
    }

    // cria uma task
    private void createTask() {
        System.out.println("\n* ****** CADASTRAR TAREFA ****** *");

        String name = readRequiredString("Nome: ");
        String description = readRequiredString("Descrição: ");
        String dueDate = readDate();
        int priority = readPriority();
        String category = showCategoryMenu();
        String status = readStatus();

        Task task = new Task(name, description, dueDate, priority, category, status);
        service.addTask(task);
        System.out.println("\nTarefa cadastrada com sucesso!");
    }

    // lista as tasks de acordo com a opcao
    private void listTasks() {
        int option;

        List<Task> tasks = service.list();

        if (tasks.isEmpty()) {
            System.out.println("\nNenhuma tarefa cadastrada.");
            return;
        }

        do {
            showListMenu();
            option = readInt("Escolha uma opção: ");

            switch (option) {
                case 1:
                    listAllTasks();
                    break;
                case 2:
                    listPriorityTasks();
                    break;
                case 3:
                    listCategoryTasks();
                    break;
                case 4:
                    listStatusTasks();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (option != 0);
    }

    // exibe o menu de como quer listar as tasks
    private void showListMenu() {
        System.out.println("\n* ******** LISTAR TAREFAS ******** *");
        System.out.println("* 1. Listar todas                  *");
        System.out.println("* 2. Listar por prioridade         *");
        System.out.println("* 3. Listar por categoria          *");
        System.out.println("* 4. Listar por status             *");
        System.out.println("* 0. Voltar                        *");
        System.out.println("* ******************************** *");
    }

    // lista todas as tasks
    private void listAllTasks() {
        displayTasks(service.list());
    }

    // obtem a prioridade e chama funçao para listar as tasks de acordo com a prioridade
    private void listPriorityTasks() {
        int priority = readPriority();

        displayTasks(service.listPriority(priority));
    }

    // obtem a categoria e chama funçao para listar as tasks de acordo com a categoria
    private void listCategoryTasks() {
        String category = showCategoryMenu();

        displayTasks(service.listCategory(category));
    }

    // obtem o status e chama funçao para listar as tasks de acordo com o status
    private void listStatusTasks() {
        String status = readStatus();

        displayTasks(service.listStatus(status));
    }

    // mostra as tasks separadas
    private void displayTasks(List<Task> tasks) {

        if (tasks.isEmpty()) {
            System.out.println("\nNenhuma tarefa encontrada.");
            return;
        }

        System.out.println("\n* ********** TAREFAS *********** *");

        for (int i = 0; i < tasks.size(); i++) { displayTask(tasks.get(i), i + 1); }
    }

    // mostra uma task especifica
    private void displayTask(Task task, int number) {
        System.out.println("\n" + number + ". " + task.getName());
        System.out.println("Descrição: " + task.getDesc());
        System.out.println("Data de término: " + task.getDoneDate());
        System.out.println("Prioridade: " + task.getPriority());
        System.out.println("Categoria: " + task.getCategory());
        System.out.println("Status: " + task.getStatus());
    }

    // remove uma task
    private void removeTask() {
        Task task = selectTask();

        if (task == null) return;

        service.removeTask(task);
        System.out.println("Tarefa removida com sucesso!");
    }

    // chama uma funcao especifica para atualizar uma task
    private void updateTask() {
        Task task = selectTask();

        if (task == null) return;

        int option;
        do {
            showUpdateMenu();
            option = readInt("Escolha uma opção: ");

            switch (option) {
                case 1:
                    updateName(task);
                    break;
                case 2:
                    updateDescription(task);
                    break;
                case 3:
                    updateDueDate(task);
                    break;
                case 4:
                    updatePriority(task);
                    break;
                case 5:
                    updateCategory(task);
                    break;
                case 6:
                    updateStatus(task);
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (option != 0);
    }

    // mostra o menu de alteraçao
    private void showUpdateMenu() {
        System.out.println("\n* ******** ALTERAR TAREFA ******** *");
        System.out.println("* 1. Alterar nome                  *");
        System.out.println("* 2. Alterar descrição             *");
        System.out.println("* 3. Alterar data de término       *");
        System.out.println("* 4. Alterar prioridade            *");
        System.out.println("* 5. Alterar categoria             *");
        System.out.println("* 6. Alterar status                *");
        System.out.println("* 0. Voltar                        *");
        System.out.println("* ******************************** *");
    }

    // a partir daqui, funçoes que alteram algo de uma tarefa ja cadastrada
    private void updateName(Task task) {
        String name = readRequiredString("Novo nome: ");
        task.setName(name);

        System.out.println("Nome alterado com sucesso!");
    }

    private void updateDescription(Task task) {
        String description = readRequiredString("Nova descrição: ");
        task.setDesc(description);

        System.out.println("Descrição alterada com sucesso!");
    }

    private void updateDueDate(Task task) {
        String dueDate = readDate();
        task.setDoneDate(dueDate);

        System.out.println("Data de término alterada com sucesso!");
    }

    private void updatePriority(Task task) {
        int priority = readPriority();
        task.setPriority(priority);

        System.out.println("Prioridade alterada com sucesso!");
    }

    private void updateCategory(Task task) {
        String category = showCategoryMenu();
        task.setCategory(category);

        System.out.println("Categoria alterada com sucesso!");
    }

    private void updateStatus(Task task) {
        String status = readStatus();
        service.updateStatus(task, status);

        System.out.println("Status alterado com sucesso!");
    }

    // mostra todas as tasks + pede para selecionar uma delas
    private Task selectTask() {
        List<Task> tasks = service.list();

        if (tasks.isEmpty()) {
            System.out.println("\nNenhuma tarefa cadastrada.");
            return null;
        }

        System.out.println("\n* ********** TAREFAS *********** *");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getName());
        }

        int option = readInt("\nDigite o número da tarefa: ");

        if (option < 1 || option > tasks.size()) {
            System.out.println("Tarefa inválida!");
            return null;
        }

        return tasks.get(option - 1);
    }

    // funçoes para ler uma string/inteiro/prioridade/data de acordo com os padroes estabelecidos
    private String readRequiredString(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) return value;

            System.out.println("Erro: este campo não pode ser vazio.");
        }
    }

    private int readInt(String message) {
        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Erro: digite um número válido.");
            }
        }
    }

    private String readDate() {
        while (true) {
            System.out.print("Data de término (dd/mm/yyyy): ");
            String dueDate = scanner.nextLine().trim();

            try {
                LocalDate date = LocalDate.parse(dueDate, formatter);
                if (date.isBefore(LocalDate.now())) {
                    System.out.println("Erro: a data de término não pode ser anterior à data atual.");
                    continue;
                }
                return dueDate;
            } catch (DateTimeParseException e) {
                System.out.println("Erro: data inválida. Use o formato dd/mm/yyyy.");
            }
        }
    }

    private int readPriority() {
        while (true) {
            int priority = readInt("Prioridade (1-5): ");

            if (priority >= 1 && priority <= 5) return priority;

            System.out.println("Erro: a prioridade deve estar entre 1 e 5.");
        }
    }

    private String readStatus() {
        while (true) {
            System.out.print("Status (TODO/DOING/DONE): ");

            String status = scanner.nextLine().trim().toUpperCase();

            if (status.equals("TODO") || status.equals("DOING") || status.equals("DONE")) return status;

            System.out.println("Erro: status inválido. " + "Use TODO, DOING ou DONE.");
        }
    }

    // mostra o menu de cadastro de categorias
    private String showCategoryMenu() {
        while (true) {
            System.out.println("Categorias:");
            System.out.println("* 1. Estudos");
            System.out.println("* 2. Trabalho");
            System.out.println("* 3. Pessoal");
            System.out.println("* 4. Compras");
            System.out.println("* 5. Outros");

            int option = readInt("Escolha uma categoria: ");

            switch (option) {
                case 1:
                    return "Estudos";
                case 2:
                    return "Trabalho";
                case 3:
                    return "Pessoal";
                case 4:
                    return "Compras";
                case 5:
                    return "Outros";
                default:
                    System.out.println("Erro: opção inválida.");
            }
        }
    }
}