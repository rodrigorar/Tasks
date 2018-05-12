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

import rodrigorar.data.DAOFactory;
import rodrigorar.data.daos.AppConfigurationsDAO;
import rodrigorar.domain.interfaces.BaseService;
import rodrigorar.domain.pojos.AppConfigurations;

public class ServiceSetDataDirectory 
implements BaseService<String> {

	private final String _dataDirectory;
	private String _result;
	
	public ServiceSetDataDirectory(String dataDirectory) {
		_dataDirectory = dataDirectory;
	}
	
	@Override
	public void execute() {
		AppConfigurationsDAO configsDAO = DAOFactory.getAppConfigurationsDAO(); 
		AppConfigurations configs = configsDAO.load();
		configs.setDataDirectory(_dataDirectory);
		configsDAO.save(configs);
		System.out.println("Data Directory Saved");
		_result = configs.getDataDirectory();
	}

	@Override
	public String getResult() {
		return _result;
	}

}
