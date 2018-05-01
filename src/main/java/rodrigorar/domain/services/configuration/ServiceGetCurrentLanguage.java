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
import rodrigorar.domain.interfaces.BaseService;
import rodrigorar.domain.pojos.Language;
import rodrigorar.domain.pojos.AppConfigurations;
import rodrigorar.domain.SupportedLanguages;

public class ServiceGetCurrentLanguage
implements BaseService<Language> {
    private Language _currentLanguage;

    @Override
    public void execute() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AppConfigurationsDAO appConfigsDao = daoFactory.getAppConfigurationsDAO();
        AppConfigurations appConfigurations = appConfigsDao.load();

        LanguageDAO languageDao = daoFactory.getLanguageDAO();
        String language = appConfigurations.getLanguage();
        SupportedLanguages.Languages languageEnum = SupportedLanguages.getLanguage(language);
        _currentLanguage = languageDao.load(languageEnum);
    }

    @Override
    public Language getResult() {
        return _currentLanguage;
    }
}
