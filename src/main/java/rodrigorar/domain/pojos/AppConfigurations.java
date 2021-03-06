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

package rodrigorar.domain.pojos;

public class AppConfigurations {
    private String _baseDirectory;
    private String _dataDirectory;
    private String _priorityDirectory;
    private String _language;

    public AppConfigurations() {
    	/* Empty Constructor */
    }

    public AppConfigurations(
        String baseDirectory,
        String dataDirectory,
        String priorityDirectory,
        String language) {

        _baseDirectory = baseDirectory;
        _dataDirectory = dataDirectory;
        _priorityDirectory = priorityDirectory;
        _language = language;
    }

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

    public void setPriorityDirectory(String priorityDirectory) {
        _priorityDirectory = priorityDirectory;
    }

    public String getPriorityDirectory() {
        return _priorityDirectory;
    }

    public void setLanguage(String language) {
        _language = language;
    }

    public String getLanguage() {
        return _language;
    }
}
