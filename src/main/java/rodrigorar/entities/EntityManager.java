package rodrigorar.entities;

import rodrigorar.entities.Task;
import rodrigorar.entities.TaskList;
import rodrigorar.entities.exceptions.InvalidTitleException;
import rodrigorar.data.PersistenceManager;

public class EntityManager {
    private static EntityManager _instance;
    private TaskList _taskList;

    public static EntityManager getInstance() {
        if (_instance == null) {
            _instance = new EntityManager();
        }
        return _instance;
    }

    private EntityManager() {
        load();
    }

    public Task newTask(String title, String description) {
        Task newTask = null;

        try {
            newTask = new Task(title, description);
            _taskList.addTask(newTask);
        } catch (InvalidTitleException exception) {
            exception.printStackTrace();
        }

        return newTask;
    }

    public Task getTask(String taskId) {
        return _taskList.getTask(taskId);
    }

    public void removeTask(String taskId) {
        _taskList.removeTask(taskId);
    }

    public void removeTask(Task task) {
        _taskList.removeTask(task);
    }

    public TaskList getTaskList() {
        return _taskList;
    }

    public void save() {
        PersistenceManager manager = PersistenceManager.getInstance();
        manager.saveTaskList(_taskList);
    }

    public void load() {
        PersistenceManager manager = PersistenceManager.getInstance();
        _taskList = manager.loadTaskList();
    }

}
