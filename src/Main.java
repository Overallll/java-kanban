import package_managers.HistoryManager;
import package_managers.Managers;
import package_managers.TaskManager;
import tasks.*;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();


        Epic epic1 = new Epic("Учеба", "Организация учебного процесса после работы");

        taskManager.addEpic(epic1);
        System.out.println(epic1);
        Epic updatedEpic = new Epic("Учеба", "Уделять время каждый вечер для учебы");
        updatedEpic.setId(epic1.getId());
        taskManager.updateEpic(updatedEpic);
        System.out.println("Обновленный эпик " + updatedEpic);
        // Проверка добавления эпика
        Epic epic = taskManager.getEpicById(1); //
        if (epic != null) {
            System.out.println("Эпик: " + epic);
        } else {
            System.out.println("Эпик с таким ID не найден.");
        }

        Task task1 = new Task("Сделать финальное задание", "Начать делать код");
        Task task2 = new Task("Сдать финальное задание", "Отправить код на ревью и получить правки");

        historyManager.add(task1);
        System.out.println(historyManager.getHistory()+"История просмотров");

        taskManager.addTask(task1);
        System.out.println(task1);
        Task updatedTask = new Task("Исправить финальное задание", "Внести правки");
        updatedTask.setId(task1.getId());
        updatedTask.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateTask(updatedTask);
        System.out.println("Обновленная задача " + updatedTask);


        taskManager.addTask(task2);

        taskManager.getTaskById(task1.getId());
        taskManager.removeTaskById(task1.getId());

        System.out.println("Вывод задач после удаления по айди " + task1.getId() +
                " оставшиеся задачи: " + taskManager.getAllTasks());

        // Добавление подзадач в эпик
        Subtask subtask1 = new Subtask("Открыть теорию", "Пройти несколько уроков", epic.getId());
        Subtask subtask2 = new Subtask("Практика", "Решить несколько задач по теории", epic.getId());

        taskManager.addSubtask(subtask1);
        Subtask updatedSubtask = new Subtask("Открыть теорию", "Уроки пройдены", epic.getId());
        updatedSubtask.setId(subtask1.getId());
        updatedSubtask.setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(updatedSubtask);

        taskManager.addSubtask(subtask2);

        // Проверка получения подзадач для эпика
        System.out.println("Подзадачи для эпика: " + taskManager.getSubtasksForEpic(epic.getId()));

        System.out.println("Получение подзадачи по айди: " + taskManager.getSubtaskById(subtask1.getId()));

        // Обновление статуса эпика на основе подзадач

        subtask1.setStatus(TaskStatus.DONE);
        taskManager.addSubtask(subtask1);

        subtask2.setStatus(TaskStatus.DONE);
        taskManager.addSubtask(subtask2);
        System.out.println("Статус эпиков после обновления статуса подзадач: "
                + taskManager.getEpicById(epic1.getId()).getStatus());

        System.out.println("Статус эпика после изменения статуса подзадачи: " + epic.getStatus());

        // Проверка удаления подзадачи по ID
        taskManager.removeSubtaskById(subtask2.getId());
        System.out.println("Подзадачи после удаления второй подзадачи: " + taskManager.getAllSubtasks());

        epic.clearSubtask();

        // Проверка удаления эпика по ID
        taskManager.removeEpicById(epic.getId());
        System.out.println("Все эпики после удаления эпика 1: " + taskManager.getAllEpics());

        // Проверка удаления всех задач
        taskManager.removeAllTasks();
        System.out.println("Все задачи после удаления всех задач: " + taskManager.getAllTasks());


    }
}

