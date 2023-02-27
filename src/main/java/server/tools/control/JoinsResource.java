
package server.tools.control;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import server.tools.dao.JoinsDAO;
import server.tools.model.EmployeeCustomerJoin;
import server.tools.model.EmployeeEmployeeJoin;
import server.tools.model.JoinPageInfo;
import server.tools.model.PageData;

/**
 * REST Web Service
 *
 */
@Path("joins")
public class JoinsResource {

	private static final Logger LOG = Logger.getLogger(JoinsResource.class.getName());

	// dao: database functions
	private JoinsDAO dao = new JoinsDAO();
    
	@POST
	@Path("/page")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJoinByPage(JoinPageInfo joinPageInfo) {
		Response resp = null;
		try {
			LOG.log(Level.INFO, "get joinPageInfo: " + joinPageInfo);
			String joinType = joinPageInfo.joinType;
			if("SelfJoin".equals(joinType)) {
				//
				PageData<EmployeeEmployeeJoin> pageData = dao.getSelfJoinByPage(joinPageInfo);
				//
				resp = Response.status(200).entity(pageData).build();
			} else {
				//
				PageData<EmployeeCustomerJoin> pageData = dao.getJoinByPage(joinPageInfo);
				//
				resp = Response.status(200).entity(pageData).build();
			}
			
			//
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
			String msg = "Error" + ex.getMessage();
			// code 500 : internal server error
			resp = Response.status(500).entity(msg).build();
		}
		return resp;
	}


}  //end class
