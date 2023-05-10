package project3.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import project1.entity.Class;
import project1.entity.School;
import project3.model.ClassDtls;
import project3.model.SchoolDtls;
import project3.model.UserDtls;
import project3.service.Services;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class handler {
	
//	@Autowired
//	private ISchoolDetailsService service;
//	@Autowired
//	private ServiceImp service;
	@Autowired
	private Services service1;
	
	
	public Mono<ServerResponse> getAllSchools(ServerRequest serverRequest) {
		Flux<SchoolDtls> responseMono= service1.getAllSchools();
		return ServerResponse.ok() 
				.contentType(MediaType.APPLICATION_JSON)
				.body(responseMono, School.class);
	}
	
		
//	/school/{schoolId}
	public Mono<ServerResponse> getSchoolDetails(ServerRequest serverRequest) {
		String uniqueCode= serverRequest.pathVariable("uniqueCode");
		Mono<SchoolDtls> responseMono= service1.getSchoolDetails(uniqueCode);
		return ServerResponse.ok() 
				.contentType(MediaType.APPLICATION_JSON)
				.body(responseMono, School.class);
	}
	

//	/class/{classId}
	//public Mono<ServerResponse> getClassDetails(ServerRequest serverRequest) {
	//	String classCode= serverRequest.pathVariable("classCode");
	//	Mono<ClassDtls> responseMono= service1.getAllClass(classCode);
	//	return ServerResponse.ok() 
	//			.contentType(MediaType.APPLICATION_JSON)
		//		.body(responseMono, School.class);
	//}


//	public Mono<ServerResponse> getClassData(ServerRequest serverRequest) {
//		String schoolId= serverRequest.pathVariable("schoolId");
//		Mono<List<ClassDetails>> responseMono= service.getClassData(schoolId);
//		return ServerResponse.ok() 
//				.contentType(MediaType.APPLICATION_JSON)
//				.body(responseMono, School.class);
//	}

	
	
	
//	public Mono<ServerResponse> getUserByClassId(ServerRequest serverRequest) {
//		String classId= serverRequest.pathVariable("classId");
//		Flux<UserDetails> responseMono= service.getUserByClassId(classId);
//		return ServerResponse.ok() 
//				.contentType(MediaType.APPLICATION_JSON)
//				.body(responseMono, School.class);
//	}
	
//	/user/{userId} : fetch details of a specific user
//	public Mono<ServerResponse> getUserDetailsByUserId(ServerRequest serverRequest) {
//		String userId= serverRequest.pathVariable("userId");
//		Mono<UserDetails> responseMono= service.getUserDetailsByUserId(userId);
//		return ServerResponse.ok() 
//				.contentType(MediaType.APPLICATION_JSON)
//				.body(responseMono, School.class);
//	}

//	/user
	public Mono<ServerResponse> getAllUserDetails(ServerRequest serverRequest) {
		Flux<UserDtls> responseMono= service1.getAllUserDetails();
		return ServerResponse.ok() 
				.contentType(MediaType.APPLICATION_JSON)
				.body(responseMono, School.class);
	}
	
//	/user/{userId}
	public Mono<ServerResponse> getUserDetailsById(ServerRequest serverRequest) {
		String userId= serverRequest.pathVariable("userId");
		Mono<UserDtls> responseMono= service1.getUserDetailsById(userId);
		return ServerResponse.ok() 
				.contentType(MediaType.APPLICATION_JSON)
				.body(responseMono, School.class);
	}
	public Mono<ServerResponse> getClassDetailsBySchoolAndClassId(ServerRequest serverRequest){
		String uniqueCode= serverRequest.pathVariable("uniqueCode");
		String classCode= serverRequest.pathVariable("classCode");
		Mono<ClassDtls> responseMono= service1.getClassDetailsBySchoolAndClassId(uniqueCode,classCode);
		return ServerResponse.ok() 
				.contentType(MediaType.APPLICATION_JSON)
				.body(responseMono, School.class);
	}
	public Mono<ServerResponse> getAllStudentsOfSchool(ServerRequest request) {
		String uniqueCode=request.pathVariable("uniqueCode");
		Flux<UserDtls> userFlux=service1.getAllStudentsOfSchool(uniqueCode);
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(userFlux,UserDtls.class);
	}
	
	public Mono<ServerResponse> getAllTeachersOfSchool(ServerRequest request) {
		String uniqueCode=request.pathVariable("uniqueCode");
		Flux<UserDtls> userFlux=service1.getAllTeachersOfSchool(uniqueCode);
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(userFlux,UserDtls.class);
	}
	
}
