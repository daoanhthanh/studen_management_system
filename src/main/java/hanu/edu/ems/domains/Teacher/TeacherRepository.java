package hanu.edu.ems.domains.Teacher;

import hanu.edu.ems.domains.Teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
