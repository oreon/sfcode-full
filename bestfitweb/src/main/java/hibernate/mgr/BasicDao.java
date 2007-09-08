package hibernate.mgr;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.witchcraft.model.support.BusinessEntity;

public class BasicDao extends JpaDaoSupport {

	private EntityManager entityManager;

	public List query(String queryString, final Object... params) {
		Query query = entityManager.createQuery(queryString);
		//entityManager.setParameters(query, params);
		return query.getResultList();
	}

	public BusinessEntity save(BusinessEntity businessEntity) {
		getJpaTemplate().persist(businessEntity);
		return businessEntity;
	}
}
