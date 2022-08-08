package com.selin.restapi.controller;

import com.selin.restapi.repository.TedTalkRepository;
import com.selin.restapi.service.TedTalkService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TedTalkControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private TedTalkRepository repo;
    @InjectMocks
    private TedTalkService tedTalkService;

    @Test
    void create() {
    }

    @Test
    void getAll() throws Exception {
        this.mockMvc
                .perform(get("/ted-talks"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdate() {
    }

    @Test
    void deleteStudent() {
    }
}