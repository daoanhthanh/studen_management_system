package hanu.edu.ems.domains.CourseRelease;

import hanu.edu.ems.domains.CourseRelease.dto.CreateCourseReleaseDTO;
import hanu.edu.ems.domains.CourseRelease.dto.UpdateCourseReleaseDTO;
import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
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
@Tag(name = "Course Release Manager", description = "The Course Release API")
public class CourseReleaseController {

    private final CourseReleaseService courseReleaseService;

    @Autowired
    public CourseReleaseController(CourseReleaseService courseReleaseService) {
        this.courseReleaseService = courseReleaseService;
    }

    @Operation(summary = "Create a new Course Release for a particular Course")
    @PostMapping("/courseReleases")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public CourseRelease create(@Valid @RequestBody CreateCourseReleaseDTO createCourseReleaseDTO) {
        return courseReleaseService.create(createCourseReleaseDTO);
    }

    @Operation(summary = "Get all Course Releases")
    @GetMapping(value = "/courseReleases/all")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    public List<CourseRelease> findAll(@RequestParam(required = false) Long courseID) {
        if (courseID == null)
            return courseReleaseService.findAll();
        else
            return courseReleaseService.findAllByCourseID(courseID);
    }

    @Operation(summary = "Get Course Releases with paginating and sorting options")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    @GetMapping("/courseReleases")
    @PageableAsQueryParam
    public Page<CourseRelease> findAll(
        @Parameter(name = "Pagination", hidden = true) Pageable pageable,
        @RequestParam(required = false) Long courseID
    ) {
        if (courseID == null)
            return courseReleaseService.findAll(pageable);
        else
            return courseReleaseService.findAllByCourseID(courseID, pageable);
    }

    @Operation(summary = "Get Course Release by ID")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    @GetMapping(value = "/courseReleases/{id}")
    public CourseRelease getByID(@PathVariable Long id) {
        return courseReleaseService.getById(id);
    }

    @Operation(summary = "Update a Course Release by ID")
    @PutMapping(value = "/courseReleases/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    public CourseRelease updateByID(@PathVariable Long id, @Valid @RequestBody UpdateCourseReleaseDTO updateCourseReleaseDTO) {
        return courseReleaseService.updateById(id, updateCourseReleaseDTO);
    }

    @Operation(summary = "Delete a Course release by ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/courseReleases/{id}")
    public void deleteByID(@PathVariable Long id) {
        courseReleaseService.deleteById(id);
    }

    @Operation(summary = "Create new Release for a Course")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/courses/{courseID}/releases")
    public CourseRelease createCourseReleaseForCourse(@PathVariable Long courseID, @Valid @RequestBody CreateCourseReleaseDTO createCourseReleaseDTO) {
        return courseReleaseService.createForCourse(courseID, createCourseReleaseDTO);
    }

    @Operation(summary = "Get all Releases of a Course with its id")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/courses/{courseID}/releases/all")
    public List<CourseRelease> findAllByCourseID(@PathVariable Long courseID) {
        return courseReleaseService.findAllByCourseID(courseID);
    }

    @Operation(summary = "Get many Releases of a Course with its id with paginating and sorting options")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/courses/{courseID}/releases")
    public Page<CourseRelease> findAllByCourseID(@PathVariable Long courseID, @Parameter(name = "Pagination", hidden = true) Pageable pageable) {
        return courseReleaseService.findAllByCourseID(courseID, pageable);
    }
}
