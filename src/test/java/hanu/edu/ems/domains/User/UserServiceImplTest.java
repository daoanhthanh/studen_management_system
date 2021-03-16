//package hanu.edu.ems.domains.User;
//
//import hanu.edu.ems.domains.Authority.AuthorityRepositoryTest;
//import hanu.edu.ems.domains.Authority.entity.Authority;
//import hanu.edu.ems.domains.User.dto.CreateUserDTO;
//import hanu.edu.ems.domains.User.dto.UpdateUserDTO;
//import hanu.edu.ems.domains.User.entity.User;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.Collections;
//import java.util.List;
//
//public class UserServiceImplTest {
//
//    private UserRepositoryTest userRepositoryTest;
//
//    private AuthenticationManager authenticationManager;
//
//    private final AuthorityRepositoryTest authorityRepositoryTest;
//
//    private final ModelMapper modelMapper;
//
//    @Autowired
//    public UserServiceImplTest(AuthorityRepositoryTest authorityRepositoryTest, ModelMapper modelMapper) {
//        this.authorityRepositoryTest = authorityRepositoryTest;
//        this.modelMapper = modelMapper;
//    }
//
//    @Autowired
//    public void setUserRepository(UserRepositoryTest userRepositoryTest) {
//        this.userRepositoryTest = userRepositoryTest;
//    }
//
//    @Autowired
//    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//}
