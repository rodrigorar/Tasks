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

package rodrigorar.domain.services.configuration;

import rodrigorar.domain.interfaces.BaseService;

public class FactoryServicesConfiguration {

    public static ServiceGetCurrentLanguage getServiceGetCurrentLanguage() {
        return new ServiceGetCurrentLanguage();
    }

    public static BaseService<?> getServicesSetCurrentLanguage(String currentLanguage) {
    	return new ServiceSetCurrentLanguage(currentLanguage);
    }

    public static BaseService<?> getServiceGetDataDirectory() {
        return new ServiceGetDataDirectory();
    }

    public static BaseService<?> getServiceSetDataDirectory(String dataDirectory) {
    	return new ServiceSetDataDirectory(dataDirectory);
    }

    public static BaseService<?> getServiceGetPriorityDirectory() {
        return new ServiceGetPriorityDirectory();
    }

    public static BaseService<?> getServiceGetBaseDirectory() {
        return new ServiceGetBaseDirectory();
    }
    
}
