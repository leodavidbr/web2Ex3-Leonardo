package imd.ufrn.thetriade.web2Ex2.api;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import imd.ufrn.thetriade.web2Ex2.Web2Ex2Application;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Web2Ex2Application.class);
	}

}
