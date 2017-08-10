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

package rodrigorar.data;

import org.jdom2.Element;

import rodrigorar.data.interfaces.IData;
import rodrigorar.entities.Task;
import rodrigorar.entities.exceptions.InvalidTitleException;
import rodrigorar.data.utils.JDOMBuilder;

public class TaskData
implements
IData<Task> {
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String TASK = "task";

    @Override
    public Element save(Task task) {
        JDOMBuilder jdomUtils = new JDOMBuilder();
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
