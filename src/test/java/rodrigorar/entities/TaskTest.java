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

package rodrigorar.entities;

import org.junit.Test;

import rodrigorar.entities.Task;
import rodrigorar.entities.exceptions.InvalidTitleException;

public class TaskTest {
    public final String VALID_TITLE = "Shopping";
    public final String EMPTY_TITLE = "";
    public final String NULL_TITLE = null;
    private Task testTask;

    @Test
    public void validTitleTest()
    throws
    InvalidTitleException {
        Task testTask = new Task(VALID_TITLE);
    }

    @Test (expected = InvalidTitleException.class)
    public void emptyTitleTest()
    throws
    InvalidTitleException {
        Task testTask = new Task(EMPTY_TITLE);
    }

    @Test (expected = InvalidTitleException.class)
    public void nullTitleTest()
    throws
    InvalidTitleException {
        Task testTask = new Task(NULL_TITLE);
    }
}
