package hanu.edu.ems.domains.Teacher.dto;

import hanu.edu.ems.domains.Teacher.entity.Teacher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateTeacherDTOConverter implements Converter<CreateTeacherDTO, Teacher> {
    private final ModelMapper modelMapper;

    @Autowired
    public CreateTeacherDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Teacher convert(CreateTeacherDTO createTeacherDTO) {
        return Teacher.builder().build();
    }
}
