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

import org.jdom2.Element;

import rodrigorar.data.interfaces.IData;
import rodrigorar.data.utils.JDOMBuilder;
import rodrigorar.configs.AppConfigurations;
import rodrigorar.utils.Constants.XMLLabels;

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

        return configsElement;
    }

    public AppConfigurations load(Element configsElement) {
        AppConfigurations configs = AppConfigurations.getInstance();

        Element baseDirectoryElement = configsElement.getChild(XMLLabels.BASE_DIRECTORY);
        String baseDirectory = baseDirectoryElement.getText().trim();

        Element dataDirectoryElement = configsElement.getChild(XMLLabels.DATA_DIRECTORY);
        String dataDirectory = dataDirectoryElement.getText().trim();

        configs.setBaseDirectory(baseDirectory);
        configs.setDataDirectory(dataDirectory);

        return configs;
    }
}
