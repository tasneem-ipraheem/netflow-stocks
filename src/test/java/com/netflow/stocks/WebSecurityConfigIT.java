package com.netflow.stocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.net.URL;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class WebSecurityConfigIT extends BaseIntegrationTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    @Value("${local.server.port}")
    private int port;

    private URL base;
    private RestTemplate template;


    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void requestProtectedStatsResource() throws Exception {

        base = new URL("http://localhost:" + port + "/stocks/stats");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(response.getHeaders().getFirst("Location")).endsWith("/login");

    }

    @Test
    public void requestProtectedActuarResource() throws Exception {

        base = new URL("http://localhost:" + port + "/manage/info");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(response.getHeaders().getFirst("Location")).endsWith("/login");

    }

    @Test
    public void testLogin() throws Exception {
        mvc.perform(formLogin("/login").user("admin").password("secret"))
                .andExpect(authenticated());
    }


}