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

public final class Constants {

    public static class Labels {
        public static final String SAVE = "save";
        public static final String CANCEL = "cancel";
        public static final String EDIT = "edit";
        public static final String DESCRIPTION = "description";
        public static final String TITLE = "title";
        public static final String TASK = "task";
        public static final String TASKS = "tasks";
        public static final String TASK_LIST = "task_list";
        public static final String NEW_TASK = "new_task";
        public static final String SEARCH = "search";
        public static final String DELETE = "delete";
        public static final String SETTINGS = "settings";
        public static final String CHOOSE_FILE = "choose_data_file";
        public static final String FILE_BROWSER = "file_browser";
        public static final String LANGUAGE = "language";
        public static final String PRIORITY = "priority";
    }

    public static class SettingsXML {
        public static final String BASE_DIRECTORY = "base_directory";
        public static final String DATA_DIRECTORY = "data_directory";
        public static final String PRIORITY_DIRECTORY = "priority_directory";
        public static final String CONFIGS = "configs";
        public static final String LANGUAGE = "language";
    }

    public static class TaskXML {
        public static final String ID = "id";
        public static final String TASK = "task";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String PRIORITY = "priority";
        public static final String TASK_LIST_ID = "task_list_id";
    }

    public static class TaskListXML {
        public static final String ID = "id";
        public static final String TASK_LIST = "task_list";
        public static final String TASK = "task";
    }

    public static class LanguageXML {
        public static final String ID = "id";
        public static final String LANGUAGE = "language";
        public static final String TRANSLATIONS = "translations";
        public static final String TRANSLATION = "translation";
        public static final String NAME = "name";
        public static final String SIMPLE_NAME = "simple_name";
        public static final String KEY = "key";
        public static final String VALUE = "value";
    }

    public static class PriorityXML {
        public static final String ID = "id";
        public static final String PRIORITY = "priority";
        public static final String NAME = "name";
        public static final String VALUE = "value";
    }

}
