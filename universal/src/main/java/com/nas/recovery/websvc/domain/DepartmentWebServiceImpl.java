package com.nas.recovery.websvc.domain;
import javax.jws.WebService;
import java.util.List;

import com.oreon.tapovan.domain.Department;

@WebService(endpointInterface = "com.nas.recovery.websvc.domain.DepartmentWebService", serviceName = "DepartmentWebService")
public class DepartmentWebServiceImpl implements DepartmentWebService {

	public Department loadById(Long id) {

		return null;
	}

	public List<Department> findByExample(Department exampleDepartment) {
		return null;
	}

	public void save(Department department) {
	}
}
