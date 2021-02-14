package hanu.edu.ems.domains.Course.dto;

import hanu.edu.ems.domains.Course.entity.Course;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UpdateCourseDTOConverter implements Converter<UpdateCourseDTO, Course> {

    private final ModelMapper modelMapper;

    @Autowired
    public UpdateCourseDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Course convert(UpdateCourseDTO updateCourseDTO) {
        Course course = modelMapper.map(updateCourseDTO, Course.class);
        return course;
    }
}
