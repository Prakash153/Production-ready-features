package com.prakash.prod_ready_features.clients.impl;

import com.prakash.prod_ready_features.advice.ApiResponse;
import com.prakash.prod_ready_features.clients.EmployeeClient;
import com.prakash.prod_ready_features.dto.EmployeeDTO;
import com.prakash.prod_ready_features.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class EmployeeClientImpl implements EmployeeClient {
    @Autowired
    private RestClient restClient;

    // Logging in springboot
    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployee() {

//        log.error("error log");
//        log.warn("warn log");
//        log.debug("debug log");
//        log.trace("trace log");
//        log.info("info log");
        log.trace("trying to retrieve all employees from getAllEmployees");
        try {
            ApiResponse<List<EmployeeDTO>> employeeDTOList =
                    restClient.get()
                            .uri("employees")
                            .retrieve()
                            .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                              log.error(new String( res.getBody().readAllBytes()));
                                throw new ResourceNotFoundException("could not create the employee");
                            })
                            .body(new ParameterizedTypeReference<>() {
                            });
            log.debug("successfully retrieved the employees in getAllEmployees");
            log.trace("Retrieved Employees details of geTaLLEmployees : {}" , employeeDTOList.getData());
            return employeeDTOList.getData();
        } catch (Exception e) {
            log.error("Exception occured in getAllEmployees", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        log.trace("trying to retrieve Employee By id  from getEmployeeById");
        try {
            ApiResponse<EmployeeDTO> employeeDTO = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String( res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not find the employee by id " + employeeId );
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("successfully retrieved the employee in getEmployeeById");
            log.trace("Retrieved Employee details of  getEmployeeById: {}" , employeeDTO.getData());
            return employeeDTO.getData();
        }
        catch (Exception e)
        {
            log.error("Exception occured in getEmployeeById", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createEmployeeDTO(EmployeeDTO employee) {
        log.trace("trying to create Employee in createEmployee :  {}" , employee);
        try{
           ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTO = restClient.post()
                    .uri("employees")
                    .body(employee)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.debug("4xxClientError occured during createEmployee");
                        log.error(new String( res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            log.trace("created Employee  in createEmployee: {}" , employeeDTO.getBody().getData());
            return  employeeDTO.getBody().getData();
        }
        catch (Exception e) {
            log.error("Exception occured in createEmployee", e);
            throw new RuntimeException(e);
        }
    }
}
