package hanu.edu.ems.domains.Student;

import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Enrollment.entity.Enrollment;
import hanu.edu.ems.domains.Student.dto.CreateStudentDTO;
import hanu.edu.ems.domains.Student.dto.UpdateStudentDTO;
import hanu.edu.ems.domains.Student.entity.Student;
import hanu.edu.ems.domains.User.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository, DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Student create(CreateStudentDTO createStudentDTO) {

        Student student = modelMapper.map(createStudentDTO, Student.class);

        Department department = departmentRepository.findById(createStudentDTO.getDepartmentID()).orElseThrow(EntityNotFoundException::new);
        student.setDepartment(department);

        return studentRepository.save(student);
    }

    @Override
    public Student updateById(Long id, UpdateStudentDTO updateStudentDTO) {
        Student student = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(updateStudentDTO, modelMapper);

        Department department = departmentRepository.findById(updateStudentDTO.getDepartmentID()).orElseThrow(EntityNotFoundException::new);
        student.setDepartment(department);

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
