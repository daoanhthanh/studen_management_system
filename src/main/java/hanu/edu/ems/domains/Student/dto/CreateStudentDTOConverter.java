package hanu.edu.ems.domains.Student.dto;

import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Student.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateStudentDTOConverter implements Converter<CreateStudentDTO, Student> {
    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CreateStudentDTOConverter(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Student convert(CreateStudentDTO createStudentDTO) {
        Department department = departmentRepository.getOne(createStudentDTO.getDepartmentID());
        Student student = modelMapper.map(createStudentDTO, Student.class);
        student.setDepartment(department);
        return student;
    }
}
