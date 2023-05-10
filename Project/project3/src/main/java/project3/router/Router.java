package project3.router;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import project3.handler.handler;

@Configuration
public class Router {

		
	
	@Bean 
	public RouterFunction<ServerResponse> schoolAPIs(handler handler){
		return route(GET("/user/{userId}"), handler :: getUserDetailsById)
				.andRoute(GET("/user"), handler :: getAllUserDetails)
				.andRoute(GET("/schools/{uniqueCode}/classes/{classCode}"), handler :: getClassDetailsBySchoolAndClassId)
				.andRoute(GET("/schools"), handler :: getAllSchools)
				.andRoute(GET("/schools/{uniqueCode}"), handler :: getSchoolDetails)
				.andRoute(GET("/schools/{uniqueCode}/students"), handler :: getAllStudentsOfSchool)
				.andRoute(GET("/schools/{uniqueCode}/teacher"), handler :: getAllTeachersOfSchool);
				//.andRoute(GET("/class/{classCode}"), handler :: getClassDetails)
				//.andRoute(GET("/class/{userId}"), handler :: getClassesByUserId);
			
	}

}
