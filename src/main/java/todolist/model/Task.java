package todolist.model;

public class Task {
    // atributos
    private String name;
    private String desc;
    private String dueDate;
    private int priority;
    private String category;
    private String status;

    // construtor
    public Task(String name, String desc, String dueDate,int priority, String category, String status) {
        this.name = name;
        this.desc = desc;
        this.dueDate = dueDate;
        this.priority = priority;
        this.category = category;
        this.status = status;
    }

    // getters
    public String getName() { return this.name; }
    public String getDesc() { return this.desc; }
    public String getDoneDate() { return this.dueDate; }
    public int getPriority() { return this.priority; }
    public String getCategory() { return this.category; }
    public String getStatus() { return this.status; }

    // setters
    public void setName(String name) { this.name = name; }
    public void setDesc(String desc) { this.desc = desc; }
    public void setDoneDate(String dueDate) { this.dueDate = dueDate; }
    public void setPriority(int priority) { this.priority = priority; }
    public void setCategory(String category) { this.category = category; }
    public void setStatus(String status) { this.status = status; }
}
