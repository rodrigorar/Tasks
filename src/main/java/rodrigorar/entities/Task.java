package rodrigorar.entities;

import rodrigorar.utils.IdGenerator;
import rodrigorar.entities.exceptions.InvalidTitleException;
import rodrigorar.entities.interfaces.IEntity;

public class Task
implements
IEntity {
    private String _id;
    private String _title;
    private String _description;

    public Task(String title)
    throws
    InvalidTitleException {
        this.setTitle(title);
        _id = IdGenerator.getInstance().generateTaskId();
    }

    public Task(String title, String description)
    throws
    InvalidTitleException {
        this(title);
        this.setDescription(description);
    }

    public String getId() {
        return _id;
    }

    public void setTitle(String title)
    throws
    InvalidTitleException {
        if (title != null && !title.equals("")) {
            _title = title;
        } else {
            throw new InvalidTitleException("The title is invalid.");
        }
    }

    public String getTitle() {
        return _title;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getDescription() {
        return _description;
    }
}
