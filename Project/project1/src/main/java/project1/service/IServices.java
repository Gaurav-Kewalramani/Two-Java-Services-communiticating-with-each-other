package project1.service;

import project1.entity.School;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IServices {
	
	public Flux<School> getAllStudents();
	public Mono<School> addStudent(School student);
	public Mono<School> updateStudent(School student);
	public Mono<Void> deleteStudentByuniqueCode(String uniqueCode);
	public Mono<School> getStudentById(String uniqueCode);

}