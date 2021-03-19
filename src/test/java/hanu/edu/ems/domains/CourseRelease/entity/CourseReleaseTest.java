package hanu.edu.ems.domains.CourseRelease.entity;

import hanu.edu.ems.domains.Course.entity.Course;
import hanu.edu.ems.domains.Enrollment.entity.Enrollment;
import hanu.edu.ems.domains.Teacher.entity.Teacher;
import hanu.edu.ems.domains.Timetable.entity.Timetable;
import hanu.edu.ems.domains.Timetable.entity.TimetableCell;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;


@SpringBootTest
public class CourseReleaseTest {

    @Mock
    Teacher teacher;

    @Mock
    Course course;

    @Mock
    List<Enrollment> enrollments;

    @Mock
    Timetable timeTable;

    @InjectMocks
    CourseRelease courseRelease;

    @Before
    public void setUp() {
    }

    @Test
    public void testSetId() {
        courseRelease.setId(1L);
    }

    @Test
    public void testSetSeason() {
        courseRelease.setSeason(Season.SPRING);
    }

    @Test
    public void testSetReleaseYear() {
        courseRelease.setReleaseYear(0);
    }

    @Test
    public void testSetIsActive() {
        courseRelease.setIsActive(Boolean.TRUE);
    }

    @Test
    public void testSetTimeTable() {
        courseRelease.setTimeTable(new Timetable(1L, Collections.singletonList(new TimetableCell(0, 0, 0, null)), LocalDateTime.of(2021, Month.MARCH, 20, 14, 20, 56), LocalDateTime.of(2021, Month.MARCH, 20, 14, 20, 56)));
    }

    @Test
    public void testSetStartDate() {
        courseRelease.setStartDate(LocalDate.of(2021, Month.MARCH, 20));
    }

    @Test
    public void testSetEndDate() {
        courseRelease.setEndDate(LocalDate.of(2021, Month.MARCH, 20));
    }

    @Test
    public void testSetCreatedAt() {
        courseRelease.setCreatedAt(LocalDateTime.of(2021, Month.MARCH, 20, 14, 20, 56));
    }

    @Test
    public void testSetUpdatedAt() {
        courseRelease.setUpdatedAt(LocalDateTime.of(2021, Month.MARCH, 20, 14, 20, 56));
    }

    public static CourseRelease getExampleCourseRelease() {
        return CourseRelease.builder().build();
    }
}