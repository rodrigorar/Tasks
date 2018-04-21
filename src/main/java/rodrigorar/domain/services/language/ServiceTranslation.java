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

import rodrigorar.domain.interfaces.BaseService;
import rodrigorar.domain.pojos.Language;

public class ServiceTranslation
implements BaseService<String> {
    private String _key;
    private String _translation;

    public ServiceTranslation(String key) {
        _key = key;
    }

    public void execute() {
        // TODO: Get configured language from ServiceGetLanguageConfiguration
        // TODO: Get Language from ServiceGetLanguage
        // TODO: Set the translation to be returned
        // XXX: All state for the new services will be maintained at
        // configuration and data files, no state will be maintained in memory
        // from now on.
    }

    public String getResult() {
        return _translation;
    }
}
