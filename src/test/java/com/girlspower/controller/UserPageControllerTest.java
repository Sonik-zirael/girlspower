package com.girlspower.controller;

import com.girlspower.repos.UserRepository;
import com.girlspower.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-for-userpage-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
@Sql(value = {"/delete-after-userpage.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails("b")
public class UserPageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Добро пожаловать!")));
    }

    @Test
    public void testIfUserPageShowsUsername() throws Exception {
        this.mockMvc.perform(get("/cabinet"))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(model().attribute("username", is("B"))) ;
    }
}