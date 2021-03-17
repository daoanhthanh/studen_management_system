package hanu.edu.ems.domains.User;

import hanu.edu.ems.base.CRUDService;
import hanu.edu.ems.domains.User.dto.CreateUserDTO;
import hanu.edu.ems.domains.User.dto.UpdateUserDTO;
import hanu.edu.ems.domains.User.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends CRUDService<User, Long, CreateUserDTO, UpdateUserDTO>, UserDetailsService {
    boolean isUsernameUnique(String username);

    boolean isEmailUnique(String email);

    boolean isPhoneNumberUnique(String phoneNumber);

    void changePassword(String oldPassword, String newPassword);
}
