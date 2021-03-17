package hanu.edu.ems.domains.Teacher;

import hanu.edu.ems.domains.Teacher.dto.CreateTeacherDTO;
import hanu.edu.ems.domains.Teacher.dto.UpdateTeacherDTO;
import hanu.edu.ems.domains.Teacher.entity.Teacher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Teacher Manager", description = "The Teacher API")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Operation(summary = "Create a new teacher")
    @PostMapping("/teachers")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public Teacher create(@Valid @RequestBody CreateTeacherDTO createTeacherDTO) {
        return teacherService.create(createTeacherDTO);
    }

    @Operation(summary = "Get all teachers")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/teachers/all")
    public List<Teacher> findAll(@RequestParam(required = false) Long departmentID) {
        if (departmentID == null)
            return teacherService.findAll();
        else
            return teacherService.findAllByDepartmentId(departmentID);
    }

    @Operation(summary = "Get teachers with paginating and sorting options")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/teachers")
    @PageableAsQueryParam
    public Page<Teacher> findAll(
        @Parameter(name = "Pagination", hidden = true) Pageable pageable,
        @RequestParam(required = false) Long departmentID,
        @RequestParam(required = false) String keyword
    ) {
        if (departmentID != null)
            return teacherService.findAllByDepartmentId(departmentID, pageable);
        else if (keyword != null)
            return teacherService.findByKeyWord(keyword, pageable);
        else
            return teacherService.findAll(pageable);
    }

    @Operation(summary = "Get teacher by ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/teachers/{id}")
    public Teacher getByID(@PathVariable Long id) {
        return teacherService.getById(id);
    }

    @Operation(summary = "Update a teacher by ID")
    @PutMapping(value = "/teachers/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    public Teacher updateByID(@PathVariable Long id, @Valid @RequestBody UpdateTeacherDTO updateTeacherDTO) {
        return teacherService.updateById(id, updateTeacherDTO);
    }

    @Operation(summary = "Delete a teacher by ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/teachers/{id}")
    public void deleteByID(@PathVariable Long id) {
        teacherService.deleteById(id);
    }

    @Operation(summary = "Get all Teachers of a Department")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "departments/{departmentID}/teachers")
    public Page<Teacher> findAllTeachersOfDepartment(@PathVariable Long departmentID, Pageable pageable) {
        return teacherService.findAllByDepartmentId(departmentID, pageable);
    }

    @Operation(summary = "Get all Teachers of a Department")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "departments/{departmentID}/teachers/all")
    public List<Teacher> findAllTeachersOfDepartment(@PathVariable Long departmentID) {
        return teacherService.findAllByDepartmentId(departmentID);
    }
}
