package swforge;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

/**
 * Created by sungwoo on 14. 12. 24.
 */
public interface InventorySessionHome extends EJBHome {
    /**
     *	Factory Method of the Inventory's object.
     *
     *	@param	item	제품명
     *	@return	Inventory EJB object
     *	@exception	RemoteException
     *	@exception	CreateException
     */
    public InventorySession create(String item)
            throws CreateException, RemoteException;
}
