package rodrigorar.data.services;

public class ServicesDataFactory {
	
	public static ServicesPersistence getPersistenceServices() {
		return new ServicesPersistence();
	}
}
