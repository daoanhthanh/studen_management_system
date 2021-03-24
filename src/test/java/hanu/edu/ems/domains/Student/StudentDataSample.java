package hanu.edu.ems.domains.Student;

import hanu.edu.ems.domains.Department.DepartmentDataSample;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Student.entity.Student;
import hanu.edu.ems.domains.User.entity.Gender;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class StudentDataSample {

    private static final Department department = DepartmentDataSample.getOne();

    private static final Student minh = Student.builder()
        .email("1801040147@s.hanu.edu.vn")
        .dob(LocalDate.of(2000, 12, 4))
        .firstName("Minh")
        .lastName("Tang Ba")
        .gender(Gender.MALE)
        .fatherName("Tang Ba Hoang")
        .motherName("Nguyen Thai Ha")
        .fullAddress("Thanh Xuan, Hanoi")
        .sinceYear(2018)
        .phoneNumber("0904842088")
        .username("1801040147")
        .password("1801040147")
        .build();

    private static final Student duong = Student.builder()
        .email("1801040052@s.hanu.edu.vn")
        .dob(LocalDate.of(2000, 8, 8))
        .firstName("Duong")
        .lastName("Nguyen Thi Thuy")
        .gender(Gender.FEMALE)
        .fatherName("Duong's Father Name")
        .motherName("Duong's Mother Name")
        .fullAddress("Quynh Coi, Thai Binh")
        .sinceYear(2018)
        .phoneNumber("0969696960")
        .username("1801040052")
        .password("1801040052")
        .build();

    private static final Student thanh = Student.builder()
        .email("1801040000@s.hanu.edu.vn")
        .dob(LocalDate.of(2000, 12, 4))
        .firstName("Thanh")
        .lastName("Dao Anh")
        .gender(Gender.MALE)
        .fatherName("Thanh's Father Name")
        .motherName("Thanh's Mother Name")
        .fullAddress("Thanh Xuan, Hanoi")
        .sinceYear(2018)
        .phoneNumber("0969696968")
        .username("1801040000")
        .password("1801040000")
        .build();

    public static Student getExampleStudent() {
        return minh;
    }

    public static List<Student> get3() {
        return Arrays.asList(minh, duong, thanh);
    }
}
