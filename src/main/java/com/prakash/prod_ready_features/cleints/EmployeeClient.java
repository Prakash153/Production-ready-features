package com.prakash.prod_ready_features.cleints;

import com.prakash.prod_ready_features.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {
    List<EmployeeDTO> getAllEmployee();
    EmployeeDTO getEmployeeById(Long employeeId );
}
