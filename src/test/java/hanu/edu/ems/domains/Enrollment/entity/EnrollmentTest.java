package hanu.edu.ems.domains.Enrollment.entity;

import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import hanu.edu.ems.domains.CourseRelease.entity.CourseReleaseTest;
import hanu.edu.ems.domains.Student.StudentDataSample;
import hanu.edu.ems.domains.Student.entity.Student;
import hanu.edu.ems.domains.Student.entity.StudentTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;


@SpringBootTest
public class EnrollmentTest {
    @Mock
    Student student;

    @Mock
    CourseRelease courseRelease;

    @InjectMocks
    Enrollment enrollment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetId() {
        enrollment.setId(1L);
    }

    @Test
    void testSetStudent() {
        enrollment.setStudent(StudentDataSample.getExampleStudent());
    }

    @Test
    void testSetCourseRelease() {
        enrollment.setCourseRelease(CourseReleaseTest.getExampleCourseRelease());
    }

    @Test
    void testSetAttendanceMark() {
        enrollment.setAttendanceMark(1.1f);
    }

    @Test
    void testSetMidtermMark() {
        enrollment.setMidtermMark(1.1f);
    }

    @Test
    void testSetFinalMark() {
        enrollment.setFinalMark(1.1f);
    }

    @Test
    void testSetCreatedAt() {
        enrollment.setCreatedAt(LocalDateTime.of(2021, Month.MARCH, 21, 15, 37, 15));
    }

    @Test
    void testSetUpdatedAt() {
        enrollment.setUpdatedAt(LocalDateTime.of(2021, Month.MARCH, 21, 15, 37, 15));
    }

    public static Enrollment getExampleEnrollment() {
        return Enrollment.builder().build();
    }
}

