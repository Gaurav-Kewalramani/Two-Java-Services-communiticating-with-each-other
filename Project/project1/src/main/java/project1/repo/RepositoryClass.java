package project1.repo;



import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;



import project1.entity.School;
import reactor.core.publisher.Mono;

//import reactor.core.publisher.Flux;


@Repository
public interface RepositoryClass extends ReactiveMongoRepository<School,String> {
	Mono<School> findByuniqueCode(String uniqueCode);
	Mono<Void> deleteByuniqueCode(String uniqueCode);
	
}