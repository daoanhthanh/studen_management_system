package hanu.edu.ems.base;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Association<E, T extends JpaRepository<?, ?>> {

}
