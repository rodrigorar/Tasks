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

import java.util.LinkedList;
import java.util.List;

import rodrigorar.data.DAOFactory;
import rodrigorar.data.daos.LanguageDAO;
import rodrigorar.domain.SupportedLanguages;
import rodrigorar.domain.SupportedLanguages.Languages;
import rodrigorar.domain.interfaces.BaseService;
import rodrigorar.domain.interfaces.IService;
import rodrigorar.domain.pojos.Language;
import rodrigorar.domain.services.configuration.FactoryServicesConfiguration;

public class ServicesLanguage
implements
IService {
    private Language _activeLanguage;

    public ServicesLanguage() {
        LanguageDAO languageDAO = DAOFactory.getLanguageDAO();
        
        BaseService<?> getCurrentLanguageService =
	        	FactoryServicesConfiguration.getServiceGetCurrentLanguage();
        getCurrentLanguageService.execute();
        _activeLanguage =
            languageDAO.load(
                SupportedLanguages.getLanguage(
                		(String)getCurrentLanguageService.getResult()
                	)
            );
    }

    public Language getActiveLanguage() {
        return _activeLanguage;
    }

    public void setActiveLanguage(String languageId) {
        LanguageDAO languageDAO = DAOFactory.getLanguageDAO();
        _activeLanguage =
            languageDAO.load(
                SupportedLanguages.getLanguage(languageId)
            );
    }

    public String getTranslation(String key) {
        return _activeLanguage.getTranslation(key);
    }

    public List<String> getSupportedLanguages() {
        List<String> languages = new LinkedList<String>();

        for (Languages language : SupportedLanguages.Languages.values()) {
            languages.add(language.toString());
        }

        return languages;
    }
}
