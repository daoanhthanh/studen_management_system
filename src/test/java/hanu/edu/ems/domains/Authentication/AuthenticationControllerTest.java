package hanu.edu.ems.domains.Authentication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.edu.ems.domains.Authentication.dto.LoginRequestDTO;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldReturnToken_whenLoginWithCorrectCredentials() throws Exception {
        LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder()
            .username("admin")
            .password("sqa@2021")
            .build();

        this.mockMvc.perform(
            post("/auth/login")
                .content(asJsonString(loginRequestDTO))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
            .andDo(print());
    }

    @Test
    public void shouldReturnStatus400_whenLoginWithFalseCredentials() throws Exception {
        LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder()
            .username("admin")
            .password("")
            .build();

        this.mockMvc.perform(
            post("/auth/login")
                .content(asJsonString(loginRequestDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andDo(print());
    }
}