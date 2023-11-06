package com.widetechnologies.simpleapp.controller;

import com.widetechnologies.simpleapp.dto.DepartmentDTO;
import com.widetechnologies.simpleapp.dto.EmployeeDTO;
import com.widetechnologies.simpleapp.exception.type.BadRequestException;
import com.widetechnologies.simpleapp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @ResponseBody
    @PostMapping(value = "/save/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        if(employeeDTO.getName() == null || employeeDTO.getName().isEmpty()) {
            throw new BadRequestException("Employee Bad Request", "Name can't be null");
        }
        if(employeeDTO.getEmail() == null || employeeDTO.getEmail().isEmpty()) {
            throw new BadRequestException("Employee Bad Request", "Email can't be null");
        }
        if(employeeDTO.getAge() == null || employeeDTO.getAge().equals(0)) {
            throw new BadRequestException("Employee Bad Request", "Age can't be null or 0");
        }
        if(employeeDTO.getDepartmentId() == null) {
            throw new BadRequestException("Employee Bad Request", "Department id can't be null or 0");
        }
        EmployeeDTO resultDTO = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok(resultDTO);
    }

    @ResponseBody
    @PostMapping(value = "/email/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDTO> getEmployeeByEmail(@RequestBody EmployeeDTO employeeDTO) {
        if(employeeDTO.getEmail() == null || employeeDTO.getEmail().isEmpty()) {
            throw new BadRequestException("Employee Bad Request", "Email can't be null");
        }
        EmployeeDTO resultDTO = employeeService.getByEmail(employeeDTO.getEmail());
        return ResponseEntity.ok(resultDTO);
    }

    @ResponseBody
    @PutMapping(value = "/update/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        if(employeeDTO.getId() == null) {
            throw new BadRequestException("Employee Bad Request", "Id Employee can't be null");
        }
        EmployeeDTO employee = employeeService.updateEmployee(employeeDTO);
        return ResponseEntity.ok(employee);
    }

    @ResponseBody
    @DeleteMapping(value = "/delete/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updatesDepartment(@PathVariable Integer id) {
        boolean deleteEmployee = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(deleteEmployee);
    }
}
