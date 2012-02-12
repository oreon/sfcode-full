package com.hrb.tservices.websvc.message;

import javax.jws.WebService;
import com.hrb.tservices.domain.message.MessageTranslation;
import java.util.List;

@WebService
public interface MessageTranslationWebService {

	public MessageTranslation loadById(Long id);

	public List<MessageTranslation> findByExample(
			MessageTranslation exampleMessageTranslation);

	public void save(MessageTranslation messageTranslation);

}
