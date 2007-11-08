package mailertasks;

import java.util.Map;

import javax.persistence.*;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

public class PasswordRetrievalMailer extends PasswordRetrievalMailerBase {

	private static final Logger log = Logger
			.getLogger(PasswordRetrievalMailer.class);

	public PasswordRetrievalMailer passwordRetrievalMailerInstance() {
		return this;
	}

	@Override
	protected SimpleMailMessage createMessage(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return null;
	}
}
