package hanu.edu.ems.domains.Student.entity;

import hanu.edu.ems.domains.Authority.entity.Authority;
import hanu.edu.ems.domains.Authority.entity.AuthorityName;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Enrollment.entity.Enrollment;
import hanu.edu.ems.domains.User.entity.Gender;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class StudentTest {
    @Mock
    Department department;

    @Mock
    List<Enrollment> enrollments;

    @Mock
    List<Authority> authorities;

    @InjectMocks
    Student student;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSetSinceYear() {
        student.setSinceYear(0);
    }

    @Test
    void testSetFatherName() {
        student.setFatherName("fatherName");
    }

    @Test
    void testSetMotherName() {
        student.setMotherName("motherName");
    }

    @Test
    void testSetFullAddress() {
        student.setFullAddress("fullAddress");
    }

    @Test
    void testIsAccountNonExpired() {
        boolean result = student.isAccountNonExpired();
        Assertions.assertTrue(result);
    }

    @Test
    void testIsAccountNonLocked() {
        boolean result = student.isAccountNonLocked();
        Assertions.assertTrue(result);
    }

    @Test
    void testIsCredentialsNonExpired() {
        boolean result = student.isCredentialsNonExpired();
        Assertions.assertTrue(result);
    }

    @Test
    void testIsEnabled() {
        boolean result = student.isEnabled();
        Assertions.assertTrue(result);
    }

    @Test
    void testSetId() {
        student.setId(1L);
    }

    @Test
    void testSetUsername() {
        student.setUsername("username");
    }

    @Test
    void testSetPassword() {
        student.setPassword("password");
    }

    @Test
    void testSetFirstName() {
        student.setFirstName("firstName");
    }

    @Test
    void testSetLastName() {
        student.setLastName("lastName");
    }

    @Test
    void testSetGender() {
        student.setGender(Gender.MALE);
    }

    @Test
    void testSetEmail() {
        student.setEmail("email");
    }

    @Test
    void testSetPhoneNumber() {
        student.setPhoneNumber("phoneNumber");
    }

    @Test
    void testSetDob() {
        student.setDob(LocalDate.of(2021, Month.MARCH, 21));
    }

    @Test
    void testSetAuthorities() {
        student.setAuthorities(Collections.singletonList(new Authority(1L, AuthorityName.STUDENT)));
    }

    @Test
    void testSetCreatedAt() {
        student.setCreatedAt(LocalDateTime.of(2021, Month.MARCH, 21, 15, 29, 27));
    }

    @Test
    void testSetUpdatedAt() {
        student.setUpdatedAt(LocalDateTime.of(2021, Month.MARCH, 21, 15, 29, 27));
    }
}

