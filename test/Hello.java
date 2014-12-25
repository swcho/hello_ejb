
import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;
import java.util.Properties;

/**
 * Created by sungwoo on 14. 12. 13.
 */
public class Hello {

    private static void printAllTree(InitialContext ctx, String jndiName, String prefix) throws NamingException {
        NamingEnumeration<NameClassPair> list = ctx.list(jndiName);
        while (list != null && list.hasMore()) {
            String name = list.next().getName();
            System.out.println(prefix + name);
            Hello.printAllTree(ctx, jndiName + "/" + name, "  " + prefix);
        }
    }

    public static void main(String[] args) throws NamingException, RemoteException, CreateException, RemoveException {
        System.out.println("Test");
//        Context context = EJBContainer.createEJBContainer().getContext();

        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
        props.setProperty("java.naming.provider.url", "http://localhost:8080/tomee/ejb");
//        props.setProperty("java.naming.provider.url", "127.0.0.1:4201");

        InitialContext ctx = new InitialContext(props);
        Hello.printAllTree(ctx, "", "");

        Object obj = ctx.lookup("HelloSessionEJBRemoteHome");
        System.out.println(obj.getClass().getName());
        //	narrow casting
        HelloSessionHome home =
                (HelloSessionHome) PortableRemoteObject.narrow(obj, HelloSessionHome.class);
        // 	Hello 의 레퍼런스를 얻어낸다.
        HelloSession hello = home.create();
        System.out.println(hello.getClass().getName());
        // 	비즈니스 메서드 호출
        String result = hello.sayHello("haha");
        System.out.println(result);
        hello.remove();
    }

}
