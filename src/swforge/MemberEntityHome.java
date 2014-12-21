package swforge;

import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.Collection;
import javax.ejb.CreateException;
/**
 * <code>MemberEntityHome</code>. 
 *	Bean Managed Persistence Example 
 *
 * @version	1.0 2002.08 
 * @since	1.0
 * @author	Jeon HongSeong 
 */
public interface MemberEntityHome extends EJBHome
{
    /**
     *	MemberEntity table에 row를 insert하고, MemberEntity object를 	 
     *	생성 반환한다.	 
     *
     *	@param	socialId	주민등록번호	 
     *	@return EJB object	 
     *	@exception	javax.ejb.CreateException
     *	@exception	java.rmi.RemoteException
     */	public MemberEntity create(String socialId)	throws CreateException, RemoteException;
    /**
     *	MemberEntity table에 row를 insert하고, MemberEntity object를 	 
     *	생성 반환한다.	 
     *
     *	@param	socialId	주민등록번호	 
     *	@param	name		이름	 
     *	@param	address		주소	 
     *	@return EJB object	 
     *	@exception	javax.ejb.CreateException
     *	@exception	java.rmi.RemoteException
     */
    public MemberEntity create(String socialId, String name, String address)
            throws CreateException, RemoteException;
    /**
     *	프라이머리 키에 해당하는 MemberEntity 객체를 검색 반환한다.	 
     *
     *	@param	socialId	주민등록번호	 
     *	@return EJB object	 
     *	@exception	javax.ejb.FinderException
     *	@exception	java.rmi.RemoteException
     */
    public MemberEntity findByPrimaryKey(String socialId)
            throws FinderException, RemoteException;
    /**
     *	패러미터 주소에 해당하는 MemberEntity 객체들을 검색 반환한다.	 
     *
     *	@param	address	주소	 
     *	@return 검색 결과 EJB object의 Collection	 
     *	@exception	javax.ejb.FinderException
     *	@exception	java.rmi.RemoteException
     */
    public Collection findByAddress(String address)
            throws FinderException, RemoteException;
    /**
     *	패러미터 이름에 해당하는 MemberEntity 객체들을 검색 반환한다.	 
     *
     *	@param	name	이름	 
     *	@return 검색 결과 EJB object의 Collection	 
     *	@exception	javax.ejb.FinderException
     *	@exception	java.rmi.RemoteException
     */
    public Collection findByName(String name)
            throws FinderException, RemoteException;
}
