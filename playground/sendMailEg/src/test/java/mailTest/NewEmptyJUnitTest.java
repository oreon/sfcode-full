package mailTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import junit.framework.TestCase;

public class NewEmptyJUnitTest extends TestCase {

	ApplicationContext ctx = null;
    MailSender mailSender = null;
    SimpleMailMessage mailMessage = null;

    public NewEmptyJUnitTest(String testName) {
        super(testName);
        String[] paths = {"mail.xml"};
        ctx = new ClassPathXmlApplicationContext(paths);
    }
	
    protected void setUp() throws Exception {
    	mailSender = (MailSender) ctx.getBean("mailSender");
        mailMessage = (SimpleMailMessage) ctx.getBean("mailMessage");
	}
	
    
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testSendMail() {

        SimpleMailMessage msg = this.mailMessage;
        msg.setTo("hegde.varun04@gmail.com");
        msg.setText("This is a test");
        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            throw new RuntimeException(ex);
        }
    }
}
