package rodrigorar.domain.services.priority;

import java.util.List;
import java.util.LinkedList;

public class FactoryServicesPriority {

    public static ServiceGetPriorityList getServiceGetPriorityList() {
        return new ServiceGetPriorityList();
    }

    public static ServiceGetPriority getServiceGetPriority(String priorityId) {
        return new ServiceGetPriority(priorityId);
    }
}
