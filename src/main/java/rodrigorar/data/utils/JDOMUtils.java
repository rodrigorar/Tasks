package rodrigorar.data.utils;

import org.jdom2.Element;

public class JDOMUtils {

    public Element buildStringElement(String name, String value) {
        Element stringElement = new Element(name);
        stringElement.setText(value);
        return stringElement;
    }
}
