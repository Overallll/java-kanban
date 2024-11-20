import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private int nextId = 1;

    public int generateId() {
        return nextId++;
    }

    public void add(Task task) {
        tasks.put(task.getId(), task);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public void addEpic(String title, String description) {
        int id = generateId();
        Epic epic = new Epic(id, title, description);
        epics.put(id, epic);
    }

    // Получение эпика по ID
    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    // Удаление эпика по ID
    public void removeEpicById(int id) {
        epics.remove(id);
    }

    // Получение всех эпиков
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    // Добавление подзадачи
    public void addSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            epic.addSubTask(subtask);
        }
    }

    // Получение подзадачи по ID
    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    // Удаление подзадачи по ID
    public void removeSubtaskById(int id) {
        subtasks.remove(id);
    }

    // Получение всех подзадач
    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    // Получение подзадач для конкретного эпика
    public List<Subtask> getSubtasksForEpic(int epicId) {
        List<Subtask> epicSubtasks = new ArrayList<>();
        for (Subtask subtask : subtasks.values()) {
            if (subtask.getEpicId() == epicId) {
                epicSubtasks.add(subtask);
            }
        }
        return epicSubtasks;
    }

    // Обновление статуса эпика на основе статусов подзадач
    public void updateEpicStatus(Epic epic) {
        if (epic.getSubTasks().isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }

        boolean allDone = true;
        boolean allNew = true;

        for (Subtask subTask : epic.getSubTasks()) {
            if (subTask.getStatus() != TaskStatus.DONE) {
                allDone = false;
            }
            if (subTask.getStatus() != TaskStatus.NEW) {
                allNew = false;
            }
        }

        if (allDone) {
            epic.setStatus(TaskStatus.DONE);
        } else if (allNew) {
            epic.setStatus(TaskStatus.NEW);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }
}
