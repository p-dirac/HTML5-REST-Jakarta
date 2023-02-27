/*
 * Copyright (c) 2014, 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package server.tools.control;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import server.tools.dao.CustomerDAO;
import server.tools.model.Customer;
import server.tools.model.PageData;
import server.tools.model.PageInfo;


/**
 * REST Web Service
 *
 */
@Path("customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

	private static final Logger LOG = Logger.getLogger(CustomerResource.class.getName());
	
	 //  @Inject //-- not working,  dao -> null
	 //  private CustomerDAO dao;
	
	private CustomerDAO dao = new CustomerDAO();	
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTest() {
		Response resp = null;
		try {
			HashMap<String, Integer> test = new HashMap<>();
			test.put("Test Only", 123);
			test.put("Status", 1);
			resp = Response.status(200).entity(test).build();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
			throw new RuntimeException(ex);
		}
		return resp;

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Customer cust) {
		Response resp = null;
		HashMap<String, String> respData = new HashMap<>();
		String msg = null;
		try {
			LOG.log(Level.INFO, "add customer, cust: " + cust);
			//
			Integer rowAffected = dao.add(cust);
			if(rowAffected == 1) {
				msg = "Result: 1 record added";
			} else if (rowAffected == 0) {
				msg = "Result: no record added";
			}
			respData.put("msg", msg);
			// code 200 : success
			resp = Response.status(200).entity(respData).build();
			LOG.log(Level.INFO, "add Employee, resp: " + resp);
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
	public Response update(Customer cust) {
		Response resp = null;
		HashMap<String, String> respData = new HashMap<>();
		String msg = null;
		try {
			LOG.log(Level.INFO, "update customer, cust: " + cust);
			//
			Integer rowAffected = dao.update(cust);
			if(rowAffected == 1) {
				msg = "Result: 1 record updated";
			} else if (rowAffected == 0) {
				msg = "Result: no record updated";
			}
			respData.put("msg", msg);
			// code 200 : success
			resp = Response.status(200).entity(respData).build();
			LOG.log(Level.INFO, "update Employee, resp: " + resp);
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
	public Response getCustomersByPage(PageInfo pageInfo) {
		Response resp = null;
		try {
			LOG.log(Level.INFO, "pageInfo: " + pageInfo);
			//
			PageData<Customer> pageData = dao.getCustomersByPage(pageInfo);
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
	 * @return an instance of Customer
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") Integer id) {
		Response resp = null;
		try {
			LOG.log(Level.INFO, "get customer for id: " + id);
			//
			Customer customer = dao.getCustomer(id);
			resp = Response.status(200).entity(customer).build();
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
	public Response delete(@PathParam("id") Integer id) {
		Response resp = null;
		HashMap<String, String> respData = new HashMap<>();
		String msg = null;
		try {
			LOG.log(Level.INFO, "delete employee for id: " + id);
			//
			Integer rowAffected = dao.deleteCustomer(id);
			if(rowAffected == 1) {
				msg = "Result: 1 record deleted";
			} else if (rowAffected == 0) {
				msg = "Result: no record deleted";
			}
			respData.put("msg", msg);
			resp = Response.status(200).entity(respData).build();
			LOG.log(Level.INFO, "delete by id, resp: " + resp);
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
