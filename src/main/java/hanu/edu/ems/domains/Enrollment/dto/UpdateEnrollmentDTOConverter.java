package hanu.edu.ems.domains.Enrollment.dto;

import hanu.edu.ems.domains.Enrollment.entity.Enrollment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UpdateEnrollmentDTOConverter implements Converter<UpdateEnrollmentDTO, Enrollment> {

    private final ModelMapper modelMapper;

    @Autowired
    public UpdateEnrollmentDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Enrollment convert(UpdateEnrollmentDTO updateEnrollmentDTO) {
        Enrollment enrollment = modelMapper.map(updateEnrollmentDTO, Enrollment.class);
        return enrollment;
    }
}
