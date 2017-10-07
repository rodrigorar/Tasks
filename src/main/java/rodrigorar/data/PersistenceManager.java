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

import rodrigorar.entities.Task;
import rodrigorar.entities.TaskList;
import rodrigorar.entities.exceptions.InvalidTitleException;
import rodrigorar.configs.AppConfigurations;
import rodrigorar.configs.AppConfigurationsData;
import rodrigorar.utils.SystemUtils;

public class PersistenceManager {
    private static PersistenceManager _instance;

    public static PersistenceManager getInstance() {
        if (_instance == null) {
            _instance = new PersistenceManager();
        }
        return _instance;
    }

    private PersistenceManager() { /* Empty Constructor */ }

    public TaskList loadTaskList() {
        AppConfigurations configs = AppConfigurations.getInstance();
        TaskList loadedTaskList = null;

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(configs.getDataDirectory());
        try {
            Document document = (Document)builder.build(xmlFile);
            Element taskListElement = document.getRootElement();

            TaskListData listData = new TaskListData();
            loadedTaskList = listData.load(taskListElement);
        } catch (IOException  | JDOMException exception) {
            exception.printStackTrace();
        }

        return loadedTaskList;
    }

    public void saveTaskList(TaskList taskList) {
        AppConfigurations configs = AppConfigurations.getInstance();

        try {
            TaskListData listData = new TaskListData();
            Element taskListElement = listData.save(taskList);
            Document document = new Document(taskListElement);
            XMLOutputter outputter = new XMLOutputter();

            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, new FileWriter(configs.getDataDirectory()));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public AppConfigurations loadAppConfigurations() {
        AppConfigurations configs = null;

        try {
            SAXBuilder builder = new SAXBuilder();
            Document document = (Document)builder.build(SystemUtils.getDefaultLinuxSettings());
            Element configElement = document.getRootElement();

            AppConfigurationsData configData = new AppConfigurationsData();
            configs = configData.load(configElement);
        } catch (IOException | JDOMException exception) {
            System.out.println("No Settings Exist... Creating new ones");
            configs = AppConfigurations.getInstance();
            configs.setBaseDirectory(SystemUtils.getDefaultLinuxDirectory());
            configs.setDataDirectory(SystemUtils.getDefaultLinuxData());
        }

        return configs;
    }

    public void saveAppConfigurations(AppConfigurations configs) {
        try {
            AppConfigurationsData configData = new AppConfigurationsData();
            Element configsElement = configData.save(configs);
            Document document = new Document(configsElement);
            XMLOutputter outputter = new XMLOutputter();

            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, new FileWriter(SystemUtils.getDefaultLinuxSettings()));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
