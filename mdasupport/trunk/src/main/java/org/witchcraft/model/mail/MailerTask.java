package org.witchcraft.model.mail;

import java.util.Map;

public interface MailerTask {

	public abstract void sendMessage(Map<String, Object> mapData);

}