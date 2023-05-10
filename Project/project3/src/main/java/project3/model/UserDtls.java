package project3.model;

import org.springframework.data.annotation.Id;

import project1.entity.School;
import project1.entity.Class;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDtls{
	@Id
	private String userId;
	private String userName;
	private String userrole;
	private String classCode;
	private Class class1;
	private School school;
	
//	public Class getClass1() {
//		return class1;
//	}
//	public void setClass1(Class class1) {
//		this.class1 = class1;
//	}
//	public School getSchool() {
//		return school;
//	}
//	public void setSchool(School school) {
//		this.school = school;
//	}
//	public String getUserId() {
//		return userId;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	public String getUserrole() {
//		return userrole;
//	}
//	public void setUserrole(String userrole) {
//		this.userrole = userrole;
//	}
//	public String getClassCode() {
//		return classCode;
//	}
//	public void setClassCode(String classCode) {
//		this.classCode = classCode;
//	}
//	
//	
	
//	private Class class1;
//	private School school;
}
