package org.luisyang.integration.container.jetty.plugin;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		AnnotationConfigWebApplicationContext webApplicationContext =  
                new AnnotationConfigWebApplicationContext();  
        webApplicationContext.register(AppConfig.class);  
  
  
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);  
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcherServlet", dispatcherServlet);  
        dynamic.addMapping("/");  
	}

}
