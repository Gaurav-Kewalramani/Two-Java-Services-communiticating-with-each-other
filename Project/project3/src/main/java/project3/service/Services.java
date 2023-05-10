package project3.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import project1.entity.School;
import project2.entity.Users;
import project3.model.ClassDtls;
import project3.model.SchoolDtls;
import project3.model.UserDtls;
import project1.entity.Class;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Services {
	
	// Project 1 URL
	public static final String PROJECT1 = "http://localhost:8080/";		
	// Project 2 URL 
	public static final String PROJECT2 = "http://localhost:8081/";	

	WebClient webClient = WebClient.create();

	
	
	
	
	
//	/users :- fetch all user details including school, class and teacher details
	public Flux<UserDtls> getAllUserDetails(){
		return webClient.get() 
				.uri(PROJECT2+"/user")
				.retrieve()
				.bodyToFlux(Users.class)
				.flatMap(user-> getUserDetailsById(user.getUserId()));
	}
	
	
//	/user/{userId} : fetch details of a specific user
	public Mono<UserDtls> getUserDetailsById(String userId){
		return getUserById(userId).flatMap(user->{
			return getClassesByUserId(user);
		});		
	}
	private Mono<Users> getUserById(String userId){
		return webClient.get()
				.uri(PROJECT2+"user/"+userId)
				.retrieve()
				.bodyToMono(Users.class);
	}
	
	private Mono<UserDtls> getClassesByUserId(Users user){
		return webClient.get()
						.uri(PROJECT1+"class/"+user.getClassCode()) 
						.retrieve()
						.bodyToMono(Class.class)
						.flatMap(class1->{
							return getSchoolByClassId(class1, user);							
						});
	} 

	private Mono<UserDtls> getSchoolByClassId(Class class1, Users user){
		UserDtls userDetails= new UserDtls();
		return webClient.get()
				.uri(PROJECT1+"students/"+class1.getUniqueCode()) 
				.retrieve()
				.bodyToMono(School.class)
				.flatMap(school->{
					userDetails.setUserId(user.getUserId());
					userDetails.setUserName(user.getName());
					userDetails.setUserrole(user.getUserrole());
					userDetails.setClassCode(user.getClassCode());
					userDetails.setClass1(class1);
					userDetails.setSchool(school);
					return Mono.just(userDetails);
				});
	} 
	
	
	
	

	
	
	
	
	
	
	
	
	
	
//	/schools :- to fetch details for all schools includes class , student as well as teacher details , add query param on school name

	public Flux<SchoolDtls> getAllSchools(){
		return webClient.get() 
				.uri(PROJECT1+"/students")
				.retrieve()
				.bodyToFlux(School.class)
				.flatMap(school-> getSchoolDetails(school.getUniqueCode()));
	}
	
	// /school/{schoolId} : fetch details for a particular school 
	public Mono<SchoolDtls> getSchoolDetails(String uniqueCode){
		return getSchoolById(uniqueCode).flatMap(school->{
			return getClassesBySchoolId(school)
					.flatMap(schoolObjectWithClassDetail->{
						return getUsersByClassId(schoolObjectWithClassDetail);
					});
		});		
	}
	
	private Mono<School> getSchoolById(String uniqueCode){
		return webClient.get()
				.uri(PROJECT1+"students/"+uniqueCode)
				.retrieve()
				.bodyToMono(School.class);
	}

	private Mono<SchoolDtls> getClassesBySchoolId(School school){
		SchoolDtls schoolDetails= new SchoolDtls();
		return webClient.get()
						.uri(PROJECT1+"classes") 
						.retrieve()
						.bodyToFlux(ClassDtls.class)
						.filter(class1-> class1.getUniqueCode().equals(school.getUniqueCode()))
						.collectList()
						.flatMap(list->{
							schoolDetails.setUniqueCode(school.getUniqueCode());
							schoolDetails.setSchoolName(school.getSchoolName());
							schoolDetails.setClassList(list);
							return Mono.just(schoolDetails);
							});
	} 

	private Mono<SchoolDtls> getUsersByClassId(SchoolDtls schoolDtls){
		List<ClassDtls> classes= schoolDtls.getClassList();
		
		return Flux.fromIterable(classes).flatMap(classObj->{
			return getUsers(classObj.getUserId())
					.flatMap(users->setUsersInClassObj(classObj, users));
		}).collectList().flatMap(classesObj->{
			schoolDtls.setClassList(classesObj);
			return Mono.just(schoolDtls);
		});
	}
		
		private Mono<ClassDtls> setUsersInClassObj(ClassDtls classObj, List<Users>users){
			classObj.setUsersList(users);
			return Mono.just(classObj);
		}
		
		private Mono<List<Users>> getUsers(String classCode){
			return webClient.get()
					.uri(PROJECT2+"users") 
					.retrieve()
					.bodyToFlux(Users.class)
					.filter(user-> user.getClassCode().equals(classCode))
					.collectList();
			
		}


//		/school/{schoolId}/class/{classId}: fetch details for a class in a school
	public Mono<ClassDtls> getClassDetailsBySchoolAndClassId(String uniqueCode, String classCode){
		return getSchoolById(uniqueCode).flatMap(school->getClassByClassId(classCode, uniqueCode));
	}
	
	
	public Mono<ClassDtls> getClassByClassId(String classCode, String uniqueCode) {
		ClassDtls classDetails= new ClassDtls();
		return webClient.get()
				.uri(PROJECT1+"class/"+classCode)
				.retrieve()
				.bodyToMono(ClassDtls.class)
				.flatMap(class1->{
					if(class1.getUniqueCode().equals(uniqueCode)) {
						classDetails.setClassName(class1.getClassName());
						classDetails.setStudentStrength(class1.getStudentStrength());
						classDetails.setUserId(class1.getUserId());
						classDetails.setUniqueCode(class1.getUniqueCode());
						return Mono.just(classDetails);
					}
					throw new IllegalArgumentException("Given Id not found."+ uniqueCode);
				});
			
	}

	

//	public Flux<UserDtls> getAllStudentsOfSchool(String userId) {
//		Flux<UserDtls>userList=getAllUsers();
//		Flux<UserDtls>students=userList.filter(e->e.getUserrole().equals("STUDENT"));
//		Flux<UserDtls>studentsOfSchool=students.filterWhen(e->getClassOfSchoolById(e.getClassCode(), userId).hasElements());
//		
//		return studentsOfSchool;
//		
//	}
//	
//	//-/user/{schoolId}/teachers
//	
//	public Flux<UserDtls> getAllTeachersOfSchool(String userId) {
//		Flux<UserDtls>userList=getAllUsers();
//		Flux<UserDtls>teachers=userList.filter(e->e.getUserrole().equals("INSTRUCTOR"));
//		Flux<UserDtls>teachersOfSchool=teachers.filterWhen(e->getClassOfSchoolById(e.getClassCode(), userId).hasElements());
//		
//		return teachersOfSchool;
//	}
//	
//	
	
	public Flux<ClassDtls>getAllStudentsOfSchool(String classCode,String uniqueCode) {
		Flux<ClassDtls>classList=webClient.get()
				 .uri("http://localhost:8081/classes/")
				 .retrieve()
				 .bodyToFlux(ClassDtls.class);
		Flux<ClassDtls>filtered=classList.filter(e->e.getUniqueCode()==uniqueCode);
			
		return filtered.filter(e->e.getClassCode()==classCode);
	}
	public Flux<ClassDtls> getAllTeachersOfSchool(String classCode,String uniqueCode) {
		Flux<ClassDtls>classList=webClient.get()
				 .uri("http://localhost:8080/classes")
				 .retrieve()
				 .bodyToFlux(ClassDtls.class);
		Flux<ClassDtls>filtered=classList.filter(a->a.getUniqueCode()==uniqueCode);
			
		return filtered.filter(e->e.getClassCode()==classCode);
	}
	
	
	public Flux<UserDtls> getAllStudentsOfSchool(String uniqueCode){
		Flux<UserDtls> userList=getAllUserDetails();
		Flux<UserDtls> studentsFlux=userList.filter(a->a.getUserrole().equals("Student"));
		//Flux<UserDetails> studentsSchool=
		
		return studentsFlux.filterWhen(a->getAllStudentsOfSchool(a.getClassCode(),uniqueCode).hasElements());
	}
//For user/{schoolId}/teacher
	
	public Flux<UserDtls> getAllTeachersOfSchool(String uniqueCode) {
		Flux<UserDtls> userList=getAllUserDetails();
		Flux<UserDtls> teacherFlux=userList.filter(a->a.getUserrole().equals("Teacher"));
		return teacherFlux.filterWhen(a->getAllTeachersOfSchool(a.getClassCode(),uniqueCode).hasElements());
	}
}
