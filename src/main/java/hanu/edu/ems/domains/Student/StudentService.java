package hanu.edu.ems.domains.Student;

import hanu.edu.ems.base.CRUDService;
import hanu.edu.ems.domains.Student.dto.CreateStudentDTO;
import hanu.edu.ems.domains.Student.dto.UpdateStudentDTO;
import hanu.edu.ems.domains.Student.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface StudentService extends CRUDService<Student, Long, CreateStudentDTO, UpdateStudentDTO> {

    Page<Student> findByDepartmentId(Long departmentId, Pageable pageable);

    List<Student> findByDepartmentId(Long departmentId);

    Page<Student> findByCourseReleaseId(Long courseReleaseId, Pageable pageable);

    Page<Student> findByCourseId(Long courseId, Pageable pageable);

    Page<Student> findByKeyWord(String keyword, Pageable pageRequest);

    Page<Student> findAll(Pageable pageRequest);

    List<Student> createManyStudents(List<CreateStudentDTO> createStudentDTOList);
}
