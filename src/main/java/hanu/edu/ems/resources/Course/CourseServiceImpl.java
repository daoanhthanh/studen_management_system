package hanu.edu.ems.resources.Course;

import hanu.edu.ems.resources.Course.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void updateByID(Long id, Course course) {
        course.setId(id);
        courseRepository.save(course);
    }

    @Override
    public void deleteByID(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course getByID(Long id) {
        return courseRepository.getOne(id);
    }

    @Override
    public Page<Course> getMany(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }
}
