package rodrigorar.data;

public class PersistenceManager {
    private static PersistenceManager _instance;

    public static PersistenceManager getInstance() {
        if (_instance == null) {
            _instance = new PersistenceManager();
        }
        return _instance;
    }

    private PersistenceManager() {
        // Code
    }

    public Task loadTask(String taskId) {
        Task loadedTask = null;

        return loadedTask;
    }

    public void saveTask(Task task) {

    }

    public TaskList loadTaskList(String taskListId) {
        TaskList loadedTaskList = null;

        return loadedTaskList;
    }

    public void saveTaskList(TaskList taskList) {

    }
}
