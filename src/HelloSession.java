import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * Created by sungwoo on 14. 12. 13.
 */
public interface HelloSession extends EJBObject {

    public String sayHello(String name) throws RemoteException;

}
