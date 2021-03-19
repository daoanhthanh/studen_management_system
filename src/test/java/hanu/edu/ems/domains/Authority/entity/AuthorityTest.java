package hanu.edu.ems.domains.Authority.entity;

import org.junit.jupiter.api.Test;

public class AuthorityTest {
    Authority authority = new Authority(1L, AuthorityName.STUDENT);

    @Test
    void testSetId() {
        authority.setId(1L);
    }

    @Test
    void testSetName() {
        authority.setName(AuthorityName.STUDENT);
    }
}

