package server.tools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import server.tools.model.Customer;
import server.tools.model.Employee;
import server.tools.model.PageData;
import server.tools.model.PageInfo;

public class CustomerDAO extends BaseDAO {

	private static final Logger LOG = Logger.getLogger(EmployeeDAO.class.getName());
	//
	private PageDAO pageDAO = new PageDAO<Customer>();
	private CustomerMapper mapper = new CustomerMapper();

	public CustomerDAO() {
	}

	public int add(Customer customer) {
		int rowAffected = 0;
		Connection con = null;
		PreparedStatement prep = null;
		try {
			con = DbManager.openDbConnection();
			//
			// note: id is auto generated
			prep = con.prepareStatement("insert into Customers (lastName, firstName, phone, email, "
					+ "addressLine1, addressLine2, city, state, postalCode ) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			int idx = 1;
			// for insert
			mapPrep(idx, customer, prep);
			//
			rowAffected = prep.executeUpdate();
			con.commit();
			System.out.println("Created new Customer, rowAffected: " + rowAffected);
		} catch (SQLException e) {
			System.err.println("SQL error: " + e);
			e.printStackTrace(System.out);
			rollBack(con);
		} finally {
			closeCon(con);
		}
		return rowAffected;
	}
	public void mapPrep(int idx, Customer customer, PreparedStatement prep) throws SQLException {
		prep.setString(idx++, customer.lastName);
		prep.setString(idx++, setNullOnEmpty(customer.firstName));
		prep.setString(idx++, setNullOnEmpty(customer.phone));
		prep.setString(idx++, customer.email);
		prep.setString(idx++, customer.addressLine1);
		prep.setString(idx++, setNullOnEmpty(customer.addressLine2));
		prep.setString(idx++, customer.city);
		prep.setString(idx++, customer.state);
		prep.setString(idx++, customer.postalCode);
}

	public int update(Customer customer) {
		int rowAffected = 0;
		Connection con = null;
		PreparedStatement prep = null;
		try {
			con = DbManager.openDbConnection();
			//
			prep = con.prepareStatement("update Customers " + " Set lastName = ?, firstName = ?, phone = ?, email =? , "
					+ " addressLine1 = ?, addressLine2 = ?, city = ?, state = ?, postalCode = ?" + "  where id = "
					+ customer.id);
			int idx = 1;
			// for update
			mapPrep(idx, customer, prep);
			//
			rowAffected = prep.executeUpdate();
			con.commit();
			System.out.println("Update Employee, rowAffected: " + rowAffected);
		} catch (SQLException e) {
			System.err.println("SQL error: " + e);
			e.printStackTrace(System.out);
			rollBack(con);
		} finally {
			closeCon(con);
		}
		return rowAffected;

	}

	public Customer getCustomer(int id) {
		Customer customer = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DbManager.openDbConnection();
			String sql = "select * from Customers where customerNumber = ?";
			PreparedStatement p = con.prepareStatement(sql);
			p.setInt(1, id);
			rs = p.executeQuery();
			//
			if (rs.next()) {
				customer = mapper.mapRow(rs);
			}
		} catch (SQLException e) {
			System.err.println("SQL error: " + e);
			e.printStackTrace(System.out);
		} finally {
			closeRS(rs);
			closeCon(con);
		}
		return customer;
	}

	public PageData<Customer> getCustomersByPage(PageInfo pageInfo) {
		pageDAO.setMapper(mapper);
		String pagePrep = "select * from customers LIMIT ? OFFSET ? ";
		String countPrep = "select count(*) AS rowCount from customers";
		PageData<Customer> pageData = pageDAO.getDataByPage(pageInfo, pagePrep, countPrep);
		return pageData;
	}

	public int deleteCustomer(int id) {
		int rowAffected = -1;
		Connection con = null;
		PreparedStatement prep = null;
		try {
			con = DbManager.openDbConnection();
			String sql = "delete from customers where id = ?";
			prep = con.prepareStatement(sql);
			prep.setInt(1, id);
			rowAffected = prep.executeUpdate();
			con.commit();
			System.out.println("Deleted Customer, rowAffected: " + rowAffected);
		} catch (SQLException e) {
			System.err.println("SQL error: " + e);
			e.printStackTrace(System.out);
			rollBack(con);
		} finally {
			closeCon(con);
		}
		return rowAffected;
	}

} // end class