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

import java.util.List;

import rodrigorar.data.DAOFactory;
import rodrigorar.data.daos.PriorityDAO;

import rodrigorar.domain.pojos.Priority;
import rodrigorar.domain.interfaces.BaseService;
import rodrigorar.domain.services.configuration.FactoryServicesConfiguration;
import rodrigorar.domain.services.configuration.ServiceGetPriorityDirectory;

public class ServiceGetPriorityList
implements
BaseService<List<Priority>> {
    private List<Priority> _priorityList;

    public void execute() {
        DAOFactory factory = DAOFactory.getInstance();
        PriorityDAO daoPriority = factory.getPriorityDAO();
        ServiceGetPriorityDirectory serviceGetPriorityDirectory =
            FactoryServicesConfiguration.getServiceGetPriorityDirectory();
        serviceGetPriorityDirectory.execute();
        _priorityList = daoPriority.load(serviceGetPriorityDirectory.getResult());

        System.out.println("Priorites have been loaded");
    }

    public List<Priority> getResult() {
        return _priorityList;
    }
}
