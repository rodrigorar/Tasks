package rodrigorar.data;

import org.jdom2.Element;

import rodrigorar.data.interfaces.IData;
import rodrigorar.data.utils.JDOMBuilder;
import rodrigorar.entities.AppConfigurations;

public class AppConfigurationsData
implements
IData<AppConfigurations> {
    public static final String CONFIGS = "configs";
    public static final String BASE_DIRECTORY = "base_directory";
    public static final String DATA_DIRECTORY = "data_directory";

    public Element save(AppConfigurations configs) {
        JDOMBuilder jdomUtils = new JDOMBuilder();
        Element configsElement = new Element(CONFIGS);

        Element baseDirectory =
            jdomUtils.buildStringElement(BASE_DIRECTORY, configs.getBaseDirectory());
        configsElement.addContent(baseDirectory);

        Element dataDirectory =
            jdomUtils.buildStringElement(DATA_DIRECTORY, configs.getDataDirectory());
        configsElement.addContent(dataDirectory);

        return configsElement;
    }

    public AppConfigurations load(Element configsElement) {
        AppConfigurations configs = AppConfigurations.getInstance();

        Element baseDirectoryElement = configsElement.getChild(BASE_DIRECTORY);
        String baseDirectory = baseDirectoryElement.getText().trim();

        Element dataDirectoryElement = configsElement.getChild(DATA_DIRECTORY);
        String dataDirectory = dataDirectoryElement.getText().trim();

        configs.setBaseDirectory(baseDirectory);
        configs.setDataDirectory(dataDirectory);

        return configs;
    }
}
