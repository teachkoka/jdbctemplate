package com.batch8.spring.jdbctemplate1;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        StudentRepository repository = new StudentRepository();
        
       Student std = new Student();
        std.setName("koka");
        std.setCourse("java");
        
        Student std1 = new Student();
        std1.setName("siva");
        std1.setCourse("spring");
       
        repository.persist(std);
    	repository.persist(std1);
        
        System.out.println(repository.findCourse("siva"));
        System.out.println(repository.findOne("siva"));
        System.out.println(repository.findAll());
    	
    }
}
