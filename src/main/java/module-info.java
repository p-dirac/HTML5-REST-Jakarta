module restJoins {
	exports server.tools.control;
	exports server.tools.dao;
	exports server.tools.model;

	requires java.sql;
	// requires jakarta.servlet;
	 requires jakarta.ws.rs;
	 requires jakarta.cdi;
	// requires jakarta.persistence;
}