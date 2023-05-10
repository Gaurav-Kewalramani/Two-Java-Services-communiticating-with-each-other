package project2.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import project2.handler.Handler;

@Configuration
public class router {
	@Bean
	public RouterFunction<ServerResponse> route(Handler handler){
		return RouterFunctions
				.route(POST("/user").and(accept(MediaType.APPLICATION_JSON)), handler::addUser)
				.andRoute(GET("/user").and(accept(MediaType.APPLICATION_JSON)),handler::getUsers)//
				.andRoute(GET("/user/{id}").and(accept(MediaType.APPLICATION_JSON)),handler::getUserById)
				.andRoute(PUT("/user/{id}").and(accept(MediaType.APPLICATION_JSON)),handler::updateUsser)
				.andRoute(DELETE("/user/{id}").and(accept(MediaType.APPLICATION_JSON)),handler::getUserById)
				.andRoute(GET("/class").and(accept(MediaType.APPLICATION_JSON)),handler::getClassFrmServ1);
	}
}