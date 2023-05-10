package project1.service;

import project1.entity.School;
//import assignment3.entity.Product;
import project1.repo.RepositoryClass;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


   
    
//    private RepositoryClass ProductRepository;
//
//
//    public Flux<Dto> getProducts(){
//        return ProductRepository.findAll().map(Utils::entityToDto);
//    }
//   
//    public Mono<Dto> saveProduct(Mono<Dto> productDtoMono){
//        
//      return  productDtoMono.map(Utils::dtoToEntity)
//                .flatMap(ProductRepository::insert)
//                .map(Utils::entityToDto);
//    }
//
//    public Mono<Dto> updateProduct(Mono<Dto> productDtoMono,String id){
//       return ProductRepository.findById(id)
//                .flatMap(a->productDtoMono.map(Utils::dtoToEntity)
//                .doOnNext(q->q.setId(id)))
//                .flatMap(ProductRepository::save)
//                .map(Utils::entityToDto);
//    }
//
//    public Mono<Void> deleteProduct(String id){
//        return ProductRepository.deleteById(id);
//    }
//}
    

@Service
    public class ServiceClass implements IServices {
    	
	@Autowired
	private RepositoryClass studentRepository;

	@Override
	public Flux<School> getAllStudents() {
		return studentRepository.findAll();
	}



	@Override
	public Mono<School> addStudent(School student) {
		return studentRepository.save(student);
	}

	@Override
	public Mono<School> updateStudent(School student) {
		return studentRepository.save(student);
	}

	@Override
	public Mono<Void> deleteStudentByuniqueCode(String uniqueCode) {
		return studentRepository.deleteByuniqueCode(uniqueCode);
	}
	@Override
	public Mono<School> getStudentById(String uniqueCode){ 
		return studentRepository.findById(uniqueCode);
	} 
	
}
	






	

	
