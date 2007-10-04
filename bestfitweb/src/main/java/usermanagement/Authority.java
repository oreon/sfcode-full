package usermanagement;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Entity
@Table(name = "authorities")
public class Authority extends AuthorityBase {

	private static final Logger log = Logger.getLogger(Authority.class);

	public Authority authorityInstance() {
		return this;
	}
}
