package swforge;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * <code>Book</code>.
 *	Container Managed Persistence Example
 *
 */
public interface BookEntity extends EJBObject
{
    /**
     *	code를 반환하는 getter method
     *	@return	code
     *	@exception	java.rmi.RemoteException
     */
    public String getCode()
            throws RemoteException;

    /**
     *	title을 반환하는 getter method
     *	@return	title
     *	@exception	java.rmi.RemoteException
     */
    public String getTitle()
            throws RemoteException;

    /**
     *	writer를 반환하는 getter method
     *	@return	writer
     *	@exception	java.rmi.RemoteException
     */
    public String getWriter()
            throws RemoteException;

    /**
     *	price를 반환하는 getter method
     *	@return	price
     *	@exception	java.rmi.RemoteException
     */
    public double getPrice()
            throws RemoteException;

    /**
     *	title을 저장하는 setter method
     *	@param	title	제목
     *	@exception	java.rmi.RemoteException
     */
    public void setTitle(String title)
            throws RemoteException;

    /**
     *	writer을 저장하는 setter method
     *	@param	writer	저자
     *	@exception	java.rmi.RemoteException
     */
    public void setWriter(String writer)
            throws RemoteException;

    /**
     *	price를 저장하는 setter method
     *	@param	price	가격
     *	@exception	java.rmi.RemoteException
     */
    public void setPrice(double price)
            throws RemoteException;
}