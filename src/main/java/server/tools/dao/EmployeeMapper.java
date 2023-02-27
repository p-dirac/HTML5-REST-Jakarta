package server.tools.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import server.tools.model.Employee;
import server.tools.model.EmployeeCustomerJoin;

public class EmployeeMapper implements Mapper<Employee>{

	@Override
	public Employee mapRow(ResultSet rs) throws SQLException {
		Employee row = new Employee();
		//
		row.id = rs.getInt("id");
		row.lastName = rs.getString("lastName");
		row.firstName = rs.getString("firstName");
		row.phone = rs.getString("phone");
		row.email = rs.getString("email");
		row.reportsTo = rs.getInt("reportsTo");
		row.jobTitle = rs.getString("jobTitle");
		row.addressLine1 = rs.getString("addressLine1");
		row.addressLine2 = rs.getString("addressLine2");
		row.city = rs.getString("city");
		row.state = rs.getString("state");
		row.postalCode = rs.getString("postalCode");

		return row;
	}

}
