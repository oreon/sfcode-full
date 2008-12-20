package oaw4.demo.classic.uml.meta;

/**
 * @author jsingh
 *
 */
public class MailerTask extends AbstractEntity {

	private String subject;
	private String from;
	private String body;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 6185686863145901694L;

	
}
