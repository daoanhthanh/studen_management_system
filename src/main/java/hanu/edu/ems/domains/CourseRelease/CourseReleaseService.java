package hanu.edu.ems.domains.CourseRelease;

import hanu.edu.ems.base.CRUDService;
import hanu.edu.ems.domains.CourseRelease.dto.CreateCourseReleaseDTO;
import hanu.edu.ems.domains.CourseRelease.dto.UpdateCourseReleaseDTO;
import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseReleaseService extends CRUDService<CourseRelease, Long, CreateCourseReleaseDTO, UpdateCourseReleaseDTO> {
   CourseRelease createForCourse(Long courseID, CreateCourseReleaseDTO createCourseReleaseDTO);

   List<CourseRelease> findAllByCourseID(Long courseID);

   Page<CourseRelease> findAllByCourseID(Long courseID, Pageable pageable);
}
