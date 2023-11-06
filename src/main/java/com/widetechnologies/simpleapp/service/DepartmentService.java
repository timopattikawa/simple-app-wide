package com.widetechnologies.simpleapp.service;

import com.widetechnologies.simpleapp.domain.Department;
import com.widetechnologies.simpleapp.dto.DepartmentDTO;
import com.widetechnologies.simpleapp.exception.type.NotFoundDataException;
import com.widetechnologies.simpleapp.repository.DepartmentRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentDTO getDepartmentById(Integer id) {
        Optional<Department> byId = departmentRepository.findById(id);
        if(byId.isEmpty()) {
            throw new NotFoundDataException("Not Found Department", "can't find by id");
        }

        return new DepartmentDTO(
                byId.get().getId(),
                byId.get().getName(),
                byId.get().getAddress(),
                byId.get().getCode()
        );
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department(
                departmentDTO.getId(),
                departmentDTO.getName(),
                departmentDTO.getAddress(),
                departmentDTO.getCode()
        );
        Department save = departmentRepository.save(department);
        return new DepartmentDTO(
                save.getId(),
                save.getName(),
                save.getAddress(),
                save.getCode()
        );
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) {
        Optional<Department> byId = departmentRepository.findById(departmentDTO.getId());
        if(byId.isEmpty()) {
            throw new NotFoundDataException("Not Fount Department", "Can't found department for update");
        }
        Department department = new Department(
                departmentDTO.getId(),
                departmentDTO.getName(),
                departmentDTO.getAddress(),
                departmentDTO.getCode()
        );
        Department getDept = departmentRepository.getReferenceById(department.getId());
        getDept.setId(departmentDTO.getId());
        getDept.setName(departmentDTO.getName());
        getDept.setCode(departmentDTO.getCode());
        getDept.setAddress(departmentDTO.getAddress());
        Department save = departmentRepository.save(getDept);
        return new DepartmentDTO(
                save.getId(),
                save.getName(),
                save.getAddress(),
                save.getCode()
        );
    }

}
