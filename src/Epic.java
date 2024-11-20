import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Subtask> subtasks;

    public Epic(int id, String title, String description) {
        super(id, title, description, TaskStatus.NEW);
        this.subtasks = new ArrayList<>();
    }

    public List<Subtask> getSubTasks() {
        return subtasks;
    }

    public void addSubTask(Subtask subtask) {
        subtasks.add(subtask);
        updateStatus();
    }

    public void clearSubtask() {
        subtasks.clear();
        updateStatus();
    }

    public void updateStatus() {
        boolean allNew = true;
        boolean allDone = true;

        for (Subtask subTask : subtasks) {
            if (subTask.getStatus() != TaskStatus.NEW) {
                allNew = false;
            }
            if (subTask.getStatus() != TaskStatus.DONE) {
                allDone = false;
            }
        }

        if (subtasks.isEmpty() || allNew) {
            setStatus(TaskStatus.NEW);
        } else if (allDone) {
            setStatus(TaskStatus.DONE);
        } else {
            setStatus(TaskStatus.IN_PROGRESS);
        }
    }
}
