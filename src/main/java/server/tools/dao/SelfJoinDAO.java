package server.tools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ws.rs.core.Response;
import server.tools.model.EmployeeCustomerJoin;
import server.tools.model.EmployeeEmployeeJoin;
import server.tools.model.JoinPageInfo;
import server.tools.model.PageData;
import server.tools.model.PageInfo;

public class SelfJoinDAO  {

	private static final Logger LOG = Logger.getLogger(SelfJoinDAO.class.getName());
	//
	private PageDAO pageDAO = new PageDAO<EmployeeEmployeeJoin>();

	private static final String SELF_PREP = "SELECT e.id as id, e.email as email,  "
			+ " CONCAT(m.lastName, ', ', m.firstName) AS Manager, m.jobTitle as mgrTitle, m.id as mgrId, "
			+ " CONCAT(e.lastName, ', ', e.firstName) AS 'Direct report', e.jobTitle as jobTitle "
			+ " FROM " + " employees e " 
			+ " INNER JOIN employees m ON m.id = e.reportsTo " 
			+ " ORDER BY Manager "
			+ " LIMIT ? OFFSET ? ";

	private static final String SELF_PREP_COUNT = "SELECT COUNT(*) "
			+ " FROM employees as e INNER JOIN employees as m ON m.id = e.reportsTo ";


	public SelfJoinDAO() {
	}


	public PageData<EmployeeEmployeeJoin> getJoinByPage(JoinPageInfo joinPageInfo) {
		Mapper m = new EmployeeEmployeeMapper();
		pageDAO.setMapper(m);
		PageInfo pageInfo = joinPageInfo.pageInfo;
		PageData<EmployeeEmployeeJoin> pageData = pageDAO.getDataByPage(pageInfo, SELF_PREP, SELF_PREP_COUNT);
		return pageData;
	}


} // end class
