package hanu.edu.ems.domains.Student;

import hanu.edu.ems.domains.Student.dto.CreateStudentDTO;
import hanu.edu.ems.domains.Student.dto.UpdateStudentDTO;
import hanu.edu.ems.domains.Student.entity.Student;
import hanu.edu.ems.domains.User.entity.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class StudentDataSample {

    private static final Student minh = Student.builder()
        .id(11L)
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
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();

    private static final Student duong = Student.builder()
        .id(12L)
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
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();

    private static final Student thanh = Student.builder()
        .id(13L)
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
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();

    private static final CreateStudentDTO minhDTO = CreateStudentDTO.builder()
        .username("1801040147")
        .password("1801040147")
        .gender(Gender.MALE)
        .firstName("Minh")
        .lastName("Tang Ba")
        .email("1801040147@s.hanu.edu.vn")
        .phoneNumber("0904842088")
        .dob(LocalDate.of(2000, 12, 4))
        .fatherName("Tang Ba Hoang")
        .motherName("Nguyen Thai Ha")
        .fullAddress("Thanh Xuan, Hanoi")
        .sinceYear(2018)
        .build();

    private static final CreateStudentDTO duongDTO = CreateStudentDTO.builder()
        .username("1801040052")
        .password("1801040052")
        .gender(Gender.FEMALE)
        .firstName("Duong")
        .lastName("Nguyen Thi Thuy")
        .email("1801040052@s.hanu.edu.vn")
        .phoneNumber("0904842092")
        .dob(LocalDate.of(2000, 8, 8))
        .fatherName("Duong Father's Name")
        .motherName("Duong Mother's Name")
        .fullAddress("Quynh Coi, Thai Binh")
        .sinceYear(2018)
        .build();

    private static final CreateStudentDTO thanhDTO = CreateStudentDTO.builder()
        .username("1801040000")
        .password("1801040000")
        .gender(Gender.MALE)
        .firstName("Thanh")
        .lastName("Dao Anh")
        .email("1801040000@s.hanu.edu.vn")
        .phoneNumber("0904842000")
        .dob(LocalDate.of(2000, 12, 4))
        .fatherName("Thanh Father's Name")
        .motherName("Thanh Mother's Name")
        .fullAddress("Thanh's Full Address")
        .sinceYear(2018)
        .build();

    private static final UpdateStudentDTO updateDTO = UpdateStudentDTO.builder()
        .gender(Gender.MALE)
        .firstName("Thanh")
        .lastName("Dao Anh")
        .email("1801040000@s.hanu.edu.vn")
        .phoneNumber("0904842000")
        .dob(LocalDate.of(2000, 12, 4))
        .fatherName("Thanh Father's Name")
        .motherName("Thanh Mother's Name")
        .fullAddress("Thanh's Full Address")
        .sinceYear(2018)
        .build();

    public static Student getExampleValidStudent() {
        return minh.toBuilder().build();
    }

    public static CreateStudentDTO getExampleValidCreateStudentDTO() {
        return minhDTO.toBuilder().build();
    }

    public static UpdateStudentDTO getExampleValidUpdateStudentDTO() {
        return updateDTO.toBuilder().build();
    }

    public static List<Student> get3Students() {
        return Arrays.asList(minh.toBuilder().build(), duong.toBuilder().build(), thanh.toBuilder().build());
    }

    public static List<CreateStudentDTO> get3CreateStudentDTOs() {
        return Arrays.asList(minhDTO.toBuilder().build(), duongDTO.toBuilder().build(), thanhDTO.toBuilder().build());
    }
}
