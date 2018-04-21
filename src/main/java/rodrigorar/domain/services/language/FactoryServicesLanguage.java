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

package rodrigorar.domain.services.language;

import rodrigorar.data.daos.LanguageDAO;
import rodrigorar.data.DAOFactory;
import rodrigorar.domain.pojos.Language;
import rodrigorar.domain.services.ServicesAppConfigurations;
import rodrigorar.domain.services.ServicesFactory;
import rodrigorar.domain.SupportedLanguages;

// TODO: Refactor this factory, there should be no state maintained here
// all state should be maintained by the configuration and data files.
public class FactoryServicesLanguage {
    private static FactoryServicesLanguage _instance;

    private Language _activeLanguage;

    public static final FactoryServicesLanguage getInstance() {
        if (_instance == null) {
            _instance = new FactoryServicesLanguage();
        }
        return _instance;
    }

    private FactoryServicesLanguage() {
        LanguageDAO languageDAO = DAOFactory.getInstance().getLanguageDAO();

        // TODO: This service has to be refactored to work with
        // the new services architecture.
        ServicesAppConfigurations configServices =
            ServicesFactory.getInstance().getConfigurationServices();

        _activeLanguage =
            languageDAO.load(
                SupportedLanguages.getLanguage(configServices.getLanguage())
            );
    }

    public ServiceTranslation getTranslationService(String key) {
        return new ServiceTranslation(_activeLanguage, key);
    }
}
