package bizobjects.service;

import bizobjects.Employee;

import org.springframework.test.jpa.AbstractJpaTests;

import java.util.List;


public class EmployeeDaoTest extends AbstractJpaTests {
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[] { "classpath:/applicationContext.xml" };
    }

    /**
    * Do the setup before the test in this method
    **/
    protected void onSetUpInTransaction() throws Exception {
    }

    public void testSave() {
        //test saving a new record and updating an existing record;
    }

    public void testDelete() {
        //return false;
    }

    public void testLoad() {
        //return null;
    }

    public void testFindBycode() {
    }

    public void testFindByfirstName() {
    }

    public void testFindBylastName() {
    }

    public void testSearchByExample() {
        Employee employee = new Employee();

        //employee.setFirstName("Eri");
        List<Employee> employees = employeeService.searchByExample(employee);
        assertTrue(!employees.isEmpty());
    }
}
