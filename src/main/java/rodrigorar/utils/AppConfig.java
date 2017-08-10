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

package rodrigorar.utils;

public class AppConfig {
    private static AppConfig _instance;
    private String _username;
    private String _baseDirectory;
    private String _dataDirectory;

    public static AppConfig getInstance() {
        if (_instance == null) {
            _instance = new AppConfig();
        }
        return _instance;
    }

    private AppConfig() {
        _username = System.getProperty("user.name");
        _baseDirectory = "/home/" + _username + "/.tasks";
        _dataDirectory = "/home/" + _username + "/tasks.xml";
    }

    public String getBaseDirectory() {
        return _baseDirectory;
    }

    public String getDataDirectory() {
        return _dataDirectory;
    }
}
