package rodrigorar.data;

import java.util.List;
import org.jdom2.Element;

import rodrigorar.entities.Task;
import rodrigorar.entities.TaskList;

public class TaskListData
implements
IData<TaskList> {
    public static final String TASK_LIST = "task_list";

    public Element save(TaskList taskList) {
        Element taskListElement = new Element(TASK_LIST);
        TaskData builder = new TaskData();

        for (Task task : taskList.getAllTasks()) {
            Element taskElement = builder.save(task);
            taskListElement.addContent(taskElement);
        }

        return taskListElement;
    }

    public TaskList load(Element taskListElement) {
        TaskList taskList = new TaskList();
        TaskData builder = new TaskData();
        List<Element> taskElements = taskListElement.getChildren();

        for (Element taskElement : taskElements) {
            Task task = builder.load(taskElement);
            taskList.addTask(task);
        }

        return taskList;
    }
}
