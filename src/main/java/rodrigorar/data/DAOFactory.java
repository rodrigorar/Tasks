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

import rodrigorar.data.daos.BaseDAO;
import rodrigorar.data.daos.TaskListDAO;
import rodrigorar.data.daos.TaskDAO;
import rodrigorar.data.daos.LanguageDAO;
import rodrigorar.data.daos.AppConfigurationsDAO;

public class DAOFactory {

    public static TaskListDAO getTaskListDAO() {
        return new TaskListDAO();
    }

    public static TaskDAO getTaskDAO() {
        return new TaskDAO();
    }

    public static LanguageDAO getLanguageDAO() {
        return new LanguageDAO();
    }

    public static AppConfigurationsDAO getAppConfigurationsDAO() {
        return new AppConfigurationsDAO();
    }
}