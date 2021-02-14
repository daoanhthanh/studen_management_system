package hanu.edu.ems.domains.Teacher;

import hanu.edu.ems.domains.Authority.AuthorityRepository;
import hanu.edu.ems.domains.Authority.entity.Authority;
import hanu.edu.ems.domains.Authority.entity.AuthorityName;
import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Teacher.dto.CreateTeacherDTO;
import hanu.edu.ems.domains.Teacher.dto.UpdateTeacherDTO;
import hanu.edu.ems.domains.Teacher.entity.Teacher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    private final AuthorityRepository authorityRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, DepartmentRepository departmentRepository, ModelMapper modelMapper, AuthorityRepository authorityRepository) {
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Teacher create(CreateTeacherDTO createTeacherDTO) {
        Teacher teacher = modelMapper.map(createTeacherDTO, Teacher.class);

        Authority authority = authorityRepository.findByName(AuthorityName.TEACHER);
        teacher.setAuthorities(Collections.singletonList(authority));

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
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Teacher> findAll(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    @Override
    public Page<Teacher> findAllByDepartmentId(Long departmentID, Pageable pageable) {
        return teacherRepository.findByDepartmentId(departmentID, pageable);
    }

    @Override
    public List<Teacher> findAllByDepartmentId(Long departmentID) {
        return teacherRepository.findByDepartmentId(departmentID);
    }

    @Override
    public Page<Teacher> findByKeyWord(String keyword, Pageable pageable) {
        return teacherRepository.findByKeyword(keyword, pageable);
    }
}
