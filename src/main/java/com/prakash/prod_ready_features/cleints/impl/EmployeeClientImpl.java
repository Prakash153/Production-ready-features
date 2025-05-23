package com.prakash.prod_ready_features.cleints.impl;

import com.prakash.prod_ready_features.advice.ApiResponse;
import com.prakash.prod_ready_features.cleints.EmployeeClient;
import com.prakash.prod_ready_features.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class EmployeeClientImpl implements EmployeeClient {
    @Autowired
    private RestClient restClient;


    @Override
    public List<EmployeeDTO> getAllEmployee() {
        try {
            ApiResponse<List<EmployeeDTO>> employeeDTOList =
                    restClient.get()
                            .uri("employees")
                            .retrieve()
                            .body(new ParameterizedTypeReference<>() {
                            });
            return employeeDTOList.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try {
            ApiResponse<EmployeeDTO> employeeDTO = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTO.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
