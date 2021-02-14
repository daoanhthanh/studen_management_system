package hanu.edu.ems.domains.Enrollment.dto;

import hanu.edu.ems.domains.Enrollment.entity.Enrollment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateEnrollmentDTOConverter implements Converter<CreateEnrollmentDTO, Enrollment> {
    private final ModelMapper modelMapper;

    @Autowired
    public CreateEnrollmentDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public Enrollment convert(CreateEnrollmentDTO createEnrollmentDTO) {
        Enrollment enrollment = modelMapper.map(createEnrollmentDTO, Enrollment.class);
        return enrollment;
    }
}
