package server.tools.model;

/**
 * Java object EmployeeEmployeeJoin maps to employees database table
 * 
 * Note: there are no setters or getters in this class
 * 
 * jakarta json will automatically convert to/from json
 * 
 * @author cook
 *
 */
public class EmployeeEmployeeJoin {
	//
	public String directReportName;
	public Employee directReport = new Employee();
	//
	public String managerName;
	public Employee manager = new Employee();
	//
	public EmployeeEmployeeJoin() {
	}
	@Override
	public String toString() {
		return "EmployeeEmployeeJoin [directReport=" + directReport + ", manager=" + manager + "]";
	}
	
	
}
