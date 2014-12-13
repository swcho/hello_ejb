package swforge;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

/**
 * Created by sungwoo on 14. 12. 13.
 */
public interface LoanSessionHome extends EJBHome {
    swforge.LoanSession create() throws RemoteException, CreateException;
}
