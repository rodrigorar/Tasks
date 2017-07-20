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

    public TaskList loadTaskList() {
        TaskList loadedTaskList = new TaskList();

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/home/rodrigo/tasks.xml");

        try {
            Document document = (Document)builder.build(xmlFile);
            Element taskListElement = document.getRootElement();

            List<Element> taskElements = taskListElement.getChildren();
            for (Element taskElement : taskElements) {
                String title = taskElement.getChild("title").getText().trim();
                String description = taskElement.getChild("description").getText().trim();

                loadedTaskList.addTask(new Task(title, description));
            }
        } catch (IOException  | JDOMException | InvalidTitleException exception) {
            exception.printStackTrace();
        }

        return loadedTaskList;
    }

    public void saveTaskList(TaskList taskList) {
        try {
            Element taskListElement = new Element("task_list");

            for (Task task : taskList.getAllTasks()) {
                Element titleElement = new Element("title");
                titleElement.setText(task.getTitle());
                Element descriptionElement = new Element("description");
                descriptionElement.setText(task.getDescription());

                Element taskElement = new Element("task");
                taskElement.addContent(titleElement);
                taskElement.addContent(descriptionElement);
                taskListElement.addContent(taskElement);
            }

            Document document = new Document(taskListElement);
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, new FileWriter("/home/rodrigo/tasks.xml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
