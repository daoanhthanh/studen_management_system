package hanu.edu.ems.domains.Course;

import hanu.edu.ems.base.CRUDService;
import hanu.edu.ems.domains.Course.dto.CreateCourseDTO;
import hanu.edu.ems.domains.Course.dto.UpdateCourseDTO;
import hanu.edu.ems.domains.Course.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService extends CRUDService<Course, Long, CreateCourseDTO, UpdateCourseDTO> {

    List<Course> findAllByDepartmentID(Long departmentID);

    Page<Course> findAllByDepartmentID(Long departmentID, Pageable pageable);

    Page<Course> findAllByNameLike(String partialName, Pageable pageable);

    List<Course> findAllByNameLike(String partialName);

    Boolean isRegistrationCodeUnique(String registrationCode);
}
