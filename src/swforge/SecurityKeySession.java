package swforge;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * Created by sungwoo on 14. 12. 25.
 */
public interface SecurityKeySession extends EJBObject {
    /**
     * Sample method
     *
     * @param no	number<br>
     * @return Security key
     * @exception RemoteException
     */
    public int getSecurityKey(int no) throws RemoteException;
}
