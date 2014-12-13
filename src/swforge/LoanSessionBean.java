package swforge;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Created by sungwoo on 14. 12. 13.
 */
@Stateless(name = "LoanSessionEJB")
public class LoanSessionBean {

    private SessionContext context;

    public LoanSessionBean() {
        System.out.println("LoanSessionBean.LoanSessionBean() method");
    }

    /**
     *	SessionContext를 빈에 넘겨주기 위해 초기화 과정중 컨테이너에
     호출되는 메소드
     *
     * @param	context javax.ejb.SessionContext object
     */
    public void setSessionContext(SessionContext context)
    {
        this.context = context;
        System.out.println("LoanEJB.setSessionContext() method");
    }
    /**
     *	빈 객체 생성시 초기화 작업을 위해 컨테이너에 호출되는 메소드
     *
     */
    public void ejbCreate()
    {
        System.out.println("LoanEJB.ejbCreate() method");
    }
    /**
     *	빈 객체 소멸시 undo 작업을 위해 컨테이너에 호출되는 메소드
     *
     */
    public void ejbRemove()
    {
        System.out.println("LoanEJB.ejbRemove() method");
    }
    /**
     *	Stateless Bean 에서는 사용안됨
     *
     */
    public void ejbActivate()
    {
        System.out.println("LoanEJB.ejbActivate() method");
    }
    /**
     *	Stateless Bean 에서는 사용안됨
     *
     */
    public void ejbPassivate()
    {
        System.out.println("LoanEJB.ejbPassivate() method");
    }
    /**
     *	년 이자 금액을 계산하는 메소드
     *
     *	@param	amount	총액
     *	@return	현 금리를 적용한 년간 이자액
     */
    public double interestByYear(double amount)
    {
        Context ctx = null;
        try
        {
            ctx = new InitialContext();
            String codedName = "java:comp/env/loan/interest";
            Double interest = (Double)ctx.lookup(codedName);
            if(interest==null)
                throw new EJBException("Check the env-entry. - "+codedName);
            return amount*interest.doubleValue();
        }catch(Exception e)
        {
            e.printStackTrace();
            throw new EJBException(e.getMessage());
        }
    }
    /**
     *	월 이자 금액을 계산하는 메소드
     *
     *	@param	amount	총액
     *	@return	현 금리를 적용한 월간 이자액
     */
    public double interestByMonth(double amount)
    {
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context)ctx.lookup("java:comp/env");
            Double interest = (Double)envCtx.lookup("loan/interest");
            if(interest==null){
                throw new EJBException("Check the env-entry. "+
                        "- java:comp/env/loan/interest");
            }
            return amount*interest.doubleValue()/12.0;
        } catch(Exception e) {
            e.printStackTrace();
            throw new EJBException(e.getMessage());
        }
    }
}

