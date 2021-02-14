package hanu.edu.ems.domains.TimeTable.entity;

import hanu.edu.ems.base.TimeStamps;
import hanu.edu.ems.domains.TimeTable.entity.TimeTableCell.TimetableCell;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "timetables")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timetable implements TimeStamps {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "timetable", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<TimetableCell> timetableCells;

    @PastOrPresent
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime updatedAt;
}
