package swforge;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * <code>Member</code>.
 *	Bean Managed Persistence Example
 *
 * @version	1.0 2002.08
 * @since	1.0
 * @author	Jeon HongSeong
 */
public interface MemberEntity extends EJBObject
{
    /**
     *	socialId를 반환하는 getter method
     *	@return	socialId
     *	@exception	java.rmi.RemoteException
     */
    public String getSocialId()
            throws RemoteException;

    /**
     *	name을 반환하는 getter method
     *	@return	name
     *	@exception	java.rmi.RemoteException
     */
    public String getName()
            throws RemoteException;

    /**
     *	address를 반환하는 getter method
     *	@return	address
     *	@exception	java.rmi.RemoteException
     */
    public String getAddress()
            throws RemoteException;

    /**
     *	name을 저장하는 setter method
     *	@param	name	이름
     *	@exception	java.rmi.RemoteException
     */
    public void setName(String name)
            throws RemoteException;

    /**
     *	address을 저장하는 setter method
     *	@param	address	주소
     *	@exception	java.rmi.RemoteException
     */
    public void setAddress(String address)
            throws RemoteException;
}
