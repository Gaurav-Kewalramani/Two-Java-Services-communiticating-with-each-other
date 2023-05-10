package project2.services;

//import project.Service2.entity.SchoolUser;
import project2.entity.Users;
import project2.model.ClassDtls;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IServices {
	Mono<Users> addUser(Users user);
	Mono<Users> getUserById(String uid);
	Flux<Users> getusers();
	Mono<Users> updateUser(String id, Users usser);
	Mono<Void> deleteUserById(String id);
	Flux<ClassDtls> getClassesFrmServ1();
	
}