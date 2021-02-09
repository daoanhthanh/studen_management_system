package hanu.edu.ems.resources.Teacher;

import hanu.edu.ems.resources.Teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
