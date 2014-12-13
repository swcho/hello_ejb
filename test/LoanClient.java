import swforge.LoanSession;
import swforge.LoanSessionHome;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Properties;

public class LoanClient
{
    public static void main(String[] args) throws Exception
    {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
        props.setProperty("java.naming.provider.url", "http://localhost:8080/tomee/ejb");

        //	JNDI Naming Service에 접근하기 위한 Context 객체 생성
        Context ctx = new InitialContext(props);

        //	Naming Service에 MyLoan이름의 객체를 lookup
        Object obj = ctx.lookup("LoanSessionEJBRemoteHome");

        //	narrow casting
        LoanSessionHome home =
                (LoanSessionHome) PortableRemoteObject.narrow(obj, LoanSessionHome.class);

        // 	Loan 의 레퍼런스를 얻어낸다.
        LoanSession loan = home.create();

        // 	비즈니스 메소드 호출
        double amtByYear = loan.interestByYear(50000000.0);
        System.out.println("Interest [year] : "+amtByYear);

        double amtByMonth = loan.interestByMonth(50000000.0);
        System.out.println("Interest [month] : "+amtByMonth);
    }
}
