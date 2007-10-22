package org.witchcraft.model.mail;

import java.util.Map;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/** Utility implementation of MailerTask - classes derived from it must
 * implement createMessage task
 * @author jsingh
 *
 */
public abstract class AbstractMailerTask implements MailerTask {
	
	private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    /* (non-Javadoc)
	 * @see bizobjects.MailerTask#sendMessage(java.util.Map)
	 */
    public void sendMessage(Map<String, Object> mapData){
    	 try{
            mailSender.send(createMessage(mapData));
         }
         catch(MailException ex) {
             // simply log it and go on...
             System.err.println(ex.getMessage());            
         }
    }

	protected abstract SimpleMailMessage createMessage(Map<String, Object> mapData);

}
