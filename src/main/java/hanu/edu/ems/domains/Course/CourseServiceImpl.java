package hanu.edu.ems.domains.Course;

import hanu.edu.ems.domains.Course.dto.CreateCourseDTO;
import hanu.edu.ems.domains.Course.dto.UpdateCourseDTO;
import hanu.edu.ems.domains.Course.entity.Course;
import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department.entity.Department;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Course create(CreateCourseDTO createCourseDTO) {
        Course course = modelMapper.map(createCourseDTO, Course.class);

        Department department = departmentRepository.findById(createCourseDTO.getDepartmentID()).orElseThrow(EntityNotFoundException::new);
        course.setDepartment(department);

        return courseRepository.save(course);
    }

    @Override
    public Course updateById(Long id, UpdateCourseDTO updateCourseDTO) {
        Course course = courseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(updateCourseDTO, course);
        Department department = departmentRepository.findById(updateCourseDTO.getDepartmentID()).orElseThrow(EntityNotFoundException::new);
        course.setDepartment(department);
        return courseRepository.save(course);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Course> getMany(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }
}
