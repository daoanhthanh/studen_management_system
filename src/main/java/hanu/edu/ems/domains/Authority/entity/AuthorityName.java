package hanu.edu.ems.domains.Authority.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AuthorityName {
    @JsonProperty("Student")
    STUDENT,

    @JsonProperty("Admin")
    ADMIN,

    @JsonProperty("Teacher")
    TEACHER
}
