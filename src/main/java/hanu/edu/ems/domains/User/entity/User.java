package hanu.edu.ems.domains.User.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import hanu.edu.ems.base.TimeStamps;
import hanu.edu.ems.domains.Authority.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EntityListeners(UserEntityListener.class)
public class User implements UserDetails, Serializable, TimeStamps {

    public static final int MIN_LENGTH_FIRST_NAME = 1;
    public static final int MAX_LENGTH_FIRST_NAME = 50;

    public static final int MIN_LENGTH_LAST_NAME = 1;
    public static final int MAX_LENGTH_LAST_NAME = 50;

    public static final int MIN_LENGTH_USERNAME = 4;
    public static final int MAX_LENGTH_USERNAME = 25;

    public static final int MIN_LENGTH_PASSWORD = 8;
    public static final int MAX_LENGTH_PASSWORD = 100;

    public static final int MIN_LENGTH_PHONE_NUMBER = 9;
    public static final int MAX_LENGTH_PHONE_NUMBER = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = MIN_LENGTH_USERNAME, max = MAX_LENGTH_USERNAME)
    private String username;

    @NotNull
    @Size(min = MIN_LENGTH_PASSWORD, max = MAX_LENGTH_PASSWORD)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Size(min = MIN_LENGTH_FIRST_NAME, max = MAX_LENGTH_FIRST_NAME)
    private String firstName;

    @Size(min = MIN_LENGTH_LAST_NAME, max = MAX_LENGTH_LAST_NAME)
    private String lastName;

    @NotNull
    private Gender gender;

    @Column(unique = true)
    @Email
    private String email;

    @Column(unique = true)
    @Size(min = MIN_LENGTH_PHONE_NUMBER, max = MAX_LENGTH_PHONE_NUMBER)
    private String phoneNumber;

    @PastOrPresent
    private LocalDate dob;

    @Singular
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;

    @PastOrPresent
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime updatedAt;

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
}
