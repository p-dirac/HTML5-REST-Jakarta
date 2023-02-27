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

public class CrossJoinDAO {

	private static final Logger LOG = Logger.getLogger(InnerJoinDAO.class.getName());
	//
	private PageDAO pageDAO = new PageDAO<EmployeeCustomerJoin>();

	private static final String CROSS_PREP = "SELECT A.id as empid, A.email as empEmail, A.lastName as empLastName, A.jobTitle as jobTitle, B.id as custid, B.email as custEmail, "
			+ " B.lastName as custLastName FROM employees as A Cross Join customers as B LIMIT ? OFFSET ? ";

	private static final String CROSS_PREP_COUNT = "SELECT COUNT(*) "
			+ " FROM employees as A Cross Join customers as B ";

	public CrossJoinDAO() {
	}


	public PageData<EmployeeCustomerJoin> getJoinByPage(JoinPageInfo joinPageInfo) {
		Mapper m = new EmployeeCustomerMapper();
		pageDAO.setMapper(m);
		PageInfo pageInfo = joinPageInfo.pageInfo;
		PageData<EmployeeCustomerJoin> pageData = pageDAO.getDataByPage(pageInfo, CROSS_PREP, CROSS_PREP_COUNT);
		return pageData;
	}


} // end class
