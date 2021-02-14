package hanu.edu.ems.domains.Student.dto;

import hanu.edu.ems.domains.Student.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UpdateStudentDTOConverter implements Converter<UpdateStudentDTO, Student> {
    private final ModelMapper modelMapper;

    @Autowired
    public UpdateStudentDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Student convert(UpdateStudentDTO updateStudentDTO) {
        Student student = modelMapper.map(updateStudentDTO, Student.class);
        return student;
    }
}
