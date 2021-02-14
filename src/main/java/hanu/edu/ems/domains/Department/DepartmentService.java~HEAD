package hanu.edu.ems.domains.Department;

import hanu.edu.ems.base.CRUDService;
import hanu.edu.ems.domains.Department.dto.CreateDepartmentDTO;
import hanu.edu.ems.domains.Department.dto.UpdateDepartmentDTO;
import hanu.edu.ems.domains.Department.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService extends CRUDService<Department, Long, CreateDepartmentDTO, UpdateDepartmentDTO> {
    boolean isCodeUnique(String code);

    Page<Department> findAllByKeyword(String keyword, Pageable pageable);

    List<Department> findByKeyword(String keyword);
}
