package hanu.edu.ems.domains.Enrollment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanu.edu.ems.base.TimeStamps;
import hanu.edu.ems.domains.Enrollment.entity.Enrollment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateEnrollmentDTO implements TimeStamps {

    @NotNull
    private Long studentID;

    @NotNull
    private Long courseReleaseID;

    @DecimalMin(Enrollment.MIN_ATTENDANCE_MARK)
    @DecimalMax(Enrollment.MAX_ATTENDANCE_MARK)
    private Float attendanceMark;

    @DecimalMin(Enrollment.MIN_MIDTERM_MARK)
    @DecimalMax(Enrollment.MAX_MIDTERM_MARK)
    private Float midtermMark;

    @DecimalMin(Enrollment.MIN_FINAL_MARK)
    @DecimalMax(Enrollment.MAX_FINAL_MARK)
    private Float finalMark;

    @JsonIgnore
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonIgnore
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}
