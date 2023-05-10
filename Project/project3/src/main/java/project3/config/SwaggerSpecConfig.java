package project3.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;


import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Configuration
public class SwaggerSpecConfig {

	 @Primary
	    @Bean
	    public SwaggerResourcesProvider swaggerResourcesProvider(InMemorySwaggerResourcesProvider defaultResourcesProvider) {
	        return () -> {
	            SwaggerResource wsResource = new SwaggerResource();
	            wsResource.setName("Demo Application");
	            wsResource.setSwaggerVersion("2.0");
	            wsResource.setLocation("/swagger-apis/swagger.yaml");

	            //List<SwaggerResource> resources = new ArrayList<>(defaultResourcesProvider.get());
	            List<SwaggerResource> resources = new ArrayList<>();
	            resources.add(wsResource);
	            return resources;
	        };
	    }
	 
	 private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/static/" };

	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/**")
	            .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	    }
}
