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
import rodrigorar.data.daos.LanguageDAO;
import rodrigorar.domain.SupportedLanguages;
import rodrigorar.domain.interfaces.BaseService;
import rodrigorar.domain.pojos.AppConfigurations;
import rodrigorar.domain.pojos.Language;

public class ServiceSetCurrentLanguage 
implements BaseService<Language> {

	private final String _currentLanguage;
	private Language _result;
	
	public ServiceSetCurrentLanguage(String currentLanguage) {
		_currentLanguage = currentLanguage;
	}
	
	@Override
	public void execute() {
		
		SupportedLanguages.Languages language = 
				SupportedLanguages.getLanguage(_currentLanguage);
		LanguageDAO languageDAO = DAOFactory.getLanguageDAO();
		Language loadedLanguage = languageDAO.load(language);		
		if (loadedLanguage == null) {
			throw new NullPointerException("Theres is not language " + language.name());
		}

		AppConfigurationsDAO appConfigsDAO = DAOFactory.getAppConfigurationsDAO();
		AppConfigurations configs = appConfigsDAO.load();
		configs.setLanguage(loadedLanguage.getSimpleName());
		appConfigsDAO.save(configs);
		
		_result = loadedLanguage;
	}

	@Override
	public Language getResult() {
		return _result;
	}

}
