package hanu.edu.ems.domains.CourseRelease.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanu.edu.ems.base.TimeStamps;
import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import hanu.edu.ems.domains.CourseRelease.entity.Season;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCourseReleaseDTO implements TimeStamps{

    @NotNull
    private Long teacherID;

    @NotNull
    private Long teacherID;

    @NotNull
    private Long courseID;

    @NotNull
    private Long timetableID;

    @NotNull
    private Season season;

    @NotNull
    private Boolean isActive;

    @Min(CourseRelease.MIN_RELEASE_YEAR)
    @Max(CourseRelease.MAX_RELEASE_YEAR)
    private Integer releaseYear;

    private LocalDate startDate;

    private LocalDate endDate;

    @JsonIgnore
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonIgnore
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}
