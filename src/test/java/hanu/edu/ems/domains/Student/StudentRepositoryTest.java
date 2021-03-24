package hanu.edu.ems.domains.Student;

import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Department.entity.DepartmentTest;
import hanu.edu.ems.domains.Student.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestEntityManager
@Slf4j
class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department department;

    @BeforeEach
    void setUp() {
        Department department = DepartmentTest.getExampleDepartment();
        this.department = departmentRepository.saveAndFlush(department);
    }

    @AfterEach
    void tearDown() {
        departmentRepository.deleteAll();
    }

    @Test
    @Transactional
    void testFindAllByDepartmentId() {
        // Given
        List<Student> sampleStudents = StudentDataSample.get3();

        Student minh = sampleStudents.get(0);
        minh.setDepartment(this.department);

        Student duong = sampleStudents.get(1);
        duong.setDepartment(this.department);

        Student thanh = sampleStudents.get(2);
        thanh.setDepartment(this.department);

        entityManager.persistAndFlush(minh);
        entityManager.persistAndFlush(duong);
        entityManager.persistAndFlush(thanh);

        // When
        List<Student> students = studentRepository.findAllByDepartmentId(this.department.getId());
        // Then
        assertEquals(students.size(), 3);

        studentRepository.deleteAll();
        List<Student> studentsList = studentRepository.findAll();
        log.info("SIZE");
        log.info(String.valueOf(studentsList.size()));
    }

    @Test
    @Transactional
    void findByCourseReleaseId() {
        List<Student> sampleStudents = StudentDataSample.get3();

        Student minh = sampleStudents.get(0);
        minh.setDepartment(this.department);

        Student duong = sampleStudents.get(1);
        duong.setDepartment(this.department);

        Student thanh = sampleStudents.get(2);
        thanh.setDepartment(this.department);

        entityManager.persistAndFlush(minh);
        entityManager.persistAndFlush(duong);
        entityManager.persistAndFlush(thanh);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Student> studentsPage = studentRepository.findByCourseReleaseId(1L, pageable);

        assertEquals(studentsPage.getContent().size(), 0);
    }

    @Test
    @Transactional
    void findByCourseId() {
        List<Student> sampleStudents = StudentDataSample.get3();

        Student minh = sampleStudents.get(0);
        minh.setDepartment(this.department);

        Student duong = sampleStudents.get(1);
        duong.setDepartment(this.department);

        Student thanh = sampleStudents.get(2);
        thanh.setDepartment(this.department);

        entityManager.persistAndFlush(minh);
        entityManager.persistAndFlush(duong);
        entityManager.persistAndFlush(thanh);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Student> studentsPage = studentRepository.findByCourseId(1L, pageable);

        assertEquals(studentsPage.getContent().size(), 0);
    }

    @Test
    @Transactional
    void findAllByKeyword() {
        List<Student> sampleStudents = StudentDataSample.get3();

        Student minh = sampleStudents.get(0);
        minh.setDepartment(this.department);

        Student duong = sampleStudents.get(1);
        duong.setDepartment(this.department);

        Student thanh = sampleStudents.get(2);
        thanh.setDepartment(this.department);

        entityManager.persistAndFlush(minh);
        entityManager.persistAndFlush(duong);
        entityManager.persistAndFlush(thanh);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Student> studentsPage = studentRepository.findAllByKeyword("1801", pageable);

        assertEquals(studentsPage.getContent().size(), 3);
    }
}