package hanu.edu.ems.domains.Student;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Department.entity.DepartmentTest;
import hanu.edu.ems.domains.Student.dto.CreateStudentDTO;
import hanu.edu.ems.domains.Student.entity.Student;
import hanu.edu.ems.domains.User.entity.Gender;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@TestPropertySource(locations = "classpath:application-test.properties")
public class StudentIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department department;

    @BeforeEach
    public void setUp() {
        Department department = DepartmentTest.getExampleDepartment();
        this.department = departmentRepository.saveAndFlush(department);
        log.info(this.department.toString());
    }

    @After
    public void resetDb() {
        studentRepository.deleteAll();
        departmentRepository.deleteAll();
    }

    private void createExampleStudent(Student student) {
        studentRepository.saveAndFlush(student);
    }

    private void createExampleStudent() {
        Student student = Student.builder().build();
        createExampleStudent(student);
    }

    private RequestPostProcessor adminAuthority() {
        return user("admin").authorities(new SimpleGrantedAuthority("ADMIN"));
    }

    @Test
    void whenCreateWithValidDataAsAdmin_thenReturnStudent() throws Exception {
        CreateStudentDTO createStudentDTO = CreateStudentDTO.builder()
            .departmentID(this.department.getId())
            .email("1801040147@s.hanu.edu.vn")
            .dob(LocalDate.of(2000, 12, 4))
            .firstName("Minh")
            .lastName("Tang Ba")
            .gender(Gender.MALE)
            .fatherName("Tang Ba Hoang")
            .motherName("Nguyen Thai Ha")
            .fullAddress("Thanh Xuan, Hanoi")
            .sinceYear(2018)
            .phoneNumber("0904842084")
            .username("1801040147")
            .password("1801040147")
            .build();

        Student expectedStudentResult = Student.builder()
            .department(this.department)
            .email("1801040147@s.hanu.edu.vn")
            .dob(LocalDate.of(2000, 12, 4))
            .firstName("Minh")
            .lastName("Tang Ba")
            .gender(Gender.MALE)
            .fatherName("Tang Ba Hoang")
            .motherName("Nguyen Thai Ha")
            .fullAddress("Thanh Xuan, Hanoi")
            .sinceYear(2018)
            .phoneNumber("0904842084")
            .username("1801040147")
            .password("1801040147")
            .build();

        mockMvc.perform(post("/students")
            .with(adminAuthority())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createStudentDTO))
        )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.department.id").value(expectedStudentResult.getDepartment().getId()))
            .andExpect(jsonPath("$.email").value(expectedStudentResult.getEmail()))
            .andExpect(jsonPath("$.dob").isArray())
            .andExpect(jsonPath("$.firstName").value(expectedStudentResult.getFirstName()))
            .andExpect(jsonPath("$.lastName").value(expectedStudentResult.getLastName()))
            .andExpect(jsonPath("$.gender").value("Male"))
            .andExpect(jsonPath("$.fatherName").value(expectedStudentResult.getFatherName()))
            .andExpect(jsonPath("$.motherName").value(expectedStudentResult.getMotherName()))
            .andExpect(jsonPath("$.fullAddress").value(expectedStudentResult.getFullAddress()))
            .andExpect(jsonPath("$.sinceYear").value(expectedStudentResult.getSinceYear()))
            .andExpect(jsonPath("$.phoneNumber").value(expectedStudentResult.getPhoneNumber()))
            .andExpect(jsonPath("$.username").value(expectedStudentResult.getUsername()))
            .andExpect(jsonPath("$.password").doesNotExist())
            .andDo(print());
    }

    @Test
    public void whenGetManyAsAdmin_thenReturnStudentsPage() {

    }

    @Test
    public void whenGetAllAsAdmin_thenReturnAllStudents() {

    }

    @Test
    public void whenUpdateAsAdmin_thenReturnUpdated() {

    }

    @Test
    public void whenDeleteAsAdmin_thenSuccess() {

    }
}
