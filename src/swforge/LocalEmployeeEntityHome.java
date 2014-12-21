package swforge;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 * Created by sungwoo on 14. 12. 21.
 */
public interface LocalEmployeeEntityHome extends EJBLocalHome {
    swforge.LocalEmployeeEntity findByPrimaryKey(String key) throws FinderException;
}
