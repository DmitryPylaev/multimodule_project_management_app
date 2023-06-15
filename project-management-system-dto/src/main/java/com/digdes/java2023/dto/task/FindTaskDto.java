package com.digdes.java2023.dto.task;

import com.digdes.java2023.model.task.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Schema(description = "Проект. Данные на вход по операциям поиска")
public class FindTaskDto {

    @Schema(description = "Наименование задачи")
    private String name;
    private TaskStatus taskStatus;

    @Schema(description = "Исполнитель задачи")
    private String employee;

    @Schema(description = "Автор задачи")
    private String author;

    @Schema(description = "Крайний срок, нижняя граница фильтра")
    private LocalDate deadlineMin;

    @Schema(description = "Крайний срок, верхняя граница фильтра")
    private LocalDate deadlineMax;

    @Schema(description = "Дата создания, нижняя граница фильтра")
    private LocalDateTime createDateMin;

    @Schema(description = "Дата создания, верхняя граница фильтра")
    private LocalDateTime createDateMax;
}
