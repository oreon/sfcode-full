package prfl;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@Aspect
public class BaseExceptionHandler {

	private static final Logger logger = Logger.getLogger(BaseExceptionHandler.class);
	private MailSender mailSender;
	private SimpleMailMessage mailMessage;

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

	@AfterThrowing(pointcut = "within(  eu.alenislimited.kesoxprocessing.support.services.**) && !within(BaseExceptionHandler+)", throwing = "ex")
	public void report(Exception ex) {
		System.out.println("in exhandler");
		try {
			SimpleMailMessage message = new SimpleMailMessage(mailMessage);
			String sub = "EXCEPTION ";
			sub += ex.getMessage(); //(ex.getMessage() != null)?ex.getMessage():ex.getCause().getMessage();
			message.setSubject(sub);
			
			StackTraceElement[] trace = ex.getStackTrace();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < trace.length; i++) {
				buffer.append(  trace[i].toString() + "\n" );
			}
			message.setText(buffer.toString());
		
			mailSender.send(message);
		} catch (Exception e) {
			logger.error("Error sending exception email", e);
			//log the error
		}
	}
}
