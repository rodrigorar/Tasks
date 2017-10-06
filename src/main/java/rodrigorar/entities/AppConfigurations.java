package rodrigorar.entities;

public class AppConfigurations {
    private static AppConfigurations _instance;
    private String _baseDirectory;
    private String _dataDirectory;

    public static AppConfigurations getInstance() {
        if (_instance == null) {
            _instance = new AppConfigurations();
        }
        return _instance;
    }

    private AppConfigurations() { /* Empty Constructor */ }

    public void setBaseDirectory(String baseDirectory) {
        _baseDirectory = baseDirectory;
    }

    public String getBaseDirectory() {
        return _baseDirectory;
    }

    public void setDataDirectory(String dataDirectory) {
        _dataDirectory = dataDirectory;
    }

    public String getDataDirectory() {
        return _dataDirectory;
    }
}
