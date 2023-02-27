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

public class FullAntiJoinDAO {

	private static final Logger LOG = Logger.getLogger(FullAntiJoinDAO.class.getName());
	//
	private PageDAO pageDAO = new PageDAO<EmployeeCustomerJoin>();

	// special case of FULL Join using Union of left anti join and right anti join
	private static final String ANTI_FULL_PREP = "SELECT A.id as empid, A.email as empEmail, A.lastName as empLastName, A.jobTitle as jobTitle, B.id as custid, B.email as custEmail, "
			+ "B.lastName as custLastName FROM employees as A Left Join customers as B ON A.email = B.email "
			+ "Where B.email is NUll " 
			+ "Union distinct "
			+ "SELECT A.id as empid, A.email as empEmail, A.lastName as empLastName, A.jobTitle as jobTitle, B.id as custid, B.email as custEmail, "
			+ "B.lastName as custLastName FROM employees as A Right Join customers as B ON A.email = B.email "
			+ "Where A.email is NUll " + " LIMIT ? OFFSET ? ";

	// special case of FULL Join using Union of left anti join and right anti join
	private static final String ANTI_FULL_PREP_COUNT = "SELECT COUNT(*) " 
			+ "FROM " + "( " + "SELECT A.email "
			+ " FROM " + "employees as A Left Join customers as B ON A.email = B.email " 
			+ "Where B.email is NUll "
			+ "Union distinct " 
			+ "SELECT A.email "
			+ " FROM employees as A Right Join customers as B ON A.email = B.email "
			+ "Where A.email is NUll "
			+ ") as TBL ";

	public FullAntiJoinDAO() {
	}


	public PageData<EmployeeCustomerJoin> getJoinByPage(JoinPageInfo joinPageInfo) {
		Mapper m = new EmployeeCustomerMapper();
		pageDAO.setMapper(m);
		PageInfo pageInfo = joinPageInfo.pageInfo;
		PageData<EmployeeCustomerJoin> pageData = pageDAO.getDataByPage(pageInfo, ANTI_FULL_PREP, ANTI_FULL_PREP_COUNT);
		return pageData;
	}


} // end class
