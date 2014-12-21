package swforge;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

/**
 * Created by sungwoo on 14. 12. 21.
 */
public abstract class EmployeeEntityBean implements EntityBean {
    public EmployeeEntityBean() {
    }

    public void setEntityContext(EntityContext entityContext) throws EJBException {
    }

    public void unsetEntityContext() throws EJBException {
    }

    public void ejbRemove() throws RemoveException, EJBException {
    }

    public void ejbActivate() throws EJBException {
    }

    public void ejbPassivate() throws EJBException {
    }

    public void ejbLoad() throws EJBException {
    }

    public void ejbStore() throws EJBException {
    }

    public abstract String getEmpNo();

    public abstract void setEmpNo(String empNo);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract String getDept();

    public abstract void setDept(String dept);

    public abstract LocalCompanyEntity getCompany();

    public abstract void setCompany(LocalCompanyEntity company);
}
