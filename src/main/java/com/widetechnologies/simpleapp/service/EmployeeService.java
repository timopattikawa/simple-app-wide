package com.widetechnologies.simpleapp.service;

import com.widetechnologies.simpleapp.domain.Department;
import com.widetechnologies.simpleapp.domain.Employee;
import com.widetechnologies.simpleapp.dto.EmployeeDTO;
import com.widetechnologies.simpleapp.exception.type.BadRequestException;
import com.widetechnologies.simpleapp.exception.type.NotFoundDataException;
import com.widetechnologies.simpleapp.repository.DepartmentRepository;
import com.widetechnologies.simpleapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Optional<Employee> employeesByEmail = employeeRepository.getEmployeesByEmail(employeeDTO.getEmail());

        if(employeesByEmail.isPresent()) {
            throw new BadRequestException("Bad Request Employee", "Employee is present");
        }

        Optional<Department> checkDepartment = departmentRepository.findById(employeeDTO.getDepartmentId());

        if(checkDepartment.isEmpty()) {
            throw new BadRequestException("Bad Request Employee", "Department id not found");
        }

        Employee employee = new Employee(
                employeeDTO.getId(),
                employeeDTO.getName(),
                employeeDTO.getEmail(),
                employeeDTO.getAge(),
                employeeDTO.getDepartmentId()
        );

        Employee savedEmployee = employeeRepository.save(employee);
        return new EmployeeDTO(
                savedEmployee.getId(),
                savedEmployee.getName(),
                savedEmployee.getEmail(),
                savedEmployee.getAge(),
                savedEmployee.getDepartmentId()
        );
    }

    public EmployeeDTO getByEmail(String email) {
        Optional<Employee> employeesByEmail = employeeRepository.getEmployeesByEmail(email);
        if(employeesByEmail.isEmpty()) {
            throw new BadRequestException("Not Found Employee", "Employee is Not Found By Email");
        }
        return new EmployeeDTO(
                employeesByEmail.get().getId(),
                employeesByEmail.get().getName(),
                employeesByEmail.get().getEmail(),
                employeesByEmail.get().getAge(),
                employeesByEmail.get().getDepartmentId()
        );
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        System.out.println("1" + employeeDTO.getId());

        Optional<Employee> employeesById = employeeRepository.findById(employeeDTO.getId());

        if(employeesById.isEmpty()) {
            throw new NotFoundDataException("Not Found Employee", "Employee is Not Found By Id");
        }

        Optional<Department> checkDepartment = departmentRepository.findById(employeeDTO.getDepartmentId());

        if(checkDepartment.isEmpty()) {
            throw new NotFoundDataException("Bad Request Employee", "Department id not found");
        }

        System.out.println(employeeDTO.getId());

        Employee employee = employeeRepository.getReferenceById(employeeDTO.getId());

        if(!employee.getName().isEmpty() && employeeDTO.getName() != null) {
            employee.setName(employeeDTO.getName());
        }
        if(!employeeDTO.getEmail().isEmpty()) {
            employee.setEmail(employeeDTO.getEmail());
        }
        if (employeeDTO.getAge() != null
                && !employeeDTO.getAge().equals(employee.getAge())) {
            employee.setAge(employeeDTO.getAge());
        }
        if(employeeDTO.getDepartmentId() != null) {
            employee.setDepartmentId(employeeDTO.getDepartmentId());
        }

        Employee savedEmployee = employeeRepository.save(employee);
        return new EmployeeDTO(
                savedEmployee.getId(),
                savedEmployee.getName(),
                savedEmployee.getEmail(),
                savedEmployee.getAge(),
                savedEmployee.getDepartmentId()
        );
    }

    public boolean deleteEmployee(Integer id) {
        Optional<Employee> employeesById = employeeRepository.findById(id);

        if(employeesById.isEmpty()) {
            throw new NotFoundDataException("Not Found Employee", "Employee is Not Found By Id");
        }
        try {
            employeeRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            throw new BadRequestException("Somthing wrong when delete", "something wrong");
        }
    }
}
