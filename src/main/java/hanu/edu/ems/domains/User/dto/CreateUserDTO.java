package hanu.edu.ems.domains.User.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanu.edu.ems.base.TimeStamps;
import hanu.edu.ems.domains.Authority.entity.AuthorityName;
import hanu.edu.ems.domains.User.entity.Gender;
import hanu.edu.ems.domains.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO implements TimeStamps {

    @Size(min = User.MIN_LENGTH_USERNAME, max = User.MAX_LENGTH_USERNAME)
    private String username;

    @Size(min = User.MIN_LENGTH_PASSWORD, max = User.MAX_LENGTH_PASSWORD)
    private String password;

    @Size(min = User.MIN_LENGTH_FIRST_NAME, max = User.MAX_LENGTH_FIRST_NAME)
    private String firstName;

    @Size(min = User.MIN_LENGTH_LAST_NAME, max = User.MAX_LENGTH_LAST_NAME)
    private String lastName;

    @Column(unique = true)
    @Email
    private String email;

    @Column(unique = true)
    @Size(min = User.MIN_LENGTH_PHONE_NUMBER, max = User.MAX_LENGTH_PHONE_NUMBER)
    private String phoneNumber;

    @NotNull
    private Gender gender;

    @PastOrPresent
    private LocalDate dob;

    private AuthorityName role;

    @JsonIgnore
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonIgnore
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}
