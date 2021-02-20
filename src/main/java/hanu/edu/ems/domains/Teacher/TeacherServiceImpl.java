package hanu.edu.ems.domains.Teacher;

import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Teacher.dto.CreateTeacherDTO;
import hanu.edu.ems.domains.Teacher.dto.UpdateTeacherDTO;
import hanu.edu.ems.domains.Teacher.entity.Teacher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Teacher create(CreateTeacherDTO createTeacherDTO) {
        Teacher teacher = modelMapper.map(createTeacherDTO, Teacher.class);

        Department department = departmentRepository.findById(createTeacherDTO.getDepartmentID()).orElseThrow(EntityNotFoundException::new);
        teacher.setDepartment(department);

        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateById(Long id, UpdateTeacherDTO updateTeacherDTO) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        modelMapper.map(updateTeacherDTO, teacher);

        Department department = departmentRepository.findById(updateTeacherDTO.getDepartmentID()).orElseThrow(EntityNotFoundException::new);
        teacher.setDepartment(department);

        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Teacher> getMany(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }
}
