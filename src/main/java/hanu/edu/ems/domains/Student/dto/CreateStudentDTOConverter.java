package hanu.edu.ems.domains.Student.dto;

import hanu.edu.ems.domains.Student.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateStudentDTOConverter implements Converter<CreateStudentDTO, Student> {
    private final ModelMapper modelMapper;

    @Autowired
    public CreateStudentDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Student convert(CreateStudentDTO createStudentDTO) {
        Student student = modelMapper.map(createStudentDTO, Student.class);
        return student;
    }
}
