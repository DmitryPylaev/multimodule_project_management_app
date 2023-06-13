package com.digdes.java2023.service;

import com.digdes.java2023.dto.project.EditProjectDto;
import com.digdes.java2023.dto.project.ProjectDto;
import com.digdes.java2023.model.project.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {ProjectService.class})
@EnableAutoConfiguration
@ComponentScan("com.digdes.java2023")
@EnableJpaRepositories("com.digdes.java2023.repository")
@EntityScan(basePackages = "com.digdes.java2023.model")
class ProjectServiceTest extends BaseTest{

    @Autowired
    ProjectService service;

    @Test
    void create() {
        EditProjectDto project = new EditProjectDto();
        project.setCode("code17");
        project.setName("top secret");
        project.setDescription("very top secret");

        ProjectDto expect = new ProjectDto();
        expect.setCode("code17");
        expect.setName("top secret");
        expect.setDescription("very top secret");
        expect.setProjectStatus(ProjectStatus.PREPARE);

        Assertions.assertEquals(expect, service.create(project));
    }

    @Test
    void edit() {
        create();

        EditProjectDto project = new EditProjectDto();
        project.setCode("code17");
        project.setName("crazy top");

        ProjectDto expect = new ProjectDto();
        expect.setCode("code17");
        expect.setName("crazy top");
        expect.setDescription("very top secret");
        expect.setProjectStatus(ProjectStatus.PREPARE);

        Assertions.assertEquals(expect, service.edit(project));
    }

    @Test
    void find() {
        create();

        EditProjectDto project = new EditProjectDto();
        project.setCode("code22");
        project.setName("bond");
        project.setDescription("Not description");
        service.create(project);

        ProjectDto expect = new ProjectDto();
        expect.setCode("code22");
        expect.setName("bond");
        expect.setDescription("Not description");
        expect.setProjectStatus(ProjectStatus.PREPARE);

        List<ProjectDto> expectList = new ArrayList<>();
        expectList.add(expect);
        List<ProjectStatus> statuses = new ArrayList<>(List.of(new ProjectStatus[]{ProjectStatus.PREPARE}));
        Assertions.assertEquals(expectList, service.find(statuses,"22"));
    }

    @Test
    void changeStatus() {
        create();

        EditProjectDto project = new EditProjectDto();
        project.setCode("code17");

        ProjectDto expect = new ProjectDto();
        expect.setCode("code17");
        expect.setName("top secret");
        expect.setDescription("very top secret");
        expect.setProjectStatus(ProjectStatus.DEVELOP);

        Assertions.assertEquals(expect, service.changeStatus(project));
    }
}