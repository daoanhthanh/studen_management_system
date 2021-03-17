package hanu.edu.ems.domains.User.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gender {

    @JsonProperty("Male")
    MALE,

    @JsonProperty("Female")
    FEMALE
}
