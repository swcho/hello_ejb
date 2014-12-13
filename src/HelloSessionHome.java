import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

/**
 * Created by sungwoo on 14. 12. 13.
 */
public interface HelloSessionHome extends EJBHome {
    HelloSession create() throws RemoteException, CreateException;
}
