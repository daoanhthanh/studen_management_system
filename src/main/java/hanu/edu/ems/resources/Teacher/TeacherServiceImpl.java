package hanu.edu.ems.resources.Teacher;

import hanu.edu.ems.resources.Teacher.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher create(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void updateByID(Long id, Teacher teacher) {
        teacher.setId(id);
        teacherRepository.save(teacher);
    }

    @Override
    public void deleteByID(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getByID(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Teacher> getMany(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }
}
