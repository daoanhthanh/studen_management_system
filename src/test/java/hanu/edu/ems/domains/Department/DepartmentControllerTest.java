//package hanu.edu.ems.domains.Department;
//
//import hanu.edu.ems.base.CRUDController;
//import hanu.edu.ems.domains.Department.dto.CreateDepartmentDTO;
//import hanu.edu.ems.domains.Department.dto.UpdateDepartmentDTO;
//import hanu.edu.ems.domains.Department.entity.Department;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springdoc.core.converters.models.PageableAsQueryParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import java.util.List;
//
//public class DepartmentControllerTest {
//
//    private final DepartmentService departmentService;
//
//    @Autowired
//    public DepartmentControllerTest(DepartmentService departmentService) {
//        this.departmentService = departmentService;
//    }
//}