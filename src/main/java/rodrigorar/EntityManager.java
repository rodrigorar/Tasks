package rodrigorar;

public class EntityManager {
    private static EntityManager _instance;

    public static EntityManager getInstance() {
        if (_instance == null) {
            _instance = new EntityManager();
        }
        return _instance;
    }

    private EntityManager() {
        // Code
    }

    public void newTask(String title, String description) {
        // Code
    }

    public Task getTask(String taskId) {
        // Code
        return null;
    }

    public void removeTask(String taskId) {
        // Code
    }

    public void save() {
        // Code
    }

    public void load() {
        // Code
    }

}
