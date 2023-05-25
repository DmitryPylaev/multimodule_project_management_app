package com.digdes.java2023.controller.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void create() throws Exception {
        this.mvc.perform(post("/employee/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"lastName\": \"svetlova\",\n" +
                        "    \"name\": \"elena\",\n" +
                        "    \"patronymic\": \"vitalyevna\",\n" +
                        "    \"position\": \"spec\",\n" +
                        "    \"account\":\"sw\",\n" +
                        "    \"email\": \"aka@gmail.com\"\n" +
                        "    \"username\": \"user2\"\n" +
                        "    \"password\": \"333\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"displayName\": \"svetlova elena vitalyevna\",\n" +
                        "    \"position\": \"spec\",\n" +
                        "    \"account\": \"sw\",\n" +
                        "    \"email\": \"aka@gmail.com\",\n" +
                        "    \"employeeStatus\": \"ACTIVE\"\n" +
                        "}"));
    }

    @Test
    void edit() throws Exception {
        this.mvc.perform(post("/employee/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"lastName\": \"svetlovo\",\n" +
                                "    \"name\": \"elena\",\n" +
                                "    \"patronymic\": \"vitalyevna\",\n" +
                                "    \"position\": \"specialist\",\n" +
                                "    \"account\":\"sw\",\n" +
                                "    \"email\": \"aka@gmail.com\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"displayName\": \"svetlovo elena vitalyevna\",\n" +
                        "    \"position\": \"specialist\",\n" +
                        "    \"account\": \"sw\",\n" +
                        "    \"email\": \"aka@gmail.com\",\n" +
                        "    \"employeeStatus\": \"ACTIVE\"\n" +
                        "}"));
    }

    @Test
    void getById() throws Exception {
        this.mvc.perform(get("/employee/get_by_id/-35"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"displayName\": \"svetlovo elena vitalyevna\",\n" +
                        "    \"position\": \"specialist\",\n" +
                        "    \"account\": \"sw\",\n" +
                        "    \"email\": \"aka@gmail.com\",\n" +
                        "    \"employeeStatus\": \"ACTIVE\"\n" +
                        "}"));
    }

    @Test
    void getByAccount() throws Exception {
        this.mvc.perform(get("/employee/get_by_account/sw"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"displayName\": \"svetlovo elena vitalyevna\",\n" +
                        "    \"position\": \"specialist\",\n" +
                        "    \"account\": \"sw\",\n" +
                        "    \"email\": \"aka@gmail.com\",\n" +
                        "    \"employeeStatus\": \"ACTIVE\"\n" +
                        "}"));
    }

    @Test
    void find() throws Exception {
        this.mvc.perform(post("/employee/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"lastName\": \"Иванов\",\n" +
                                "    \"name\": \"Петр\",\n" +
                                "    \"patronymic\": \"Сергеевич\",\n" +
                                "    \"position\": \"Java Developer\",\n" +
                                "    \"account\":\"ivi\",\n" +
                                "    \"email\": \"iv@mail.ru\"\n" +
                                "    \"username\": \"user1\"\n" +
                                "    \"password\": \"123\"\n" +
                                "}"))
                .andExpect(status().isOk());

        this.mvc.perform(get("/employee/find?input=ail"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"displayName\": \"Иванов Петр Сергеевич\",\n" +
                        "        \"position\": \"Java Developer\",\n" +
                        "        \"account\": \"ivi\",\n" +
                        "        \"email\": \"iv@mail.ru\",\n" +
                        "        \"employeeStatus\": \"ACTIVE\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"displayName\": \"svetlovo elena vitalyevna\",\n" +
                        "        \"position\": \"specialist\",\n" +
                        "        \"account\": \"sw\",\n" +
                        "        \"email\": \"aka@gmail.com\",\n" +
                        "        \"employeeStatus\": \"ACTIVE\"\n" +
                        "    }\n" +
                        "]"));
    }

    @Test
    void delete() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/employee/-35"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"displayName\": \"svetlovo elena vitalyevna\",\n" +
                        "    \"position\": \"specialist\",\n" +
                        "    \"account\": \"sw\",\n" +
                        "    \"email\": \"aka@gmail.com\",\n" +
                        "    \"employeeStatus\": \"REMOVED\"\n" +
                        "}"));
    }
}