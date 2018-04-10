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

public class SystemUtils {

    public static String getDefaultLinuxDirectory() {
        String homeDirectory = System.getProperty("user.home");
        StringBuilder builder = new StringBuilder(homeDirectory);
        builder.append("/");
        builder.append(".tasks");
        return builder.toString();
    }

    public static String getDefaultLinuxData() {
        StringBuilder builder = new StringBuilder(getDefaultLinuxDirectory());
        builder.append("/");
        builder.append("tasks.xml");
        return builder.toString();
    }

    public static String getDefaultLinuxSettings() {
        StringBuilder builder = new StringBuilder(getDefaultLinuxDirectory());
        builder.append("/");
        builder.append("settings.xml");
        return builder.toString();
    }

    public static String getDefaultPriorityDirectory() {
        StringBuilder builder = new StringBuilder(getDefaultLinuxDirectory());
        builder.append("/");
        builder.append("priorities.xml");
        return builder.toString();
    }

    public static String getDefaultLanguage() {
    	return "EN";
    }
}
