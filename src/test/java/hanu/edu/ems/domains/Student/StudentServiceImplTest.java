//package hanu.edu.ems.domains.Student;
//
//import hanu.edu.ems.domains.Authority.AuthorityRepositoryTest;
//import hanu.edu.ems.domains.Authority.entity.Authority;
//import hanu.edu.ems.domains.Authority.entity.AuthorityName;
//import hanu.edu.ems.domains.Department.DepartmentRepositoryTest;
//import hanu.edu.ems.domains.Department.entity.Department;
//import hanu.edu.ems.domains.Student.dto.CreateStudentDTO;
//import hanu.edu.ems.domains.Student.dto.UpdateStudentDTO;
//import hanu.edu.ems.domains.Student.entity.Student;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@Service
//public class StudentServiceImplTest {
//
//    private final StudentRepositoryTest studentRepositoryTest;
//
//    private final DepartmentRepositoryTest departmentRepositoryTest;
//
//    private final AuthorityRepositoryTest authorityRepositoryTest;
//
//    private final ModelMapper modelMapper;
//
//    @Autowired
//    public StudentServiceImplTest(StudentRepositoryTest studentRepositoryTest, DepartmentRepositoryTest departmentRepositoryTest, AuthorityRepositoryTest authorityRepositoryTest, ModelMapper modelMapper) {
//        this.studentRepositoryTest = studentRepositoryTest;
//        this.departmentRepositoryTest = departmentRepositoryTest;
//        this.authorityRepositoryTest = authorityRepositoryTest;
//        this.modelMapper = modelMapper;
//    }
//}
