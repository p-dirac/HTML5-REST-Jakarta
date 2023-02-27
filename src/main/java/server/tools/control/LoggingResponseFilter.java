package server.tools.control;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;

@Provider
public class LoggingResponseFilter implements ContainerResponseFilter {

	private static final Logger LOG = Logger.getLogger(LoggingResponseFilter.class.getName());

	@Override
	public void filter(ContainerRequestContext reqCtx, ContainerResponseContext respCtx) throws IOException {

		MultivaluedMap<String,Object> headers = respCtx.getHeaders();
		LOG.log(Level.INFO, "response headers: " + headers);
		
		if (respCtx.hasEntity()) {
			Object entity = respCtx.getEntity();
			LOG.log(Level.INFO, "response entity: " + entity);
		}

	}
}