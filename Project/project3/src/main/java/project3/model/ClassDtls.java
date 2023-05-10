package project3.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import project2.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Flux;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClassDtls {
	@Id
	 String classCode;
	 String className;
	 int studentStrength;
	 String userId;
	 String uniqueCode;
	
	 List<Users> usersList;

	
}
