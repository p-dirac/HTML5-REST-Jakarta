package server.tools.control;

import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class SampleApp extends Application {
	
	@Override
	public Map<String, Object> getProperties(){
		Map<String,Object> props = new HashMap<>();
		try {
			props.put("appName", "Html5-Jakarta");
			
        } catch (Exception e) {
            System.out.println("getProperties error: " + e.getMessage());
            throw new RuntimeException(e);
        }
		return props;
	}
	
    
    
}
