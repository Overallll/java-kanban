import java.util.List;

public interface TaskManager {
    void addTask(Task task);

    List<Task> getAllTasks();

    void removeAllTasks();

    void removeTaskById(int id);

    Task getTaskById(int id);

    void addEpic(Epic epic);

    Epic getEpicById(int id);

    void removeEpicById(int id);

    List<Epic> getAllEpics();

    void addSubtask(Subtask subtask);

    void removeSubtaskById(int id);

    Subtask getSubtaskById(int id);

    List<Subtask> getAllSubtasks();

    List<Subtask> getSubtasksForEpic(int epicId);

    void updateTask(Task updatedTask);

    void updateEpic(Epic updatedEpic);

    void updateSubtask(Subtask updatedSubtask);

    HistoryManager getHistory();
}
