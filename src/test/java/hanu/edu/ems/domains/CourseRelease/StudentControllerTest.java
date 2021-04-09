package hanu.edu.ems.domains.CourseRelease;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.edu.ems.domains.Authority.entity.Authority;
import hanu.edu.ems.domains.Authority.entity.AuthorityName;
import hanu.edu.ems.domains.CourseRelease._sample.StudentDataSample;
import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department._sample.DepartmentDataSample;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Student.StudentService;
import hanu.edu.ems.domains.Student.dto.CreateStudentDTO;
import hanu.edu.ems.domains.Student.dto.UpdateStudentDTO;
import hanu.edu.ems.domains.Student.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Slf4j
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenCreateValid_thenReturnNewStudent() throws Exception {

        CreateStudentDTO createStudentDTO = StudentDataSample.getExampleValidCreateStudentDTO();

        createStudentDTO.setDepartmentID(1L);

        Student expectedResult = StudentDataSample.getExampleValidStudent();

        Department fit = DepartmentDataSample.getOneValidDepartment();
        createStudentDTO.setDepartmentID(fit.getId());

        given(departmentRepository.findById(1L)).willReturn(Optional.of(fit));
        given(studentService.create(Mockito.any(CreateStudentDTO.class))).willReturn(expectedResult);

        String content = objectMapper.writeValueAsString(createStudentDTO);

        mockMvc.perform(post("/students")
            .contentType(MediaType.APPLICATION_JSON)
            .with(adminAuthority())
            .content(content)
        )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.username").value(expectedResult.getUsername()))
            .andExpect(jsonPath("$.phoneNumber").value(expectedResult.getPhoneNumber()))
            .andExpect(jsonPath("$.email").value(expectedResult.getEmail()))
            .andExpect(jsonPath("$.firstName").value(expectedResult.getFirstName()))
            .andExpect(jsonPath("$.lastName").value(expectedResult.getLastName()))
            .andExpect(jsonPath("$.gender").value("Male"))
            .andExpect(jsonPath("$.authorities").isArray())
//            .andExpect(jsonPath("$.authorities[0].authority").value("STUDENT"))
//            .andExpect(jsonPath("$.authorities[1]").doesNotExist())
            .andExpect(jsonPath("$.dob").isArray())
            .andExpect(jsonPath("$.createdAt").isArray())
            .andExpect(jsonPath("$.updatedAt").isArray())
            .andExpect(jsonPath("$.sinceYear").value(expectedResult.getSinceYear()))
//            .andExpect(jsonPath("$.department").isMap())
//            .andExpect(jsonPath("$.department.id").value(1L))
            .andDo(print());
    }

    @Test
    void whenAdminGetAll_thenReturnAllStudents() throws Exception {

        List<Student> studentList = StudentDataSample.get3Students();

        Department department = DepartmentDataSample.getOneValidDepartment();
        department.setId(1L);

        Student std0 = studentList.get(0);
        Student std1 = studentList.get(1);
        Student std2 = studentList.get(2);

        Authority authority = Authority.builder()
            .id(1L)
            .name(AuthorityName.STUDENT)
            .build();

        std0.setAuthorities(Collections.singletonList(authority));
        std0.setDepartment(department);

        std1.setAuthorities(Collections.singletonList(authority));
        std1.setDepartment(department);

        std2.setAuthorities(Collections.singletonList(authority));
        std2.setDepartment(department);

        given(studentService.findAll()).willReturn(studentList);

        Department fit = DepartmentDataSample.getOneValidDepartment();

        given(departmentRepository.findById(1L)).willReturn(Optional.of(fit));

        mockMvc.perform(get("/students/all").with(adminAuthority()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$[0].username").value(std0.getUsername()))
            .andExpect(jsonPath("$[0].phoneNumber").value(std0.getPhoneNumber()))
            .andExpect(jsonPath("$[0].email").value(std0.getEmail()))
            .andExpect(jsonPath("$[0].firstName").value(std0.getFirstName()))
            .andExpect(jsonPath("$[0].lastName").value(std0.getLastName()))
            .andExpect(jsonPath("$[0].gender").value("Male"))
            .andExpect(jsonPath("$[0].authorities").isArray())
            .andExpect(jsonPath("$[0].authorities[0].authority").exists())
            .andExpect(jsonPath("$[0].authorities[1]").doesNotExist())
            .andExpect(jsonPath("$[0].dob").isArray())
            .andExpect(jsonPath("$[0].createdAt").isArray())
            .andExpect(jsonPath("$[0].updatedAt").isArray())
            .andExpect(jsonPath("$[0].sinceYear").value(std0.getSinceYear()))
            .andExpect(jsonPath("$[0].department").isMap())
            .andExpect(jsonPath("$[0].department.id").value(1L))

            .andExpect(jsonPath("$[1].username").value(std1.getUsername()))
            .andExpect(jsonPath("$[1].phoneNumber").value(std1.getPhoneNumber()))
            .andExpect(jsonPath("$[1].email").value(std1.getEmail()))
            .andExpect(jsonPath("$[1].firstName").value(std1.getFirstName()))
            .andExpect(jsonPath("$[1].lastName").value(std1.getLastName()))
            .andExpect(jsonPath("$[1].gender").value("Female"))
            .andExpect(jsonPath("$[1].authorities").isArray())
            .andExpect(jsonPath("$[1].authorities[0].authority").exists())
            .andExpect(jsonPath("$[1].authorities[1]").doesNotExist())
            .andExpect(jsonPath("$[1].dob").isArray())
            .andExpect(jsonPath("$[1].createdAt").isArray())
            .andExpect(jsonPath("$[1].updatedAt").isArray())
            .andExpect(jsonPath("$[1].sinceYear").value(std1.getSinceYear()))
            .andExpect(jsonPath("$[1].department").isMap())
            .andExpect(jsonPath("$[1].department.id").value(1L))

            .andExpect(jsonPath("$[2].username").value(std2.getUsername()))
            .andExpect(jsonPath("$[2].phoneNumber").value(std2.getPhoneNumber()))
            .andExpect(jsonPath("$[2].email").value(std2.getEmail()))
            .andExpect(jsonPath("$[2].firstName").value(std2.getFirstName()))
            .andExpect(jsonPath("$[2].lastName").value(std2.getLastName()))
            .andExpect(jsonPath("$[2].gender").value("Male"))
            .andExpect(jsonPath("$[2].authorities").isArray())
            .andExpect(jsonPath("$[2].authorities[0].authority").exists())
            .andExpect(jsonPath("$[2].authorities[1]").doesNotExist())
            .andExpect(jsonPath("$[2].dob").isArray())
            .andExpect(jsonPath("$[2].createdAt").isArray())
            .andExpect(jsonPath("$[2].updatedAt").isArray())
            .andExpect(jsonPath("$[2].sinceYear").value(std2.getSinceYear()))
            .andExpect(jsonPath("$[2].department").isMap())
            .andExpect(jsonPath("$[2].department.id").value(1L))
            .andDo(print());
    }

    private RequestPostProcessor adminAuthority() {
        return user("admin").authorities(new SimpleGrantedAuthority("ADMIN"));
    }

    @Test
    void whenAdminGetManyStudents_thenReturnStudentPage() throws Exception {
        Page<Student> studentPage = Page.empty();
        Pageable pageable = Pageable.unpaged();

        given(studentService.findAll(pageable)).willReturn(studentPage);

        mockMvc.perform(get("/students").with(adminAuthority()))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    void whenAdminGetStudentWithID_thenReturnStudent() throws Exception {
        Student student = StudentDataSample.getExampleValidStudent();

        Authority authority = Authority.builder()
            .id(1L)
            .name(AuthorityName.STUDENT)
            .build();

        student.setAuthorities(Collections.singletonList(authority));
        Department department = DepartmentDataSample.getOneValidDepartment();

        student.setDepartment(department);
        department.setId(1L);

        given(studentService.getById(1L)).willReturn(student);

        mockMvc.perform(get("/students/1").with(adminAuthority()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.username").value(student.getUsername()))
            .andExpect(jsonPath("$.phoneNumber").value(student.getPhoneNumber()))
            .andExpect(jsonPath("$.email").value(student.getEmail()))
            .andExpect(jsonPath("$.firstName").value(student.getFirstName()))
            .andExpect(jsonPath("$.lastName").value(student.getLastName()))
            .andExpect(jsonPath("$.gender").value("Male"))
            .andExpect(jsonPath("$.authorities").isArray())
            .andExpect(jsonPath("$.authorities[0].authority").value("STUDENT"))
            .andExpect(jsonPath("$.authorities[1]").doesNotExist())
            .andExpect(jsonPath("$.dob").isArray())
            .andExpect(jsonPath("$.createdAt").isArray())
            .andExpect(jsonPath("$.updatedAt").isArray())
            .andExpect(jsonPath("$.sinceYear").value(student.getSinceYear()))
            .andExpect(jsonPath("$.department").isMap())
            .andExpect(jsonPath("$.department.id").value(1L))
            .andDo(print());
    }

    @Test
    void whenAdminUpdateByID_thenReturnUpdatedResult() throws Exception {

        UpdateStudentDTO createStudentDTO = StudentDataSample.getExampleValidUpdateStudentDTO();

        createStudentDTO.setDepartmentID(1L);

        Student expectedResult = StudentDataSample.getExampleValidStudent();

        Department fit = DepartmentDataSample.getOneValidDepartment();
        createStudentDTO.setDepartmentID(fit.getId());

        given(departmentRepository.findById(eq(1L))).willReturn(Optional.of(fit));
        given(studentService.updateById(eq(1L), Mockito.any(UpdateStudentDTO.class))).willReturn(expectedResult);

        String content = objectMapper.writeValueAsString(createStudentDTO);

        mockMvc.perform(put("/students/1")
            .contentType(MediaType.APPLICATION_JSON)
            .with(adminAuthority())
            .content(content)
        )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.username").value(expectedResult.getUsername()))
            .andExpect(jsonPath("$.phoneNumber").value(expectedResult.getPhoneNumber()))
            .andExpect(jsonPath("$.email").value(expectedResult.getEmail()))
            .andExpect(jsonPath("$.firstName").value(expectedResult.getFirstName()))
            .andExpect(jsonPath("$.lastName").value(expectedResult.getLastName()))
            .andExpect(jsonPath("$.gender").value("Male"))
            .andExpect(jsonPath("$.authorities").isArray())
//            .andExpect(jsonPath("$.authorities[0].authority").value("STUDENT"))
//            .andExpect(jsonPath("$.authorities[1]").doesNotExist())
            .andExpect(jsonPath("$.dob").isArray())
            .andExpect(jsonPath("$.createdAt").isArray())
            .andExpect(jsonPath("$.updatedAt").isArray())
            .andExpect(jsonPath("$.sinceYear").value(expectedResult.getSinceYear()))
//            .andExpect(jsonPath("$.department").isMap())
//            .andExpect(jsonPath("$.department.id").value(1L))
            .andDo(print());
    }

    @Test
    void whenAdminDeleteByID_thenSuccess() throws Exception {
        mockMvc.perform(delete("/students/1").with(adminAuthority()))
            .andExpect(status().isOk());
    }
}