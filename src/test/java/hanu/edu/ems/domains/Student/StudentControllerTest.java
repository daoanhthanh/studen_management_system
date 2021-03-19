package hanu.edu.ems.domains.Student;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.edu.ems.domains.Authority.entity.AuthorityName;
import hanu.edu.ems.domains.Student.dto.CreateStudentDTO;
import hanu.edu.ems.domains.Student.entity.Student;
import hanu.edu.ems.domains.User.entity.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenCreateValid_thenReturnNewStudent() throws Exception {
        CreateStudentDTO createStudentDTO = CreateStudentDTO.builder()
            .username("1801040147")
            .password("1801040147")
            .gender(Gender.MALE)
            .firstName("Minh")
            .lastName("Tang Ba")
            .email("minhtb.4c18@s.hanu.edu.vn")
            .phoneNumber("0969696969")
            .role(AuthorityName.ADMIN)
            .dob(LocalDate.of(2000, 12, 4))

            .departmentID(1L)
            .fatherName("Minh Father's Name")
            .motherName("Minh Mother's Name")
            .fullAddress("Minh Full Address")
            .sinceYear(2018)
            .build();

        mockMvc.perform(post("/students")
            .with(adminAuthority())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createStudentDTO))
        )
//            .andExpect(status().isCreated())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.firstName").value("Minh"))
            .andExpect(jsonPath("$.lastName").value("Tang Ba"))
            .andDo(print());
    }

    @Test
    void givenAdminAuthority_whenGetAll_thenReturnAllStudents() throws Exception {
        List<Student> studentList = Arrays.asList(
            Student.builder().firstName("minh").build(),
            Student.builder().firstName("duong").build()
        );
        given(studentService.findAll()).willReturn(studentList);

        mockMvc.perform(get("/students/all").with(adminAuthority()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].firstName").value("minh"))
            .andExpect(jsonPath("$[1].firstName").value("duong"))
            .andDo(print());
    }

    private RequestPostProcessor adminAuthority() {
        return user("admin").authorities(new SimpleGrantedAuthority("ADMIN"));
    }

    @Test
    void givenAdminAuthority_whenGetManyStudents_thenReturnStudentPage() throws Exception {
        Page<Student> studentPage = Page.empty();
        Pageable pageable = Pageable.unpaged();
        given(studentService.findAll(pageable)).willReturn(studentPage);

        mockMvc.perform(get("/students").with(adminAuthority()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andDo(print());
    }

    @Test
    void givenAdminAuthority_whenGetStudentWithID_thenReturnStudent() throws Exception {
        Student student = Student.builder()
            .firstName("Minh")
            .build();
        given(studentService.getById(1L)).willReturn(student);

        mockMvc.perform(get("/students/1").with(adminAuthority()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andDo(print());
    }

    @Test
    void givenAdminAuthority_whenUpdateByID_thenReturnUpdatedResult() {
    }

    @Test
    void deleteByID() {
    }

    @Test
    void testFindAllStudentsOfDepartment() {
    }
}