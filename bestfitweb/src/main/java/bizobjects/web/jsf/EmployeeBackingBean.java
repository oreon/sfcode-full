package bizobjects.web.jsf;

import bizobjects.Employee;


public class EmployeeBackingBean {
    private Employee employee = new Employee();

    public Employee getEmployee(Employee employee) {
        return employee;
    }

    public void set(Employee employee) {
        this.employee = employee;
    }

    public String update() {
        return "success";
    }
}
