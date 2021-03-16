//package hanu.edu.ems.domains.Teacher;
//
//import hanu.edu.ems.domains.Authority.AuthorityRepositoryTest;
//import hanu.edu.ems.domains.Authority.entity.Authority;
//import hanu.edu.ems.domains.Authority.entity.AuthorityName;
//import hanu.edu.ems.domains.Department.DepartmentRepositoryTest;
//import hanu.edu.ems.domains.Department.entity.Department;
//import hanu.edu.ems.domains.Teacher.dto.CreateTeacherDTO;
//import hanu.edu.ems.domains.Teacher.dto.UpdateTeacherDTO;
//import hanu.edu.ems.domains.Teacher.entity.Teacher;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.Collections;
//import java.util.List;
//
//public class TeacherServiceImplTest {
//
//    private final TeacherRepositoryTest teacherRepositoryTest;
//
//    private final DepartmentRepositoryTest departmentRepositoryTest;
//
//    private final ModelMapper modelMapper;
//
//    private final AuthorityRepositoryTest authorityRepositoryTest;
//
//    @Autowired
//    public TeacherServiceImplTest(TeacherRepositoryTest teacherRepositoryTest, DepartmentRepositoryTest departmentRepositoryTest, ModelMapper modelMapper, AuthorityRepositoryTest authorityRepositoryTest) {
//        this.teacherRepositoryTest = teacherRepositoryTest;
//        this.departmentRepositoryTest = departmentRepositoryTest;
//        this.modelMapper = modelMapper;
//        this.authorityRepositoryTest = authorityRepositoryTest;
//    }
//}
