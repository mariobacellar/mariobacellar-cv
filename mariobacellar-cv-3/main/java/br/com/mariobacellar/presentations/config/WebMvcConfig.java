package br.com.mariobacellar.presentations.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
	
	private static final String RESOURCES_HANDLER = "/resources/";
	
	private static final String RESOURCES_LOCATION = RESOURCES_HANDLER + "**";
	
	 private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	            "classpath:/META-INF/resources/**", 
	            "classpath:/resources/**",
	            "classpath:/resources/images/**",
	            "classpath:/static/", 
	            "classpath:/public/" };
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry
	          .addResourceHandler("/resources/**")
	          .addResourceLocations("file:./src/main/resources/"); 
	    }
	

}
