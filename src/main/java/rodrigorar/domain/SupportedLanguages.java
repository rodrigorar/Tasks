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

package rodrigorar.domain;

import rodrigorar.domain.services.ServicesAppConfigurations;
import rodrigorar.domain.services.ServicesDomainFactory;

public class SupportedLanguages {
    public static final String EN = "EN";
    public static final String PT = "PT";

    public static enum Languages {
        EN {
            @Override
            public String getFile() {
                ServicesAppConfigurations configurationServices = 
                		ServicesDomainFactory.getConfigurationServices();
                
                String file = configurationServices.getBaseDirectory();
                file = file + "/languages/en_EN.xml";
                return file;
            }
        },
        PT {
            @Override
            public String getFile() {
                ServicesAppConfigurations configurationServices = 
                		ServicesDomainFactory.getConfigurationServices();
                
                String file = configurationServices.getBaseDirectory();
                file = file + "/languages/pt_PT.xml";
                return file;
            }
        };

        public abstract String getFile();
    }

    public static Languages getLanguage(String language) {
        Languages rValue = null;

        switch (language) {
            case EN:
                System.out.println("English");
                rValue = Languages.EN;
                break;
            case PT:
                rValue = Languages.PT;
                System.out.println("Portuguese");
                break;
            default:
                System.out.println("Default");
                rValue = Languages.EN;
        }

        return rValue;
    }
}
