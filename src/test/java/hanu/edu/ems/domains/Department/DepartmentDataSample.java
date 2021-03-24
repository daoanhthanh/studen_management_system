package hanu.edu.ems.domains.Department;

import hanu.edu.ems.domains.Department.entity.Department;

public class DepartmentDataSample {
    private static final Department fit = Department.builder().build();

    public static Department getOne() {
        return fit;
    }
}
