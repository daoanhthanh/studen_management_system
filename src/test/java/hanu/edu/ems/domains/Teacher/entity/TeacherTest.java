package hanu.edu.ems.domains.Teacher.entity;

import hanu.edu.ems.domains.Authority.entity.Authority;
import hanu.edu.ems.domains.Authority.entity.AuthorityName;
import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import hanu.edu.ems.domains.CourseRelease.entity.CourseReleaseTest;
import hanu.edu.ems.domains.Department.DepartmentDataSample;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Department.entity.DepartmentTest;
import hanu.edu.ems.domains.User.entity.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class TeacherTest {
    @Mock
    Department department;

    @Mock
    List<CourseRelease> courseReleases;

    @Mock
    List<Authority> authorities;

    @InjectMocks
    Teacher teacher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetPerformanceRating() {
        teacher.setPerformanceRating(1.1f);
    }

    @Test
    void testSetDepartment() {
        teacher.setDepartment(DepartmentDataSample.getOneValidDepartment());
    }

    @Test
    void testSetCourseReleases() {
        teacher.setCourseReleases(Collections.singletonList(CourseReleaseTest.getExampleCourseRelease()));
    }

    @Test
    void testIsAccountNonExpired() {
        boolean result = teacher.isAccountNonExpired();
        Assertions.assertTrue(result);
    }

    @Test
    void testIsAccountNonLocked() {
        boolean result = teacher.isAccountNonLocked();
        Assertions.assertTrue(result);
    }

    @Test
    void testIsCredentialsNonExpired() {
        boolean result = teacher.isCredentialsNonExpired();
        Assertions.assertTrue(result);
    }

    @Test
    void testIsEnabled() {
        boolean result = teacher.isEnabled();
        Assertions.assertTrue(result);
    }

    @Test
    void testSetId() {
        teacher.setId(1L);
    }

    @Test
    void testSetUsername() {
        teacher.setUsername("username");
    }

    @Test
    void testSetPassword() {
        teacher.setPassword("password");
    }

    @Test
    void testSetFirstName() {
        teacher.setFirstName("firstName");
    }

    @Test
    void testSetLastName() {
        teacher.setLastName("lastName");
    }

    @Test
    void testSetGender() {
        teacher.setGender(Gender.MALE);
    }

    @Test
    void testSetEmail() {
        teacher.setEmail("email");
    }

    @Test
    void testSetPhoneNumber() {
        teacher.setPhoneNumber("phoneNumber");
    }

    @Test
    void testSetDob() {
        teacher.setDob(LocalDate.of(2021, Month.MARCH, 21));
    }

    @Test
    void testSetAuthorities() {
        teacher.setAuthorities(Collections.singletonList(new Authority(1L, AuthorityName.STUDENT)));
    }

    @Test
    void testSetCreatedAt() {
        teacher.setCreatedAt(LocalDateTime.of(2021, Month.MARCH, 21, 15, 38, 53));
    }

    @Test
    void testSetUpdatedAt() {
        teacher.setUpdatedAt(LocalDateTime.of(2021, Month.MARCH, 21, 15, 38, 53));
    }

    public static Teacher getExampleTeacher() {
        return Teacher.builder().build();
    }
}

