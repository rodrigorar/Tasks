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

public class IdGenerator {

    private static String generateId(StringBuilder builder) {
        long numberId = (long)(Math.random() * 1000000000);
        builder.append(numberId);
        return builder.toString();
    }

    public static String generateTaskId() {
        StringBuilder id = new StringBuilder("task-");
        return generateId(id);
    }

    public static String generateTaskListId() {
        StringBuilder id = new StringBuilder("tasklist-");
        return generateId(id);
    }

    public static String generateLanguageId() {
        StringBuilder id = new StringBuilder("language-");
        return generateId(id);
    }

    public static String generatePriorityId() {
        StringBuilder id = new StringBuilder("priority-");
        return generateId(id);
    }
}
