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
import rodrigorar.domain.interfaces.IService;
import rodrigorar.domain.pojos.AppConfigurations;

public class ServicesAppConfigurations
implements
IService {
    private AppConfigurations _appConfigurations;

    public ServicesAppConfigurations() {
        AppConfigurationsDAO appConfigsDAO = DAOFactory.getInstance().getAppConfigurationsDAO();
        _appConfigurations = appConfigsDAO.load();
    }

    public AppConfigurations getAppConfigurations() {
    	return _appConfigurations;
    }

    public String getDataDirectory() {
    	return _appConfigurations.getDataDirectory();
    }

    public void setDataDirectory(String dataDirectory) {
    	_appConfigurations.setDataDirectory(dataDirectory);
        save();
    }

    public String getLanguage() {
    	return _appConfigurations.getLanguage();
    }

    // TODO: Refactor this so the save is called in a central location
    public void setLanguage(String language) {
    	_appConfigurations.setLanguage(language);
        save();
    }

    public String getBaseDirectory() {
    	return _appConfigurations.getBaseDirectory();
    }

    public void setBaseDirectory(String baseDirectory) {
    	_appConfigurations.setBaseDirectory(baseDirectory);
        save();
    }

    public void setPriorityDirectory(String priorityDirectory) {
        _appConfigurations.setPriorityDirectory(priorityDirectory);
        save();
    }

    public String getPriorityDirectory() {
        System.out.println("Priority Directory " + _appConfigurations.getPriorityDirectory());
        return _appConfigurations.getPriorityDirectory();
    }

    public void save() {
        AppConfigurationsDAO appConfigsDAO = DAOFactory.getInstance().getAppConfigurationsDAO();
    	appConfigsDAO.save(_appConfigurations);
    }


}
