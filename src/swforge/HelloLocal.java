package swforge;

import javax.ejb.EJBLocalObject;

/**
 * HelloLocal.
 *	Stateless Session Bean Local Interface: Sample Code
 *
 * @version	1.0 2002.08
 * @since	1.0
 * @author	Jeon HongSeong 
 */
public interface HelloLocal extends EJBLocalObject
{
    /**
     *	Sample method
     *
     *	@param	name	user name
     *	@return	"HelloLocal "+name 형태의 String
     *	@exception	RemoteException
     */
    public String sayHello(String name);
}
