package server.tools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDAO {

	
	public BaseDAO() {
	}

	public void closeRS(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ignore) {
				// do nothing
			}
		}
	}

	public void closeCon(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ignore) {
				// do nothing
			}
		}
	}

	public void rollBack(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ignore) {
				// do nothing
			}
		}
	}
	
	public int numberOfPages(int rowCount, int pageSize) {
		int frac = (rowCount % pageSize == 0 ? 0 : 1);
		return rowCount / pageSize + frac;
	}

	public String setNullOnEmpty(final String text) {
	    return text != null && text.isBlank() ? null : text;
	}
	public static void setIntOrNull(PreparedStatement prep, int index, Integer value) throws SQLException
	{
	    if (value != 0) {
	    	prep.setInt(index, value);
	    } else {
	    	prep.setNull(index, java.sql.Types.INTEGER);
	    }
	}
}
