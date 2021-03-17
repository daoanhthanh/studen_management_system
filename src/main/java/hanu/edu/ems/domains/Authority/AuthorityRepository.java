package hanu.edu.ems.domains.Authority;

import hanu.edu.ems.domains.Authority.entity.Authority;
import hanu.edu.ems.domains.Authority.entity.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByName(@NotNull AuthorityName name);

    @Modifying
    @Query(value = "INSERT INTO authorities(name) VALUES (:role_name)", nativeQuery = true)
    @Transactional
    void saveAuth(@Param("role_name") String roleName);
}
