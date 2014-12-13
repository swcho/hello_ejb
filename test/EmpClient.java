import swforge.EmpSession;
import swforge.EmpSessionHome;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Properties;
import java.util.Vector;

public class EmpClient
{
    public static void main(String[] args)
    {
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
            props.setProperty("java.naming.provider.url", "http://localhost:8080/tomee/ejb");

            Context initial = new InitialContext(props);
            Object objref = initial.lookup("EmpSessionEJBRemoteHome");
            EmpSessionHome home =
                    (EmpSessionHome) PortableRemoteObject.narrow(objref, EmpSessionHome.class);
            EmpSession emp = home.create();
            Vector v=emp.getEmpContents();
            System.out.println("row : "+ v.size());
            for(int i=0;i< v.size();i++)
            {
                String[] row = (String[])v.elementAt(i);
                for(int j=0;j< row.length;j++)
                {
                    System.out.print(row[j]+"\t");
                }
                System.out.println();
            }
            emp.remove();
        }
        catch (Exception ex)
        {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
    }
}
