package states.web.jsf;

import org.apache.log4j.Logger;

public class IndexBean extends IndexBeanBase {

	private static final Logger log = Logger.getLogger(IndexBean.class);

	@Override
	public String doAuthenticate() {
		// TODO Auto-generated method stub
		return "success";
	}

}
