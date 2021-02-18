package hanu.edu.ems.domains.Course;

import hanu.edu.ems.base.CRUDService;
import hanu.edu.ems.domains.Course.dto.CreateCourseDTO;
import hanu.edu.ems.domains.Course.dto.UpdateCourseDTO;
import hanu.edu.ems.domains.Course.entity.Course;

public interface CourseService extends CRUDService<Course, Long, CreateCourseDTO, UpdateCourseDTO> {
}
