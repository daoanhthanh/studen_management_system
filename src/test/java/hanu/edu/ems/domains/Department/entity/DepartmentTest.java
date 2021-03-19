package hanu.edu.ems.domains.Department.entity;

import hanu.edu.ems.domains.Course.entity.Course;
import hanu.edu.ems.domains.Student.entity.Student;
import hanu.edu.ems.domains.Teacher.entity.Teacher;
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
public class DepartmentTest {
    @Mock
    List<Student> students;

    @Mock
    List<Course> courses;

    @Mock
    List<Teacher> teachers;

    @InjectMocks
    Department department;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetId() {
        department.setId(1L);
    }

    @Test
    void testSetName() {
        department.setName("name");
    }

    @Test
    void testSetCode() {
        department.setCode("code");
    }

    @Test
    void testSetCreatedAt() {
        department.setCreatedAt(LocalDateTime.of(2021, Month.MARCH, 21, 15, 35, 36));
    }

    @Test
    void testSetUpdatedAt() {
        department.setUpdatedAt(LocalDateTime.of(2021, Month.MARCH, 21, 15, 35, 36));
    }

    public static Department getExampleDepartment() {
        return Department.builder()
            .name("Faculty Of Information Technology")
            .code("FIT")
            .build();
    }
}
