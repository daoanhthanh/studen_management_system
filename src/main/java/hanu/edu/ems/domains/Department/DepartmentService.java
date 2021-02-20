package hanu.edu.ems.domains.Department;

import hanu.edu.ems.base.CRUDService;
import hanu.edu.ems.domains.Department.dto.CreateDepartmentDTO;
import hanu.edu.ems.domains.Department.dto.UpdateDepartmentDTO;
import hanu.edu.ems.domains.Department.entity.Department;

public interface DepartmentService extends CRUDService<Department, Long, CreateDepartmentDTO, UpdateDepartmentDTO> {
}
