package mailTest;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import junit.framework.TestCase;

public class MimeMessageTest extends TestCase {

	ApplicationContext ctx = null;
    JavaMailSenderImpl mailSender = null;
    MimeMessage mailMessage = null;
    
     public MimeMessageTest(String testName) {
        super(testName);
        String[] paths = {"mail.xml"};
        ctx = new ClassPathXmlApplicationContext(paths);
    }
     
     protected void setUp() throws Exception {
     	mailSender = (JavaMailSenderImpl) ctx.getBean("mailSender");
         mailMessage = mailSender.createMimeMessage();
         
 	}
     
     protected void tearDown() throws Exception {
         super.tearDown();
         ctx=null;
     }
     public void testSendMimeMail()
     {
    	 try {
    		 
    		 mailSender.send(new MimeMessagePreparator(){

				public void prepare(MimeMessage mailMessage) throws Exception{
			
					MimeMessageHelper mimeMessage = new MimeMessageHelper(mailMessage,true);
					mimeMessage.setTo("dpworld007@gmail.com");
					mimeMessage.setSubject("mail with attachments");
					mimeMessage.setText("my text <img src='cid:myLogo'>", true);
					mimeMessage.addAttachment("testdoc.doc", new ClassPathResource("testdoc.doc"));
					mimeMessage.addInline("myLogo", new ClassPathResource("garden.jpg"));
				}
    			 
    		 });
			
			
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
    	 
    
     }
     
     
     
     
    
}
