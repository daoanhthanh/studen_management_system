package hanu.edu.ems.domains.Teacher;

import hanu.edu.ems.base.CRUDService;
import hanu.edu.ems.domains.Teacher.dto.CreateTeacherDTO;
import hanu.edu.ems.domains.Teacher.dto.UpdateTeacherDTO;
import hanu.edu.ems.domains.Teacher.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService extends CRUDService <Teacher, Long, CreateTeacherDTO, UpdateTeacherDTO> {
    Page<Teacher> findAllByDepartmentId(Long departmentID, Pageable pageable);

    List<Teacher> findAllByDepartmentId(Long departmentID);

    Page<Teacher> findByKeyWord(String keyword, Pageable pageable);
}
