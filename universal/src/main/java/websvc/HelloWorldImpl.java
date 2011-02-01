package websvc;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.jws.WebService;

import com.oreon.tapovan.domain.Employee;

@WebService(endpointInterface = "websvc.HelloWorld",
            serviceName = "HelloWorld")
public class HelloWorldImpl implements HelloWorld {
    Map<Integer, Employee> users = new LinkedHashMap<Integer, Employee>();


    public String sayHi(String text) {
        System.out.println("sayHi called");
        return "Hello " + text;
    }

    public String sayHiToUser(Employee user) {
        System.out.println("sayHiToUser called");
        //users.put(users.size() + 1, user);
        return "Hello "  + user.getFirstName();
    }

    public Map<Integer, Employee> getUsers() {
        System.out.println("getUsers called");
        return users;
    }

}