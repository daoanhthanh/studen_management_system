package hanu.edu.ems.domains.Department;

import hanu.edu.ems.base.CRUDController;
import hanu.edu.ems.domains.Department.dto.CreateDepartmentDTO;
import hanu.edu.ems.domains.Department.dto.UpdateDepartmentDTO;
import hanu.edu.ems.domains.Department.entity.Department;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Department Manager", description = "The Department API")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Operation(summary = "Create a new department")
    @PostMapping("/departments")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public Department create(@Valid @RequestBody CreateDepartmentDTO createDepartmentDTO) {
        return departmentService.create(createDepartmentDTO);
    }

    @Operation(summary = "Get departments with paginating and sorting options")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    @GetMapping(value = "/departments/all")
    public List<Department> findAll(@RequestParam(required = false) String keyword) {
        if (keyword != null)
            return departmentService.findByKeyword(keyword);
        else
            return departmentService.findAll();
    }

    @Operation(summary = "Get departments with paginating and sorting options")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    @GetMapping("/departments")
    @PageableAsQueryParam
    public Page<Department> findAll(@Parameter(name = "Pagination", hidden = true) Pageable pageable, @RequestParam(required = false) String keyword) {
        if (keyword != null)
            return departmentService.findAllByKeyword(keyword, pageable);
        else
            return departmentService.findAll(pageable);
    }

    @Operation(summary = "Get departments with paginating and sorting options")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    @GetMapping(value = "/departments/{id}")
    public Department getByID(@PathVariable Long id) {
        return departmentService.getById(id);
    }

    @Operation(summary = "Update a department by ID")
    @PutMapping(value = "/departments/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    public Department updateByID(@PathVariable Long id, @Valid @RequestBody UpdateDepartmentDTO updateDepartmentDTO) {
        return departmentService.updateById(id, updateDepartmentDTO);
    }

    @Operation(summary = "Delete a department by ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/departments/{id}")
    public void deleteByID(@PathVariable Long id) {
        departmentService.deleteById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Check if a Department's code has not been registered before in the system.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Something went wrong"),
        @ApiResponse(responseCode = "403", description = "Access denied"),
        @ApiResponse(responseCode = "404", description = "The username is not valid"),
        @ApiResponse(responseCode = "500", description = "Expired or invalid JWT token") })
    @GetMapping(value = "/Department/checkUniqueness/code/{code}")
    public boolean isPhoneNumberUnique(@PathVariable String code) {
        return departmentService.isCodeUnique(code);
    }
}
