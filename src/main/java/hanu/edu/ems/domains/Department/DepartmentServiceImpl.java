package hanu.edu.ems.domains.Department;

import hanu.edu.ems.domains.Department.dto.CreateDepartmentDTO;
import hanu.edu.ems.domains.Department.dto.UpdateDepartmentDTO;
import hanu.edu.ems.domains.Department.entity.Department;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Department create(CreateDepartmentDTO createDepartmentDTO) {
        Department department = modelMapper.map(createDepartmentDTO, Department.class);
        return departmentRepository.save(department);
    }

    @Override
    public Department updateById(Long id, UpdateDepartmentDTO updateDepartmentDTO) {

        Department department = departmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(updateDepartmentDTO, department);
        return departmentRepository.save(department);
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Department> findAll(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    @Override
    public boolean isCodeUnique(String code) {
        return departmentRepository.existsByCode(code);
    }

    @Override
    public Page<Department> findAllByKeyword(String keyword, Pageable pageable) {
        return departmentRepository.findAllByNameLike(keyword, pageable);
    }

    @Override
    public List<Department> findByKeyword(String keyword) {
        return departmentRepository.findAllByNameLike(keyword);
    }
}
