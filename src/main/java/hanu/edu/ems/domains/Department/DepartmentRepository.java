package hanu.edu.ems.domains.Department;

import hanu.edu.ems.domains.Department.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Size;

import java.util.List;

import static hanu.edu.ems.domains.Department.entity.Department.MAX_LENGTH_CODE;
import static hanu.edu.ems.domains.Department.entity.Department.MIN_LENGTH_CODE;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByCode(@Size(min = MIN_LENGTH_CODE, max = MAX_LENGTH_CODE) String code);

    Page<Department> findAllByNameLike(String partialName, Pageable pageable);

    List<Department> findAllByNameLike(String partialName);
}
