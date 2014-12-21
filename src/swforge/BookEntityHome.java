package swforge;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.Collection;

/**
 * <code>BookHome</code>.
 *	Container Managed Persistence Example
 *
 */
public interface BookEntityHome extends EJBHome
{
    /**
     *	Book table에 row를 insert하고, Book object를
     *	생성 반환한다.
     *
     *	@param	code	 	코드
     *	@param	title		제목
     *	@param	writer		작가
     *	@param	price		가격
     *	@return EJB object
     *	@exception	javax.ejb.CreateException
     *	@exception	java.rmi.RemoteException
     */
    public BookEntity create(String code, String title,
                       String writer, double price)
            throws CreateException, RemoteException;

    /**
     *	프라이머리 키에 해당하는 Book 객체를 검색 반환한다.
     *
     *	@param	code	코드
     *	@return EJB object
     *	@exception	javax.ejb.FinderException
     *	@exception	java.rmi.RemoteException
     */
    public BookEntity findByPrimaryKey(String code)
            throws FinderException, RemoteException;

    /**
     *	파라미터 작가에 해당하는 Book 객체들을 검색 반환한다.
     *
     *	@param	writer	작가
     *	@return 검색 결과 EJB object의 Collection
     *	@exception	javax.ejb.FinderException
     *	@exception	java.rmi.RemoteException
     */
    public Collection findByWriter(String writer)
            throws FinderException, RemoteException;

    /**
     *	파라미터 가격 범위에 속하는 Book 객체들을 검색 반환한다.
     *
     *	@param	lowPrice	하한 가격
     *	@param	highPrice	상한 가격
     *	@return 검색 결과 EJB object의 Collection
     *	@exception	javax.ejb.FinderException
     *	@exception	java.rmi.RemoteException
     */
    public Collection findInRange(double lowPrice, double highPrice)
            throws FinderException, RemoteException;
}
