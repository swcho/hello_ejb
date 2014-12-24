package swforge;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

/**
 * Created by sungwoo on 14. 12. 25.
 */
public interface SecurityKeySessionHome extends EJBHome {
    swforge.SecurityKeySession create() throws RemoteException, CreateException;
}
