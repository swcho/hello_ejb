package swforge;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 * Created by sungwoo on 14. 12. 13.
 */
public interface EmpSession extends EJBObject {

    /**
     *	emp 테이블의 내용을 Vector로 반환하는 메소드
     *
     *	@return	emp 테이블의 내용
     *	@exception	RemoteException
     */
    public Vector getEmpContents() throws RemoteException;
}
