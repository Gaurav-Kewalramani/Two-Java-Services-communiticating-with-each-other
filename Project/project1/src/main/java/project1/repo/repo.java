package project1.repo;



import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;



import project1.entity.Class;
import reactor.core.publisher.Mono;
//import reactor.core.publisher.Flux;


@Repository
public interface repo extends ReactiveMongoRepository<Class,String> {
	Mono<Class> findByclassCode(int ClassID);
	Mono<Void> deleteByclassCode(String classCode);
}