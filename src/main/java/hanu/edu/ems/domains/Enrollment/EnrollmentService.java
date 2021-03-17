package hanu.edu.ems.domains.Enrollment;

import hanu.edu.ems.base.CRUDService;
import hanu.edu.ems.domains.Enrollment.dto.CreateEnrollmentDTO;
import hanu.edu.ems.domains.Enrollment.dto.UpdateEnrollmentDTO;
import hanu.edu.ems.domains.Enrollment.entity.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EnrollmentService extends CRUDService<Enrollment, Long, CreateEnrollmentDTO, UpdateEnrollmentDTO> {
    Page<Enrollment> findAllByStudentId(Long studentID, Pageable pageable);

    List<Enrollment> findAllByStudentId(Long studentID);

    List<Enrollment> findAllByCourseReleaseId(Long studentID);

    Page<Enrollment> findAllByCourseReleaseId(Long studentID, Pageable pageable);
}
