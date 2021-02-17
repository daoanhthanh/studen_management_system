package hanu.edu.ems.domains.Teacher.dto;

import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Teacher.entity.Teacher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UpdateTeacherDTOConverter implements Converter<UpdateTeacherDTO, Teacher> {

    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UpdateTeacherDTOConverter(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Teacher convert(UpdateTeacherDTO updateTeacherDTO) {
        Department department = departmentRepository.getOne(updateTeacherDTO.getDepartmentID());
        Teacher teacher = modelMapper.map(updateTeacherDTO, Teacher.class);
        teacher.setDepartment(department);
        return teacher;
    }
}
