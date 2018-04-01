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
