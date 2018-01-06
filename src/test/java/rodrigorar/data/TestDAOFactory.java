package rodrigorar.data;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import rodrigorar.data.DAOFactory;
import rodrigorar.data.daos.BaseDAO;
import rodrigorar.data.daos.TaskListDAO;
import rodrigorar.data.daos.TaskDAO;
import rodrigorar.data.daos.LanguageDAO;
import rodrigorar.data.daos.AppConfigurationsDAO;
import rodrigorar.domain.pojos.TaskList;

public class TestDAOFactory {

    @Test
    public void testReuseTaskListDAO() {
        TaskListDAO initialDAO = DAOFactory.getInstance().getTaskListDAO();
        TaskListDAO reusedDAO = DAOFactory.getInstance().getTaskListDAO();

        assertNotNull(initialDAO);
        assertNotNull(reusedDAO);
        assertEquals(initialDAO, reusedDAO);
    }

    @Test
    public void testReuseLanguageDAO() {
        LanguageDAO initialDAO = DAOFactory.getInstance().getLanguageDAO();
        LanguageDAO reusedDAO = DAOFactory.getInstance().getLanguageDAO();

        assertNotNull(initialDAO);
        assertNotNull(reusedDAO);
        assertEquals(initialDAO, reusedDAO);
    }

    @Test
    public void testReuseTaskDAO() {
        TaskDAO initialDAO = DAOFactory.getInstance().getTaskDAO();
        TaskDAO reusedDAO = DAOFactory.getInstance().getTaskDAO();

        assertNotNull(initialDAO);
        assertNotNull(reusedDAO);
        assertEquals(initialDAO, reusedDAO);
    }

    @Test
    public void testReuseAppConfigurationsDAO() {
        AppConfigurationsDAO initialDAO = DAOFactory.getInstance().getAppConfigurationsDAO();
        AppConfigurationsDAO reusedDAO = DAOFactory.getInstance().getAppConfigurationsDAO();

        assertNotNull(initialDAO);
        assertNotNull(reusedDAO);
        assertEquals(initialDAO, reusedDAO);
    }
}
