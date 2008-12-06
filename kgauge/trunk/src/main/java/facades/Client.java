package facades;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.oreon.kgauge.domain.User;
import com.oreon.kgauge.service.UserService;


public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		//factory.getInInterceptors().add(new LoggingInInterceptor());
		//factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(UserService.class);
		factory.setAddress("http://localhost:8080/kgauge/webService/userService");
		UserService client = (UserService) factory.create();

		User user = client.findByUsername("alpha56738");
		
		long count = client.getCount();
		
	
		System.out.println("Server said: " + user.getPassword() + " " + count );
		System.exit(0);
	}

}
