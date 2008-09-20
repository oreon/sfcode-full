package mailTest;

import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import junit.framework.TestCase;

public class SendMailGurPreetTest extends AbstractDependencyInjectionSpringContextTests {
	
	private MailSender mailSender;
	private SimpleMailMessage mailMessage;
	
	
	
	protected String[] getConfigLocations() {
		return new String[] { "mail.xml" };
	}
	
	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public SimpleMailMessage getMailMessage() {
		return mailMessage;
	}

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public void testSendMail() {
		// Create a thread safe "sandbox" of the message
		SimpleMailMessage msg = new SimpleMailMessage(this.mailMessage);
		msg.setTo("goutham.k.rao@gmail.com");
		msg.setText("This is a test");
		try {
			mailSender.send(msg);
		} catch (MailException ex) {
			throw new RuntimeException(ex);
		}
	}


}
