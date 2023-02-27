package server.tools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
/*
import jakarta.persistence.EntityManager;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
*/
import java.util.logging.Logger;

import server.tools.model.Employee;
import server.tools.model.EmployeeCustomerJoin;
import server.tools.model.PageData;
import server.tools.model.PageInfo;

public class EmployeeDAO extends BaseDAO {

	private static final Logger LOG = Logger.getLogger(EmployeeDAO.class.getName());
	//
	private PageDAO pageDAO = new PageDAO<Employee>();
	private EmployeeMapper mapper = new EmployeeMapper();

	public EmployeeDAO() {
	}

	public int add(Employee employee) {
		int rowAffected = 0;
		Connection con = null;
		PreparedStatement prep = null;
		try {
			con = DbManager.openDbConnection();
			prep = con.prepareStatement(
					"insert ignore into employees (id, lastName, firstName, phone, email, reportsTo, jobTitle, "
							+ "addressLine1, addressLine2, city, state, postalCode ) "
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			//
			int idx = 1;
			// for insert
			prep.setInt(idx++, employee.id);
			mapPrep(idx, employee, prep);
			//
			rowAffected = prep.executeUpdate();
			con.commit();
			LOG.log(Level.INFO, "Add Employee, rowAffected: " + rowAffected);
		} catch (SQLException ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
			rollBack(con);
		} finally {
			closeCon(con);
		}
		return rowAffected;
	}
	public void mapPrep(int idx, Employee employee, PreparedStatement prep) throws SQLException {
			prep.setString(idx++, employee.lastName);
			prep.setString(idx++, setNullOnEmpty(employee.firstName));
			prep.setString(idx++, setNullOnEmpty(employee.phone));
			prep.setString(idx++, employee.email);
			// reportsTo can be null only for top level president
			setIntOrNull(prep, idx++, employee.reportsTo);
			prep.setString(idx++, employee.jobTitle);
			prep.setString(idx++, employee.addressLine1);
			prep.setString(idx++, setNullOnEmpty(employee.addressLine2));
			prep.setString(idx++, employee.city);
			prep.setString(idx++, employee.state);
			prep.setString(idx++, employee.postalCode);
	}

	public int updateEmployee(Employee employee) {
		int rowAffected = 0;
		Connection con = null;
		PreparedStatement prep = null;
		try {
			con = DbManager.openDbConnection();
			prep = con.prepareStatement("update employees "
					+ " Set lastName = ?, firstName = ?, phone = ?, email =? , reportsTo = ?, jobTitle = ?, "
					+ " addressLine1 = ?, addressLine2 = ?, city = ?, state = ?, postalCode = ?" + "  where id = "
					+ employee.id);
			int idx = 1;
			// for update
			mapPrep(idx, employee, prep);
			//
			rowAffected = prep.executeUpdate();
			con.commit();
			LOG.log(Level.INFO, "Update Employee, rowAffected: " + rowAffected);
		} catch (SQLException ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
			rollBack(con);
		} finally {
			closeCon(con);
		}
		return rowAffected;
	}

	public Employee getEmployee(int id) {
		Employee emp = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DbManager.openDbConnection();

			String sql = "select * from employees where empid = ?";
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setInt(1, id);
			rs = prep.executeQuery();
			//
			if (rs.next()) {
				emp = mapper.mapRow(rs);
			}
			LOG.log(Level.INFO, "Get Employee comleted");
		} catch (SQLException ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			closeRS(rs);
			closeCon(con);
		}
		return emp;
	}

	public PageData<Employee> getEmployeesByPage(PageInfo pageInfo) {
		PageData<Employee> pageData = null;
		try {
			pageDAO.setMapper(mapper);
			String pagePrep = "select * from employees LIMIT ? OFFSET ? ";
			String countPrep = "select count(*) AS rowCount from employees";
			pageData = pageDAO.getDataByPage(pageInfo, pagePrep, countPrep);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return pageData;
	}

	public int deleteEmployee(int id) {
		int rowAffected = 0;
		Connection con = null;
		PreparedStatement prep = null;
		try {
			con = DbManager.openDbConnection();
			String sql = "delete from employees where id = ?";
			prep = con.prepareStatement(sql);
			prep.setInt(1, id);
			rowAffected = prep.executeUpdate();
			con.commit();
			LOG.log(Level.INFO, "Deleted Employee, rowAffected: " + rowAffected);
		} catch (SQLException ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
			rollBack(con);
		} finally {
			closeCon(con);
		}
		return rowAffected;
	}

} // end class