package hanu.edu.ems.domains.Teacher.dto;

import hanu.edu.ems.domains.Teacher.entity.Teacher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UpdateTeacherDTOConverter implements Converter<UpdateTeacherDTO, Teacher> {

    private final ModelMapper modelMapper;

    @Autowired
    public UpdateTeacherDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Teacher convert(UpdateTeacherDTO updateTeacherDTO) {
        return Teacher.builder().build();
    }
}
