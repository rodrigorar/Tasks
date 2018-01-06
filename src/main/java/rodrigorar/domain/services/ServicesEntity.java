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

package rodrigorar.domain.services;

import rodrigorar.data.daos.TaskListDAO;
import rodrigorar.data.daos.AppConfigurationsDAO;
import rodrigorar.data.DAOFactory;
import rodrigorar.domain.interfaces.IService;
import rodrigorar.domain.exceptions.InvalidTitleException;
import rodrigorar.domain.pojos.Task;
import rodrigorar.domain.pojos.TaskList;

public class ServicesEntity
implements
IService {
    private static ServicesEntity _instance;

    private AppConfigurationsDAO _appConfigurationsDAO;
    private TaskListDAO _taskListDAO;

    private TaskList _taskList;

    public ServicesEntity(String dataDirectory) {
        _appConfigurationsDAO = DAOFactory.getInstance().getAppConfigurationsDAO();
        _taskListDAO = DAOFactory.getInstance().getTaskListDAO();

        load(dataDirectory);
    }

    public Task newTask(String title, String description) {
        Task newTask = null;

        try {
            newTask = new Task(title, description);
            _taskList.addTask(newTask);
        } catch (InvalidTitleException exception) {
            exception.printStackTrace();
        }

        return newTask;
    }

    public Task getTask(String taskId) {
        return _taskList.getTask(taskId);
    }

    public void removeTask(String taskId) {
        _taskList.removeTask(taskId);
    }

    public void removeTask(Task task) {
        _taskList.removeTask(task);
    }

    public TaskList getTaskList() {
        return _taskList;
    }

    public void save(String dataDirectory) {
        _taskListDAO.save(dataDirectory, _taskList);
    }

    public void load(String dataDirectory) {
        _appConfigurationsDAO.load(); // FIXME: Remove this from here, it does not make any sense
        _taskList = _taskListDAO.load(dataDirectory);
    }

}
