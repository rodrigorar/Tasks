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

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import rodrigorar.data.daos.AppConfigurationsDAO;
import rodrigorar.domain.pojos.AppConfigurations;
import rodrigorar.utils.SystemUtils;

public class TestAppConfigurationsDAO {

    @Test
    public void testConvertToElement() {
        // TODO
    }

    @Test
    public void testConvertToObject() {
        // TODO
    }

    @Test
    public void testLoad() {
        AppConfigurationsDAO dao = new AppConfigurationsDAO();

        AppConfigurations configurations = dao.load();

        assertEquals(configurations.getBaseDirectory(), SystemUtils.getDefaultLinuxDirectory());
        assertEquals(configurations.getDataDirectory(), SystemUtils.getDefaultLinuxData());
        assertEquals(configurations.getLanguage(), SystemUtils.getDefaultLanguage());
    }

}
