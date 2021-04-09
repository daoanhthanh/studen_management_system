package hanu.edu.ems.domains.CourseRelease;

import hanu.edu.ems.domains.CourseRelease._sample.StudentDataSample;
import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department._sample.DepartmentDataSample;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Student.StudentRepository;
import hanu.edu.ems.domains.Student.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestEntityManager
@Slf4j
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
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
        Department department = DepartmentDataSample.getOneValidDepartment();
//        given(departmentRepository.findById(1L)).willReturn(Optional.of(department));
        this.department = departmentRepository.saveAndFlush(department);

        assertNotNull(this.department);
    }

    @Test
    @Transactional
    void testFindAllByDepartmentId() {

        log.info("DEPARTMENT");
        log.info(department.toString());
        // Given
        List<Student> sampleStudents = StudentDataSample.get3Students();

        Student minh = sampleStudents.get(0);
        minh.setDepartment(department);
        minh.setId(null);

        Student duong = sampleStudents.get(1);
        duong.setDepartment(department);
        duong.setId(null);

        Student thanh = sampleStudents.get(2);
        thanh.setDepartment(department);
        thanh.setId(null);

        entityManager.persistAndFlush(minh);
        entityManager.persistAndFlush(duong);
        entityManager.persistAndFlush(thanh);

        // When
        List<Student> students = studentRepository.findAllByDepartmentId(department.getId());
        // Then
        assertEquals(students.size(), 3);

        studentRepository.deleteAll();
    }

    @Test
    @Transactional
    void findByCourseReleaseId() {

        List<Student> sampleStudents = StudentDataSample.get3Students();

        Student minh = sampleStudents.get(0);
        minh.setDepartment(department);
        minh.setId(null);

        Student duong = sampleStudents.get(1);
        duong.setDepartment(department);
        duong.setId(null);

        Student thanh = sampleStudents.get(2);
        thanh.setDepartment(department);
        thanh.setId(null);

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

        List<Student> sampleStudents = StudentDataSample.get3Students();

        Student minh = sampleStudents.get(0);
        minh.setDepartment(department);
        minh.setId(null);

        Student duong = sampleStudents.get(1);
        duong.setDepartment(department);
        duong.setId(null);

        Student thanh = sampleStudents.get(2);
        thanh.setDepartment(department);
        thanh.setId(null);

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

        List<Student> sampleStudents = StudentDataSample.get3Students();

        Student minh = sampleStudents.get(0);
        minh.setDepartment(department);
        minh.setId(null);

        Student duong = sampleStudents.get(1);
        duong.setDepartment(department);
        duong.setId(null);

        Student thanh = sampleStudents.get(2);
        thanh.setDepartment(department);
        thanh.setId(null);

        entityManager.persistAndFlush(minh);
        entityManager.persistAndFlush(duong);
        entityManager.persistAndFlush(thanh);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Student> studentsPage = studentRepository.findAllByKeyword("1801", pageable);

        assertEquals(studentsPage.getContent().size(), 3);
    }
}