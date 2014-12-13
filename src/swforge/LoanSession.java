package swforge;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * Created by sungwoo on 14. 12. 13.
 */
public interface LoanSession extends EJBObject {

    public double interestByYear(double amount) throws RemoteException;

    public double interestByMonth(double amount) throws RemoteException;

}
