/******************************************************************************
* Copyright 2017 Rodrigo Ramos Rosa
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*******************************************************************************/

package rodrigorar.data.daos;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.jdom2.Element;

import rodrigorar.data.daos.TaskListDAO;
import rodrigorar.domain.pojos.Task;
import rodrigorar.domain.pojos.TaskList;
import rodrigorar.domain.exceptions.InvalidTitleException;
import rodrigorar.utils.Constants.XMLLabels;

public class TestTaskListDAO {
    public static final String TASK_1 = "task-1";
    public static final String TASK_2 = "task-2";
    public static final String TASK_3 = "task-3";
    public static final String ID = "123";

    private boolean containsTask(String title, List<Element> children) {
        boolean result = false;

        for (Element child : children) {
            if (title.equals(child.getChild("title").getText().trim())) {
                result = true;
                break;
            }
        }

        return result;
    }

    @Test
    public void testConvertToObject() {
        Element taskListElement = new Element(XMLLabels.TASK_LIST);

        Element idElement = new Element(XMLLabels.ID);
        idElement.setText(ID);

        Element task1 = new Element(XMLLabels.TASK);
        Element task1Title = new Element(XMLLabels.TITLE);
        task1Title.setText(TASK_1);
        task1.addContent(task1Title);

        Element task2 = new Element(XMLLabels.TASK);
        Element task2Title = new Element(XMLLabels.TITLE);
        task2Title.setText(TASK_2);
        task2.addContent(task2Title);

        Element task3 = new Element(XMLLabels.TASK);
        Element task3Title = new Element(XMLLabels.TITLE);
        task3Title.setText(TASK_3);
        task3.addContent(task3Title);

        taskListElement.addContent(idElement);
        taskListElement.addContent(task1);
        taskListElement.addContent(task2);
        taskListElement.addContent(task3);

        TaskListDAO dao = new TaskListDAO();
        TaskList taskList = dao.convertToObject(taskListElement);

        assertEquals(taskList.getId(), ID);
        assertNotNull(taskList.getTask(TASK_1));
        assertNotNull(taskList.getTask(TASK_2));
        assertNotNull(taskList.getTask(TASK_3));
    }

    @Test
    public void testConvertToElement()
    throws
    InvalidTitleException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task(TASK_1));
        taskList.addTask(new Task(TASK_2));
        taskList.addTask(new Task(TASK_3));

        TaskListDAO dao = new TaskListDAO();
        Element element = dao.convertToElement(taskList);

        List<Element> children = element.getChildren(XMLLabels.TASK);
        for (Element child : children) {
            assertTrue(containsTask(child.getChild(XMLLabels.TITLE).getText().trim(), children));
        }

        Element idElement = element.getChild(XMLLabels.ID);
        assertNotNull(idElement.getText().trim());
    }

    @Test
    public void testLoad() {
        TaskListDAO dao = new TaskListDAO();
        TaskList taskList = dao.load("src/test/java/resources/base_data_file.xml");

        assertEquals(taskList.getId(), ID);
        assertNotNull(taskList.getTask(TASK_1));
        assertNotNull(taskList.getTask(TASK_2));
        assertNotNull(taskList.getTask(TASK_3));
    }

    @Test
    public void testSave()
    throws
    InvalidTitleException {
        TaskListDAO dao = new TaskListDAO();

        TaskList taskList = new TaskList();
        taskList.addTask(new Task(TASK_1));
        taskList.addTask(new Task(TASK_2));
        taskList.addTask(new Task(TASK_3));

        dao.save("src/test/java/resources/test_data_file.xml", taskList);
        taskList = dao.load("src/test/java/resources/test_data_file.xml");

        assertNotNull(taskList.getTask(TASK_1));
        assertNotNull(taskList.getTask(TASK_2));
        assertNotNull(taskList.getTask(TASK_3));
    }
}
