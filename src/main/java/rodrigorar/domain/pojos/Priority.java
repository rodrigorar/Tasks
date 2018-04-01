package rodrigorar.domain.pojos;

import rodrigorar.domain.interfaces.IEntity;
import rodrigorar.utils.IdGenerator;

public class Priority
implements
IEntity {
    private String _id;
    private String _name;
    private Integer _value;

    public Priority(String id, String name, Integer value) {
        _id = id;
        _name = name;
        _value = value;
    }

    public Priority(String name, Integer value) {
        this(IdGenerator.generatePriorityId(), name, value);
    }

    public String getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public Integer getValue() {
        return _value;
    }
}
