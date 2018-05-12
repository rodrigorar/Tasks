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
import rodrigorar.domain.pojos.Priority;
import rodrigorar.domain.interfaces.BaseService;
import rodrigorar.domain.services.configuration.FactoryServicesConfiguration;

public class ServiceGetPriority
implements
BaseService<Priority> {
    private String _priorityId;
    private Priority _result;

    public ServiceGetPriority(String priorityId) {
        _priorityId = priorityId;
    }

    public void execute() {
        Priority searchParameter = new Priority(_priorityId, null, null);

        BaseService<?> serviceGetPriorityDirectory =
            FactoryServicesConfiguration.getServiceGetPriorityDirectory();
        serviceGetPriorityDirectory.execute();
        String priorityDirectory = (String)serviceGetPriorityDirectory.getResult();

        PriorityDAO priorityDao = DAOFactory.getPriorityDAO();
        _result = priorityDao.loadPriority(priorityDirectory, searchParameter);
    }

    public Priority getResult() {
        return _result;
    }
}
