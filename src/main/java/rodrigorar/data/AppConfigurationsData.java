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
import rodrigorar.entities.AppConfigurations;

public class AppConfigurationsData
implements
IData<AppConfigurations> {
    public static final String CONFIGS = "configs";
    public static final String BASE_DIRECTORY = "base_directory";
    public static final String DATA_DIRECTORY = "data_directory";

    public Element save(AppConfigurations configs) {
        JDOMBuilder jdomUtils = new JDOMBuilder();
        Element configsElement = new Element(CONFIGS);

        Element baseDirectory =
            jdomUtils.buildStringElement(BASE_DIRECTORY, configs.getBaseDirectory());
        configsElement.addContent(baseDirectory);

        Element dataDirectory =
            jdomUtils.buildStringElement(DATA_DIRECTORY, configs.getDataDirectory());
        configsElement.addContent(dataDirectory);

        return configsElement;
    }

    public AppConfigurations load(Element configsElement) {
        AppConfigurations configs = AppConfigurations.getInstance();

        Element baseDirectoryElement = configsElement.getChild(BASE_DIRECTORY);
        String baseDirectory = baseDirectoryElement.getText().trim();

        Element dataDirectoryElement = configsElement.getChild(DATA_DIRECTORY);
        String dataDirectory = dataDirectoryElement.getText().trim();
        
        configs.setBaseDirectory(baseDirectory);
        configs.setDataDirectory(dataDirectory);

        System.out.println("Base Directory: " + configs.getBaseDirectory());
        System.out.println("Data Directory: " + configs.getDataDirectory());

        return configs;
    }
}
