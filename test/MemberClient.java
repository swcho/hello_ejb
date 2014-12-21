import swforge.MemberEntity;
import swforge.MemberEntityHome;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

public class MemberClient
{
    public static void main(String[] args) throws Exception
    {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
        props.setProperty("java.naming.provider.url", "http://localhost:8080/tomee/ejb");

        // JNDI Naming Service에 접근하기 위한 Context 객체 생성
        Context ctx = new InitialContext(props);
        // Naming Service에 MyMember 객체를 lookup
        Object obj = ctx.lookup("MemberEntityEJBRemoteHome");
        // narrow casting
        MemberEntityHome home =
                (MemberEntityHome) PortableRemoteObject.narrow(obj, MemberEntityHome.class);
        // 	Member 의 레퍼런스를 얻어낸다.
        MemberEntity mbr1 = home.create("750405","John","Seoul");
        MemberEntity mbr2 = home.create("740404","Tom","Pusan");
        MemberEntity mbr3 = home.create("730403","Smith","Inchun");
        MemberEntity mbr4 = home.create("720402","Jane","Seoul");
        MemberEntity mbr5 = home.create("710401","Tonny","Inchun");
        mbr5.setAddress("Seoul");
        System.out.println("["+mbr5.getSocialId()+","+
                mbr5.getName()+","+mbr5.getAddress()+"]");
        System.out.println("+++++++++++++++++++");
        Collection results = home.findByAddress("Seoul");
        Iterator it = results.iterator();
        while(it.hasNext())
        {
            MemberEntity tmp = (MemberEntity)it.next();
            System.out.println("["+tmp.getSocialId()+","+
                    tmp.getName()+","+tmp.getAddress()+"]");
        }
        System.out.println("++++++++++++++++++++");
        mbr1.remove();
        mbr2.remove();
        mbr3.remove();
        mbr4.remove();
        mbr5.remove();
    }
}
