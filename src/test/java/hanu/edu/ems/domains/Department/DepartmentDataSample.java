package hanu.edu.ems.domains.Department;

import hanu.edu.ems.domains.Department.dto.CreateDepartmentDTO;
import hanu.edu.ems.domains.Department.entity.Department;

import java.time.LocalDateTime;

public class DepartmentDataSample {

    private static final Department fit = Department.builder()
        .id(1L)
        .name("Faculty Of Information and Technology")
        .code("FIT")
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();

    private static final CreateDepartmentDTO fitDTO = CreateDepartmentDTO.builder()
        .name("Faculty Of Information and Technology")
        .code("FIT")
        .build();

    public static Department getOneValidDepartment() {
        return fit.toBuilder().build();
    }

    public static CreateDepartmentDTO getOneValidCreateDepartmentDTO() {
        return fitDTO.toBuilder().build();
    }
}
