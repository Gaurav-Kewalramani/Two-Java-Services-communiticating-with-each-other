# Two-Java-Services-communiticating-with-each-other
Create 2 java services and perform communication between each other. The functionality should be as follows:
1) Service 1 manages details of the school and it’s db stores the school name, school's unique code. Class details like class code, class name, student strength, instructor’s code
2) Service 2 manages user details and stores user details like name, userId, role [STUDENT, INSTRUCTOR], class code(Check class existence using rest call before entering record in db)
3) The aim is to provide APIs to fetch details of all related entities for any fetched details like if i search based on student id, it should return me his school name, teacher name, class name. Below APIs need to be created: /schools :- to fetch details for all schools includes class, student as well as teacher details, add query param on school name
1) /school/{schoolId} : fetch details for a particular school /school/{schoolId}/class/{classId}: fetch details for a class in a school
2) /users :- fetch all user details including school, class and teacher details
3) /user/{userId} : fetch details of a specific user
4) /user/{schoolId}/teachers :- to fetch teachers’ details for a school including school details
5) /user/{schoolId}/students :- to fetch students’ details for a school including school details

	Follow the below points -
1) Use MongoDB
2) Use Gradle as a build tool
