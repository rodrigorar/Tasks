package rodrigorar.domain.interfaces;

public interface BaseService<T> {
    void execute();
    T getResult();
}
