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

public class JoinsDAO  {

	private static final Logger LOG = Logger.getLogger(JoinsDAO.class.getName());
	
	private InnerJoinDAO innerJoinDAO = new InnerJoinDAO();
	//
	private LeftJoinDAO leftJoinDAO = new LeftJoinDAO();
	private RightJoinDAO rightJoinDAO = new RightJoinDAO();
	private FullJoinDAO fullJoinDAO = new FullJoinDAO();
	//
	private CrossJoinDAO crossJoinDAO = new CrossJoinDAO();
	//
	private LeftAntiJoinDAO leftAntiJoinDAO = new LeftAntiJoinDAO();
	private RightAntiJoinDAO rightAntiJoinDAO = new RightAntiJoinDAO();
	private FullAntiJoinDAO fullAntiJoinDAO = new FullAntiJoinDAO();
	//
	private SelfJoinDAO selfJoinDAO = new SelfJoinDAO();
	
	


	public JoinsDAO() {
	}
	
	public PageData<EmployeeCustomerJoin> getJoinByPage(JoinPageInfo joinPageInfo) {
		PageData<EmployeeCustomerJoin> pageData = null;
		try {
			String joinType = joinPageInfo.joinType;
			String joinOption = null;
			switch (joinType) {
			case "InnerJoin":
				pageData = innerJoinDAO.getJoinByPage(joinPageInfo);
				break;
			case "LeftJoin":
				pageData = leftJoinDAO.getJoinByPage(joinPageInfo);
				break;
			case "RightJoin":
				pageData = rightJoinDAO.getJoinByPage(joinPageInfo);
				break;
			case "FullJoin":
				pageData = fullJoinDAO.getJoinByPage(joinPageInfo);
				break;
			case "CrossJoin":
				pageData = crossJoinDAO.getJoinByPage(joinPageInfo);
				break;
			case "LeftAntiJoin":
				pageData = leftAntiJoinDAO.getJoinByPage(joinPageInfo);
				break;
			case "RightAntiJoin":
				pageData = rightAntiJoinDAO.getJoinByPage(joinPageInfo);
				break;
			case "FullAntiJoin":
				pageData = fullAntiJoinDAO.getJoinByPage(joinPageInfo);
				break;
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return pageData;
	}

	public PageData<EmployeeEmployeeJoin> getSelfJoinByPage(JoinPageInfo joinPageInfo) {
		PageData<EmployeeEmployeeJoin> pageData = null;
		try {
				pageData = selfJoinDAO.getJoinByPage(joinPageInfo);
			
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return pageData;
	}


} // end class
