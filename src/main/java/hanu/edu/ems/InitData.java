package hanu.edu.ems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import hanu.edu.ems.domains.Authority.AuthorityService;
import hanu.edu.ems.domains.Authority.dto.CreateAuthorityDTO;
import hanu.edu.ems.domains.Authority.entity.AuthorityName;
import hanu.edu.ems.domains.Course.CourseService;
import hanu.edu.ems.domains.Course.dto.CreateCourseDTO;
import hanu.edu.ems.domains.Course.entity.Course;
import hanu.edu.ems.domains.CourseRelease.CourseReleaseService;
import hanu.edu.ems.domains.CourseRelease.dto.CreateCourseReleaseDTO;
import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import hanu.edu.ems.domains.CourseRelease.entity.Season;
import hanu.edu.ems.domains.Department.DepartmentService;
import hanu.edu.ems.domains.Department.dto.CreateDepartmentDTO;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Enrollment.EnrollmentService;
import hanu.edu.ems.domains.Enrollment.dto.CreateEnrollmentDTO;
import hanu.edu.ems.domains.Student.StudentService;
import hanu.edu.ems.domains.Student.dto.CreateStudentDTO;
import hanu.edu.ems.domains.Student.entity.Student;
import hanu.edu.ems.domains.Teacher.TeacherService;
import hanu.edu.ems.domains.Teacher.dto.CreateTeacherDTO;
import hanu.edu.ems.domains.Teacher.entity.Teacher;
import hanu.edu.ems.domains.Timetable.TimetableService;
import hanu.edu.ems.domains.Timetable.dto.CreateTimetableDTO;
import hanu.edu.ems.domains.Timetable.dto.TimetableCellDTO;
import hanu.edu.ems.domains.Timetable.entity.Timetable;
import hanu.edu.ems.domains.User.UserService;
import hanu.edu.ems.domains.User.dto.CreateUserDTO;
import hanu.edu.ems.domains.User.entity.Gender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class InitData {
    private final AuthorityService authorityService;

    private final CourseService courseService;

    private final CourseReleaseService courseReleaseService;

    private final DepartmentService departmentService;

    private final EnrollmentService enrollmentService;

    private final StudentService studentService;

    private final TeacherService teacherService;

    private final TimetableService timetableService;

    private final UserService userService;

    @Autowired
    public InitData(
        AuthorityService authorityService,
        CourseService courseService,
        CourseReleaseService courseReleaseService,
        DepartmentService departmentService,
        EnrollmentService enrollmentService,
        StudentService studentService,
        TeacherService teacherService,
        TimetableService timetableService,
        UserService userService) {
        this.authorityService = authorityService;
        this.courseService = courseService;
        this.courseReleaseService = courseReleaseService;
        this.departmentService = departmentService;
        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.timetableService = timetableService;
        this.userService = userService;
    }

    static List<List<String>> parse(String fileName) {
        List<List<String>> records = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(new ClassPathResource(fileName).getInputStream()))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public void init() {
        if (userService.findAll().isEmpty()) {

            String username = "admin";
            String password = "sqa@2021";

            CreateAuthorityDTO adminAuthorityDTO = new CreateAuthorityDTO(AuthorityName.ADMIN);
            CreateAuthorityDTO studentAuthorityDTO = new CreateAuthorityDTO(AuthorityName.STUDENT);
            CreateAuthorityDTO teacherAuthorityDTO = new CreateAuthorityDTO(AuthorityName.TEACHER);

            authorityService.create(adminAuthorityDTO);
            authorityService.create(studentAuthorityDTO);
            authorityService.create(teacherAuthorityDTO);

            CreateUserDTO createUserDTO =
                CreateUserDTO.builder()
                    .username(username)
                    .password(password)
                    .gender(Gender.MALE)
                    .firstName("Admin")
                    .lastName("SQA 2021")
                    .email("minhtb.4c18@s.hanu.edu.vn")
                    .phoneNumber("0969696969")
                    .role(AuthorityName.ADMIN)
                    .dob(LocalDate.of(2000, 12, 4))
                    .build();

            userService.create(createUserDTO);

//            testData();
        } else {
            log.info("Data has been previously initialized, ignoring InitData");
            log.info("If you want to re-initialize the data, reset the database ems (by drop & create database ems; again), and restart the application");
        }
    }

    public void testData() {

        List<CreateDepartmentDTO> createDepartmentDTOList = createDepartmentDTOList();

        List<Department> departmentList = testDataDepartment(createDepartmentDTOList);

        List<CreateStudentDTO> createStudentDTOList = createStudentDTOList();

        List<Student> studentList = testDataStudent(createStudentDTOList);

        List<CreateTeacherDTO> createTeacherDTOList = createTeacherDTOList();

        List<Teacher> teacherList = testDataTeacher(createTeacherDTOList);

        CreateCourseDTO createCourseDTO =
            CreateCourseDTO.builder()
                .name("Data Structure and Algorithm")
                // One course belongs to ong department
                .departmentID(departmentList.get(0).getId())
                .numberOfCredits(3)
                .registrationCode("FIT2AIN")
                .requiredSchoolYear(2)
                .build();

        Course course = courseService.create(createCourseDTO);

        TimetableCellDTO timetableCellDTO =
            TimetableCellDTO.builder().sessionLength(4).sessionStartIndex(0).build();

        CreateTimetableDTO createTimetableDTO =
            CreateTimetableDTO.builder().cells(Collections.singletonList(timetableCellDTO)).build();

        Timetable timetable = timetableService.create(createTimetableDTO);

        CreateCourseReleaseDTO createCourseReleaseDTO =
            CreateCourseReleaseDTO.builder()
                .season(Season.FALL)
                .startDate(LocalDate.of(2020, 6, 1))
                .endDate(LocalDate.of(2021, 12, 1))
                .isActive(true)
                // One course release belongs to one course
                .courseID(course.getId())
                // One course release is assigned to one teacher
                .teacherID(teacherList.get(0).getId())
                // One course release has one timetable
                .timetableID(timetable.getId())
                .releaseYear(2021)
                .build();

        CourseRelease courseRelease = courseReleaseService.create(createCourseReleaseDTO);

        CreateEnrollmentDTO createEnrollmentDTO =
            CreateEnrollmentDTO.builder()
                .attendanceMark(10.0f)
                .midtermMark(10.0f)
                .finalMark(10.0f)
                // One enrollment is mapped to one course release
                .courseReleaseID(courseRelease.getId())
                // One enrollment belongs to one student
                .studentID(studentList.get(0).getId())
                .build();

        enrollmentService.create(createEnrollmentDTO);

        log.info("Data Initialization on First Server Boot Completed Successfully!");
    }

    List<Department> testDataDepartment(List<CreateDepartmentDTO> createDepartmentDTOList) {

        List<Department> ret = new ArrayList<>();

        for (CreateDepartmentDTO createDepartmentDTO : createDepartmentDTOList) {
            Department department = departmentService.create(createDepartmentDTO);
            ret.add(department);
        }
        return ret;
    }

    List<CreateDepartmentDTO> createDepartmentDTOList() {
        List<List<String>> rows = parse("static/faculties_parsed.csv");

        List<CreateDepartmentDTO> createDepartmentDTOs = new ArrayList<>();

        for (List<String> row : rows) {
//            int id = Integer.parseInt(row.get(0));
            String name = row.get(1);
            String code = row.get(2);
            CreateDepartmentDTO createDepartmentDTO =
                CreateDepartmentDTO.builder().name(name).code(code).build();

            createDepartmentDTOs.add(createDepartmentDTO);
        }

        return createDepartmentDTOs;
    }

    List<Student> testDataStudent(List<CreateStudentDTO> createStudentDTOList) {
        log.info("Inserting " + createStudentDTOList.size() + " student records to database...");
        return studentService.createManyStudents(createStudentDTOList);
    }

    List<CreateStudentDTO> createStudentDTOList() {
        log.info("Parsing approximately 2500 student rows...");
        List<List<String>> rows = parse("static/students_parsed.csv");

        log.info("Parsing completed, begin to convert...");
        List<CreateStudentDTO> createStudentDTOList = new ArrayList<>();

        int count = 0;
        for (List<String> row: rows) {
            long id = Long.parseLong(row.get(0));
            String name = row.get(1);
            Gender gender = row.get(2).equals("Nam") ? Gender.MALE : Gender.FEMALE;
//            String sClass = row.get(3).replaceFirst("\\(.+\\)", "");
            Long departmentID = Long.parseLong(row.get(4));
//            String startEnd = row.get(5);
//            Long supervisorID = Long.parseLong(row.get(6));

            // Parse
            int birthYear = id > 1900_00_0000L ? 2001 : id > 1800_00_0000L ? 2000 : 1999;
            Random random = new Random();

            int randomMonth = random.nextInt(12) + 1;
            int randomDay = random.nextInt(28) + 1;

            int spaceIndex = name.lastIndexOf(' ');
            String firstName = name.substring(spaceIndex + 1);
            String lastName = name.substring(0, spaceIndex);

            CreateStudentDTO createStudentDTO = CreateStudentDTO.builder()
                .departmentID(departmentID)
                .email(id + "@s.hanu.edu.vn")
                .dob(LocalDate.of(birthYear, randomMonth, randomDay))
                .firstName(firstName)
                .lastName(lastName)
                .gender(gender)
                .fatherName(firstName + " Father's Name")
                .motherName(firstName + " Mother's Name")
                .fullAddress(name + "'s Full Address")
                .sinceYear(birthYear + 18)
                .phoneNumber(Long.toString(id))
                .username(Long.toString(id))
                .password(Long.toString(id))
                .build();

            createStudentDTOList.add(createStudentDTO);

            count++;
            if (count % 100 == 0) {
                log.debug("Done " + count + "/" + rows.size());
            }
        }
        log.info("Done converting students data");
        return createStudentDTOList;
    }

    List<Teacher> testDataTeacher(List<CreateTeacherDTO> createTeacherDTOList) {
        List<Teacher> ret = new ArrayList<>();

        for (CreateTeacherDTO createTeacherDTO: createTeacherDTOList) {
            Teacher teacher = teacherService.create(createTeacherDTO);
            ret.add(teacher);
        }

        return ret;
    }

    List<CreateTeacherDTO> createTeacherDTOList() {
        List<List<String>> rows = parse("static/teachers_parsed.csv");
        List<CreateTeacherDTO> ret = new ArrayList<>();

        for (List<String> row: rows) {

//            Long id = Long.parseLong(row.get(0));
            String name = row.get(1);
            String phoneNumber = row.get(2);
            String email = row.get(3);
            Long departmentID = Long.parseLong(row.get(4));

            String code = name.substring(name.indexOf('(') + 1, name.indexOf(')'));
            name = name.replace("\\(.+\\)", "");

            int lastSpaceIndex = name.lastIndexOf(' ');
            String firstName = name.substring(lastSpaceIndex + 1);
            String lastName = name.substring(0, lastSpaceIndex);

            Random random = new Random();
            int randomMonth = random.nextInt(12) + 1;
            int randomDay = random.nextInt(28) + 1;
            int randomYear = 1960 + random.nextInt(30);

            Gender gender = random.nextInt() % 2 == 0 ? Gender.MALE : Gender.FEMALE;

            CreateTeacherDTO createTeacherDTO = CreateTeacherDTO.builder()
                .performanceRating(10.0F)
                .username(code)
                .password(code)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .gender(gender)
                // One teacher is in one department
                .departmentID(departmentID)
                .dob(LocalDate.of(randomYear, randomMonth, randomDay))
                .build();

            ret.add(createTeacherDTO);
        }
        return ret;
    }
}
