package server.tools.model;

/**
 * Java object EmployeeCustomerJoin maps to employees and customers database tables
 * 
 * Note: there are no setters or getters in this class
 * 
 * jakarta json will automatically convert to/from json
 * 
 * @author cook
 *
 */
public class EmployeeCustomerJoin {
	// from employees table
    public Integer empid;
    public String empEmail;
    public String empLastName;
    public Integer reportsTo;
    public String jobTitle;
    // from customers table
	public Integer custid;
	public String custEmail;
	public String custLastName;
    public String addressLine1;
    public String addressLine2;
    // from both tables
    public String firstName;
    public String phone;
    public String city;
    public String state;
    public String postalCode;
    //
	public EmployeeCustomerJoin() {
		super();
	}
	@Override
	public String toString() {
		return "EmployeeCustomerJoin [empid=" + empid + ", empEmail=" + empEmail + ", empLastName=" + empLastName
				+ ", reportsTo=" + reportsTo + ", jobTitle=" + jobTitle + ", custid=" + custid + ", custEmail="
				+ custEmail + ", custLastName=" + custLastName + "]";
	}

    
} // end class
