package com.zakedy.rest;

import com.zakedy.domain.Authority;
import com.zakedy.domain.User;
import com.zakedy.service.DefaultUserDetailsService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorizationController {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DefaultUserDetailsService userDetailsService;

    @Test
    public void test() throws Exception {
        User user = userDetailsService.createUser(
                new User().setUsername("user1")
                        .setAuthority(Authority.USER)
                        .setPassword("$2a$10$W.yqfmnpxt1G79Y4mveoruNqITM3fQtpCoVMvbs6wgEIbPxa/vcmS")
        );

        mvc.perform(
                get("/oauth/token")
                        .with(basicAuth("client", "secret"))
                        .requestAttr("grant_type", "password")
                        .requestAttr("username", user.getUsername())
                        .requestAttr("password", user.getPassword())
        ).andExpect(status().isOk());
    }

    private RequestPostProcessor basicAuth(String client, String secret) {
        return mockHttpServletRequest -> {
            mockHttpServletRequest.addParameter("client_id", client);
            mockHttpServletRequest.addParameter("client_secret", secret);
            mockHttpServletRequest.addParameter("response_type", "token");
            return mockHttpServletRequest;
        };
    }
}