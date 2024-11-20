import java.util.Objects;

public class Task {
    private final int id;            // Уникальный идентификатор задачи
    private final String title;       // Название задачи
    private final String description;// Описание задачи
    private TaskStatus status;       // Статус задачи

    public Task(int id, String title, String description, TaskStatus status) {
        this.id = id;  // Уникальный идентификатор
        this.title = title;
        this.description = description;
        this.status = status.NEW;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && Objects.equals(description, task.description) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, status);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
