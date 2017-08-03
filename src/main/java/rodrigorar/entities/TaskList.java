package rodrigorar.entities;

import java.util.List;
import java.util.LinkedList;

import rodrigorar.utils.IdGenerator;

public class TaskList
implements
IEntity {
    private String _id;
    private List<Task> _tasks;

    public TaskList() {
        _id = IdGenerator.getInstance().generateTaskListId();
        _tasks = new LinkedList<Task>();
        System.out.println("New Task List");
    }

    @Override
    public String getId() {
        return _id;
    }

    public void addTask(Task newTask) {
        System.out.println("New Task");
        _tasks.add(newTask);
    }

    public Task getTask(String taskId) {
        for (Task index : _tasks) {
            if (index.getTitle().equals(taskId)) {
                return index;
            }
        }
        return null;
    }

    public List<Task> getAllTasks() {
        return _tasks;
    }

    public void removeTask(Task task) {
        if (task != null) {
            _tasks.remove(task);
        }
    }

    public void removeTask(String taskId) {
        Task task = getTask(taskId);
        removeTask(task);
    }
}
