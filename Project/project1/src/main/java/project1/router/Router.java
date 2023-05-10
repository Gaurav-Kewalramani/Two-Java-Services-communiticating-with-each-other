package project1.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.*;



import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import project1.handler.Handler;
import project1.handler.Handler1;


@Configuration
public class Router {
	@Bean
	RouterFunction<ServerResponse> routerFunction1(Handler studentHandler){
		 return route(GET("/students"),studentHandler::getAllStudents)
				.andRoute(POST("/students/add"),studentHandler::addStudent)
				.andRoute(RequestPredicates.PUT("/students/{uniqueCode}"),studentHandler::updateStudent)
				.andRoute(RequestPredicates.DELETE("/students/{uniqueCode}"),studentHandler::deleteStudentByuniqueCode)
		        .andRoute(GET("/students/{uniqueCode}"),studentHandler::getStudentById);
	}
	@Bean
	RouterFunction<ServerResponse> routerFunction2(Handler1 studentHand){
		return route(GET("/classes"),studentHand::getAllClasses)
				.andRoute(POST("/classes/add"),studentHand::addClass)
				.andRoute(PUT("/classes/{classCode}"),studentHand::updateClass)
				.andRoute(DELETE("/classes/{classCode}"),studentHand::deleteByclassCode)
				.andRoute(GET("/class/{classCode}"),studentHand::getStudentById);
	}
}