package hanu.edu.ems.domains.Enrollment;

import hanu.edu.ems.domains.Enrollment.dto.CreateEnrollmentDTO;
import hanu.edu.ems.domains.Enrollment.dto.UpdateEnrollmentDTO;
import hanu.edu.ems.domains.Enrollment.entity.Enrollment;
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
@Tag(name = "Enrollment Manager", description = "The Enrollment API")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @Operation(summary = "Create a new enrollment")
    @PostMapping("/enrollments")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public Enrollment create(@Valid @RequestBody CreateEnrollmentDTO createEnrollmentDTO) {
        return enrollmentService.create(createEnrollmentDTO);
    }

    @Operation(summary = "Get all enrollments")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    @GetMapping(value = "/enrollments/all")
    public List<Enrollment> findAll(@RequestParam(required = false) Long studentID) {
        if (studentID == null)
            return enrollmentService.findAll();
        else
            return enrollmentService.findAllByStudentId(studentID);
    }

    @Operation(summary = "Get enrollments with paginating and sorting options")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    @GetMapping("/enrollments")
    @PageableAsQueryParam
    public Page<Enrollment> findAll(
        @Parameter(name = "Pagination", hidden = true) Pageable pageable,
        @RequestParam(required = false) Long studentID
    ) {
        if (studentID == null)
            return enrollmentService.findAll(pageable);
        else
            return enrollmentService.findAllByStudentId(studentID, pageable);
    }

    @Operation(summary = "Get enrollment by ID")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    @GetMapping(value = "/enrollments/{id}")
    public Enrollment getByID(@PathVariable Long id) {
        return enrollmentService.getById(id);
    }

    @Operation(summary = "Update an enrollment by ID")
    @PutMapping(value = "/enrollments/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    public Enrollment updateByID(@PathVariable Long id, @Valid @RequestBody UpdateEnrollmentDTO updateEnrollmentDTO) {
        return enrollmentService.updateById(id, updateEnrollmentDTO);
    }

    @Operation(summary = "Delete an enrollment by ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/enrollments/{id}")
    public void deleteByID(@PathVariable Long id) {
        enrollmentService.deleteById(id);
    }

    @Operation(summary = "Create a new Enrollment for a Student")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/students/{studentID}/enrollments")
    public Enrollment createEnrollmentForStudent(@PathVariable Long studentID, @Valid @RequestBody CreateEnrollmentDTO createEnrollmentDTO) {
        return enrollmentService.create(createEnrollmentDTO);
    }

    @Operation(summary = "Get all Enrollments of a Student with ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/students/{studentID}/enrollments")
    public Page<Enrollment> findAllByStudentID(@PathVariable Long studentID, @Parameter(name = "Pagination", hidden = true) Pageable pageable) {
        return enrollmentService.findAllByStudentId(studentID, pageable);
    }

    @Operation(summary = "Get all Enrollments of a Student with ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/students/{studentID}/enrollments/all")
    public List<Enrollment> findAllByStudentID(@PathVariable Long studentID) {
        return enrollmentService.findAllByStudentId(studentID);
    }

    @Operation(summary = "Get all Enrollments of a Course Release with ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/courseReleases/{courseReleaseID}/enrollments/all")
    public List<Enrollment> findAllByCourseReleaseID(@PathVariable Long courseReleaseID) {
        return enrollmentService.findAllByCourseReleaseId(courseReleaseID);
    }

    @Operation(summary = "Get many Enrollments of a Course Release with its ID with paginating and sorting options")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/courseReleases/{courseReleaseID}/enrollments")
    public Page<Enrollment> findAllByCourseReleaseID(@PathVariable Long courseReleaseID, @Parameter(name = "Pagination", hidden = true) Pageable pageable) {
        return enrollmentService.findAllByCourseReleaseId(courseReleaseID, pageable);
    }
}
