package rodrigorar.domain.services.priority;

import java.util.List;
import java.util.LinkedList;

import rodrigorar.domain.interfaces.BaseService;
import rodrigorar.domain.services.ServicesAppConfigurations;

public class FactoryServicesPriority {
    private static FactoryServicesPriority _instance;
    private List<BaseService> _serviceCache;
    private ServicesAppConfigurations _appConfigurations;

    public static FactoryServicesPriority getInstance() {
        if (_instance == null) {
            _instance = new FactoryServicesPriority();
        }
        return _instance;
    }

    private FactoryServicesPriority() {
        _serviceCache = new LinkedList<BaseService>();
        _appConfigurations = new ServicesAppConfigurations();
    }

    public ServiceGetPriorityList getServiceGetPriorityList() {
        return new ServiceGetPriorityList(_appConfigurations.getPriorityDirectory());
    }
}
