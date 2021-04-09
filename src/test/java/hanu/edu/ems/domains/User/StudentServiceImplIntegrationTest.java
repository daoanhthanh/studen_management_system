package hanu.edu.ems.domains.User;

import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department._sample.DepartmentDataSample;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Student.StudentRepository;
import hanu.edu.ems.domains.Student.StudentService;
import hanu.edu.ems.domains.Student.dto.CreateStudentDTO;
import hanu.edu.ems.domains.Student.dto.UpdateStudentDTO;
import hanu.edu.ems.domains.Student.entity.Student;
import hanu.edu.ems.domains.User._sample.StudentDataSample;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class StudentServiceImplIntegrationTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    private Department department;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        Department department = DepartmentDataSample.getOneValidDepartment();
        this.department = departmentRepository.saveAndFlush(department);
    }

    @Test
    void whenCreateWithValidData_thenReturnSuccessResult() {
        // Given
        // When
        CreateStudentDTO createStudentDTO = StudentDataSample.getExampleValidCreateStudentDTO();
        createStudentDTO.setDepartmentID(department.getId());
        Student student = studentService.create(createStudentDTO);

        // Then
        assertEquals(student.getUsername(), createStudentDTO.getUsername());
    }

    @Test
    void whenUpdateExistingWithValidData_thenReturnSuccessResult() {
        // Given
        Student existingStudent = StudentDataSample.getExampleValidStudent();
        existingStudent.setDepartment(department);

        existingStudent = studentRepository.save(existingStudent);
        // When
        UpdateStudentDTO updateStudentDTO = UpdateStudentDTO.builder()
            .firstName("Minh")
            .build();

        modelMapper.map(existingStudent, updateStudentDTO);

        Student result = studentService.updateById(existingStudent.getId(), updateStudentDTO);

        // Then
        assertEquals(result.getUsername(), existingStudent.getUsername());
    }

    @Test
    void whenDeleteExisting_thenSuccess() {
        // Given
        Student existingStudent = StudentDataSample.getExampleValidStudent();
        existingStudent.setDepartment(department);

        existingStudent = studentRepository.save(existingStudent);

        // When
        studentService.deleteById(existingStudent.getId());

        // Then
        Student finalExistingStudent = existingStudent;
        assertThrows(EntityNotFoundException.class, () -> {
            studentService.getById(finalExistingStudent.getId());
        });
    }

    @Test
    void whenFindAll_thenReturnResult() {
        // Given
        List<Student> students = StudentDataSample.get3Students();

        for (Student student: students) {
            student.setDepartment(department);
        }
        students = studentRepository.saveAll(students);

        // When
        List<Student> results = studentService.findAll();

        // Then
        assertEquals(results.size(), students.size());

        for (int i = 0; i < results.size(); i++) {
            assertEquals(results.get(i).toString(), students.get(i).toString());
        }
    }

    @Test
    void whenFindMany_thenReturnPagedResult() {
        // Given
        List<Student> students = StudentDataSample.get3Students();
        for (Student student: students) {
            student.setDepartment(department);
        }
        students = studentRepository.saveAll(students);

        Page<Student> studentPage = new PageImpl<>(students);
        // When
        Pageable pageable = PageRequest.of(0, 10);
        Page<Student> resultsPage = studentService.findAll(pageable);

        // Then
        assertEquals(studentPage.getNumberOfElements(), resultsPage.getNumberOfElements());

        for (int i = 0; i < studentPage.getNumberOfElements(); i++) {
            assertEquals(studentPage.getContent().get(i).toString(), resultsPage.getContent().get(i).toString());
        }
    }

    @Test
    void whenGetByIdOfExisting_thenReturnExistingResult() {
        // Given
        Student student = StudentDataSample.getExampleValidStudent();
        student.setDepartment(department);
        student = studentRepository.save(student);

        // When
        Student result = studentService.getById(student.getId());

        // Then
        assertEquals(result.toString(), student.toString());
    }

    @Test
    void testFindByDepartmentId() {
        // Given
        List<Student> students = StudentDataSample.get3Students();

        for (Student student: students) {
            student.setDepartment(department);
        }
        students = studentRepository.saveAll(students);
        // When
        List<Student> results = studentService.findByDepartmentId(this.department.getId());

        // Then
        assertEquals(students.size(), results.size());

        for (int i = 0; i < results.size(); i++) {
            assertEquals(students.get(i).toString(), results.get(i).toString());
        }
    }

    @Test
    void testFindByCourseReleaseId() {
        // Given
        List<Student> students = StudentDataSample.get3Students();

        for (Student student: students) {
            student.setDepartment(department);
        }
        students = studentRepository.saveAll(students);
        // When

        // Then
    }

    @Test
    void testFindByCourseId() {
        // Given
        List<Student> students = StudentDataSample.get3Students();

        for (Student student: students) {
            student.setDepartment(department);
        }
        students = studentRepository.saveAll(students);
        // When

        Pageable pageable = PageRequest.of(0, 10);
        studentService.findByCourseId(1L, pageable);

        // Then
    }

    @Test
    void testFindByKeyWord() {
        // Given
        List<Student> students = StudentDataSample.get3Students();

        for (Student student: students) {
            student.setDepartment(department);
        }
        students = studentRepository.saveAll(students);
        // When
        Pageable pageable = PageRequest.of(0, 10);
        Page<Student> resultsPage = studentService.findByKeyWord("Minh", pageable);
        // Then
        assertEquals(1, resultsPage.getContent().size());
        assertEquals(students.get(0).toString(), resultsPage.getContent().get(0).toString());
    }
}
