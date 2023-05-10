package project1.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import project1.entity.School;
import project1.service.IServices;

import reactor.core.publisher.Mono;

@Component
public class Handler {
	
	@Autowired
	IServices studentServiceImpl;
	
	public Mono<ServerResponse> getAllStudents(ServerRequest serverRequest){
		return ServerResponse
				.ok()
				.body(studentServiceImpl.getAllStudents(),School.class);
	}
	
	
	
	public Mono<ServerResponse> addStudent(ServerRequest serverRequest){
		
		 Mono<School> studentMono = serverRequest.bodyToMono(School.class);
	        return  studentMono.flatMap(student -> ServerResponse  
	             .status(HttpStatus.CREATED)
	             .body(studentServiceImpl.addStudent(student),School.class) );

	}
	
	public Mono<ServerResponse> updateStudent(ServerRequest serverRequest) {
		Mono<School> student = serverRequest.bodyToMono(School.class);
		Mono<School> updatedStudent = student.flatMap(studentServiceImpl::updateStudent);
		return ServerResponse
				.status(HttpStatus.CREATED)
				.body(updatedStudent,School.class);
	}
	
	public Mono<ServerResponse> deleteStudentByuniqueCode(ServerRequest serverRequest){
		String uniqueCode= serverRequest.pathVariable("uniqueCode");
		Mono<Void> studentMono= studentServiceImpl.deleteStudentByuniqueCode(uniqueCode);
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(studentMono, School.class);
	}
	public Mono<ServerResponse> getAllClass(ServerRequest serverRequest){
		return ServerResponse
				.ok()
				.body(studentServiceImpl.getAllStudents(),Class.class);
	}
	public Mono<ServerResponse> getStudentById(ServerRequest serverRequest){
		String uniqueCode= serverRequest.pathVariable("uniqueCode");
		Mono<School> studentMono= studentServiceImpl.getStudentById(uniqueCode);
		return studentMono.flatMap(r-> ServerResponse.ok()
								.contentType(MediaType.APPLICATION_JSON)
								.body(studentMono, School.class));
						
	}
	
	
}