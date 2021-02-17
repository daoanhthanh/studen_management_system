package hanu.edu.ems.domains.Course.dto;

import hanu.edu.ems.domains.Course.entity.Course;
import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department.entity.Department;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UpdateCourseDTOConverter implements Converter<UpdateCourseDTO, Course> {

    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UpdateCourseDTOConverter(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Course convert(UpdateCourseDTO updateCourseDTO) {
        Department department = departmentRepository.getOne(updateCourseDTO.getDepartmentID());
        Course course = modelMapper.map(updateCourseDTO, Course.class);
        course.setDepartment(department);
        return course;
    }
}
