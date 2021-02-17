package hanu.edu.ems.domains.Student;

import hanu.edu.ems.domains.Student.entity.Student;
import hanu.edu.ems.domains.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateById(Long id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Page<Student> getMany(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student getById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Student> findByDepartmentId(Long departmentId, Pageable pageable) {
        return studentRepository.findByDepartmentId(departmentId, pageable);
    }

    @Override
    public Page<Student> findByCourseReleaseId(Long courseReleaseId, Pageable pageable) {
        return studentRepository.findByCourseReleaseId(courseReleaseId, pageable);
    }

    @Override
    public Page<Student> findByCourseId(Long courseId, Pageable pageable) {
        return studentRepository.findByCourseId(courseId, pageable);
    }

    @Override
    public Page<Student> findByKeyWord(String keyword, Pageable pageable) {
        return studentRepository.findByKeyword(keyword, pageable);
    }
}
