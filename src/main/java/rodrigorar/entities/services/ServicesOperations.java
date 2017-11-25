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

package rodrigorar.entities.services;

import java.util.List;
import java.util.LinkedList;

import rodrigorar.entities.Task;
import rodrigorar.entities.TaskList;
import rodrigorar.entities.interfaces.IOperationsFacade;

public class ServicesOperations
implements
IOperationsFacade {
    private ServicesEntity _entityServices;

    public ServicesOperations() {
        _entityServices = ServicesEntity.getInstance();
    }

    public Task createTask(String title, String description) {
        return _entityServices.newTask(title, description);
    }

    public Task findTask(String taskId) {
        return _entityServices.getTask(taskId);
    }

    public void deleteTask(String taskId) {
        _entityServices.removeTask(taskId);
    }

    public void deleteTask(Task task) {
        _entityServices.removeTask(task);
    }

    public List<String> getTaskNames(String taskListId) {
        TaskList taskList = _entityServices.getTaskList();
        List<String> taskNames = new LinkedList<String>();

        for (Task task : taskList.getAllTasks()) {
            taskNames.add(task.getTitle());
        }

        return taskNames;
    }

    public TaskList findTaskList(String taskListId) {
        return _entityServices.getTaskList();
    }

}
