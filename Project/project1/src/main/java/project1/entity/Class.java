package project1.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection="classesss")
	public class Class {
	@Id
		String classCode;
		String Classname;
		int studentStrength;
		String uniqueCode;
		String userId;
		
		
		

}
