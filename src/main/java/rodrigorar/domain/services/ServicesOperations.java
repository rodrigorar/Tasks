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

import java.util.List;
import java.util.LinkedList;

import rodrigorar.domain.interfaces.BaseService;
import rodrigorar.domain.interfaces.IService;
import rodrigorar.domain.services.ServicesFactory;
import rodrigorar.domain.services.configuration.FactoryServicesConfiguration;
import rodrigorar.domain.services.ServicesEntity;
import rodrigorar.domain.services.ServicesAppConfigurations;
import rodrigorar.domain.pojos.Task;
import rodrigorar.domain.pojos.TaskList;

public class ServicesOperations
implements
IService {

    public Task createTask(String title, String description, String priorityId) {
        ServicesEntity entityServices = ServicesFactory.getInstance().getEntityServices();
        return entityServices.newTask(title, description, priorityId);
    }

    public Task findTask(String taskId) {
        ServicesEntity entityServices = ServicesFactory.getInstance().getEntityServices();
        return entityServices.getTask(taskId);
    }

    public void deleteTask(String taskId) {
        ServicesEntity entityServices = ServicesFactory.getInstance().getEntityServices();
        entityServices.removeTask(taskId);
    }

    public void deleteTask(Task task) {
        ServicesEntity entityServices = ServicesFactory.getInstance().getEntityServices();
        entityServices.removeTask(task);
    }

    public List<String> getTaskNames(String taskListId) {
        ServicesEntity entityServices = ServicesFactory.getInstance().getEntityServices();
        TaskList taskList = entityServices.getTaskList();
        List<String> taskNames = new LinkedList<String>();

        for (Task task : taskList.getAllTasks()) {
            taskNames.add(task.getTitle());
        }

        return taskNames;
    }

    public TaskList findTaskList(String taskListId) {
        ServicesEntity entityServices = ServicesFactory.getInstance().getEntityServices();
        return entityServices.getTaskList();
    }

    public void save() {
        ServicesEntity entityServices = ServicesFactory.getInstance().getEntityServices();
        
        BaseService<?> getDataDirectoryService = 
        		FactoryServicesConfiguration.getServiceGetDataDirectory();
        getDataDirectoryService.execute();
        entityServices.save((String)getDataDirectoryService.getResult());
        
        ServicesAppConfigurations configServices =
        		ServicesFactory.getInstance().getConfigurationServices();
        configServices.save();
    }

}
