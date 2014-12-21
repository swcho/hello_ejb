package swforge;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 * Created by sungwoo on 14. 12. 21.
 */
public interface LocalCompanyEntityHome extends EJBLocalHome {
    swforge.LocalCompanyEntity findByPrimaryKey(String key) throws FinderException;
}
