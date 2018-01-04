package rodrigorar.data;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import rodrigorar.data.DAOFactory;
import rodrigorar.data.daos.BaseDAO;
import rodrigorar.data.daos.TaskListDAO;
import rodrigorar.domain.pojos.TaskList;

public class TestDAOFactory {

    @Test
    public void testGetTaskListDAO() {
        BaseDAO<TaskList> dao = DAOFactory.getTaskListDAO();

        assertTrue(dao instanceof TaskListDAO);
    }
}
