package rodrigorar.entities;

import org.junit.Test;

import rodrigorar.entities.Task;
import rodrigorar.entities.exceptions.InvalidTitleException;

public class TaskTest {
    public final String VALID_TITLE = "Shopping";
    public final String EMPTY_TITLE = "";
    public final String NULL_TITLE = null;
    private Task testTask;

    @Test
    public void validTitleTest()
    throws
    InvalidTitleException {
        Task testTask = new Task(VALID_TITLE);
    }

    @Test (expected = InvalidTitleException.class)
    public void emptyTitleTest()
    throws
    InvalidTitleException {
        Task testTask = new Task(EMPTY_TITLE);
    }

    @Test (expected = InvalidTitleException.class)
    public void nullTitleTest()
    throws
    InvalidTitleException {
        Task testTask = new Task(NULL_TITLE);
    }
}
