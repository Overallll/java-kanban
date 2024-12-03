package package_managers;

import tasks.Task;

import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final LinkedList<Task> history = new LinkedList<>();
    private static final int MAX_HISTORY_SIZE = 10;

    @Override
    public void add(Task task) {
        // Убираем задачу, если она уже есть в истории
        history.remove(task);
        // Добавляем задачу в конец истории
        history.add(task);

        // Если история больше 10, удаляем первый элемент
        if (history.size() > MAX_HISTORY_SIZE) {
            history.removeFirst();
        }
    }

    @Override
    public List<Task> getHistory() {
        return new LinkedList<>(history);  // Возвращаем копию списка
    }
}

