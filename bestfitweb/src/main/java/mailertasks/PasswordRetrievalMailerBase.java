
/**
 * This is generated code - to edit code or override methods use - PasswordRetrievalMailer class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package mailertasks;

import javax.persistence.*;
import java.util.Date;
import org.witchcraft.model.mail.AbstractMailerTask;

public abstract class PasswordRetrievalMailerBase extends AbstractMailerTask {

	private static final long serialVersionUID = 1L;

	protected String from = "pufountatin admin";

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public abstract PasswordRetrievalMailer passwordRetrievalMailerInstance();

}
