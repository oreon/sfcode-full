package org.witchcraft.model.mail;

import java.util.List;
import java.util.Map;

import org.springframework.mail.SimpleMailMessage;

/** This task should be used to send generic mail messages
 * @author jsingh
 *
 */
public class GenericMailTask extends AbstractMailerTask{
	
	private String from;
	private String to;
	private List toList;
	private String text;
	private String subject;
	
	
	@Override
	protected SimpleMailMessage createMessage(Map<String, Object> mapData) {
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject(subject);
        msg.setFrom(from);
        msg.setText(text);
 
		return msg;
	}

}
