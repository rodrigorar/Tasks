package rodrigorar;

import rodrigorar.entities.Task;
import rodrigorar.entities.TaskList;

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

    public void newTask(String title, String description) {
        Task newTask = new Task(title, description);
    }

    public Task getTask(String taskId) {
        return _taskList.getTask(taskId);
    }

    public void removeTask(String taskId) {
        _taskList.removeTask(taskId);
    }

    public void save() {
        // Code
    }

    public void load() {
        // Needs to be updated to be able to load the task list from
        // the persitence.
        _taskList = new TaskList();
    }

}
