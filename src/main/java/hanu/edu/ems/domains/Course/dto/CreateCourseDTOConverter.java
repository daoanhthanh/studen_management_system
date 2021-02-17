package hanu.edu.ems.domains.Course.dto;

import hanu.edu.ems.domains.Course.entity.Course;
import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department.entity.Department;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateCourseDTOConverter implements Converter<CreateCourseDTO, Course> {

    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CreateCourseDTOConverter(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Course convert(CreateCourseDTO createCourseDTO) {
        Department department = departmentRepository.getOne(createCourseDTO.getDepartmentID());
        Course course = modelMapper.map(createCourseDTO, Course.class);
        course.setDepartment(department);
        return course;
    }
}
