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
import org.jdom2.Element;

import rodrigorar.data.daos.BaseDAO;
import rodrigorar.data.daos.TaskDAO;
import rodrigorar.data.DAOFactory;
import rodrigorar.domain.pojos.Task;
import rodrigorar.domain.pojos.TaskList;
import rodrigorar.utils.Constants.XMLLabels;

public class TaskListDAO
extends
BaseDAO<TaskList> {

    @Override
    public Element convertToElement(TaskList taskList) {
        Element taskListElement = new Element(XMLLabels.TASK_LIST);
        TaskDAO taskDAO = DAOFactory.getTaskDAO();

        Element idElement = new Element(XMLLabels.ID);
        idElement.setText(taskList.getId());

        taskListElement.addContent(idElement);
        for (Task task : taskList.getAllTasks()) {
            Element taskElement = taskDAO.convertToElement(task);
            taskListElement.addContent(taskElement);
        }

        return taskListElement;
    }

    @Override
    public TaskList convertToObject(Element taskListElement) {
        TaskDAO taskDAO = DAOFactory.getTaskDAO();

        Element idElement = taskListElement.getChild(XMLLabels.ID);
        TaskList taskList = new TaskList(idElement.getText().trim());

        List<Element> taskElements = taskListElement.getChildren(XMLLabels.TASK);

        for (Element taskElement : taskElements) {
            Task task = taskDAO.convertToObject(taskElement);
            taskList.addTask(task);
        }

        return taskList;
    }

    public TaskList load(String dataDirectory) {
        TaskList loadedTaskList = null;

        Element rootElement = getRootElement(dataDirectory);
        if (rootElement != null) {
            loadedTaskList = convertToObject(rootElement);
        }

        return loadedTaskList;
    }

    public void save(String dataDirectory, TaskList taskList) {
        writeToFile(convertToElement(taskList), dataDirectory);
    }
}
