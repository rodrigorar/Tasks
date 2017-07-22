package rodrigorar.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import rodrigorar.entities.Task;
import rodrigorar.entities.TaskList;
import rodrigorar.entities.exceptions.InvalidTitleException;

public class PersistenceManager {
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String TASK = "task";
    public static final String TASK_LIST = "task_list";
    public static final String DATA = "/home/rodrigo/tasks.xml";
    private static PersistenceManager _instance;

    public static PersistenceManager getInstance() {
        if (_instance == null) {
            _instance = new PersistenceManager();
        }
        return _instance;
    }

    private PersistenceManager() { /* Empty Constructor */ }

    public Element buildTaskElement(Task task) {
        Element titleElement = new Element(TITLE);
        titleElement.setText(task.getTitle());

        Element descriptionElement = new Element(DESCRIPTION);
        descriptionElement.setText(task.getDescription());

        Element taskElement = new Element(TASK);
        taskElement.addContent(titleElement);
        taskElement.addContent(descriptionElement);

        return taskElement;
    }

    public Task buildTaskObject(Element taskElement) {
        Task task = null;

        try {
            Element titleElement = taskElement.getChild("title");
            String title = titleElement.getText().trim();

            Element descriptionElement = taskElement.getChild("description");
            String description = descriptionElement.getText().trim();

            task = new Task(title);
            task.setDescription(description);
        } catch (InvalidTitleException exception) {
            exception.printStackTrace();
        }

        return task;
    }

    public Element buildTaskListElement(TaskList taskList) {
        Element taskListElement = new Element(TASK_LIST);

        for (Task task : taskList.getAllTasks()) {
            Element taskElement = buildTaskElement(task);
            taskListElement.addContent(taskElement);
        }

        return taskListElement;
    }

    public TaskList buildTaskListObject(Element taskListElement) {
        TaskList taskList = new TaskList();
        List<Element> taskElements = taskListElement.getChildren();

        for (Element taskElement : taskElements) {
            Task task = buildTaskObject(taskElement);
            taskList.addTask(task);
        }

        return taskList;
    }

    public TaskList loadTaskList() {
        TaskList loadedTaskList = null;

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/home/rodrigo/tasks.xml");

        try {
            Document document = (Document)builder.build(xmlFile);
            Element taskListElement = document.getRootElement();

            loadedTaskList = buildTaskListObject(taskListElement);
        } catch (IOException  | JDOMException exception) {
            exception.printStackTrace();
        }

        return loadedTaskList;
    }

    public void saveTaskList(TaskList taskList) {
        try {
            Element taskListElement = buildTaskListElement(taskList);
            Document document = new Document(taskListElement);
            XMLOutputter outputter = new XMLOutputter();

            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, new FileWriter("/home/rodrigo/tasks.xml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
