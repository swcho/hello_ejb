package swforge;

import javax.ejb.EJBLocalObject;

/**
 * Created by sungwoo on 14. 12. 21.
 */
public interface LocalEmployeeEntity extends EJBLocalObject {
    String getEmpNo();

    void setEmpNo(String empNo);

    String getName();

    void setName(String name);

    String getDept();

    void setDept(String dept);

    LocalCompanyEntity getCompany();

    void setCompany(LocalCompanyEntity company);
}
