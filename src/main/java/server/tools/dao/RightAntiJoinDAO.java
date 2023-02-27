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

public class RightAntiJoinDAO {

	private static final Logger LOG = Logger.getLogger(RightAntiJoinDAO.class.getName());
	//
	private PageDAO pageDAO = new PageDAO<EmployeeCustomerJoin>();

	// cannot use ? placeholder for join type, use string format %s
	private static final String ANTI_JOIN_PREP = "SELECT A.id as empid, A.email as empEmail, A.lastName as empLastName, A.jobTitle as jobTitle, B.id as custid, B.email as custEmail, "
			+ "B.lastName as custLastName FROM employees as A Right Join customers as B ON A.email = B.email Where A.email is NULL LIMIT ? OFFSET ? ";

	// cannot use ? placeholder for join type, use string format %s
	private static final String ANTI_JOIN_PREP_COUNT = "SELECT COUNT(*) "
			+ " FROM employees as A Right Join customers as B ON A.email = B.email  Where A.email is NULL ";

	public RightAntiJoinDAO() {
	}


	public PageData<EmployeeCustomerJoin> getJoinByPage(JoinPageInfo joinPageInfo) {
		Mapper m = new EmployeeCustomerMapper();
		pageDAO.setMapper(m);
		PageInfo pageInfo = joinPageInfo.pageInfo;
		PageData<EmployeeCustomerJoin> pageData = pageDAO.getDataByPage(pageInfo, ANTI_JOIN_PREP, ANTI_JOIN_PREP_COUNT);
		return pageData;
	}


} // end class
