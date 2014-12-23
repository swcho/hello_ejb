package swforge;

import javax.ejb.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sungwoo on 14. 12. 24.
 */
@Stateful(name = "InventorySessionEJB")
public class InventorySessionBean {

    /** 제품명 */
    private String item;
    /** 재고량 */
    private int quantity;

    /** DataSource에 대한 JNDI 이름(Coded Name) */
    private static final String jndi="java:comp/env/jdbc/testDb";
    /** javax.sql.DataSource */
    private DataSource ds;

    /** javax.ejb.SessionContext */
    private SessionContext context;

    /**
     *	SessionContext를 빈에 넘겨주기 위해 초기화 과정중 컨테이너에 호출되는 메소드
     *
     * @param	context javax.ejb.SessionContext object
     */
    public void setSessionContext(SessionContext context)
    {
        this.context = context;
    }

    /**
     *	DataSource와 item을 초기화 하고,
     *	inventory 테이블로 부터 quntity를 읽어	초기화 한다.
     *
     *	@param	item	제품명
     *	@exception	CreateException
     */
    public void ejbCreate(String item)
            throws CreateException
    {
        if(item == null)
            throw new CreateException("Item is null.");
        this.item = item;
        try {
            initDataSource();
            initItem();
        } catch(SQLException e) {
            throw new CreateException(e.getMessage());
        } catch(EJBException e) {
            throw new CreateException(e.getMessage());
        }

        System.out.println("Inventory Bean is created. ["+
                item+","+quantity+"]");
    }

    /**
     *	재고 출고 메소드
     *
     *	@param	quantity	제품에 대한 출고량
     */
    public void delivery(int quantity)
    {
        this.quantity -= quantity;
        try {
            updateQuantity();
            if(this.quantity<0) {
                this.quantity += quantity;
                context.setRollbackOnly();
                //throw new EJBException("재고량이 부족합니다. - "+item);
            }
            logging();
        } catch(SQLException e) {
            this.quantity += quantity;
            context.setRollbackOnly();
            e.printStackTrace();
        }
    }

    /**
     *	제품명을 반환하는 getter method
     *
     *	@return	제품명
     */
    public String getItem()
    {
        return this.item;
    }

    /**
     *	제품의 재고량을 반환하는 getter method
     *
     *	@return	재고량
     */
    public int getQuantity()
    {
        return this.quantity;
    }

    /**
     *	빈 객체 소멸시 undo 작업을 위해 컨테이너에 호출되는 메소드
     *
     */
    @Remove
    public void ejbRemove()
    {
        ds = null;
    }

    /**
     *	Passivate 전 호출되는 call back 메소드
     *
     */
    public void ejbPassivate()
    {
        ds = null;
    }

    /**
     *	Activate 전 호출되는 call back 메소드
     *
     */
    public void ejbActivate()
    {
        initDataSource();
    }

    //	inventory 테이블의 재고량을 변경한다.
    private void updateQuantity()
            throws SQLException
    {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            String query = "UPDATE INVENTORY SET QUANTITY=? "+
                    "WHERE ITEM = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,quantity);
            pstmt.setString(2,item);

            pstmt.executeUpdate();
        } finally {
            if(pstmt != null) pstmt.close();
            if(con != null) con.close();
        }
    }

    // inventory_log 테이블에 제품 출고를 로깅한다.
    private void logging()
            throws SQLException
    {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            String query = "INSERT INTO INVENTORY_LOG "+
                    "(ITEM, QUANTITY, REGDATE) VALUES(?,?,SYSDATE)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,item);
            pstmt.setInt(2,quantity);

            pstmt.executeUpdate();
        } finally {
            if(pstmt != null) pstmt.close();
            if(con != null) con.close();
        }
    }

    // item의 quantity를 테이블로부터 읽어 초기화 한다.
    private void initItem()
            throws SQLException
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            String query = "SELECT QUANTITY "+
                    "FROM INVENTORY WHERE ITEM=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,item);

            rs = pstmt.executeQuery();
            if(!rs.next())
                throw new EJBException("Item is not Founded. - "+item);

            quantity = rs.getInt(1);
        } finally {
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(con != null) con.close();
        }
    }

    // DataSourcd를 JNDI namespace로 부터 lookup해 초기화 한다.
    private void initDataSource()
    {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup(jndi);
        } catch(NamingException e) {
            throw new EJBException(e.getMessage());
        }
    }

}
