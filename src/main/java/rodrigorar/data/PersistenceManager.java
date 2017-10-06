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
import rodrigorar.entities.AppConfigurations;
import rodrigorar.entities.exceptions.InvalidTitleException;
import rodrigorar.data.AppConfigurationsData;
import rodrigorar.utils.AppConfig;

public class PersistenceManager {
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String TASK = "task";
    public static final String TASK_LIST = "task_list";
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

        SAXBuilder builder = new SAXBuilder();
        File configFile = new File(AppConfig.CONFIG_FILE);
        System.out.println("Opened File");
        try {
            Document document = (Document)builder.build(AppConfig.CONFIG_FILE);
            Element configElement = document.getRootElement();

            AppConfigurationsData configData = new AppConfigurationsData();
            configs = configData.load(configElement);
        } catch (IOException | JDOMException exception) {
            exception.printStackTrace();
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
            outputter.output(document, new FileWriter(AppConfig.CONFIG_FILE));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
