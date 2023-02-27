package server.tools.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import server.tools.model.EmployeeCustomerJoin;
import server.tools.model.EmployeeEmployeeJoin;

public class EmployeeEmployeeMapper implements Mapper<EmployeeEmployeeJoin>{

	public EmployeeEmployeeMapper() {
		super();
	}

	@Override
	public EmployeeEmployeeJoin mapRow(ResultSet rs) throws SQLException {
		EmployeeEmployeeJoin row = new EmployeeEmployeeJoin();
		row.directReport.id = rs.getInt("id");
		row.directReportName = rs.getString("Direct report");
		row.directReport.email = rs.getString("email");
		row.directReport.jobTitle = rs.getString("jobTitle");
		//
		row.managerName = rs.getString("Manager");
		row.manager.jobTitle = rs.getString("mgrTitle");
		row.manager.id = rs.getInt("mgrId");

		return row;
	}
}
