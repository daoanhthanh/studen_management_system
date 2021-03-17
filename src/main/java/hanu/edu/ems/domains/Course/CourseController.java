package hanu.edu.ems.domains.Course;

import hanu.edu.ems.domains.Course.dto.CreateCourseDTO;
import hanu.edu.ems.domains.Course.dto.UpdateCourseDTO;
import hanu.edu.ems.domains.Course.entity.Course;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@Tag(name = "Course Manager", description = "The Course API")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Create a new course")
    @PostMapping("/courses")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public Course create(@Valid @RequestBody CreateCourseDTO createCourseDTO) {
        return courseService.create(createCourseDTO);
    }

    @Operation(summary = "Get all courses")
    @GetMapping(value = "/courses/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Course> findAll(@RequestParam(required = false) Long departmentID, @RequestParam(required = false) String keyword) {
        if (departmentID != null)
            return courseService.findAllByDepartmentID(departmentID);
        else if (keyword != null)
            return courseService.findAllByNameLike(keyword);
        else
            return courseService.findAll();
    }

    @Operation(summary = "Get courses with paginating and sorting options")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    @GetMapping("/courses")
    @PageableAsQueryParam
    public Page<Course> findAll(
        @Parameter(name = "Pagination", hidden = true) Pageable pageable,
        @RequestParam(required = false) Long departmentID,
        @RequestParam(required = false) String keyword
    ) {
        if (departmentID != null)
            return courseService.findAllByDepartmentID(departmentID, pageable);
        else if (keyword != null)
            return courseService.findAllByNameLike(keyword, pageable);
        else
            return courseService.findAll(pageable);
    }

    @Operation(summary = "Get course by ID")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    @GetMapping(value = "/courses/{id}")
    public Course getByID(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @Operation(summary = "Update a course by ID")
    @PutMapping(value = "/courses/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    public Course updateByID(@PathVariable Long id, @Valid @RequestBody UpdateCourseDTO updateCourseDTO) {
        return courseService.updateById(id, updateCourseDTO);
    }

    @Operation(summary = "Delete a course by ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/courses/{id}")
    public void deleteByID(@PathVariable Long id) {
        courseService.deleteById(id);
    }

    @Operation(summary = "Get all Courses of a Department with Department id")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/departments/{departmentID}/courses/all")
    public List<Course> findAllByDepartmentID(@PathVariable Long departmentID) {
        return courseService.findAllByDepartmentID(departmentID);
    }

    @Operation(summary = "Get many Courses of a Department with Department id with paginating and sorting options")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/departments/{departmentID}/courses")
    public Page<Course> findAllByDepartmentID(@PathVariable Long departmentID, @Parameter(name = "Pagination", hidden = true) Pageable pageable) {
        return courseService.findAllByDepartmentID(departmentID, pageable);
    }
}
