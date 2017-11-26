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

package rodrigorar.domain;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;

import rodrigorar.domain.interfaces.IEntity;
import rodrigorar.utils.IdGenerator;

public class Language
implements
IEntity {
    private String _id;
    private String _name;
    private String _simpleName;
    private Map<String, String> _translations;

    public Language(String name, String simpleName, Map<String, String> translations) {
        buildId();
        setTranslations(translations);
        setName(name);
        setSimpleName(simpleName);
    }

    private void buildId() {
        _id = IdGenerator.generateLanguageId();
    }

    public String getId() {
        return _id;
    }

    public void setName(String name) {
        if (name != null && !name.equals("")) {
            _name = name;
        }
    }

    public String getName() {
        return _name;
    }

    public void setSimpleName(String simpleName) {
        if (simpleName != null && !simpleName.equals("")) {
            _simpleName = simpleName;
        }
    }

    public String getSimpleName() {
        return _simpleName;
    }

    public void setTranslations(Map<String, String> translations) {
        _translations = translations;
    }

    public String getTranslation(String key) {
        return _translations.get(key);
    }
}
