package hanu.edu.ems.domains.Department;

import hanu.edu.ems.domains.Department.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void updateById(Long id, Department department) {
        department.setId(id);
        departmentRepository.save(department);
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Department> getMany(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }
}
