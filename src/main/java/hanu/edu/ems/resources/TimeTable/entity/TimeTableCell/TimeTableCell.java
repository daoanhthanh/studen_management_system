package hanu.edu.ems.resources.TimeTable.entity.TimeTableCell;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TimeTableCell {

    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    public Integer getId() {
        return id;
    }
}
