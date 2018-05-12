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

package rodrigorar.domain.services.entity;

import rodrigorar.data.DAOFactory;
import rodrigorar.data.daos.TaskDAO;
import rodrigorar.domain.interfaces.BaseService;
import rodrigorar.domain.pojos.Task;
import rodrigorar.domain.pojos.Priority;
import rodrigorar.domain.services.priority.FactoryServicesPriority;
import rodrigorar.domain.services.configuration.FactoryServicesConfiguration;
import rodrigorar.domain.exceptions.InvalidTitleException;

public class ServiceNewTask
implements BaseService<Task> {
    private String _title;
    private String _description;
    private String _priorityId;

    private Task _result;

    public ServiceNewTask(String title, String description, String priorityId) {
        _title = title;
        _description = description;
        _priorityId = priorityId;
    }

    public ServiceNewTask(String title, String description) {
        this(title, description, null);
    }

    @Override
    public void execute() {
        if (_priorityId == null) {
            BaseService<?> priorityService = FactoryServicesPriority.getServiceDefaultPriority();
            priorityService.execute();
            Priority priority = (Priority)priorityService.getResult();
            _priorityId = priority.getId();
        }

        try {
            // TODO: Task list id will be selected by the user upon creation
            // this is just a hammer so the application works.
            _result = new Task("tasklist-000001", _title, _description, _priorityId);
            TaskDAO taskDao = DAOFactory.getTaskDAO();

            BaseService<?> dataDirectoryService =
                FactoryServicesConfiguration.getServiceGetDataDirectory();
            dataDirectoryService.execute();
            String dataDirectory = (String)dataDirectoryService.getResult();
            System.out.println("Data Directory: " + dataDirectory);
            taskDao.saveTask(dataDirectory, _result);
        } catch (InvalidTitleException exception) {
            exception.printStackTrace();
            _result = null;
        }
    }

    @Override
    public Task getResult() {
        return _result;
    }
}
