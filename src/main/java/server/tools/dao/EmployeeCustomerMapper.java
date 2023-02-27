package server.tools.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import server.tools.model.EmployeeCustomerJoin;

public class EmployeeCustomerMapper implements Mapper<EmployeeCustomerJoin>{

	@Override
	public EmployeeCustomerJoin mapRow(ResultSet rs) throws SQLException {
		EmployeeCustomerJoin row = new EmployeeCustomerJoin();
		row.empid = rs.getInt("empid");
		row.empEmail = rs.getString("empEmail");
		row.empLastName = rs.getString("empLastName");
		row.jobTitle = rs.getString("jobTitle");
		row.custid = rs.getInt("custid");
		row.custEmail = rs.getString("custEmail");
		row.custLastName = rs.getString("custLastName");

		return row;
	}
}
