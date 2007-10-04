package usermanagement;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Entity
@Table(name = "users")
public class User extends UserBase {

	private static final Logger log = Logger.getLogger(User.class);

	public User userInstance() {
		return this;
	}
}
