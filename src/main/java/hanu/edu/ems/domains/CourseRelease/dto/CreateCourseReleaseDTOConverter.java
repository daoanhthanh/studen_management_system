package hanu.edu.ems.domains.CourseRelease.dto;

import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateCourseReleaseDTOConverter implements Converter<CreateCourseReleaseDTO, CourseRelease> {

    private final ModelMapper modelMapper;

    @Autowired
    public CreateCourseReleaseDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseRelease convert(CreateCourseReleaseDTO createCourseReleaseDTO) {
        return CourseRelease.builder().build();
    }
}
