package hanu.edu.ems.domains.User;

import hanu.edu.ems.domains.User.dto.UpdateUserDTO;
import hanu.edu.ems.domains.User.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * The {@link UserController} contains all rest api function that need to
 * manage the application users.
 *
 * @see UserService
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "User Manager", description = "The User API")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Get all Users.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Something went wrong"),
        @ApiResponse(responseCode = "403", description = "Access denied"),
        @ApiResponse(responseCode = "404", description = "The users don't exists"),
        @ApiResponse(responseCode = "500", description = "Invalid JWT token")})
    @GetMapping(value = "/users/all")
    public List<User> getAll() {
        return userService.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Get all Users.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Something went wrong"),
        @ApiResponse(responseCode = "403", description = "Access denied"),
        @ApiResponse(responseCode = "404", description = "The users don't exists"),
        @ApiResponse(responseCode = "500", description = "Invalid JWT token")})
    @GetMapping(value = "/users")
    public Page<User> getAll(@Parameter(name = "Pagination", hidden = true) Pageable pageable) {
        return userService.findAll(pageable);
    }

    @PreAuthorize("hasAuthority('ADMIN') " +
        "or @authorityServiceImpl.hasStudentAccess(principal.id, #id)")
    @Operation(summary = "Get User by ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Something went wrong"),
        @ApiResponse(responseCode = "403", description = "Access denied"),
        @ApiResponse(responseCode = "404", description = "The user doesn't found"),
        @ApiResponse(responseCode = "500", description = "Expired or invalid JWT token")})
    @GetMapping(value = "/users/{id}")
    public User getByID(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Check if an User's username has not been registered before in the system.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Something went wrong"),
        @ApiResponse(responseCode = "403", description = "Access denied"),
        @ApiResponse(responseCode = "404", description = "The username is not valid"),
        @ApiResponse(responseCode = "500", description = "Expired or invalid JWT token")})
    @GetMapping(value = "/User/checkUniqueness/username/{username}")
    public boolean isUsernameUnique(@PathVariable String username) {
        return userService.isUsernameUnique(username);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Check if an User's email has not been registered before in the system.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Something went wrong"),
        @ApiResponse(responseCode = "403", description = "Access denied"),
        @ApiResponse(responseCode = "404", description = "The username is not valid"),
        @ApiResponse(responseCode = "500", description = "Expired or invalid JWT token")})
    @GetMapping(value = "/User/checkUniqueness/email/{email}")
    public boolean isEmailUnique(@PathVariable String email) {
        return userService.isEmailUnique(email);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Check if an User's phone number has not been registered before inthe system.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Something went wrong"),
        @ApiResponse(responseCode = "403", description = "Access denied"),
        @ApiResponse(responseCode = "404", description = "The username is not valid"),
        @ApiResponse(responseCode = "500", description = "Expired or invalid JWT token")})
    @GetMapping(value = "/User/checkUniqueness/phoneNumber/{phoneNumber}")
    public boolean isPhoneNumberUnique(@PathVariable String phoneNumber) {
        return userService.isPhoneNumberUnique(phoneNumber);
    }

    @PreAuthorize("hasAuthority('ADMIN') or principal.id == #id")
    @Operation(summary = "Update an User by ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Something went wrong"),
        @ApiResponse(responseCode = "403", description = "Access denied"),
        @ApiResponse(responseCode = "404", description = "The user doesn't found"),
        @ApiResponse(responseCode = "500", description = "Expired or invalid JWT token")})
    @PutMapping(value = "/users/{id}")
    public User updateByID(@PathVariable Long id, @RequestBody UpdateUserDTO updateUserDTO) {
        return userService.updateById(id, updateUserDTO);
    }
}
