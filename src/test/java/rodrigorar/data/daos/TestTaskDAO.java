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

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jdom2.Element;

import rodrigorar.domain.pojos.Task;
import rodrigorar.domain.exceptions.InvalidTitleException;
import rodrigorar.utils.Constants.XMLLabels;

public class TestTaskDAO {
    public static final String TITLE = "title-1";
    public static final String DESCRIPTION = "Description for title-1";

    @Test
    public void testConvertToObject() {
        Element taskElement = new Element(XMLLabels.TASK);

        Element titleElement = new Element(XMLLabels.TITLE);
        titleElement.setText(TITLE);

        Element descriptionElement = new Element(XMLLabels.DESCRIPTION);
        descriptionElement.setText(DESCRIPTION);

        taskElement.addContent(titleElement);
        taskElement.addContent(descriptionElement);

        TaskDAO dao = new TaskDAO();
        Task task = dao.convertToObject(taskElement);

        assertEquals(task.getTitle(), TITLE);
        assertEquals(task.getDescription(), DESCRIPTION);
    }

    @Test
    public void testConvertToObjectWithNullDescription() {
        Element taskElement = new Element(XMLLabels.TASK);

        Element titleElement = new Element(XMLLabels.TITLE);
        titleElement.setText(TITLE);

        taskElement.addContent(titleElement);

        TaskDAO dao = new TaskDAO();
        Task task = dao.convertToObject(taskElement);

        assertEquals(task.getTitle(), TITLE);
        assertNull(task.getDescription());
    }

    @Test
    public void testConvertToElement()
    throws
    InvalidTitleException {
        Task task = new Task(TITLE);
        task.setDescription(DESCRIPTION);

        TaskDAO dao = new TaskDAO();

        Element element = dao.convertToElement(task);
        String title = element.getChild("title").getText().trim();
        String description = element.getChild("description").getText().trim();

        assertEquals(TITLE, title);
        assertEquals(DESCRIPTION, description);
    }

    @Test
    public void testConvertToElementWithNullDescription()
    throws
    InvalidTitleException {
        Task task = new Task(TITLE);
        task.setDescription(null);

        TaskDAO dao = new TaskDAO();

        Element element = dao.convertToElement(task);
        String title = element.getChild("title").getText().trim();
        String description = element.getChild("description").getText();

        assertEquals(TITLE, title);
        assertEquals("", description);
    }
}
