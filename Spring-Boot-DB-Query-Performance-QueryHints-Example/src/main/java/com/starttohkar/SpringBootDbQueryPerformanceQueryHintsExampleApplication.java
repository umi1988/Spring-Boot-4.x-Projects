package com.starttohkar;

import com.starttohkar.entity.Employee;
import com.starttohkar.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringBootDbQueryPerformanceQueryHintsExampleApplication {

//	@Autowired
//    private EmployeeRepository repository;
//
//	@PostConstruct
//	public void initDB() {
//		List<Employee> employees = IntStream.rangeClosed(1, 200000)
//				.mapToObj(i -> new Employee(
//						"emp" + i,
//						"dept" + i,
//						"employee" + i + "@gmail.com",
//						getGender(),
//						generateSalary(50000, 100000)
//				))
//				.collect(Collectors.toList());
//
//		repository.saveAll(employees);
//	}
//
//
//	public static double generateSalary(double min, double max) {
//		Random random = new Random();
//		double randomValue = random.nextDouble(); // 0.0 to 1.0
//		return min + (randomValue * (max - min));
//	}
//
//	private String getGender() {
//		return new Random().nextBoolean() ? "Male" : "Female";
//	}


	public static void main(String[] args) {
		SpringApplication.run(SpringBootDbQueryPerformanceQueryHintsExampleApplication.class, args);
	}

}
