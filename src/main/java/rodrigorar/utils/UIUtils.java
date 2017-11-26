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

import java.awt.Dimension;
import java.awt.Component;

import javax.swing.JViewport;
import javax.swing.JScrollPane;

public class UIUtils<T> {

    public static <T extends Component> JScrollPane buildScrollable(
                    T instance, int width, int height) {
        JScrollPane scrollPane = new JScrollPane(instance);
        scrollPane.setMaximumSize(new Dimension(width, height));

        return scrollPane;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T getInnerComponent(Class<T> clazz, JScrollPane scrollPane) {
        JViewport viewport = scrollPane.getViewport();
        return (T) viewport.getView();
    }
}
