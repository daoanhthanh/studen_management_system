//package hanu.edu.ems.domains.CourseRelease;
//
//import hanu.edu.ems.domains.Course.CourseRepositoryTest;
//import hanu.edu.ems.domains.Course.entity.Course;
//
//import hanu.edu.ems.domains.CourseRelease.dto.CreateCourseReleaseDTO;
//import hanu.edu.ems.domains.CourseRelease.dto.UpdateCourseReleaseDTO;
//import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
//import hanu.edu.ems.domains.Teacher.TeacherRepositoryTest;
//import hanu.edu.ems.domains.Teacher.entity.Teacher;
//import hanu.edu.ems.domains.Timetable.TimetableRepositoryTest;
//import hanu.edu.ems.domains.Timetable.entity.Timetable;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//
//public class CourseReleaseServiceImplTest {
//
//    private final TeacherRepositoryTest teacherRepositoryTest;
//
//    private final TimetableRepositoryTest timetableRepositoryTest;
//
//    private final CourseRepositoryTest courseRepositoryTest;
//
//    private final CourseReleaseRepositoryTest courseReleaseRepositoryTest;
//
//    private final ModelMapper modelMapper;
//
//
//    @Autowired
//    public CourseReleaseServiceImplTest(TeacherRepositoryTest teacherRepositoryTest, TimetableRepositoryTest timetableRepositoryTest, CourseReleaseRepositoryTest courseReleaseRepositoryTest, CourseRepositoryTest courseRepositoryTest, ModelMapper modelMapper) {
//        this.teacherRepositoryTest = teacherRepositoryTest;
//        this.timetableRepositoryTest = timetableRepositoryTest;
//        this.courseReleaseRepositoryTest = courseReleaseRepositoryTest;
//        this.courseRepositoryTest = courseRepositoryTest;
//        this.modelMapper = modelMapper;
//    }
//
//}
