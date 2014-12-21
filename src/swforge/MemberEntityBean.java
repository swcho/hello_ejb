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
import java.util.ArrayList;
import java.util.Collection;


/**
 * <code>MemberEJB</code>.
 *	Bean Managed Persistence Example
 *
 * @version	1.0 2002.08
 * @since	1.0
 * @author	Jeon HongSeong
 */
public class MemberEntityBean implements EntityBean
{
    /** name */
    private String name;
    /** address */
    private String address;
    /** javax.sql.DataSource */
    private DataSource ds;
    /** JNDI NAME */
    private static final String JNDI_NAME =
            "java:comp/env/jdbc/testDb";
    /** javax.ejb.EntityContext */
    private EntityContext context;
    /**
     *	Default Constructor
     */
    public MemberEntityBean()	{
        System.out.println("MemberEJB.MemberEJB()");
    }
    //--------------------- Business Method
    /**
     *	socialId를 반환하는 getter method
     *	@return	socialId
     */
    public String getSocialId()
    {
        System.out.println("MemberEJB.getSocialId() - "+
                context.getPrimaryKey());
        return (String)context.getPrimaryKey();
    }
    /**
     *	name을 반환하는 getter method
     *	@return	name
     */
    public String getName()
    {
        System.out.println("MemberEJB.getName() - "+
                context.getPrimaryKey());
        return this.name;
    }
    /**
     *	address를 반환하는 getter method
     *	@return	address
     */
    public String getAddress()
    {
        System.out.println("MemberEJB.getAddress() - "+
                context.getPrimaryKey());
        return this.address;
    }
    /**
     *	name을 저장하는 setter method
     *	@param	name	이름
     */
    public void setName(String name)
    {
        System.out.println("MemberEJB.setName() - "+
                context.getPrimaryKey());
        this.name = name;
    }
    /**
     *	address을 저장하는 setter method
     *	@param	address	주소
     */
    public void setAddress(String address)
    {
        System.out.println("MemberEJB.setAddress() - "+context.getPrimaryKey());
        this.address = address;
    }
    //--------------------- Container Call back Method
    /**
     *	EntityContext를 초기화하기 위해 빈 인스턴스 생성 시
     *	컨테이너에 의해 호출된다.
     *
     *	@param	context	javax.ejb.EntityContext
     */
    public void setEntityContext(EntityContext context)
    {
        System.out.println("MemberEJB.setEntityContext()");
        this.context = context;
        initDataSource();
    }
    /**
     *	객체 풀링에서 빈이 제거될 때 컨테이너에 의해 호출된다.
     */
    public void unsetEntityContext()
    {
        System.out.println("MemberEJB.unsetEntityContext()");
        this.context = null;
    }
    /**
     *	테이블에 Row를 insert하고, 멤버 변수를 초기화 한다.
     *
     *	@param	socialId	주민등록번호
     *	@return	프라이머리 키(주민등록번호)
     *	@exception	javax.ejb.CreateException
     */
    public String ejbCreate(String socialId)
            throws CreateException
    {
        System.out.println("MemberEJB.ejbCreate() - "+socialId);
        // primary key : null check
        if(socialId==null) {
            throw new CreateException(
                    "The socialId is null. - "+socialId
            );
        }
        try {
            // primary key : duplication check
            if(existsId(socialId)) {
                throw new CreateException(
                        "The socialId is duplicated. - "+socialId
                );
            }
            // 	INSERT a Row
            insertRow(socialId,"","");
        }
        catch(SQLException e) {
            throw new EJBException(e.getMessage());
        }
        // initiate the member variable
        this.name = "";
        this.address = "";
        // return primary key
        return socialId;
    }
    /**
     *	테이블에 Row를 insert하고, 멤버 변수를 초기화 한다.
     *
     *	@param	socialId	주민등록번호
     *	@param	name		이름
     *	@param	address		주소
     *	@return	프라이머리 키(주민등록번호)
     *	@exception	javax.ejb.CreateException
     */
    public String ejbCreate(String socialId, String name, String address)
            throws CreateException
    {
        System.out.println("MemberEJB.ejbCreate() - "+socialId);
        // primary key : null check
        if(socialId==null) {
            throw new CreateException(
                    "The socialId is null. - "+socialId
            );
        }
        try {
            // primary key : duplication check
            if(existsId(socialId)) {
                throw new CreateException(
                        "The socialId is duplicated. - "+socialId
                );
            }
            // 	INSERT a Row
            insertRow(socialId,"","");
        }
        catch(SQLException e) {
            throw new EJBException(e.getMessage());
        }
        // initiate the member variable
        this.name = name;
        this.address = address;
        // return primary key
        return socialId;
    }
    /**
     *	ejbPostCreate() method
     *
     *	@param	socialId	주민등록번호
     */
    public void ejbPostCreate(String socialId)
    {
        System.out.println("MemberEJB.ejbPostCreate() - "+socialId);
    }
    /**
     *	ejbPostCreate() method
     *
     *	@param	socialId	주민등록번호
     *	@param	name		이름
     *	@param	address		주소
     */
    public void ejbPostCreate(String socialId, String name, String address)
    {
        System.out.println("MemberEJB.ejbPostCreate() - "+socialId);
    }
    /**
     *	primary key를 얻어 해당 Row를 삭제한다.
     */
    public void ejbRemove()
    {
        String pk = (String)context.getPrimaryKey();
        System.out.println("MemberEJB.ejbPostCreate() - "+pk);
        try {
            deleteRow(pk);
        }
        catch(SQLException e) {
            throw new EJBException(e.getMessage());
        }
    }
    /**
     *	primary key를 얻어 해당 Row를 읽어 멤버에 초기화한다.
     */
    public void ejbLoad()	{
        String pk = (String)context.getPrimaryKey();
        System.out.println("MemberEJB.ejbLoad() - "+pk);
        try {
            selectRow(pk);
        }
        catch(SQLException e) {
            throw new EJBException(e.getMessage());
        }
    }
    /**
     *	primary key를 얻어 해당 Row에 빈의 상태를 저장합니다.
     */
    public void ejbStore()
    {
        String pk = (String)context.getPrimaryKey();
        System.out.println("MemberEJB.ejbStore() - "+pk);
        try {
            updateRow(pk);
        }
        catch(SQLException e) {
            throw new EJBException(e.getMessage());
        }
    }
    /**
     *	Free Pool에서 ready 상태로 갈때 컨테이너가 호출한다.
     */
    public void ejbActivate()
    {
        System.out.println("MemberEJB.ejbActivate() - "+
                context.getPrimaryKey());
    }
    /**
     *	ready에서 Free Pool 상태로 갈때 컨테이너가 호출한다.
     */
    public void ejbPassivate()
    {
        System.out.println("MemberEJB.ejbPassivate() - "+
                context.getPrimaryKey());
    }
    /**
     *	Single Row Finder Method
     *
     *	@param	socialId	primaryKey
     *	@return	primary Key
     *	@exception	javax.ejb.FinderException
     */
    public String ejbFindByPrimaryKey(String socialId)
            throws FinderException
    {
        try {
            boolean flag = existsId(socialId);
            if(!flag)
                throw new FinderException("Not found - "+socialId);
            return socialId;
        }
        catch(SQLException e) {
            throw new EJBException(e.getMessage());
        }
    }
    /**
     *	Multiple Rows Finder Method
     *
     *	@param	name	이름
     *	@return	검색 결과 primary key 의 Collection
     *	@exception	javax.ejb.FinderException
     */
    public Collection ejbFindByName(String name)
            throws FinderException
    {
        try {
            Collection results = selectByName(name);
            if(results == null || results.size()==0)
                throw new FinderException("Not found - "+name);
            return results;
        }
        catch(SQLException e) {
            throw new EJBException(e.getMessage());
        }
    }
    /**
     *	Multiple Rows Finder Method
     *
     *	@param	address	주소
     *	@return	검색 결과 primary key 의 Collection
     *	@exception	javax.ejb.FinderException
     */
    public Collection ejbFindByAddress(String address)
            throws FinderException
    {
        try {
            Collection results = selectByName(address);
            if(results == null || results.size()==0)
                throw new FinderException("Not found - "+address);
            return results;
        }
        catch(SQLException e) {
            throw new EJBException(e.getMessage());
        }
    }
    //------------------------------------------
    private void initDataSource()
    {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup(JNDI_NAME);
        }
        catch(NamingException e) {
            e.printStackTrace();
        }
    }
    private boolean existsId(String socialId)
            throws SQLException
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            String query = "SELECT socialid "+
                    "FROM member WHERE socialid=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, socialId);
            rs = pstmt.executeQuery();
            return rs.next();
        }
        finally {
            if(rs!=null) rs.close();
            if(pstmt!=null) pstmt.close();
            if(con!=null) con.close();
        }
    }
    private void insertRow(String socialId, String name, String address)
            throws SQLException
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ds.getConnection();
            String query = "INSERT INTO "+
                    " member(socialid, name, address)  "+
                    " VALUES(?,?,?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, socialId);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.executeUpdate();
        }
        finally {
            if(pstmt!=null) pstmt.close();
            if(con!=null) con.close();
        }
    }
    private void deleteRow(String pk)
            throws SQLException
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ds.getConnection();
            String query = "DELETE FROM member "+
                    " WHERE socialid=?  ";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, pk);
            pstmt.executeUpdate();
        }
        finally {
            if(pstmt!=null) pstmt.close();
            if(con!=null) con.close();
        }
    }
    private void selectRow(String pk)
            throws SQLException
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            String query = "SELECT socialid,name,address "+
                    "FROM member WHERE socialid=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, pk);
            rs = pstmt.executeQuery();
            if(!rs.next()) {
                throw new EJBException(
                        "The row is not existed. - "+pk
                );
            }
            this.name = rs.getString(2);
            this.address = rs.getString(3);
        }
        finally {
            if(rs!=null) rs.close();
            if(pstmt!=null) pstmt.close();
            if(con!=null) con.close();
        }
    }
    private void updateRow(String pk)
            throws SQLException
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ds.getConnection();
            String query = "UPDATE member SET "+
                    " name = ?, address = ? WHERE socialid=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, this.name);
            pstmt.setString(2, this.address);
            pstmt.setString(3, pk);
            pstmt.executeUpdate();
        }
        finally {
            if(pstmt!=null) pstmt.close();
            if(con!=null) con.close();
        }
    }
    public Collection selectByName(String name)
            throws SQLException
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            String query = "SELECT socialid FROM member "+
                    " WHERE NAME LIKE '%' || ? || '%'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            ArrayList al = new ArrayList();
            while(rs.next()) {
                String id = rs.getString(1);
                al.add(id);
            }
            return al;
        }
        finally {
            if(rs!=null) rs.close();
            if(pstmt!=null) pstmt.close();
            if(con!=null) con.close();
        }
    }
    public Collection selectByAddress(String address)
            throws SQLException
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            String query = "SELECT socialid FROM member "+
                    " WHERE address LIKE '%' || ? || '%'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, address);
            rs = pstmt.executeQuery();
            ArrayList al = new ArrayList();
            while(rs.next()) {
                String id = rs.getString(1);
                al.add(id);
            }
            return al;
        }
        finally {
            if(rs!=null) rs.close();
            if(pstmt!=null) pstmt.close();
            if(con!=null) con.close();
        }
    }
}

//public class MemberEntityBean implements EntityBean {
//    public MemberEntityBean() {
//    }
//
//    public String ejbFindByPrimaryKey(String key) throws FinderException {
//        return null;
//    }
//
//    public Collection ejbFindByName(String name) throws FinderException {
//        return null;
//    }
//
//    public Collection ejbFindByAddress(String address) throws FinderException {
//        return null;
//    }
//
//    public void setEntityContext(EntityContext entityContext) throws EJBException {
//    }
//
//    public void unsetEntityContext() throws EJBException {
//    }
//
//    public void ejbRemove() throws RemoveException, EJBException {
//    }
//
//    public void ejbActivate() throws EJBException {
//    }
//
//    public void ejbPassivate() throws EJBException {
//    }
//
//    public String ejbCreate(String socialId, String name, String address) throws CreateException {
//        return null;
//    }
//
//    public void ejbPostCreate(String socialId, String name, String address) throws CreateException {
//
//    }
//
//    public String ejbCreate(String socialId) throws CreateException {
//        return null;
//    }
//
//    public void ejbPostCreate(String socialId) throws CreateException {
//
//    }
//
//    public void ejbLoad() throws EJBException {
//
//    }
//
//    public void ejbStore() throws EJBException {
//    }
//
//
//    public void setAddress(String address) {
//
//    }
//
//    public String getAddress() {
//        return null;
//    }
//
//    public void setName(String name) {
//
//    }
//
//    public String getName() {
//        return null;
//    }
//
//    public String getSocialId() {
//        return null;
//    }
//}
