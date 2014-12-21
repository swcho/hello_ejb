import swforge.BookEntity;
import swforge.BookEntityHome;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

public class BookClient {
    public static void main(String args[]) throws Exception
    {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
        props.setProperty("java.naming.provider.url", "http://localhost:8080/tomee/ejb");

        Context ctx = new InitialContext(props);
        Object obj = ctx.lookup("BookEntityEJBRemoteHome");
        BookEntityHome home =
                (BookEntityHome) PortableRemoteObject.narrow(obj,
                        BookEntityHome.class);

        BookEntity eb1 = home.create(
                "JP_01","Jeon HongSeong",
                "Enterprise JavaBeans",100.3
        );
        BookEntity eb2 = home.create(
                "JP_02","Hong KilDong",
                "Java Servlets",811.1
        );
        BookEntity eb3 = home.create(
                "JP_03","Kim YongSu",
                "JavaServer Pages",120.5
        );
        BookEntity eb4 = home.create(
                "JP_04","Hong KilDong","JDBC",50.5
        );

        try {
            BookEntity tmp = home.findByPrimaryKey("JP_01");
            System.out.println("["+tmp.getCode()+","+
                    tmp.getTitle()+","+tmp.getWriter()+","+
                    tmp.getPrice()+"]");

            tmp.setPrice(104.5);
            System.out.println("["+tmp.getCode()+","+
                    tmp.getTitle()+","+tmp.getWriter()+","+
                    tmp.getPrice()+"]");

            System.out.println("++++++++++++++++++++"+
                    "++++++++++++++++++++++");
            Collection col = home.findByWriter("Hong KilDong");
            Iterator it = col.iterator();
            while(it.hasNext()) {
                BookEntity b = (BookEntity)it.next();
                System.out.println("["+b.getCode()+","+
                        b.getTitle()+","+b.getWriter()+","+
                        b.getPrice()+"]");
            }

            System.out.println("++++++++++++++++++++"+
                    "++++++++++++++++++++++");
            col = home.findInRange(60.0,120.0);
            it = col.iterator();
            while(it.hasNext()) {
                BookEntity b = (BookEntity)it.next();
                System.out.println("["+b.getCode()+","+
                        b.getTitle()+","+b.getWriter()+","+
                        b.getPrice()+"]");
            }
        } finally {
            eb1.remove();
            eb2.remove();
            eb3.remove();
            eb4.remove();
        }
    }
}
