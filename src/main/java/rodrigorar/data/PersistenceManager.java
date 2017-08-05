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

    public TaskList loadTaskList() {
        TaskList loadedTaskList = null;

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/home/rodrigo/tasks.xml");

        try {
            Document document = (Document)builder.build(xmlFile);
            Element taskListElement = document.getRootElement();

            TaskListData listData = new TaskListData();
            loadedTaskList = listData.load(taskListElement);
        } catch (IOException  | JDOMException exception) {
            exception.printStackTrace();
        }

        return loadedTaskList;
    }

    public void saveTaskList(TaskList taskList) {
        try {
            TaskListData listData = new TaskListData();
            Element taskListElement = listData.save(taskList);
            Document document = new Document(taskListElement);
            XMLOutputter outputter = new XMLOutputter();

            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, new FileWriter("/home/rodrigo/tasks.xml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
