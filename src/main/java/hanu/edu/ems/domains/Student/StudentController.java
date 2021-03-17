package hanu.edu.ems.domains.Student;

import hanu.edu.ems.domains.Student.dto.CreateStudentDTO;
import hanu.edu.ems.domains.Student.dto.UpdateStudentDTO;
import hanu.edu.ems.domains.Student.entity.Student;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Student Manager", description = "The Student API")
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @Operation(summary = "Create a new Student")
    @PostMapping("/students")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public Student create(@Valid @RequestBody CreateStudentDTO createStudentDTO) {
        return studentService.create(createStudentDTO);
    }

    @Operation(summary = "Get all Students")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    @GetMapping(value = "/students/all")
    public List<Student> getAll(@RequestParam(required = false) Long departmentID) {
        if (departmentID == null)
            return studentService.findAll();
        else
            return studentService.findByDepartmentId(departmentID);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Get Students with paginating and sorting options")
    @GetMapping("/students")
    @PageableAsQueryParam
    public Page<Student> getMany(
        @Parameter(name = "Pagination", hidden = true) Pageable pageable,
        @RequestParam(value = "keyword", required = false) String keyword,
        @RequestParam(required = false) Long departmentID
    ) {
        if (departmentID != null)
            return studentService.findByDepartmentId(departmentID, pageable);
        else if (keyword != null)
            return studentService.findByKeyWord(keyword, pageable);
        else
            return studentService.findAll(pageable);
    }

    
    @Operation(summary = "Get a Student by ID")
    @PreAuthorize("hasAuthority('ADMIN') or @authorityServiceImpl.hasStudentAccess(principal.id, #id)")
    @GetMapping(value = "/students/{id}")
    public Student getByID(@PathVariable Long id) {
        return studentService.getById(id);
    }

    
    @Operation(summary = "Update a Student by ID")
    @PutMapping(value = "/students/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing request parameter(s)"),
        @ApiResponse(responseCode = "422", description = "Input constraints validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Unique field value(s) already exists")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    public Student updateByID(@PathVariable Long id, @Valid @RequestBody UpdateStudentDTO updateStudentDTO) {
        return studentService.updateById(id, updateStudentDTO);
    }

    @Operation(summary = "Delete a Student by ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/students/{id}")
    public void deleteByID(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    @Operation(summary = "Get all Students of a Department")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "departments/{departmentID}/students")
    public Page<Student> findAllStudentsOfDepartment(@PathVariable Long departmentID, Pageable pageable) {
        return studentService.findByDepartmentId(departmentID, pageable);
    }

    @Operation(summary = "Get all Students of a Department")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "departments/{departmentID}/students/all")
    public List<Student> findAllStudentsOfDepartment(@PathVariable Long departmentID) {
        return studentService.findByDepartmentId(departmentID);
    }
}
