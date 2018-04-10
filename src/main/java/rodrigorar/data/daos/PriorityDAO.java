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

import java.util.List;
import java.util.LinkedList;
import org.jdom2.Element;

import rodrigorar.data.daos.BaseDAO;
import rodrigorar.domain.pojos.Priority;
import rodrigorar.utils.Constants.XMLLabels;

public class PriorityDAO
extends
BaseDAO<Priority> {

    @Override
    public Priority convertToObject(Element dataElement) {
        Element idElement = dataElement.getChild(XMLLabels.ID);
        String id = idElement.getText().trim();

        Element nameElement = dataElement.getChild(XMLLabels.NAME);
        String name = nameElement.getText().trim();

        Element valueElement = dataElement.getChild(XMLLabels.VALUE);
        String value = valueElement.getText().trim();

        return new Priority(id, name, Integer.valueOf(value));
    }

    @Override
    public Element convertToElement(Priority dataObject) {
        Element idElement = new Element(XMLLabels.ID);
        idElement.setText(dataObject.getId());

        Element nameElement = new Element(XMLLabels.NAME);
        nameElement.setText(dataObject.getName());

        Element valueElement = new Element(XMLLabels.ID);
        valueElement.setText(dataObject.getValue().toString());

        Element priorityElement = new Element(XMLLabels.PRIORITY);
        priorityElement.addContent(idElement);
        priorityElement.addContent(nameElement);
        priorityElement.addContent(valueElement);

        return priorityElement;
    }

    public List<Priority> load(String dataDirectory) {
        List<Priority> priorityList = new LinkedList<Priority>();

        Element rootElement = getRootElement(dataDirectory);
        if (rootElement != null) {
            List<Element> priorityElementList = rootElement.getChildren(XMLLabels.PRIORITY);
            for (Element priorityElement : priorityElementList) {
                Priority priority = convertToObject(priorityElement);
                priorityList.add(priority);
            }
        }

        return priorityList;
    }
}
