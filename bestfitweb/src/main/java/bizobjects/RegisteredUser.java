package bizobjects;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Entity
public class RegisteredUser extends RegisteredUserBase {

	private static final Logger log = Logger.getLogger(RegisteredUser.class);

	public RegisteredUser registeredUserInstance() {
		return this;
	}
}
