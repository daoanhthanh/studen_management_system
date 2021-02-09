package hanu.edu.ems.resources.Course;

import hanu.edu.ems.resources.Course.entity.Course;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public Course create(@Valid @RequestBody Course course) {
        return courseService.create(course);
    }

    @GetMapping
    public Page<Course> getMany(Pageable pageable) {
        return courseService.getMany(pageable);
    }

    @PutMapping(value = "/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateByID(@PathVariable Long id, @Valid @RequestBody Course course) {
        courseService.updateByID(id, course);
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public void deleteByID(@PathVariable Long id) {
        courseService.deleteByID(id);
    }
}
