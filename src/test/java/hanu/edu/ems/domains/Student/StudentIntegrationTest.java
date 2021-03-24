package hanu.edu.ems.domains.Student;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Department.entity.DepartmentTest;
import hanu.edu.ems.domains.Student.dto.CreateStudentDTO;
import hanu.edu.ems.domains.Student.entity.Student;
import hanu.edu.ems.domains.User.entity.Gender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
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
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        this.department = departmentRepository.save(department);
    }

    @AfterEach
    public void resetDb() {
        studentRepository.deleteAll();
        departmentRepository.deleteAll();
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
    public void whenGetManyAsAdmin_thenReturnStudentsPage() throws Exception {
        // GIVEN
        List<Student> sampleStudents = StudentDataSample.get3();

        Student minh = sampleStudents.get(0);
        minh.setDepartment(this.department);

        Student duong = sampleStudents.get(1);
        duong.setDepartment(this.department);

        Student thanh = sampleStudents.get(2);
        thanh.setDepartment(this.department);

        minh = studentRepository.save(minh);
        duong = studentRepository.save(duong);
        thanh = studentRepository.save(thanh);

        mockMvc.perform(get("/students")
            .queryParam("size", "10")
            .queryParam("page", "0")
            .with(adminAuthority())
        )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[*].department.id", contains(minh.getDepartment().getId().intValue(),
                duong.getDepartment().getId().intValue(), thanh.getDepartment().getId().intValue())))
            .andExpect(jsonPath("$.content[*].email", contains(minh.getEmail(), duong.getEmail(), thanh.getEmail())))
            .andExpect(jsonPath("$.content[*].dob").isArray())
            .andExpect(jsonPath("$.content[*].firstName", contains(minh.getFirstName(), duong.getFirstName(),
                thanh.getFirstName())))
            .andExpect(jsonPath("$.content[*].lastName", contains(minh.getLastName(), duong.getLastName(), thanh.getLastName())))
            .andExpect(jsonPath("$.content[*].gender", contains("Male", "Female", "Male")))
            .andExpect(jsonPath("$.content[*].fatherName", contains(minh.getFatherName(), duong.getFatherName(),
                thanh.getFatherName())))
            .andExpect(jsonPath("$.content[*].motherName", contains(minh.getMotherName(), duong.getMotherName(),
                thanh.getMotherName())))
            .andExpect(jsonPath("$.content[*].fullAddress", contains(minh.getFullAddress(), duong.getFullAddress(),
                thanh.getFullAddress())))
            .andExpect(jsonPath("$.content[*].sinceYear", contains(minh.getSinceYear(), duong.getSinceYear(),
                thanh.getSinceYear())))
            .andExpect(jsonPath("$.content[*].phoneNumber", contains(minh.getPhoneNumber(), duong.getPhoneNumber(),
                thanh.getPhoneNumber())))
            .andExpect(jsonPath("$.content[*].username", contains(minh.getUsername(), duong.getUsername(),
                thanh.getUsername())))
            .andExpect(jsonPath("$.content[*].password").doesNotExist())
            .andDo(print());
    }

    @Test
    public void whenGetAllAsAdmin_thenReturnAllStudents() throws Exception {
        List<Student> sampleStudents = StudentDataSample.get3();

        Student minh = sampleStudents.get(0);
        minh.setDepartment(this.department);

        Student duong = sampleStudents.get(1);
        duong.setDepartment(this.department);

        Student thanh = sampleStudents.get(2);
        thanh.setDepartment(this.department);

        minh = studentRepository.save(minh);
        duong = studentRepository.save(duong);
        thanh = studentRepository.save(thanh);

        mockMvc.perform(get("/students/all")
            .with(adminAuthority())
        )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[*].department.id", contains(minh.getDepartment().getId().intValue(),
                duong.getDepartment().getId().intValue(), thanh.getDepartment().getId().intValue())))
            .andExpect(jsonPath("$[*].email", contains(minh.getEmail(), duong.getEmail(), thanh.getEmail())))
            .andExpect(jsonPath("$[*].dob").isArray())
            .andExpect(jsonPath("$[*].firstName", contains(minh.getFirstName(), duong.getFirstName(),
                thanh.getFirstName())))
            .andExpect(jsonPath("$[*].lastName", contains(minh.getLastName(), duong.getLastName(), thanh.getLastName())))
            .andExpect(jsonPath("$[*].gender", contains("Male", "Female", "Male")))
            .andExpect(jsonPath("$[*].fatherName", contains(minh.getFatherName(), duong.getFatherName(),
                thanh.getFatherName())))
            .andExpect(jsonPath("$[*].motherName", contains(minh.getMotherName(), duong.getMotherName(),
                thanh.getMotherName())))
            .andExpect(jsonPath("$[*].fullAddress", contains(minh.getFullAddress(), duong.getFullAddress(),
                thanh.getFullAddress())))
            .andExpect(jsonPath("$[*].sinceYear", contains(minh.getSinceYear(), duong.getSinceYear(),
                thanh.getSinceYear())))
            .andExpect(jsonPath("$[*].phoneNumber", contains(minh.getPhoneNumber(), duong.getPhoneNumber(),
                thanh.getPhoneNumber())))
            .andExpect(jsonPath("$[*].username", contains(minh.getUsername(), duong.getUsername(),
                thanh.getUsername())))
            .andExpect(jsonPath("$[*].password").doesNotExist())
            .andDo(print());
    }

    @Test
    public void whenUpdateAsAdmin_thenReturnUpdated() {

    }

    @Test
    public void whenDeleteAsAdmin_thenSuccess() {

    }
}
