package org.example.contactproject.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/contacts/1"))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        String json = """
                {
                  "firstName": "Test",
                  "lastName": "User",
                  "middleName": "T",
                  "phone": "123"
                }
                """;

        mockMvc.perform(post("/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        String json = """
                {
                  "firstName": "Updated",
                  "lastName": "User",
                  "middleName": "T",
                  "phone": "999"
                }
                """;

        mockMvc.perform(put("/contacts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/contacts/1"))
                .andExpect(status().isOk());
    }
}