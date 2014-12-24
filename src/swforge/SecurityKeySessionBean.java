package swforge;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 * Created by sungwoo on 14. 12. 25.
 */
@Stateless(name = "SecurityKeySessionEJB")
public class SecurityKeySessionBean {
    /** javax.ejb.SessionContext */
    private SessionContext context;

    public static final int[] SECURITY_KEYS = {
            1234, 2312, 4444, 3422, 5645, 7345, 7564,
            2133, 8423, 9764, 2455, 2345, 4524, 7457,
            3333, 5555, 6666, 7778, 8999, 2009, 2020
    };

    /**
     * SessionContext를 빈에 넘겨주기 위해 초기화 과정중 컨테이너에 호출되는 메서드
     *
     * @param	context javax.ejb.SessionContext object
     */
    public void setSessionContext(SessionContext context)
    {
        this.context = context;
        System.out.println("SecurityKeyEJB.setSessionContext() method");
    }

    /**
     * 빈 객체 생성시 초기화 작업을 위해 컨테이너에 호출되는 메서드
     *
     */
    public void ejbCreate()
    {
        System.out.println("SecurityKeyEJB.ejbCreate() method");
    }

    /**
     * 빈 객체 소멸시 undo 작업을 위해 컨테이너에 호출되는 메서드
     *
     */
    public void ejbRemove()
    {
        System.out.println("SecurityKeyEJB.ejbRemove() method");
    }

    /**
     * Stateless Bean 에서는 사용안됨
     *
     */
    public void ejbActivate()
    {
        System.out.println("SecurityKeyEJB.ejbActivate() method");
    }
    /**
     * Stateless Bean 에서는 사용안됨
     *
     */
    public void ejbPassivate()
    {
        System.out.println("SecurityKeyEJB.ejbPassivate() method");
    }

    /**
     * Sample method
     *
     * @param no number
     * @return Security key
     */
    public int getSecurityKey(int no)
    {
        System.out.println("SecurityKeyEJB.saySecurityKey() method");
        if(no>0 && no<21) {
            return SECURITY_KEYS[no];
        }

        return -1;
    }
}
