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

import java.util.List;
import org.jdom2.Element;

import rodrigorar.data.interfaces.IData;
import rodrigorar.entities.Task;
import rodrigorar.entities.TaskList;

public class TaskListData
implements
IData<TaskList> {
    public static final String TASK_LIST = "task_list";

    public Element save(TaskList taskList) {
        Element taskListElement = new Element(TASK_LIST);
        TaskData builder = new TaskData();

        for (Task task : taskList.getAllTasks()) {
            Element taskElement = builder.save(task);
            taskListElement.addContent(taskElement);
        }

        return taskListElement;
    }

    public TaskList load(Element taskListElement) {
        TaskList taskList = new TaskList();
        TaskData builder = new TaskData();
        List<Element> taskElements = taskListElement.getChildren();

        for (Element taskElement : taskElements) {
            Task task = builder.load(taskElement);
            taskList.addTask(task);
        }

        return taskList;
    }
}
