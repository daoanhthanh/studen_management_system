package hanu.edu.ems.domains.Authority;

import hanu.edu.ems.domains.Authority.dto.CreateAuthorityDTO;
import hanu.edu.ems.domains.Authority.entity.Authority;
import hanu.edu.ems.domains.Student.StudentRepository;
import hanu.edu.ems.domains.Student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    private final StudentRepository studentRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository, StudentRepository studentRepository) {
        this.authorityRepository = authorityRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Authority create(CreateAuthorityDTO createAuthorityDTO) {
        Authority authority = Authority.builder().name(createAuthorityDTO.getName()).build();
        return authorityRepository.save(authority);
    }

    @Override
    public Authority updateById(Long id, CreateAuthorityDTO createAuthorityDTO) {
        Authority authority = authorityRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        authority.setName(createAuthorityDTO.getName());
        return authorityRepository.save(authority);
    }

    @Override
    public void deleteById(Long id) {
        authorityRepository.deleteById(id);
    }

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority getById(Long id) {
        return authorityRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Authority> findAll(Pageable pageable) {
        return authorityRepository.findAll(pageable);
    }

    @Override
    public boolean hasStudentAccess(Long currentUserID, Long studentID) {
        Student student = studentRepository.findById(studentID).orElseThrow(EntityNotFoundException::new);
        return student.getId().equals(currentUserID);
    }
}
