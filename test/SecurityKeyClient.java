import swforge.SecurityKeySession;
import swforge.SecurityKeySessionHome;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;
import java.util.Properties;

/**
 * Created by sungwoo on 14. 12. 25.
 */
public class SecurityKeyClient
{
    public static void main(String[] args) throws Exception
    {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
        props.setProperty("java.naming.provider.url", "http://localhost:8080/tomee/ejb");

        // JNDI Naming Service에 접근하기 위한 Context 객체 생성
        Context ctx = new InitialContext(props);
        //	Naming Service에 MySecurityKey이름의 객체를 lookup
        Object obj = ctx.lookup("SecurityKeySessionEJBRemoteHome");

        //Object obj = ctx.lookup("MySecurityKey");
        System.out.println(obj.getClass().getName());
        //	narrow casting
        SecurityKeySessionHome home =

                (SecurityKeySessionHome) PortableRemoteObject.narrow(obj, SecurityKeySessionHome.class);

        // 	SecurityKey 의 레퍼런스를 얻어낸다.
        SecurityKeySession info = home.create();
        System.out.println(info.getClass().getName());
        // 	비즈니스 메서드 호출
        try {
            int securityKey = info.getSecurityKey(10);
            System.out.println("Security Key : "+securityKey);
        } catch(RemoteException e) {
            e.detail.printStackTrace();
        }
        info.remove();
    }
}
