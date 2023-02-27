package server.tools.control;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.inject.Inject;
// import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import server.tools.dao.EmployeeDAO;
import server.tools.model.Employee;
import server.tools.model.PageData;
import server.tools.model.PageInfo;

/**
 * REST Web Service
 * 
 * ref:
 * 
 *
 */
@Path("employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

	private static final Logger LOG = Logger.getLogger(EmployeeResource.class.getName());

	// @Inject //-- not working,  dao -> null
	//  private EmployeeDAO dao;
	
	// dao: database functions
	private EmployeeDAO dao = new EmployeeDAO();

	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTest() {
		Response resp = null;
		HashMap<String, String> respData = new HashMap<>();
		String msg = null;
		try {
			HashMap<String, Integer> test = new HashMap<>();
			test.put("Test Only", 123);
			test.put("Status", 1);
			resp = Response.status(200).entity(test).build();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
			msg = "Error" + ex.getMessage();
			respData.put("msg", msg);
			// code 500 : internal server error
			resp = Response.status(500).entity(respData).build();
		}
		return resp;

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Employee emp) {
		Response resp = null;
		HashMap<String, String> respData = new HashMap<>();
		String msg = null;
		try {
			LOG.log(Level.INFO, "add Employee, emp: " + emp);
			//
			Integer rowAffected = dao.add(emp);
			if(rowAffected == 1) {
				msg = "Result: 1 record added";
			} else if (rowAffected == 0) {
				msg = "Result: no record added";
			}
			respData.put("msg", msg);
			// code 200 : success
			resp = Response.status(200).entity(respData).build();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
			msg = "Error" + ex.getMessage();
			respData.put("msg", msg);
			// code 500 : internal server error
			resp = Response.status(500).entity(respData).build();
		}
		return resp;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Employee emp) {
		Response resp = null;
		HashMap<String, String> respData = new HashMap<>();
		String msg = null;
		try {
			LOG.log(Level.INFO, "update Employee, emp: " + emp);
			//
			Integer rowAffected = dao.updateEmployee(emp);
			if(rowAffected == 1) {
				msg = "Result: 1 record updated";
			} else if (rowAffected == 0) {
				msg = "Result: no record updated";
			}
			respData.put("msg", msg);
			// code 200 : success
			resp = Response.status(200).entity(respData).build();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
			msg = "Error" + ex.getMessage();
			respData.put("msg", msg);
			// code 500 : internal server error
			resp = Response.status(500).entity(respData).build();
		}
		return resp;
	}

	@POST
	@Path("/page")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeesByPage(PageInfo pageInfo) {
		Response resp = null;
		try {
			LOG.log(Level.INFO, "pageInfo: " + pageInfo);
			//
			PageData<Employee> pageData = dao.getEmployeesByPage(pageInfo);
			//
			resp = Response.status(200).entity(pageData).build();
			LOG.log(Level.INFO, "get page, resp: " + resp);
			//
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
			String msg = "Error" + ex.getMessage();
			// code 500 : internal server error
			resp = Response.status(500).entity(msg).build();		
		}
		return resp;
	}

	/**
	 * @return an instance of Employee
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") Integer id) {
		Response resp = null;
		try {
			LOG.log(Level.INFO, "get employee for id: " + id);
			//
			Employee emp = dao.getEmployee(id);
			resp = Response.status(200).entity(emp).build();
			LOG.log(Level.INFO, "get by id, resp: " + resp);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
			String msg = "Error" + ex.getMessage();
			// code 500 : internal server error
			resp = Response.status(500).entity(msg).build();
		}
		return resp;

	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Integer id) {
		Response resp = null;
		HashMap<String, String> respData = new HashMap<>();
		String msg = null;
		try {
			LOG.log(Level.INFO, "delete employee for id: " + id);
			//
			Integer rowAffected = dao.deleteEmployee(id);
			if(rowAffected == 1) {
				msg = "Result: 1 record deleted";
			} else if (rowAffected == 0) {
				msg = "Result: no record deleted";
			}
			LOG.log(Level.INFO, "delete employee response msg: " + msg);
			respData.put("msg", msg);
			// code 200 : success
			resp = Response.status(200).entity(respData).build();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
			msg = "Error" + ex.getMessage();
			respData.put("msg", msg);
			// code 500 : internal server error
			resp = Response.status(500).entity(respData).build();
		}
		return resp;
	}

}
