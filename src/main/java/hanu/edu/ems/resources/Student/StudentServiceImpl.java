package hanu.edu.ems.resources.Student;

import hanu.edu.ems.resources.Student.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "StudentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void updateByID(Long id, Student student) {

    }

    @Override
    public void deleteByID(Long id) {

    }

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public Student getByID(Long id) {
        return null;
    }
}
