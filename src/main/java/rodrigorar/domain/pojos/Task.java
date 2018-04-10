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

import rodrigorar.utils.IdGenerator;
import rodrigorar.domain.exceptions.InvalidTitleException;
import rodrigorar.domain.interfaces.IEntity;
import rodrigorar.domain.pojos.Priority;

public class Task
implements
IEntity {
    private String _id;
    private String _title;
    private String _description;
    private String _priorityId;

    public Task(String id) {
        _id = id;
    }

    public Task(String title, String description)
    throws
    InvalidTitleException {
        _id = IdGenerator.generateTaskId();
        this.setTitle(title);
        this.setDescription(description);
    }

    public String getId() {
        return _id;
    }

    public void setTitle(String title)
    throws
    InvalidTitleException {
        if (title != null && !title.equals("")) {
            _title = title;
        } else {
            throw new InvalidTitleException("The title is invalid.");
        }
    }

    public String getTitle() {
        return _title;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getDescription() {
        return _description;
    }

    public void setPriorityId(String priorityId) {
        _priorityId = priorityId;
    }

    public String getPriorityId() {
        return _priorityId;
    }
}
