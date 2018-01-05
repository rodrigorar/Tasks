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

import org.jdom2.Element;

import rodrigorar.data.interfaces.IData;
import rodrigorar.domain.pojos.Task;
import rodrigorar.domain.exceptions.InvalidTitleException;
import rodrigorar.data.utils.JDOMBuilder;
import rodrigorar.utils.Constants.XMLLabels;

public class TaskDAO
extends
BaseDAO<Task> {

    @Override
    public Element convertToElement(Task task) {
        Element taskElement = new Element(XMLLabels.TASK);

        Element titleElement = JDOMBuilder.buildStringElement(XMLLabels.TITLE, task.getTitle());
        taskElement.addContent(titleElement);

        Element descriptionElement = JDOMBuilder.buildStringElement(XMLLabels.DESCRIPTION, task.getDescription());
        taskElement.addContent(descriptionElement);

        return taskElement;
    }

    @Override
    public Task convertToObject(Element taskElement) {
        Task task = null;

        try {
            Element titleElement = taskElement.getChild(XMLLabels.TITLE);
            String title = titleElement.getText().trim();

            task = new Task(title);

            Element descriptionElement = taskElement.getChild(XMLLabels.DESCRIPTION);
            if (descriptionElement != null) {
                String description = descriptionElement.getText().trim();
                task.setDescription(description);
            }
        } catch (InvalidTitleException exception) {
            exception.printStackTrace();
        }

        return task;
    }
}
