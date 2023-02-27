package server.tools.model;

import java.util.Objects;

/**
 * Java object Employee maps to employees database table
 * 
 * Note: there are no setters or getters in this class
 * 
 * jakarta json will automatically convert to/from json
 * 
 * @author cook
 *
 */
public class Employee {
    public Integer id;
    public String lastName;
    public String firstName;
    public String phone;
    public String email;
    public Integer reportsTo;
    public String jobTitle;
    public String addressLine1;
    public String addressLine2;
    public String city;
    public String state;
    public String postalCode;

    public Employee() {
    }






	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", phone=" + phone
				+ ", email=" + email + ", reportsTo=" + reportsTo + ", jobTitle=" + jobTitle + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city + ", state=" + state
				+ ", postalCode=" + postalCode + "]";
	}






	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        return id == other.id && Objects.equals(email, other.email) ;
    }

}
