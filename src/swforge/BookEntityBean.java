package swforge;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

/**
 * @(#)BookEJB.java
 *
 * Copyright (c) 2002 Samsung SDS Co., Ltd. All Rights Reserved.
 */
import javax.ejb.*;

/**
 * <code>BookEJB</code>.
 *	Container Managed Persistence Example
 *
 * @version	1.0 2002.08
 * @since	1.0
 * @author	Jeon HongSeong
 */
public class BookEntityBean implements EntityBean {
    /**
     * 코드:primary key
     */
    public String code;
    /**
     * 제목
     */
    public String title;
    /**
     * 작가
     */
    public String writer;
    /**
     * 가격
     */
    public double price;

    /**
     * javax.ejb.EntityContext
     */
    private EntityContext context;

    //----------- business method

    /**
     * code를 반환하는 getter method
     *
     * @return code
     */
    public String getCode() {
        return (String) context.getPrimaryKey();
    }

    /**
     * title을 반환하는 getter method
     *
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * writer를 반환하는 getter method
     *
     * @return writer
     */
    public String getWriter() {
        return this.writer;
    }

    /**
     * price를 반환하는 getter method
     *
     * @return price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * title을 저장하는 setter method
     *
     * @param    title    제목
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * writer을 저장하는 setter method
     *
     * @param    writer    저자
     */
    public void setWriter(String writer) {
        this.writer = writer;
    }

    /**
     * price를 저장하는 setter method
     *
     * @param    price    가격
     */
    public void setPrice(double price) {
        this.price = price;
    }

    //---------- container call back method

    /**
     * 멤버 변수를 초기화 한다.
     *
     * @param    code    코드
     * @param    writer    작가
     * @param    title    제목
     * @param    price    가격
     * @return 프라이머리 키(코드)
     * @exception javax.ejb.CreateException
     */
    public String ejbCreate(String code, String writer,
                            String title, double price)
            throws CreateException {
        if (code == null)
            throw new CreateException("The code is null.");
        this.code = code;
        this.writer = writer;
        this.title = title;
        this.price = price;

        return null;
    }

    /**
     * ejbPostCreate() method
     *
     * @param    code    코드
     * @param    writer    작가
     * @param    title    제목
     * @param    price    가격
     */
    public void ejbPostCreate(String code, String writer,
                              String title, double price)
            throws CreateException {
    }

    /**
     * EntityContext를 초기화하기 위해 빈 인스턴스 생성 시
     * 컨테이너에 의해 호출된다.
     *
     * @param    context    javax.ejb.EntityContext
     */
    public void setEntityContext(EntityContext context) {
        this.context = context;
    }

    /**
     * 객체 풀링에서 빈이 제거될 때 컨테이너에 의해 호출된다.
     */
    public void unsetEntityContext() {
        this.context = null;
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }

    public void ejbRemove() {
    }

    public void ejbLoad() {
    }

    public void ejbStore() {
    }


}