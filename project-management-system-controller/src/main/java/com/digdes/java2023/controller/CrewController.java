package com.digdes.java2023.controller;

import com.digdes.java2023.dto.crew.AddCrewDto;
import com.digdes.java2023.dto.crew.CrewDto;
import com.digdes.java2023.dto.crew.DeleteCrewDto;
import com.digdes.java2023.service.CrewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crew")
@AllArgsConstructor
@Log4j2
@Tag(name = "CrewController", description = "Контроллер команды")
public class CrewController {
    CrewService crewService;

    @Operation(summary = "Добавление участника")
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CrewDto add(@RequestBody AddCrewDto dto) {
        log.info("В методе контроллера Crew::add");
        return crewService.add(dto);
    }

    @Operation(summary = "Удаление участника проекта")
    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CrewDto delete(@RequestBody DeleteCrewDto dto) {
        log.info("В методе контроллера Crew::delete");
        return crewService.delete(dto);
    }

    @Operation(summary = "Получение участников по проекту")
    @GetMapping(value = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CrewDto> get(@PathVariable String code) {
        log.info("В методе контроллера Crew::get");
        return crewService.getByProject(code);
    }
}
