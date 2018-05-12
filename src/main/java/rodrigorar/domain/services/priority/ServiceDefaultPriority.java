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

package rodrigorar.domain.services.priority;

import rodrigorar.data.DAOFactory;
import rodrigorar.data.daos.PriorityDAO;
import rodrigorar.domain.interfaces.BaseService;
import rodrigorar.domain.services.configuration.FactoryServicesConfiguration;
import rodrigorar.domain.pojos.Priority;

public class ServiceDefaultPriority
implements BaseService<Priority> {
    public static final String DEFAULT_PRIORITY_NAME = "None";

    private Priority _result;

    @Override
    public void execute() {
        Priority searchParameter = new Priority(null, DEFAULT_PRIORITY_NAME, null);

        BaseService<?> priorityService = FactoryServicesConfiguration.getServiceGetPriorityDirectory();
        priorityService.execute();
        String priorityDirectory = (String)priorityService.getResult();

        PriorityDAO priorityDao = DAOFactory.getPriorityDAO();
        _result = priorityDao.loadPriority(priorityDirectory, searchParameter);
    }

    @Override
    public Priority getResult() {
        return _result;
    }
}
