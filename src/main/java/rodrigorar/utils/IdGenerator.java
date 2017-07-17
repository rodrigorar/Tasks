package rodrigorar.utils;

public class IdGenerator {
    private static IdGenerator _instance;

    public static IdGenerator getInstance() {
        if (_instance == null) {
            _instance = new IdGenerator();
        }
        return _instance;
    }

    private IdGenerator() {
        // Empty Constructor
    }

    public String generateTaskId() {
        StringBuilder taskId = new StringBuilder("task-");
        long numberId = (long)(Math.random() * 1000000000);
        taskId.append(numberId);
        return taskId.toString();
    }
}
