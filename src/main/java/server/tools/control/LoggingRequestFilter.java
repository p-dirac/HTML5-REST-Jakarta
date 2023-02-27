package server.tools.control;

import java.io.IOException;
import java.net.URI;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.ext.Provider;


@PreMatching
@Provider
public class LoggingRequestFilter implements ContainerRequestFilter  {
	
	private static final Logger LOG = Logger.getLogger(LoggingRequestFilter.class.getName());
	
    @Override
    public void filter(ContainerRequestContext reqCtx) throws IOException {
        String method = reqCtx.getMethod();
        LOG.log(Level.INFO, "request log, method: " + method);
        //
        URI path = reqCtx.getUriInfo().getAbsolutePath();
        LOG.log(Level.INFO, "request log, path: " + path);
        //
        Set keys = reqCtx.getHeaders().keySet();
        for (Object key : keys) {
        	Object val = reqCtx.getHeaders().get(key);
        	LOG.log(Level.INFO, "headers, key: " + key + ", val: " + val);
        }
    }
}