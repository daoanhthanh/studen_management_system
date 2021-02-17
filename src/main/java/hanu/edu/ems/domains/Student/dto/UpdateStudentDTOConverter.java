package hanu.edu.ems.domains.Student.dto;

import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Student.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UpdateStudentDTOConverter implements Converter<UpdateStudentDTO, Student> {
    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UpdateStudentDTOConverter(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Student convert(UpdateStudentDTO updateStudentDTO) {
        Department department = departmentRepository.getOne(updateStudentDTO.getDepartmentID());
        Student student = modelMapper.map(updateStudentDTO, Student.class);
        student.setDepartment(department);
        return student;
    }
}
