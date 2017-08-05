package rodrigorar.data;

import org.jdom2.Element;

import rodrigorar.entities.Task;
import rodrigorar.entities.exceptions.InvalidTitleException;
import rodrigorar.data.utils.JDOMUtils;

public class TaskData
implements
IData<Task> {
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String TASK = "task";

    @Override
    public Element save(Task task) {
        JDOMUtils jdomUtils = new JDOMUtils();
        Element taskElement = new Element(TASK);

        Element titleElement = jdomUtils.buildStringElement(TITLE, task.getTitle());
        taskElement.addContent(titleElement);

        Element descriptionElement = jdomUtils.buildStringElement(DESCRIPTION, task.getTitle());
        taskElement.addContent(descriptionElement);

        return taskElement;
    }

    @Override
    public Task load(Element taskElement) {
        Task task = null;

        try {
            Element titleElement = taskElement.getChild(TITLE);
            String title = titleElement.getText().trim();

            Element descriptionElement = taskElement.getChild(DESCRIPTION);
            String description = descriptionElement.getText().trim();

            task = new Task(title);
            task.setDescription(description);
        } catch (InvalidTitleException exception) {
            exception.printStackTrace();
        }

        return task;
    }
}
