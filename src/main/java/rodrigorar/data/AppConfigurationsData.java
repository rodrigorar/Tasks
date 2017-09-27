package rodrigorar.data;

import org.jdom2.Element;

import rodrigorar.data.interfaces.IData;
import rodrigorar.entities.AppConfigurations;

public class AppConfigurationsData
implements
IData<AppConfigurations> {

    public Element save(AppConfigurations configs) {
        Element configsElement = new Element("configs");

        // TODO: Implement the parsing of the configurations
        // entity.

        return configsElement;
    }

    public AppConfigurations load(Element configsElement) {
        AppConfigurations configs = AppConfigurations.getInstance();

        // TODO: Implement the construction of the configs
        // object.

        return configs;
    }
}
