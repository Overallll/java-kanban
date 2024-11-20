public class Main {
    public static void main(String[] args) {
        // Создаем менеджер задач
        TaskManager taskManager = new TaskManager();

        // Создаем эпик и добавляем его в TaskManager
        taskManager.addEpic("Учеба", "Организация учебного процесса после работы");

        // Проверка добавления эпика
        Epic epic = taskManager.getEpicById(1); // Получаем эпик с id = 1
        if (epic != null) {
            System.out.println("Эпик: " + epic); // Выводим эпик, если он найден
        } else {
            System.out.println("Эпик с таким ID не найден.");
        }

        Task task1 = new Task(taskManager.generateId(), "Сделать спринт 4",
                "Начать делать код", TaskStatus.NEW);
        Task task2 = new Task(taskManager.generateId(), "Сдать финальное задание",
                "Отправить код на ревью и получить правки", TaskStatus.NEW);

        taskManager.add(task1);
        taskManager.add(task2);

        taskManager.getTaskById(task1.getId());

        taskManager.removeTaskById(task1.getId());
        System.out.println("Вывод задач после удаления по айди " + task1.getId() +
                " оставшиеся задачи: " + taskManager.getAllTasks());

        // Добавление подзадач в эпик
        Subtask subtask1 = new Subtask(taskManager.generateId(), "Открыть теорию",
                "Пройти несколько уроков", TaskStatus.NEW, epic.getId());
        Subtask subtask2 = new Subtask(taskManager.generateId(), "Практика",
                "Решить несколько задач по теории", TaskStatus.NEW, epic.getId());

        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);

        // Проверка получения подзадач для эпика
        System.out.println("Подзадачи для эпика: " + taskManager.getSubtasksForEpic(epic.getId()));

        System.out.println("Получение подзадачи по айди: "+taskManager.getSubtaskById(subtask1.getId()));

        // Обновление статуса эпика на основе подзадач
        subtask1.setStatus(TaskStatus.DONE);
        taskManager.updateEpicStatus(epic);

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

