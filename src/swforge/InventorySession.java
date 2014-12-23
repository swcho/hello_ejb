package swforge;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * Created by sungwoo on 14. 12. 24.
 */
public interface InventorySession extends EJBObject {

    /**
     *	재고 출고 메소드
     *
     *	@param	quantity	제품에 대한 출고량
     *	@exception	RemoteException
     */
    public void delivery(int quantity)
            throws RemoteException;

    /**
     *	제품명을 반환하는 getter method
     *
     *	@return	제품명
     *	@exception	RemoteException
     */
    public String getItem()
            throws RemoteException;

    /**
     *	제품의 재고량을 반환하는 getter method
     *
     *	@return	재고량
     *	@exception	RemoteException
     */
    public int getQuantity()
            throws RemoteException;

}
