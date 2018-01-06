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

import rodrigorar.domain.interfaces.IService;
import rodrigorar.domain.services.ServicesOperations;
import rodrigorar.domain.services.ServicesOperations;

public class ServicesFactory {
    private static ServicesFactory _instance;
    private List<IService> _servicesList;

    public static ServicesFactory getInstance() {
        if (_instance == null) {
            _instance = new ServicesFactory();
        }

        return _instance;
    }

    private ServicesFactory() {
        _servicesList = new LinkedList<IService>();
    }

    public ServicesOperations getOperations() {
        for (IService iterator : _servicesList) {
            if (iterator instanceof ServicesOperations) {
                return (ServicesOperations)iterator;
            }
        }

        ServicesOperations rValue = new ServicesOperations();
        _servicesList.add(rValue);
        return rValue;
    }

    public ServicesAppConfigurations getConfigurationServices() {
        for (IService iterator : _servicesList) {
            if (iterator instanceof ServicesAppConfigurations) {
                return (ServicesAppConfigurations)iterator;
            }
        }

        ServicesAppConfigurations rValue = new ServicesAppConfigurations();
        _servicesList.add(rValue);
        return rValue;
    }

	public ServicesLanguage getLanguageServices() {
        for (IService iterator : _servicesList) {
            if (iterator instanceof ServicesLanguage) {
                return (ServicesLanguage)iterator;
            }
        }

        ServicesLanguage rValue = new ServicesLanguage();
        _servicesList.add(rValue);
        return rValue;
	}

    public ServicesEntity getEntityServices() {
        for (IService iterator : _servicesList) {
            if (iterator instanceof ServicesEntity) {
                return (ServicesEntity)iterator;
            }
        }

        ServicesAppConfigurations configs = this.getConfigurationServices();
        ServicesEntity rValue = new ServicesEntity(configs.getDataDirectory());
        _servicesList.add(rValue);
        return rValue;
    }
}
