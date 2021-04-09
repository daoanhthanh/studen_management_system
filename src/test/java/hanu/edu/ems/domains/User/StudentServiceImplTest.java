package hanu.edu.ems.domains.User;

import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department._sample.DepartmentDataSample;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Student.StudentRepository;
import hanu.edu.ems.domains.Student.StudentService;
import hanu.edu.ems.domains.Student.dto.CreateStudentDTO;
import hanu.edu.ems.domains.Student.entity.Student;
import hanu.edu.ems.domains.User._sample.StudentDataSample;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class StudentServiceImplTest {

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ModelMapper modelMapper;

    private final Department department = DepartmentDataSample.getOneValidDepartment();

    @Test
    void whenCreateWithValidData_thenReturnSuccessResult() {

        // Given
        Student student = StudentDataSample.getExampleValidStudent();
        student.setDepartment(department);
        given(studentRepository.save(any(Student.class))).willReturn(student);

        given(departmentRepository.findById(any())).willReturn(Optional.of(department));

        // When
        CreateStudentDTO createStudentDTO = StudentDataSample.getExampleValidCreateStudentDTO();
        createStudentDTO.setDepartmentID(department.getId());
        Student result = studentService.create(createStudentDTO);

        // Then
        assertNotNull(result);
    }

    @Test
    void whenUpdateExistingWithValidData_thenReturnSuccessResult() {
        // Given
        Student student = StudentDataSample.getExampleValidStudent();

        given(studentRepository.save(any(Student.class))).willReturn(student);

        given(departmentRepository.findById(any())).willReturn(Optional.of(department));
        // When

        // Then
    }

    @Test
    void whenDeleteExisting_thenSuccess() {
        // Given

        // When
        studentService.deleteById(1L);

        // Then
    }

    @Test
    void whenFindAll_thenReturnResult() {
        List<Student> expectedResults = StudentDataSample.get3Students();
        // Given
        given(studentRepository.findAll()).willReturn(expectedResults);

        // When
        List<Student> receivedResults = studentService.findAll();

        // Then
        assertEquals(expectedResults.size(), receivedResults.size());

        for (int i = 0; i < expectedResults.size(); i++) {
            assertEquals(expectedResults.get(i).toString(), receivedResults.get(i).toString());
        }
    }

    @Test
    void whenFindMany_thenReturnPagedResult() {
        // Given
        Page<Student> expectedResults = new PageImpl<>(StudentDataSample.get3Students());

        Pageable pageable = PageRequest.of(0, 10);

        // Given
        given(studentRepository.findAll(pageable)).willReturn(expectedResults);
        // When
        Page<Student> receivedResults = studentService.findAll(pageable);
        // Then
        assertEquals(expectedResults.getSize(), receivedResults.getSize());

        for (int i = 0; i < receivedResults.getSize(); i++) {
            assertEquals(receivedResults.getContent().get(i).toString(), expectedResults.getContent().get(i).toString());
        }
    }

    @Test
    void whenGetByIdOfExisting_thenReturnExistingResult() {
        // Given
        Student expected = StudentDataSample.getExampleValidStudent();
        given(studentRepository.findById(1L)).willReturn(Optional.of(expected));

        // When
        Student result = studentService.getById(1L);
        // Then

        assertEquals(result, expected);
    }

    @Test
    void testFindAllByDepartmentId() {
        // Given
        List<Student> students = StudentDataSample.get3Students();
        given(studentRepository.findAllByDepartmentId(1L)).willReturn(students);
        // When
        List<Student> results = studentService.findByDepartmentId(1L);

        // Then
        assertEquals(students.size(), results.size());

        for (int i = 0; i < students.size(); i++) {
            assertEquals(students.get(i).toString(), results.get(i).toString());
        }
    }

    @Test
    void testFindAllByCourseReleaseId() {
        // Given
        List<Student> students = StudentDataSample.get3Students();

        Page<Student> expectedResults = new PageImpl<>(students);
        Pageable pageable = PageRequest.of(0, 10);

        given(studentRepository.findByCourseReleaseId(1L, pageable)).willReturn(expectedResults);
        // When
        Page<Student> results = studentService.findByCourseReleaseId(1L, pageable);

        // Then
        assertEquals(expectedResults.getSize(), results.getSize());

        for (int i = 0; i < results.getSize(); i++) {
            assertEquals(students.get(i).toString(), results.getContent().get(i).toString());
        }
    }

    @Test
    void testFindByCourseId() {
        // Given
        List<Student> students = StudentDataSample.get3Students();

        Page<Student> expectedResults = new PageImpl<>(students);
        Pageable pageable = PageRequest.of(0, 10);

        given(studentRepository.findByCourseId(1L, pageable)).willReturn(expectedResults);
        // When
        Page<Student> results = studentService.findByCourseId(1L, pageable);

        // Then
        assertEquals(expectedResults.getSize(), results.getSize());

        for (int i = 0; i < results.getSize(); i++) {
            assertEquals(students.get(i).toString(), results.getContent().get(i).toString());
        }
    }

    @Test
    void testFindByKeyWord() {
        // Given
        List<Student> students = StudentDataSample.get3Students();

        Page<Student> expectedResults = new PageImpl<>(students);
        Pageable pageable = PageRequest.of(0, 10);

        given(studentRepository.findAllByKeyword("Minh", pageable)).willReturn(expectedResults);
        // When
        Page<Student> results = studentService.findByKeyWord("Minh", pageable);

        // Then
        assertEquals(expectedResults.getSize(), results.getSize());

        for (int i = 0; i < results.getSize(); i++) {
            assertEquals(students.get(i).toString(), results.getContent().get(i).toString());
        }
    }
}