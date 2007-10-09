package scheduledtasks;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

public class PromotionsMailerTask extends PromotionsMailerTaskBase {

	private static final Logger log = Logger
			.getLogger(PromotionsMailerTask.class);

	public PromotionsMailerTask promotionsMailerTaskInstance() {
		return this;
	}
}
