package hanu.edu.ems.domains.Student;

import hanu.edu.ems.domains.Student.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest(properties = {"spring.cloud.config.enabled=false"})
class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void findAllByDepartmentId() {
        // Given
        Student student = Student.builder()
            .firstName("Minh")
            .lastName("Tang Ba")
            .build();
        entityManager.persist(student);
        entityManager.flush();

        // When

        // Then
    }

    @Test
    void testFindAllByDepartmentId() {
    }

    @Test
    void findByCourseReleaseId() {
    }

    @Test
    void findByCourseId() {
    }

    @Test
    void findAllByKeyword() {
    }
}