package project2.services;
//import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;

//import com.example.week3b.model.Response;
//import com.google.gson.Gson;

//import project.Service2.entity.SchoolUser;
import project2.entity.Users;
import project2.model.ClassDtls;
import project2.repository.RepositoryClass;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Services implements IServices{

	@Autowired	
	RepositoryClass userRepository;
	
	//private static final String SHOOL_URL_STRING="http://localhost:8080/schools/{schoolId}";
	WebClient webClient=WebClient.create();
	
	
	@Override
	public Mono<Users> getUserById(String uid) {
		return userRepository.findById(uid);
	}
	@Override
	public Flux<Users> getusers() {
		return userRepository.findAll();
	}
	@Override
	public Mono<Users> updateUser(String id, Users usser) {
		if(userRepository.findById(id).hasElement() != null) {
			usser.setUserId(id);
			return userRepository.save(usser);}
		return null;
	}
	@Override
	public Mono<Void> deleteUserById(String id) {
		Mono<Users> toDeletMono=userRepository.findById(id);
		if(toDeletMono.hasElement()!=null)
			return userRepository.deleteById(id);
		return null;
	}
	public Flux<ClassDtls> getClassesFrmServ1() {

//		Mono<Response> responseMono= webClientBuilder.build()
//								.get()
//								.uri(EMP_API)
//								.retrieve()
//								.bodyToMono(Response.class);
			
		
		WebClient webClient = WebClient.create();
		return webClient.get()
				.uri("http://localhost:8080/classes")
				.retrieve()
				.bodyToFlux(String.class)
				.flatMap(r->{
					Gson gson= new Gson();
					return Flux.just(gson.fromJson(r, ClassDtls.class));
				});
	}

	@Override
	public Mono<Users> addUser(Users user) {
		String classCode=user.getClassCode();
		return webClient.get()
				.uri("http://localhost:8080/class/"+classCode)
				.retrieve()
				.bodyToMono(ClassDtls.class)
				.flatMap(r->{
				return userRepository.save(user);
				});
	}

}