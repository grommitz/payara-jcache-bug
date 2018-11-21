package grommitz;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class RestApp extends ResourceConfig {

	public RestApp() {
		super();
		packages(true, "grommitz");
//		register(JacksonFeature.class);
//		register(RolesAllowedDynamicFeature.class);
//		register(ShiroFeature.class);
//		register(ObjectMapperProvider.class);
	}

}