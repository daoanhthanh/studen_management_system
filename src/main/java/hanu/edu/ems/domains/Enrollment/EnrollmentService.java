package hanu.edu.ems.domains.Enrollment;

import hanu.edu.ems.base.CRUDService;
import hanu.edu.ems.domains.Enrollment.dto.CreateEnrollmentDTO;
import hanu.edu.ems.domains.Enrollment.dto.UpdateEnrollmentDTO;
import hanu.edu.ems.domains.Enrollment.entity.Enrollment;

public interface EnrollmentService extends CRUDService<Enrollment, Long, CreateEnrollmentDTO, UpdateEnrollmentDTO> {
}
