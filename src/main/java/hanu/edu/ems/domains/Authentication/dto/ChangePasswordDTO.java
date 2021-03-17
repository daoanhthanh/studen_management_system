package hanu.edu.ems.domains.Authentication.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangePasswordDTO {

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;
}
