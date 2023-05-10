package project1.service;

import project1.entity.Class;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IServicess {
	
	public Flux<Class> getAllClasses();
	public Mono<Class> addClass(Class Class);
	public Mono<Class> updateClass(Class Class);
	public Mono<Void> deleteByclassCode(String classCode);
	public Mono<Class> getStudentById(String classCode);
}