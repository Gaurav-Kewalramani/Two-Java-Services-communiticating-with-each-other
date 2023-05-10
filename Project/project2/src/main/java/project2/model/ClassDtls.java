package project2.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ClassDtls {
	@Id
	 String classCode;
	
	String className;
	 int strength;
	 String userId;
	 String uniCode;

	
}