package hanu.edu.ems.domains.TimeTable.entity.TimeTableCell;

import hanu.edu.ems.domains.TimeTable.entity.Timetable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "timetable_cells")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimetableCell {

    public static final int MIN_SESSION_START_INDEX = 0;
    public static final int MAX_SESSION_START_INDEX = 55;

    public static final int MIN_SESSION_LENGTH = 1;
    public static final int MAX_SESSION_LENGTH = 8;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Min(MIN_SESSION_START_INDEX)
    @Max(MAX_SESSION_START_INDEX)
    private Integer sessionStartIndex;

    @Min(MIN_SESSION_LENGTH)
    @Max(MAX_SESSION_LENGTH)
    private Integer sessionLength;

    @ManyToOne
    @JoinColumn(name = "timetable_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Timetable timetable;
}