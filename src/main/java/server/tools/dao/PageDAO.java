package server.tools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import server.tools.model.EmployeeCustomerJoin;
import server.tools.model.EmployeeEmployeeJoin;
import server.tools.model.JoinPageInfo;
import server.tools.model.PageData;
import server.tools.model.PageInfo;

public class PageDAO<T> extends BaseDAO {

	private static final Logger LOG = Logger.getLogger(PageDAO.class.getName());
	
	// Mapper maps ResultSet to java row object
	private Mapper m;
	

	public PageDAO() {
	}

	public void setMapper(Mapper m) {
		this.m = m;
	}

	/**
	 * Get data rows from database for a single page 
	 * 
	 * @param pageInfo page number, page size
	 * @param pagePrep SQL query to create prepared statement for data retrieval
	 * @param countPrep SQL query to create prepared statement for total row count
	 * @return list of rows of database data for one page
	 */
	public PageData<T> getDataByPage(PageInfo pageInfo, String pagePrep, String countPrep) {
		List<T> pageList = new ArrayList<T>();
		PageData<T> pageData = null;
			Integer pageNum = pageInfo.pageNum;
			Integer pageSize = pageInfo.pageSize;
			LOG.log(Level.INFO, "get data for page: " + pageNum + ", with page size: " + pageSize);
			Connection con = null;
			PreparedStatement prep = null;
			ResultSet rs = null;
			PreparedStatement prep2 = null;
			ResultSet rs2 = null;
			//
			try {
				// add space to join command
				int recordOffset = (pageNum - 1) * pageSize;
				con = DbManager.openDbConnection();
				//
				prep = con.prepareStatement(pagePrep);
				prep.setInt(1, pageSize);
				prep.setInt(2, recordOffset);
				LOG.log(Level.INFO, "data prep: " + prep.toString());
				//
				rs = prep.executeQuery();
				while (rs.next()) {
					T nextRow = (T) m.mapRow(rs);
					pageList.add(nextRow);
				}
				int rowCount = 0;
				prep2 = con.prepareStatement(countPrep);
				LOG.log(Level.INFO, "count prep: " + prep2.toString());
				rs2 = prep2.executeQuery();
				if (rs2.next()) {
					rowCount = rs2.getInt(1);
				}
				Integer totalPages = numberOfPages(rowCount, pageSize);
				if(pageNum > totalPages) {
					pageNum = totalPages;
				}
				//
				PageInfo updatedPageInfo = new PageInfo(pageNum, pageSize, totalPages);
				pageData = new PageData<T>(updatedPageInfo, pageList);
				//
				LOG.log(Level.INFO, "getDataByPage completed, rowCount: " + rowCount + ", totalPages: " + totalPages);
			} catch (SQLException ex) {
				LOG.log(Level.SEVERE, ex.getMessage(), ex);
			} finally {
				closeRS(rs);
				closeRS(rs2);
				closeCon(con);
			}
		return pageData;
	}
	
	
	


} // end class
