package project1.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import project1.entity.Class;
import project1.entity.School;
import project1.service.IServicess;
import reactor.core.publisher.Mono;

@Component
public class Handler1 {
	
	@Autowired
	IServicess studentServiceImp;
	
	public Mono<ServerResponse> getAllClasses(ServerRequest serverRequest){
		return ServerResponse
				.ok()
				.body(studentServiceImp.getAllClasses(),Class.class);
	}
	
	public Mono<ServerResponse> addClass(ServerRequest serverRequest){
		
		 Mono<Class> classMono = serverRequest.bodyToMono(Class.class);
	        return  classMono.flatMap(Class -> ServerResponse
	                 
	             .status(HttpStatus.CREATED)
	             .body(studentServiceImp.addClass(Class),Class.class) );

	}
	
	public Mono<ServerResponse> updateClass(ServerRequest serverRequest) {
		Mono<Class> Class = serverRequest.bodyToMono(Class.class);
		Mono<Class> updateClass = Class.flatMap(studentServiceImp::updateClass);
		return ServerResponse
				.status(HttpStatus.CREATED)
				
				.body(updateClass,Class.class);
	}
	
	public Mono<ServerResponse> deleteByclassCode(ServerRequest serverRequest){
		String classCode= serverRequest.pathVariable("classCode");
		Mono<Void> studentMono= studentServiceImp.deleteByclassCode(classCode);
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(studentMono, School.class);
}
	public Mono<ServerResponse> getStudentById(ServerRequest serverRequest){
		String classCode= serverRequest.pathVariable("classCode");
		Mono<Class> studentMono= studentServiceImp.getStudentById(classCode);
		return studentMono.flatMap(r-> ServerResponse.ok()
								.contentType(MediaType.APPLICATION_JSON)
								.body(studentMono, Class.class));
						
	}
	
}