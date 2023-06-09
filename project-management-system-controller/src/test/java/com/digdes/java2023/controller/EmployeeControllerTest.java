package com.digdes.java2023.controller;

import com.digdes.java2023.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {EmployeeController.class})
@EnableAutoConfiguration
@ComponentScan("com.digdes.java2023")
@EnableJpaRepositories("com.digdes.java2023.repository")
@EntityScan(basePackages = "com.digdes.java2023.model")
@AutoConfigureMockMvc
class EmployeeControllerTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    void create() throws Exception {
        this.mvc.perform(post("/employee/create")
                        .with(user("root").password("root"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "    \"lastName\": \"svetlova\",\n" +
                        "    \"name\": \"elena\",\n" +
                        "    \"patronymic\": \"vitalyevna\",\n" +
                        "    \"position\": \"spec\",\n" +
                        "    \"account\":\"sw\",\n" +
                        "    \"email\": \"aka@gmail.com\",\n" +
                        "    \"username\":\"user2\",\n" +
                        "    \"password\":\"333\"\n" +
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
                        .with(user("user2").password("333"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
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
        this.mvc.perform(get("/employee/get_by_id/41")
                .with(user("user2").password("333")))
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
        this.mvc.perform(get("/employee/get_by_account/sw")
                .with(user("user2").password("333")))
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
                        .with(user("root").password("root"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\n" +
                                "    \"lastName\": \"Ivanov\",\n" +
                                "    \"name\": \"Petr\",\n" +
                                "    \"patronymic\": \"Sergeevich\",\n" +
                                "    \"position\": \"Java Developer\",\n" +
                                "    \"account\":\"ivi\",\n" +
                                "    \"email\": \"iv@mail.com\",\n" +
                                "    \"username\":\"user1\",\n" +
                                "    \"password\":\"123\"\n" +
                                "}"))
                .andExpect(status().isOk());

        this.mvc.perform(get("/employee/find?input=ail")
                        .with(user("user2").password("333")))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"displayName\": \"Ivanov Petr Sergeevich\",\n" +
                        "        \"position\": \"Java Developer\",\n" +
                        "        \"account\": \"ivi\",\n" +
                        "        \"email\": \"iv@mail.com\",\n" +
                        "    \"employeeStatus\": \"ACTIVE\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"displayName\": \"svetlovo elena vitalyevna\",\n" +
                        "        \"position\": \"specialist\",\n" +
                        "        \"account\": \"sw\",\n" +
                        "        \"email\": \"aka@gmail.com\",\n" +
                        "    \"employeeStatus\": \"ACTIVE\"\n" +
                        "    }\n" +
                        "]"));
    }

    @Test
    void delete() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/employee/41")
                        .with(user("user2").password("333")))
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