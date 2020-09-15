package br.com.mariobacellar.presentations.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.com.mariobacellar.presentations.interceptor.RequestInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry
	          .addResourceHandler("/resources/**")
	          .addResourceLocations("file:./src/main/resources/"); 
	    }
	
	 @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(new RequestInterceptor());
	    }
}
