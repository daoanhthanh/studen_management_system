package hanu.edu.ems.domains.Student;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.edu.ems.domains.Department.DepartmentDataSample;
import hanu.edu.ems.domains.Department.DepartmentService;
import hanu.edu.ems.domains.Department.dto.CreateDepartmentDTO;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Student.dto.CreateStudentDTO;
import hanu.edu.ems.domains.Student.dto.UpdateStudentDTO;
import hanu.edu.ems.domains.Student.entity.Student;
import hanu.edu.ems.domains.User.entity.Gender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.contains;
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
@Slf4j
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class StudentControllerIntegrationTest {

    private Department department;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentService studentService;

    @Autowired
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        CreateDepartmentDTO createDepartmentDTO = DepartmentDataSample.getOneValidCreateDepartmentDTO();
        this.department = departmentService.create(createDepartmentDTO);
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
        List<CreateStudentDTO> sampleDTOs = StudentDataSample.get3CreateStudentDTOs();

        CreateStudentDTO minhDTO = sampleDTOs.get(0);
        minhDTO.setDepartmentID(this.department.getId());

        CreateStudentDTO duongDTO = sampleDTOs.get(1);
        duongDTO.setDepartmentID(this.department.getId());

        CreateStudentDTO thanhDTO = sampleDTOs.get(2);
        thanhDTO.setDepartmentID(this.department.getId());

        Student minh = studentService.create(minhDTO);
        Student duong = studentService.create(duongDTO);
        Student thanh = studentService.create(thanhDTO);

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

        List<CreateStudentDTO> sampleDTOs = StudentDataSample.get3CreateStudentDTOs();

        CreateStudentDTO minhDTO = sampleDTOs.get(0);
        minhDTO.setDepartmentID(this.department.getId());

        CreateStudentDTO duongDTO = sampleDTOs.get(1);
        duongDTO.setDepartmentID(this.department.getId());

        CreateStudentDTO thanhDTO = sampleDTOs.get(2);
        thanhDTO.setDepartmentID(this.department.getId());

        Student minh = studentService.create(minhDTO);
        Student duong = studentService.create(duongDTO);
        Student thanh = studentService.create(thanhDTO);

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
    public void whenUpdateAsAdmin_thenReturnUpdated() throws Exception {

        CreateStudentDTO createStudentDTO = StudentDataSample.getExampleValidCreateStudentDTO();

        createStudentDTO.setDepartmentID(this.department.getId());

        Student existingStudent = studentService.create(createStudentDTO);

        // Partial Update, will not work on null value
        UpdateStudentDTO updateStudentDTO = UpdateStudentDTO.builder()
            .email("1801040@s.hanu.edu.vn")
            .build();

        UpdateStudentDTO merged = modelMapper.map(createStudentDTO, UpdateStudentDTO.class);

        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(updateStudentDTO, merged);

        String content = objectMapper.writeValueAsString(merged);

        mockMvc.perform(put("/students/" + existingStudent.getId())
            .with(adminAuthority())
            .contentType(MediaType.APPLICATION_JSON)
            .content(content)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.department.id").value(createStudentDTO.getDepartmentID()))
            .andExpect(jsonPath("$.email").value(updateStudentDTO.getEmail()))
            .andExpect(jsonPath("$.dob").isArray())
            .andExpect(jsonPath("$.firstName").value(createStudentDTO.getFirstName()))
            .andExpect(jsonPath("$.lastName").value(createStudentDTO.getLastName()))
            .andExpect(jsonPath("$.gender").value("Male"))
            .andExpect(jsonPath("$.fatherName").value(createStudentDTO.getFatherName()))
            .andExpect(jsonPath("$.motherName").value(createStudentDTO.getMotherName()))
            .andExpect(jsonPath("$.fullAddress").value(createStudentDTO.getFullAddress()))
            .andExpect(jsonPath("$.sinceYear").value(createStudentDTO.getSinceYear()))
            .andExpect(jsonPath("$.phoneNumber").value(createStudentDTO.getPhoneNumber()))
            .andExpect(jsonPath("$.username").value(createStudentDTO.getUsername()))
            .andExpect(jsonPath("$.password").doesNotExist())
            .andDo(print());
    }

    @Test
    public void whenDeleteAsAdmin_thenSuccess() throws Exception {

        CreateStudentDTO createStudentDTO = StudentDataSample.getExampleValidCreateStudentDTO();

        createStudentDTO.setDepartmentID(this.department.getId());

        Student student = studentService.create(createStudentDTO);

        mockMvc.perform(delete("/students/" + student.getId()).with(adminAuthority()))
            .andExpect(status().isOk())
            .andDo(print());
    }
}
