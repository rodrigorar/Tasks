package rodrigorar.domain.services.priority;

import java.util.List;

import rodrigorar.data.DAOFactory;
import rodrigorar.data.daos.PriorityDAO;
import rodrigorar.domain.pojos.Priority;
import rodrigorar.domain.interfaces.BaseService;

public class ServiceGetPriorities
implements
BaseService<List<Priority>> {
    private String _filepath;
    private List<Priority> _priorityList;

    public ServiceGetPriorities(String filepath) {
        _filepath = filepath;
    }

    public void execute() {
        DAOFactory factory = DAOFactory.getInstance();
        PriorityDAO daoPriority = factory.getPriorityDAO();
        _priorityList = daoPriority.load(_filepath);
    }

    public List<Priority> getResult() {
        return _priorityList;
    }
}
