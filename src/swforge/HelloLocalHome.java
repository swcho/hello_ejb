package swforge;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 * HelloLocalHome.
 *	Stateless Session Bean Local Home Interface: Sample Code
 *
 * @version	1.0 2002.08
 * @since	1.0
 * @author	Jeon HongSeong
 */
public interface HelloLocalHome extends EJBLocalHome
{
    /**
     *	Factory Method of the HelloLocal's object.
     *
     *	@exception	CreateException
     */
    public HelloLocal create() throws CreateException;
}
