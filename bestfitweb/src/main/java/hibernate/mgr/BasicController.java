package hibernate.mgr;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.model.support.BusinessEntity;

@Transactional
public class BasicController implements IBasicController {

	private BasicDao basicDAO;

	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}

	public void setBasicDAO(BasicDao basicDAO) {
		this.basicDAO = basicDAO;
	}

	public BusinessEntity save(BusinessEntity businessEntity) {
		return basicDAO.save(businessEntity);
		//return null;
	}

}
