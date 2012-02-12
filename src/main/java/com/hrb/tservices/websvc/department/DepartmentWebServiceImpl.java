package com.hrb.tservices.websvc.department;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.department.Department;

@WebService(endpointInterface = "com.hrb.tservices.websvc.department.DepartmentWebService", serviceName = "DepartmentWebService")
@Name("departmentWebService")
public class DepartmentWebServiceImpl implements DepartmentWebService {

	@In(create = true)
	com.hrb.tservices.web.action.department.DepartmentAction departmentAction;

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
