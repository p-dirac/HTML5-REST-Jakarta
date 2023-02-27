package server.tools.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import server.tools.model.Customer;
import server.tools.model.EmployeeCustomerJoin;

public class CustomerMapper implements Mapper<Customer>{

	@Override
	public Customer mapRow(ResultSet rs) throws SQLException {
		Customer row = new Customer();
		//
		row.id = rs.getInt("id");
		row.lastName = rs.getString("lastName");
		row.firstName = rs.getString("firstName");
		row.phone = rs.getString("phone");
		row.email = rs.getString("email");
		row.addressLine1 = rs.getString("addressLine1");
		row.addressLine2 = rs.getString("addressLine2");
		row.city = rs.getString("city");
		row.state = rs.getString("state");
		row.postalCode = rs.getString("postalCode");

		return row;

	}
	
}
