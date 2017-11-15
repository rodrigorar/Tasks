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

package rodrigorar.configs;

public class AppConfigurations {
    private static AppConfigurations _instance;
    private String _baseDirectory;
    private String _dataDirectory;
    private String _language;

    public static AppConfigurations getInstance() {
        if (_instance == null) {
            _instance = new AppConfigurations();
        }
        return _instance;
    }

    private AppConfigurations() { /* Empty Constructor */ }

    public void setBaseDirectory(String baseDirectory) {
        _baseDirectory = baseDirectory;
    }

    public String getBaseDirectory() {
        return _baseDirectory;
    }

    public void setDataDirectory(String dataDirectory) {
        _dataDirectory = dataDirectory;
    }

    public String getDataDirectory() {
        return _dataDirectory;
    }

    public void setLanguage(String language) {
        _language = language;
    }

    public String getLanguage() {
        return _language;
    }
}
