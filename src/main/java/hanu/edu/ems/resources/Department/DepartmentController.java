package hanu.edu.ems.resources.Department;

import hanu.edu.ems.resources.Department.entity.Department;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public Department create(@Valid @RequestBody Department course) {
        return departmentService.create(course);
    }

    @GetMapping
    public Page<Department> getMany(Pageable pageable) {
        return departmentService.getMany(pageable);
    }

    @PutMapping(value = "/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateByID(@PathVariable Long id, @Valid @RequestBody Department course) {
        departmentService.updateByID(id, course);
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public void deleteByID(@PathVariable Long id) {
        departmentService.deleteByID(id);
    }
}
