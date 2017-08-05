package rodrigorar.data;

import org.jdom2.Element;

public interface IData<T> {

    public Element save(T task);

    public T load(Element taskElement);

}
