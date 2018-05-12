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

import java.util.List;
import java.util.LinkedList;

import rodrigorar.data.daos.BaseDAO;
import rodrigorar.data.daos.TaskListDAO;
import rodrigorar.data.daos.TaskDAO;
import rodrigorar.data.daos.LanguageDAO;
import rodrigorar.data.daos.AppConfigurationsDAO;
import rodrigorar.data.daos.PriorityDAO;

// TODO: Try to abstract the iteration to a single method.
public class DAOFactory {
    private static DAOFactory _instance;
    private List<BaseDAO<?>> _daoList;

    public static DAOFactory getInstance() {
        if (_instance == null) {
            _instance = new DAOFactory();
        }

        return _instance;
    }

    private DAOFactory() {
        _daoList = new LinkedList<BaseDAO<?>>();
    }

    // TODO: Refactor this so it has only one return statement.
    public TaskListDAO getTaskListDAO() {
        for (BaseDAO<?> iterator : _daoList) {
            if (iterator instanceof TaskListDAO) {
                return (TaskListDAO)iterator;
            }
        }

        TaskListDAO rValue = new TaskListDAO();
        _daoList.add(rValue);
        return rValue;
    }

    public static TaskDAO getTaskDAO() {
        return new TaskDAO();
    }

    // TODO: Refactor this so it has only one return statement.
    public LanguageDAO getLanguageDAO() {
        for (BaseDAO<?> iterator : _daoList) {
            if (iterator instanceof LanguageDAO) {
                return (LanguageDAO)iterator;
            }
        }

        LanguageDAO rValue = new LanguageDAO();
        _daoList.add(rValue);
        return rValue;
    }

    public static AppConfigurationsDAO getAppConfigurationsDAO() {
        return new AppConfigurationsDAO();
    }

    public static PriorityDAO getPriorityDAO() {
        return new PriorityDAO();
    }
}
