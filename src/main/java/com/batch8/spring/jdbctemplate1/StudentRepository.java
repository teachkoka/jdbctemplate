package com.batch8.spring.jdbctemplate1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class StudentRepository {

	private DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/postgres");
		dataSource.setUsername("postgres");
		dataSource.setPassword("abcd12345");
		return dataSource;
	}
	private JdbcTemplate jdbcTemplate(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}
	public void persist(Student student){
		JdbcTemplate template = jdbcTemplate();
		template.update("insert into student(name, course) values(?, ?)", student.getName(), student.getCourse());
		System.out.println("Inserted successfully");
	}
	
	public String findCourse(String name){
		JdbcTemplate template = jdbcTemplate();
		String course;
		try {
			course = template.queryForObject(
					"select course from student where name=?",
					new Object[] { name }, String.class);
		} catch (Exception e) {
			return null;
		}
		if(course!=null){
			return course;
		}
		return null;
	}
	
	public Student findOne(String name){
		JdbcTemplate template = jdbcTemplate();
		Student std;
		try {
			std = template.queryForObject("select * from student where name=?", new Object[] { name },
					new RowMapper<Student>(){

						public Student mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Student student = new Student();
							student.setCourse(rs.getString("course"));
							student.setName(rs.getString("name"));
							return student;
						}
				
			});
		} catch (Exception e) {
			return null;
		}
		if(std!=null){
			return std;
		}
		return null;
	}
	
	
	public List<Student> findAll(){
		JdbcTemplate template = jdbcTemplate();
		List<Student> stdList;
		try {
			stdList = template.query("select * from student",
					new RowMapper<Student>(){
						public Student mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Student student = new Student();
							student.setCourse(rs.getString("course"));
							student.setName(rs.getString("name"));
							return student;
						}
				
			});
		} catch (Exception e) {
			return null;
		}
		if(stdList!=null){
			return stdList;
		}
		return null;
	}
	
	
	
		
}
