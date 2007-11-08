package bizobjects;

import java.util.Map;

import org.springframework.mail.SimpleMailMessage;
import org.witchcraft.model.mail.AbstractMailerTask;


public class PasswordRetrievalMailSender extends AbstractMailerTask{
	
	private static final String SUBJECT = "Your password for witchcraft";
    private static final String FROM ="wc-administrator";
    
	
    public SimpleMailMessage createMessage(Map<String, Object> mapData){
    	
    	Customer customer = (Customer) mapData.get("customer");

        SimpleMailMessage msg = createMailMessage();
        msg.setTo(customer.getPrimaryAddress().getEmail());
        msg.setText(
            "Dear " + customer.getFirstName()
                + ", Your password  is " + customer.getUserAccount().getPassword()
                );
        return msg;
    }

	private SimpleMailMessage createMailMessage() {
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject(SUBJECT);
        msg.setFrom(FROM);
		return msg;
	}

}
