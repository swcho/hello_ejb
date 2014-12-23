
import swforge.InventorySession;
import swforge.InventorySessionHome;

import javax.ejb.*;
import javax.naming.*;
import javax.rmi.*;
import java.rmi.RemoteException;
import java.util.Properties;

public class InventoryClient
{
    public static void main(String[] args) throws Exception
    {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
        props.setProperty("java.naming.provider.url", "http://localhost:8080/tomee/ejb");

        //	JNDI Naming Service에 접근하기 위한 Context 객체 생성
        Context ctx = new InitialContext(props);
        //	Naming Service에 MyInventory 객체를 lookup
        Object obj = ctx.lookup("InventorySessionEJBRemoteHome");
        //	narrow casting
        InventorySessionHome home =
                (InventorySessionHome)PortableRemoteObject.narrow(obj,InventorySessionHome.class);

        // 	Inventory 의 레퍼런스를 얻어낸다.
        InventorySession inv = home.create("Notebook");

        try {
            System.out.println("Item : "+inv.getItem());
            System.out.println("Quantity : "+inv.getQuantity());

            inv.delivery(20);

            System.out.println("Item : "+inv.getItem());
            System.out.println("Quantity : "+inv.getQuantity());
        } finally {
            inv.remove();
        }
    }
}