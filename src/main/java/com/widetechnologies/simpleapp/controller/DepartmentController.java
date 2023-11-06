package com.widetechnologies.simpleapp.controller;

import com.widetechnologies.simpleapp.dto.DepartmentDTO;
import com.widetechnologies.simpleapp.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @ResponseBody
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Integer id) {
        DepartmentDTO departmentById = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(departmentById);
    }

    @ResponseBody
    @PostMapping(value = "/save/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDTO> getDepartmentById(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO departmentById = departmentService.createDepartment(departmentDTO);
        return ResponseEntity.ok(departmentById);
    }

    @ResponseBody
    @PutMapping(value = "/update/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO departmentById = departmentService.updateDepartment(departmentDTO);
        return ResponseEntity.ok(departmentById);
    }



}
