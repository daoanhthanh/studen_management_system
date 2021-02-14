package hanu.edu.ems.domains.Department.dto;

import hanu.edu.ems.domains.Department.entity.Department;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateDepartmentDTOConverter implements Converter<CreateDepartmentDTO, Department> {
    private final ModelMapper modelMapper;

    @Autowired
    public CreateDepartmentDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Department convert(CreateDepartmentDTO createCourseDTO) {
        Department department = modelMapper.map(createCourseDTO, Department.class);
        return department;
    }
}
