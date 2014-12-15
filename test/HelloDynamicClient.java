import javax.naming.*;
import javax.rmi.*;
import javax.ejb.*;
import java.lang.reflect.*;

public class HelloDynamicClient
{
    public static void main(String args[])
            throws Exception
    {
        //	JNDI context 생성
        Context ct = new InitialContext();
        //	Home object Look up
        Object mp = ct.lookup("MyHello");
        //	narrow casting
        EJBHome home =
                (EJBHome)PortableRemoteObject.narrow(mp,EJBHome.class);
        //	Reflection API
        EJBMetaData md = home.getEJBMetaData();
        Class cHome = md.getHomeInterfaceClass();
        Class cRemote = md.getRemoteInterfaceClass();

        if(md.isStatelessSession()) {
            Class[] args1 = new Class[0];
            Method mHome =
                    cHome.getDeclaredMethod("create",args1);

            Class[] args2 = {String.class};
            Method mRemote =
                    cRemote.getDeclaredMethod("sayHello",args2);

            //	remote object 생성
            Object[] argValue1=new Object[0];
            EJBObject remote =
                    (EJBObject)mHome.invoke(home,argValue1);

            //	business method 호출
            Object[] argValue2={"HongSeong"};
            String result=(String)mRemote.invoke(remote,argValue2);

            //	결과 출력
            System.out.println(result);
        }
    }
}