package hanu.edu.ems.domains.Course.entity;

import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import hanu.edu.ems.domains.Department.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@SpringBootTest
public class CourseTest {
    @Mock
    Department department;

    @Mock
    List<CourseRelease> courseReleases;

    @InjectMocks
    Course course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetId() {
        course.setId(1L);
    }

    @Test
    void testSetRegistrationCode() {
        course.setRegistrationCode("registrationCode");
    }

    @Test
    void testSetName() {
        course.setName("name");
    }

    @Test
    void testSetNumberOfCredits() {
        course.setNumberOfCredits(0);
    }

    @Test
    void testSetRequiredSchoolYear() {
        course.setRequiredSchoolYear(0);
    }

    @Test
    void testSetActiveReleasesCount() {
        course.setActiveReleasesCount(0);
    }

    @Test
    void testSetCreatedAt() {
        course.setCreatedAt(LocalDateTime.of(2021, Month.MARCH, 20, 13, 51, 41));
    }

    @Test
    void testSetUpdatedAt() {
        course.setUpdatedAt(LocalDateTime.of(2021, Month.MARCH, 20, 13, 51, 41));
    }

    public static Course getExampleCourse() {
        return Course.builder().build();
    }
}
