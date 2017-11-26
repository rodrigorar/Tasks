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

package rodrigorar.domain.pojos;

import java.util.List;
import java.util.LinkedList;

import rodrigorar.domain.interfaces.IEntity;
import rodrigorar.utils.IdGenerator;

public class TaskList
implements
IEntity {
    private String _id;
    private List<Task> _tasks;

    public TaskList() {
        _id = IdGenerator.generateTaskListId();
        _tasks = new LinkedList<Task>();
    }

    @Override
    public String getId() {
        return _id;
    }

    public void addTask(Task newTask) {
        _tasks.add(newTask);
    }

    public Task getTask(String taskId) {
        for (Task index : _tasks) {
            if (index.getTitle().equals(taskId)) {
                return index;
            }
        }
        return null;
    }

    public List<Task> getAllTasks() {
        return _tasks;
    }

    public void removeTask(Task task) {
        if (task != null) {
            _tasks.remove(task);
        }
    }

    public void removeTask(String taskId) {
        Task task = getTask(taskId);
        removeTask(task);
    }
}
