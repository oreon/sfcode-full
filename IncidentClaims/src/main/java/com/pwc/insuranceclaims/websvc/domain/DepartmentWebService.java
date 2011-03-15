package com.pwc.insuranceclaims.websvc.domain;

import javax.jws.WebService;
import com.pwc.insuranceclaims.domain.Department;
import java.util.List;

@WebService
public interface DepartmentWebService {

	public Department loadById(Long id);

	public List<Department> findByExample(Department exampleDepartment);

	public void save(Department department);

}
