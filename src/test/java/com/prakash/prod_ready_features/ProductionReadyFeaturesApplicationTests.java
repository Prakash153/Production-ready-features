package com.prakash.prod_ready_features;

import com.prakash.prod_ready_features.cleints.EmployeeClient;
import com.prakash.prod_ready_features.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductionReadyFeaturesApplicationTests {
	@Autowired
	EmployeeClient employeeClient;

	@Test
	void contextLoads() {
	}
	@Test

	void getAllEmployeesTest(){
		List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployee();
		System.out.println(employeeDTOList);
	}

@Test

	void getEmployeeByIdTest(){
		 EmployeeDTO employeeDTO = employeeClient.getEmployeeById(1L);
		System.out.println(employeeDTO);
	}

}
