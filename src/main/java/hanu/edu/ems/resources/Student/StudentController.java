package hanu.edu.ems.resources.Student;

import java.util.List;

import hanu.edu.ems.resources.Student.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /*  It saves object into database. The @ModelAttribute puts request data
     *  into model object. You need to mention RequestMethod.POST method
     *  because default request is GET*/
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${AdminController.newYear}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "The new year cannot created"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")
    })
    public Student create(@ModelAttribute("student") Student student) {
        return studentService.create(student);
    }

    /* It provides list of employees in model object */
    @RequestMapping
    public List<Student> getAll() {
        return studentService.getAll();
    }

    /* It displays object data into form for the given id.
     * The @PathVariable puts URL data into variable.*/
    @RequestMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateByID(@PathVariable long id, @ModelAttribute("student") Student student) {
        studentService.updateByID(id, student);
    }

    /* It deletes record for the given id in URL and redirects to /viewemp */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void deleteByID(@PathVariable long id) {
        studentService.deleteByID(id);
    }
}
