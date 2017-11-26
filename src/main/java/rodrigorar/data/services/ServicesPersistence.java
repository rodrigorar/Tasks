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

package rodrigorar.data.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import rodrigorar.domain.pojos.Task;
import rodrigorar.domain.pojos.TaskList;
import rodrigorar.domain.pojos.Language;
import rodrigorar.domain.exceptions.InvalidTitleException;
import rodrigorar.configs.AppConfigurations;
import rodrigorar.configs.SupportedLanguages;
import rodrigorar.data.AppConfigurationsData;
import rodrigorar.data.LanguageData;
import rodrigorar.data.TaskListData;
import rodrigorar.utils.SystemUtils;

public class ServicesPersistence {
    private static ServicesPersistence _instance;

    public static ServicesPersistence getInstance() {
        if (_instance == null) {
            _instance = new ServicesPersistence();
        }
        return _instance;
    }

    private ServicesPersistence() { /* Empty Constructor */ }

    private Element getRootElement(String filepath) {
        Element rootElement = null;

        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(filepath);
            Document document = (Document)builder.build(xmlFile);
            rootElement = document.getRootElement();
        } catch (IOException | JDOMException exception) {
            exception.printStackTrace();
        }

        return rootElement;
    }

    private void writeToFile(Element rootElement, String filepath) {
        try {
            Document document = new Document(rootElement);
            XMLOutputter outputter = new XMLOutputter();

            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, new FileWriter(filepath));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public TaskList loadTaskList() {
        AppConfigurations configs = AppConfigurations.getInstance();
        TaskList loadedTaskList = null;

        Element rootElement = getRootElement(configs.getDataDirectory());
        if (rootElement != null) {
            TaskListData listData = new TaskListData();
            loadedTaskList = listData.load(rootElement);
        }

        return loadedTaskList;
    }

    public void saveTaskList(TaskList taskList) {
        AppConfigurations configs = AppConfigurations.getInstance();
        TaskListData listData = new TaskListData();
        writeToFile(listData.save(taskList), configs.getDataDirectory());
    }

    public AppConfigurations loadAppConfigurations() {
        AppConfigurations configs = null;

        Element rootElement = getRootElement(SystemUtils.getDefaultLinuxSettings());
        if (rootElement != null) {
            AppConfigurationsData configData = new AppConfigurationsData();
            configs = configData.load(rootElement);
        } else {
            configs = AppConfigurations.getInstance();
            configs.setBaseDirectory(SystemUtils.getDefaultLinuxDirectory());
            configs.setDataDirectory(SystemUtils.getDefaultLinuxData());
        }

        return configs;
    }

    public void saveAppConfigurations(AppConfigurations configs) {
        AppConfigurationsData configData = new AppConfigurationsData();
        writeToFile(configData.save(configs), SystemUtils.getDefaultLinuxSettings());
    }

    public Language loadLanguage(SupportedLanguages.Languages language) {
        Language languageEntity = null;

        Element rootElement = getRootElement(language.getFile());
        if (rootElement != null) {
            LanguageData languageData = new LanguageData();
            languageEntity = languageData.load(rootElement);
        }

        return languageEntity;
    }
}
