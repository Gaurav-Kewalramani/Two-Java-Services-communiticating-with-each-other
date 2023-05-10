package project2.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
//import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import project2.entity.Users;
import project2.model.ClassDtls;
import project2.services.Services;
//import project.service1.model.School;
import reactor.core.publisher.Flux;
//import project.service1.model.School;
import reactor.core.publisher.Mono;

@Component
public class Handler {
	
	@Autowired
	Services userImplement;
	
	private Mono<ServerResponse> notFound = ServerResponse.notFound().build();
	
	public Mono<ServerResponse> getClassFrmServ1(ServerRequest request) {
		Flux<ClassDtls> classessFlux=userImplement.getClassesFrmServ1();
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(classessFlux,ClassDtls.class)
				;
	}
	
	public Mono<ServerResponse> addUser(ServerRequest request) {
		Mono<Users> userMono=request.bodyToMono(Users.class);
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(fromPublisher(userMono.flatMap(userImplement::addUser),Users.class));
	}
	
	public Mono<ServerResponse> getUserById(ServerRequest request){
		String id=request.pathVariable("id");
		Mono<Users> schoolMono=userImplement.getUserById(id);
		return schoolMono.flatMap(a->ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(schoolMono,Users.class))
				.switchIfEmpty(notFound)
				;
	}
	public Mono<ServerResponse> getUsers(ServerRequest request){
		Flux<Users> schoolFlux=userImplement.getusers();
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(schoolFlux,Users.class);
	}
	public Mono<ServerResponse> updateUsser(ServerRequest request) {
		String id=request.pathVariable("id");
		Mono<Users> ussMono=request.bodyToMono(Users.class);
		return ussMono.flatMap(r->ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(userImplement.updateUser(id, r),Users.class))
				;
		
	}
	public Mono<ServerResponse> deleteUsserById(ServerRequest request) {
		String id=request.pathVariable("id");
		Mono<Void> deleteMono=userImplement.deleteUserById(id);
		return deleteMono.flatMap(r->ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(deleteMono,Users.class));
		
	}
	
}