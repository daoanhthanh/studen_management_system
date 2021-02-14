package hanu.edu.ems.domains.Course.dto;

import hanu.edu.ems.domains.Course.entity.Course;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateCourseDTOConverter implements Converter<CreateCourseDTO, Course> {

    private final ModelMapper modelMapper;

    @Autowired
    public CreateCourseDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Course convert(CreateCourseDTO createCourseDTO) {
        return modelMapper.map(createCourseDTO, Course.class);
    }
}
