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

import rodrigorar.domain.SupportedLanguages;
import rodrigorar.domain.SupportedLanguages.Languages;
import rodrigorar.domain.pojos.AppConfigurations;
import rodrigorar.data.services.ServicesPersistence;
import rodrigorar.domain.pojos.Language;

public class ServicesLanguage {
    private static ServicesLanguage _instance;
    private ServicesPersistence _servicesPersistence;
    private AppConfigurations _configurations;
    private Language _activeLanguage;

    public static ServicesLanguage getInstance() {
        if (_instance == null) {
            _instance = new ServicesLanguage();
        }
        return _instance;
    }

    private ServicesLanguage() {
        _servicesPersistence = ServicesPersistence.getInstance();
        _configurations = AppConfigurations.getInstance();
        _activeLanguage =
            _servicesPersistence.loadLanguage(
                SupportedLanguages.getLanguage(_configurations.getLanguage())
            );
    }

    public Language getActiveLanguage() {
        return _activeLanguage;
    }

    public void setActiveLanguage(String languageId) {
        _activeLanguage =
            _servicesPersistence.loadLanguage(
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
