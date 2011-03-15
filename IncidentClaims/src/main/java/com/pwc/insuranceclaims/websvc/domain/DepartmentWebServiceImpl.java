package com.pwc.insuranceclaims.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.pwc.insuranceclaims.domain.Department;

@WebService(endpointInterface = "com.pwc.insuranceclaims.websvc.domain.DepartmentWebService", serviceName = "DepartmentWebService")
@Name("departmentWebService")
public class DepartmentWebServiceImpl implements DepartmentWebService {

	@In(create = true)
	com.pwc.insuranceclaims.web.action.domain.DepartmentAction departmentAction;

	public Department loadById(Long id) {
		return departmentAction.loadFromId(id);
	}

	public List<Department> findByExample(Department exampleDepartment) {
		return departmentAction.search(exampleDepartment);
	}

	public void save(Department department) {
		departmentAction.persist(department);
	}

}
