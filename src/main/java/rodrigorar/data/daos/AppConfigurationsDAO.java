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

package rodrigorar.data.daos;

import org.jdom2.Element;

import rodrigorar.domain.pojos.AppConfigurations;
import rodrigorar.data.daos.BaseDAO;
import rodrigorar.utils.Constants.SettingsXML;
import rodrigorar.utils.SystemUtils;

public class AppConfigurationsDAO
extends
BaseDAO<AppConfigurations> {

    @Override
    public Element convertToElement(AppConfigurations configs) {
        Element baseDirectory = new Element(SettingsXML.BASE_DIRECTORY);
        baseDirectory.setText(configs.getBaseDirectory());

        Element dataDirectory = new Element(SettingsXML.DATA_DIRECTORY);
        dataDirectory.setText(configs.getDataDirectory());

        Element priorityDirectory = new Element(SettingsXML.PRIORITY_DIRECTORY);
        priorityDirectory.setText(configs.getPriorityDirectory());

        Element language = new Element(SettingsXML.LANGUAGE);
        language.setText(configs.getLanguage());

        Element configsElement = new Element(SettingsXML.CONFIGS);
        configsElement.addContent(baseDirectory);
        configsElement.addContent(dataDirectory);
        configsElement.addContent(priorityDirectory);
        configsElement.addContent(language);

        return configsElement;
    }

    @Override
    public AppConfigurations convertToObject(Element configsElement) {
        Element baseDirectoryElement = configsElement.getChild(SettingsXML.BASE_DIRECTORY);
        String baseDirectory = baseDirectoryElement.getText().trim();
        if (baseDirectory == null || baseDirectory.equals("")) {
            baseDirectory = SystemUtils.getDefaultLinuxDirectory();
        }

        Element dataDirectoryElement = configsElement.getChild(SettingsXML.DATA_DIRECTORY);
        String dataDirectory = dataDirectoryElement.getText().trim();
        if (dataDirectory == null || dataDirectory.equals("")) {
            dataDirectory = SystemUtils.getDefaultLinuxData();
        }

        Element priorityDirectoryElement = configsElement.getChild(SettingsXML.PRIORITY_DIRECTORY);
        String priorityDirectory = priorityDirectoryElement.getText().trim();
        if (priorityDirectory == null || priorityDirectory.equals("")) {
            priorityDirectory = SystemUtils.getDefaultPriorityDirectory();
        }

        Element languageElement = configsElement.getChild(SettingsXML.LANGUAGE);
        String language = languageElement.getText().trim();
        if (language == null || language.equals("")) {
            language = "EN";
        }

        AppConfigurations configs = new AppConfigurations();
        configs.setBaseDirectory(baseDirectory);
        configs.setDataDirectory(dataDirectory);
        configs.setPriorityDirectory(priorityDirectory);
        configs.setLanguage(language);

        return configs;
    }

    public AppConfigurations load() {
        final AppConfigurations configs;

        Element rootElement = getRootElement(SystemUtils.getDefaultLinuxSettings());
        if (rootElement != null) {
            configs = convertToObject(rootElement);
        } else {
            configs = new AppConfigurations();
            configs.setBaseDirectory(SystemUtils.getDefaultLinuxDirectory());
            configs.setDataDirectory(SystemUtils.getDefaultLinuxData());
            configs.setPriorityDirectory(SystemUtils.getDefaultPriorityDirectory());
            configs.setLanguage(SystemUtils.getDefaultLanguage());
        }

        return configs;
    }

    public void save(AppConfigurations configs) {
        writeToFile(convertToElement(configs), SystemUtils.getDefaultLinuxSettings());
    }
}
