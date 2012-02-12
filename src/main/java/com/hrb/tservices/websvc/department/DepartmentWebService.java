package com.hrb.tservices.websvc.department;

import javax.jws.WebService;
import com.hrb.tservices.domain.department.Department;
import java.util.List;

@WebService
public interface DepartmentWebService {

	public Department loadById(Long id);

	public List<Department> findByExample(Department exampleDepartment);

	public void save(Department department);

}
