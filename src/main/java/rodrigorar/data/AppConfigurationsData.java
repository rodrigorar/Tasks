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

package rodrigorar.data;

import org.jdom2.Element;

import rodrigorar.data.interfaces.IData;
import rodrigorar.data.utils.JDOMBuilder;
import rodrigorar.domain.pojos.AppConfigurations;
import rodrigorar.utils.Constants.XMLLabels;
import rodrigorar.utils.SystemUtils;

public class AppConfigurationsData
implements
IData<AppConfigurations> {

    public Element save(AppConfigurations configs) {
        JDOMBuilder jdomUtils = new JDOMBuilder();
        Element configsElement = new Element(XMLLabels.CONFIGS);

        Element baseDirectory =
            jdomUtils.buildStringElement(XMLLabels.BASE_DIRECTORY, configs.getBaseDirectory());
        configsElement.addContent(baseDirectory);

        Element dataDirectory =
            jdomUtils.buildStringElement(XMLLabels.DATA_DIRECTORY, configs.getDataDirectory());
        configsElement.addContent(dataDirectory);

        Element language =
            jdomUtils.buildStringElement(XMLLabels.LANGUAGE, configs.getLanguage());
        configsElement.addContent(language);

        return configsElement;
    }

    public AppConfigurations load(Element configsElement) {
        AppConfigurations configs = AppConfigurations.getInstance();

        Element baseDirectoryElement = configsElement.getChild(XMLLabels.BASE_DIRECTORY);
        String baseDirectory = baseDirectoryElement.getText().trim();
        if (baseDirectory == null || baseDirectory.equals("")) {
            baseDirectory = SystemUtils.getDefaultLinuxDirectory();
        }

        Element dataDirectoryElement = configsElement.getChild(XMLLabels.DATA_DIRECTORY);
        String dataDirectory = dataDirectoryElement.getText().trim();
        if (dataDirectory == null || dataDirectory.equals("")) {
            dataDirectory = SystemUtils.getDefaultLinuxData();
        }

        Element languageElement = configsElement.getChild(XMLLabels.LANGUAGE);
        String language = languageElement.getText().trim();
        if (language == null || language.equals("")) {
            language = "EN"; // FIXME: Set system wide default language just
                            // just like the rest of the settings.
        }

        configs.setBaseDirectory(baseDirectory);
        configs.setDataDirectory(dataDirectory);
        configs.setLanguage(language);

        return configs;
    }
}
