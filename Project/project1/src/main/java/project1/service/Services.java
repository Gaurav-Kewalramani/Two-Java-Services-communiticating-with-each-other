package project1.service;

import project1.entity.Class;
//import assignment3.entity.Product;

import project1.repo.repo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
    public class Services implements IServicess {
    	
	@Autowired
	private repo studentRepo;

	@Override
	public Flux<Class> getAllClasses() {
		return studentRepo.findAll();
	}

	@Override
	public Mono<Class> addClass(Class Class) {
		return studentRepo.save(Class);
	}

	@Override
	public Mono<Class> updateClass(Class Class) {
		return studentRepo.save(Class);
	}

	@Override
	public Mono<Void> deleteByclassCode(String classCode) {
		return studentRepo.deleteByclassCode(classCode);
	}
	@Override
	public Mono<Class> getStudentById(String classCode){ 
		return studentRepo.findById(classCode);
	}

	
}