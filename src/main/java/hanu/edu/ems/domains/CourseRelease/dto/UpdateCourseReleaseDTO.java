package hanu.edu.ems.domains.CourseRelease.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import hanu.edu.ems.domains.CourseRelease.entity.Season;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCourseReleaseDTO {

    private Long teacherID;

    private Long courseID;

    private Season season;

    @Min(CourseRelease.MIN_RELEASE_YEAR)
    @Max(CourseRelease.MAX_RELEASE_YEAR)
    private Integer releaseYear;

    private Long timetableID;

    @JsonIgnore
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}
