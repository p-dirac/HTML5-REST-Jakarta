package server.tools.control;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogManager;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class JaxrsApp extends Application {
	
	@Override
	public Map<String, Object> getProperties(){
		Map<String,Object> props = new HashMap<>();
		try {
		//	initLogging();
			props.put("appName", "Html5-Jakarta");
			
        } catch (Exception e) {
            System.out.println("getProperties error: " + e.getMessage());
            throw new RuntimeException(e);
        }
		return props;
	}
	
	/**
     * Init logging to file
     */
    public void initLogging() {
        try {
            System.out.println("initLogging");
            String logPropFile = "clientlog.properties";
            InputStream fis = getClass().getClassLoader().getResourceAsStream(logPropFile);
            if (fis != null) {
                LogManager logmgr = LogManager.getLogManager();
                logmgr.readConfiguration(fis);
                System.out.println( "logPropFile prop: " + logmgr.getProperty("server.tools.level"));
            } else {
                System.out.println("initLogging error, log propertis file: " + logPropFile + " not found");
            }
        } catch (Exception e) {
            System.out.println("initLogging error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    
}
