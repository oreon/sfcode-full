package com.hrb.tservices.websvc.message;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.message.MessageTranslation;

@WebService(endpointInterface = "com.hrb.tservices.websvc.message.MessageTranslationWebService", serviceName = "MessageTranslationWebService")
@Name("messageTranslationWebService")
public class MessageTranslationWebServiceImpl
		implements
			MessageTranslationWebService {

	@In(create = true)
	com.hrb.tservices.web.action.message.MessageTranslationAction messageTranslationAction;

	public MessageTranslation loadById(Long id) {
		return messageTranslationAction.loadFromId(id);
	}

	public List<MessageTranslation> findByExample(
			MessageTranslation exampleMessageTranslation) {
		return messageTranslationAction.search(exampleMessageTranslation);
	}

	public void save(MessageTranslation messageTranslation) {
		messageTranslationAction.persist(messageTranslation);
	}

}
