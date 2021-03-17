package hanu.edu.ems.domains.Authentication.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginRequestDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
