package hanu.edu.ems.resources.Student;

import hanu.edu.ems.resources.Student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void updateByID(Long id, Student student) {
        student.setId(id);
        studentRepository.save(student);
    }

    @Override
    public void deleteByID(Long id) {
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
    public Student getByID(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Student> findByKeyWord(String keyword, Pageable pageable) {
        return studentRepository.findByKeyword(keyword, pageable);
    }
}
