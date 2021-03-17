//package hanu.edu.ems.domains.Enrollment;
//
//import hanu.edu.ems.domains.CourseRelease.CourseReleaseRepositoryTest;
//import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
//import hanu.edu.ems.domains.Enrollment.dto.CreateEnrollmentDTO;
//import hanu.edu.ems.domains.Enrollment.dto.UpdateEnrollmentDTO;
//import hanu.edu.ems.domains.Enrollment.entity.Enrollment;
//import hanu.edu.ems.domains.Student.StudentRepositoryTest;
//import hanu.edu.ems.domains.Student.entity.Student;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//
//public class EnrollmentServiceImplTest {
//
//    private final EnrollmentRepositoryTest enrollmentRepositoryTest;
//
//    private final CourseReleaseRepositoryTest courseReleaseRepositoryTest;
//
//    private final StudentRepositoryTest studentRepositoryTest;
//
//    private final ModelMapper modelMapper;
//
//    @Autowired
//    public EnrollmentServiceImplTest(EnrollmentRepositoryTest enrollmentRepositoryTest, CourseReleaseRepositoryTest courseReleaseRepositoryTest, StudentRepositoryTest studentRepositoryTest, ModelMapper modelMapper) {
//        this.enrollmentRepositoryTest = enrollmentRepositoryTest;
//        this.courseReleaseRepositoryTest = courseReleaseRepositoryTest;
//        this.studentRepositoryTest = studentRepositoryTest;
//        this.modelMapper = modelMapper;
//    }
//}
