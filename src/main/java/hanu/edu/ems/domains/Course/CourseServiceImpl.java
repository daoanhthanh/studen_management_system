package hanu.edu.ems.domains.Course;

import hanu.edu.ems.domains.Course.dto.CreateCourseDTO;
import hanu.edu.ems.domains.Course.dto.UpdateCourseDTO;
import hanu.edu.ems.domains.Course.entity.Course;
import hanu.edu.ems.domains.CourseRelease.CourseReleaseRepository;
import hanu.edu.ems.domains.Department.DepartmentRepository;
import hanu.edu.ems.domains.Department.entity.Department;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CourseReleaseRepository courseReleaseRepository;

    private final DepartmentRepository departmentRepository;

    private ModelMapper modelMapper;

    @Override
    public Course create(CreateCourseDTO createCourseDTO) {
        Course course = modelMapper.map(createCourseDTO, Course.class);

        Department department = departmentRepository.findById(createCourseDTO.getDepartmentID())
                .orElseThrow(EntityNotFoundException::new);
        course.setDepartment(department);

        return courseRepository.save(course);
    }

    @Override
    public Course updateById(Long id, UpdateCourseDTO updateCourseDTO) {
        Course course = courseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(updateCourseDTO, course);
        Department department = departmentRepository.findById(updateCourseDTO.getDepartmentID())
                .orElseThrow(EntityNotFoundException::new);
        course.setDepartment(department);
        return courseRepository.save(course);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course getById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        setActiveCourseReleasesCount(course);
        return course;
    }

    @Override
    public Page<Course> findAll(Pageable pageable) {
        Page<Course> coursePage = courseRepository.findAll(pageable);
        setActiveCourseReleasesCount(coursePage.getContent());
        return coursePage;
    }

    private void setActiveCourseReleasesCount(Course course) {
        Integer releasesCount = courseReleaseRepository.countAllByCourseIdAndIsActiveIsTrue(course.getId());
        course.setActiveReleasesCount(releasesCount);
    }

    private void setActiveCourseReleasesCount(Collection<Course> courses) {
        for (Course course : courses)
            setActiveCourseReleasesCount(course);
    }

    @Override
    public List<Course> findAllByDepartmentID(Long departmentID) {
        List<Course> courses = courseRepository.findAllByDepartmentId(departmentID);
        setActiveCourseReleasesCount(courses);
        return courses;
    }

    @Override
    public Page<Course> findAllByDepartmentID(Long departmentID, Pageable pageable) {
        Page<Course> coursesPage = courseRepository.findAllByDepartmentId(departmentID, pageable);
        setActiveCourseReleasesCount(coursesPage.getContent());
        return coursesPage;
    }

    @Override
    public Page<Course> findAllByNameLike(String partialName, Pageable pageable) {
        Page<Course> coursePage = courseRepository.findAllByNameLike(partialName, pageable);
        setActiveCourseReleasesCount(coursePage.getContent());
        return coursePage;
    }

    @Override
    public List<Course> findAllByNameLike(String partialName) {
        List<Course> courses = courseRepository.findAllByNameLike(partialName);
        setActiveCourseReleasesCount(courses);
        return courses;
    }

    @Override
    public Boolean isRegistrationCodeUnique(String registrationCode) {
        return !courseRepository.existsByRegistrationCode(registrationCode);
    }
}
