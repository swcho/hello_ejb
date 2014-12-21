package swforge;

import javax.ejb.EJBLocalObject;

/**
 * Created by sungwoo on 14. 12. 21.
 */
public interface LocalCompanyEntity extends EJBLocalObject {
    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    LocalEmployeeEntity getEmployees();

    void setEmployees(LocalEmployeeEntity employees);
}
