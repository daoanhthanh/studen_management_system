package hanu.edu.ems.domains.Authentication;

import hanu.edu.ems.config.auth.JwtUtil;
import hanu.edu.ems.domains.Authentication.dto.ChangePasswordDTO;
import hanu.edu.ems.domains.Authentication.dto.LoginRequestDTO;
import hanu.edu.ems.domains.User.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Manager", description = "The Authentication API")
@Slf4j
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value = "/login")
    public String authenticate(@Valid @RequestBody LoginRequestDTO loginRequestDTO) throws BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        final UserDetails userDetails = userService.loadUserByUsername(loginRequestDTO.getUsername());
        log.info("UserDetails: ");
        log.info(userDetails.toString());
        return jwtUtil.generateToken(userDetails);
    }

    @Operation(summary = "Change the password for the current logged in user")
    @PostMapping(value = "/changePassword")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        String oldPassword = changePasswordDTO.getOldPassword();
        String newPassword = changePasswordDTO.getNewPassword();
        userService.changePassword(oldPassword, newPassword);
        return ResponseEntity.accepted().body(true);
    }

    @Operation(summary = "Get current logged in User")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Something went wrong"),
        @ApiResponse(responseCode = "403", description = "Access denied"),
        @ApiResponse(responseCode = "404", description = "Current user not initialized."),
        @ApiResponse(responseCode = "500", description = "Expired or invalid JWT token")})
    @GetMapping("/whoami")
    public ResponseEntity<Object> currentUser() {
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
}
