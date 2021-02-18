package hanu.edu.ems.domains.Teacher;

import hanu.edu.ems.base.CRUDService;
import hanu.edu.ems.domains.Teacher.dto.CreateTeacherDTO;
import hanu.edu.ems.domains.Teacher.dto.UpdateTeacherDTO;
import hanu.edu.ems.domains.Teacher.entity.Teacher;

public interface TeacherService extends CRUDService <Teacher, Long, CreateTeacherDTO, UpdateTeacherDTO> {
}
