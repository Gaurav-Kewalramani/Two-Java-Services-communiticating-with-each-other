package project2.repository;

//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import project2.entity.Users;

public interface RepositoryClass extends ReactiveMongoRepository<Users, String>{

}