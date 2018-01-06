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

import rodrigorar.data.DAOFactory;
import rodrigorar.data.daos.AppConfigurationsDAO;
import rodrigorar.domain.pojos.AppConfigurations;

public class ServicesAppConfigurations {
    private static ServicesAppConfigurations _instance;

    private AppConfigurations _appConfigurations;

    private AppConfigurationsDAO _appConfigurationsDAO;

    public static ServicesAppConfigurations getInstance() {
        if (_instance == null) {
            _instance = new ServicesAppConfigurations();
        }

        return _instance;
    }

    private ServicesAppConfigurations() {
    	_appConfigurationsDAO = DAOFactory.getInstance().getAppConfigurationsDAO();
        _appConfigurations = _appConfigurationsDAO.load();
    }

    public AppConfigurations getAppConfigurations() {
    	return _appConfigurations;
    }

    public String getDataDirectory() {
    	return _appConfigurations.getDataDirectory();
    }

    public void setDataDirectory(String dataDirectory) {
    	_appConfigurations.setDataDirectory(dataDirectory);
    }

    public String getLanguage() {
    	return _appConfigurations.getLanguage();
    }

    public void setLanguage(String language) {
    	_appConfigurations.setLanguage(language);
    }

    public String getBaseDirectory() {
    	return _appConfigurations.getBaseDirectory();
    }

    public void setBaseDirectory(String baseDirectory) {
    	_appConfigurations.setBaseDirectory(baseDirectory);
    }

    public void save() {
    	_appConfigurationsDAO.save(_appConfigurations);
    }


}
