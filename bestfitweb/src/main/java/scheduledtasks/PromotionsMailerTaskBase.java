package scheduledtasks;

import javax.persistence.*;

public abstract class PromotionsMailerTaskBase implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public abstract PromotionsMailerTask promotionsMailerTaskInstance();

}
