package hibernate.mgr;

import java.util.List;

import org.witchcraft.model.support.BusinessEntity;

public interface IBasicController {
	public abstract List query(String queryString, Object... params);
	public abstract BusinessEntity save(BusinessEntity businessEntity);
}
