import javax.ejb.CreateException;
import javax.ejb.Stateless;

/**
 * Created by sungwoo on 14. 12. 13.
 */
@Stateless(name = "HelloSessionEJB")
public class HelloSessionBean {
    public HelloSessionBean() {
    }

//    @Override
    public void ejbCreate() throws CreateException {

    }

//    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
