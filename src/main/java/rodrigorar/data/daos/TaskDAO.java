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

import rodrigorar.domain.pojos.Task;
import rodrigorar.domain.exceptions.InvalidTitleException;
import rodrigorar.data.daos.BaseDAO;
import rodrigorar.utils.Constants.XMLLabels;

public class TaskDAO
extends
BaseDAO<Task> {

    @Override
    public Element convertToElement(Task task) {
        Element idElement = new Element(XMLLabels.ID);
        idElement.setText(task.getId());

        Element titleElement = new Element(XMLLabels.TITLE);
        titleElement.setText(task.getTitle());

        Element descriptionElement = new Element(XMLLabels.DESCRIPTION);
        descriptionElement.setText(task.getDescription());

        Element priorityElement = new Element(XMLLabels.PRIORITY);
        priorityElement.setText(task.getPriorityId());

        Element taskElement = new Element(XMLLabels.TASK);
        taskElement.addContent(idElement);
        taskElement.addContent(titleElement);
        taskElement.addContent(descriptionElement);

        return taskElement;
    }

    @Override
    public Task convertToObject(Element taskElement) {
        Task task = null;

        try {
            Element idElement = taskElement.getChild(XMLLabels.ID);
            String id = idElement.getText().trim();

            Element titleElement = taskElement.getChild(XMLLabels.TITLE);
            String title = titleElement.getText().trim();

            Element descriptionElement = taskElement.getChild(XMLLabels.DESCRIPTION);
            String description = null;
            if (descriptionElement != null) {
                description = descriptionElement.getText().trim();
            }

            Element priorityElement = taskElement.getChild(XMLLabels.PRIORITY);
            String priorityId = null;
            if (priorityElement != null) {
                priorityId = priorityElement.getText().trim();
            }

            task = new Task(id);
            task.setTitle(title);
            task.setDescription(description);
            task.setPriorityId(priorityId);

        } catch (InvalidTitleException exception) {
            exception.printStackTrace();
        }

        return task;
    }
}
