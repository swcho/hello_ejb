package swforge;

import javax.ejb.CreateException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

/**
 * Created by sungwoo on 14. 12. 13.
 */
@Stateless(name = "EmpSessionEJB")
public class EmpSessionBean {
    public EmpSessionBean() {
    }

    /**
     *	emp 테이블의 내용을 Vector로 반환하는 메소드
     *
     *	@return	emp 테이블의 내용
     *	@exception	RemoteException
     */
    public Vector getEmpContents()
    {
        String dbName = "java:comp/env/jdbc/testDb";
        DataSource ds = null;
        try
        {
            InitialContext ic = new InitialContext();
            ds = (DataSource)ic.lookup(dbName);
            System.out.println(ds.getClass().getName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("getEmpContents() method");
        Connection con=null;
        Statement stmt=null;
        Vector v = new Vector(1,1);
        try {
            con=ds.getConnection();
            System.out.println(con.getClass().getName());
            System.out.println("Connection object is created.....");
            stmt = con.createStatement();
            System.out.println("Statement object is created......");
            String query="SELECT * FROM EMP";
            ResultSet rset = stmt.executeQuery(query);
            System.out.println("ResultRet object is returned.....");
            ResultSetMetaData md = rset.getMetaData();
            int col = md.getColumnCount();
            String row[]=null;
            while(rset.next()) {
                row=new String[col];
                for(int i=0;i<col;i++) {
                    row[i]=rset.getString(i+1);
                    System.out.print(row[i]+"\t");
                }
                System.out.println();
                v.addElement(row);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                stmt.close();
                con.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return v;
    }
    /**
     *	빈 객체 생성시 초기화 작업을 위해 컨테이너에 호출되는 메소드
     *
     */
    public void ejbCreate()
            throws CreateException
    {
        System.out.println("ejbCreate() method");
    }
    /**
     *	빈 객체 소멸시 undo 작업을 위해 컨테이너에 호출되는
     메소드
     *
     */
    public void ejbRemove()
    {
        System.out.println("ejbRemove() method");
    }
    /**
     *	Stateless Bean 에서는 사용안됨
     */
    public void ejbActivate()
    {
        System.out.println("ejbActivate() method");
    }
    /**
     *	Stateless Bean 에서는 사용안됨
     */
    public void ejbPassivate()
    {
        System.out.println("ejbPassivate() method");
    }
    /**
     *	SessionContext를 빈에 넘겨주기 위해 초기화 과정중
     컨테이너에 호출되는 메소드
     * @param	context javax.ejb.SessionContext object
     */
    public void setSessionContext(SessionContext sc)
    {
//        this.context = sc;
        System.out.println("setSessionContext() method");
    }

}
